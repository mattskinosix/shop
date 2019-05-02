package c.www.carovignoviva;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import c.www.carovignoviva.CustomUtility.CustomInfoWindowGoogleMap;
import c.www.carovignoviva.CustomUtility.CustomListvVew;

public class HomePage  extends FragmentActivity implements OnMapReadyCallback {
    static public final int REQUEST_LOCATION = 1;
    SlidingUpPanelLayout slidingUpPanelLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slideup);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        slidingUpPanelLayout = findViewById(R.id.sliding_layout);

    }
    private void creaLista(final ArrayList<Monumento> monumentos, final GoogleMap mMap){
        //CREO LA LISTA CON I DATI CONTENUTI NEL VETTORE DI MONUMENTI
        ListView listView = findViewById(R.id.listv);

        // creo e istruisco l'adattatore
        final CustomListvVew adapter = new CustomListvVew(this, R.layout.listitem, monumentos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {

                Double latitude=monumentos.get(pos).getLatitude();
                latitude+=.0002;
                Double longitude=monumentos.get(pos).getLongitude();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 19.0f));
                monumentos.get(pos).getMarker().showInfoWindow();
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<Monumento> monumenti=null;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //ESPANDO IL PANNELLO SLIDING
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        try {
            //CARICO I MONUMENTI DALLA PAGINA PHP
            monumenti=new Monumento().monumentoFromJson(new GetFromServer().execute().get());
        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        addMarker(monumenti, googleMap);
        creaLista(monumenti, googleMap);
        setCustomInfoWindows(googleMap,monumenti);
    }


    private void setCustomInfoWindows(GoogleMap mMap, final  ArrayList<Monumento> monumenti) {
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this,monumenti);
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                Intent intent = new Intent(HomePage.this ,  informazioni.class);
                int i=0;
                //TO DO
                while (!arg0.getTitle().equals(monumenti.get(i).getMarker().getTitle())) i++;
                intent.putExtra("City",monumenti.get(i));
                startActivity(intent);
            }
        });

    }



    private void addMarker(ArrayList<Monumento> monumenti, GoogleMap mMap){
        for (Monumento monumento : monumenti) {
            monumento.setMarker(mMap.addMarker(new MarkerOptions()
                    .title(monumento.getNome())
                    .position(new LatLng(monumento.getLatitude(), monumento.getLongitude()))));
        }
    }


    private static  class  GetFromServer extends AsyncTask<Void, Void, String> {
        private String dati;

        private String mostroDati(InputStream ists) throws IOException {
            if (ists != null) {
                StringBuilder sb = new StringBuilder();
                String line;

                try {
                    BufferedReader r1 = new BufferedReader(new InputStreamReader(
                            ists, "UTF-8"));
                    while ((line = r1.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } finally {
                    ists.close();
                }
                return sb.toString();
            } else {
                return "";
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            String datiLetti = "ciao";
            try {

                // Creao l'oggetto URL che rappresenta l'indirizzo della pagina da richiamare
                URL paginaURL = new URL("http://192.168.1.16/carovogno.php");

                // creo l'oggetto HttpURLConnection e apro la connessione al server
                HttpURLConnection client = (HttpURLConnection) paginaURL.openConnection();
                client.setAllowUserInteraction(false);
                client.setInstanceFollowRedirects(true);
                client.setRequestMethod("GET");
                client.connect();
                // Recupero le informazioni inviate dal server
                InputStream risposta = client.getInputStream();
                datiLetti = mostroDati(risposta);
                Log.i("CIAO", datiLetti);
            } catch (Exception e) {
                Log.i("CIAO", "ECCEZIONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                e.printStackTrace();
            }
            //strings[0]=datiLetti;

            return datiLetti;
        }


    }

}
