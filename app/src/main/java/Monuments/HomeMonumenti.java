package Monuments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
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
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import c.www.carovignoviva.R;

public class HomeMonumenti extends FragmentActivity implements OnMapReadyCallback {


    SlidingUpPanelLayout slidingUpPanelLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (!isOnline()) {
            setContentView(R.layout.error_network);
            TextView textView = findViewById(R.id.errorview);
            textView.setText("intetnet è spento");
            Button button= findViewById(R.id.refresh);
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(getIntent());
                }
            });
        } else {
            setContentView(R.layout.slideup);
            MapFragment mapFragment = MapFragment.newInstance();
            getFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
            mapFragment.getMapAsync(this);
            slidingUpPanelLayout = findViewById(R.id.sliding_layout);
        }

    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("http://79.42.27.192:8000/");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void creaLista(final ArrayList<Monumento> monumentos, final GoogleMap mMap){
        //CREO LA LISTA CON I DATI CONTENUTI NEL VETTORE DI MONUMENTI
        ListView listView = findViewById(R.id.listv);

        // creo e istruisco l'adattatore
        final CustomListViewHome adapter = new CustomListViewHome(this, R.layout.listitem, monumentos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                double latitude = monumentos.get(pos).getLatitude() + .0002;
                double longitude;
                longitude = monumentos.get(pos).getLongitude();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 19.0f));
                monumentos.get(pos).getMarker().showInfoWindow();
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<Monumento> monumenti = null;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //ESPANDO IL PANNELLO SLIDING
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        try {
            //CARICO I MONUMENTI DALLA PAGINA PHP
            monumenti = new Monumento().monumentoFromJson(new GetFromServer().execute().get());
        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (!isInternetAvailable() && isOnline() && monumenti==null) {
            setContentView(R.layout.error_network);
            TextView textView = findViewById(R.id.errorview);
            textView.setText("server rotto");
            Button button= findViewById(R.id.refresh);
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(getIntent());
                }
            });
        } else {
            if (monumenti!=null) {
                addMarker(monumenti, googleMap);
                creaLista(monumenti, googleMap);
                setCustomInfoWindows(googleMap, monumenti);
            }
        }
    }

    private void setCustomInfoWindows(GoogleMap mMap, final  ArrayList<Monumento> monumenti) {
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this,monumenti);
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                Intent intent = new Intent(HomeMonumenti.this ,  Information.class);
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
                            ists, StandardCharsets.UTF_8));
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
                URL paginaURL = new URL("http://79.42.27.192:8000/carovigno.php");

                // creo l'oggetto HttpURLConnection e apro la connessione al server
                HttpURLConnection client = (HttpURLConnection) paginaURL.openConnection();
                client.setAllowUserInteraction(false);
                client.setInstanceFollowRedirects(true);
                client.setRequestMethod("GET");
                client.connect();
                // Recupero le Information inviate dal server
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
