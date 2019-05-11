package monuments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
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
    ArrayList<Monumento> monumenti;

    {
        try {
            monumenti = new Monumento().monumentoFromJson(new GetFromServer().execute().get(), getBaseContext());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private FusedLocationProviderClient fusedLocationClient;
    SlidingUpPanelLayout slidingUpPanelLayout;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            double lat = location.getLatitude();
                            double lon = location.getLongitude();
                            setDistances(lat, lon);
                            creaLista();

                        }
                    }
                });
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

    private void creaLista( ){
        //CREO LA LISTA CON I DATI CONTENUTI NEL VETTORE DI MONUMENTI
        ListView listView = findViewById(R.id.listv);
        monumenti.sort(new ComparatorDistance());
        // creo e istruisco l'adattatore
        final CustomListViewHome adapter = new CustomListViewHome(this, R.layout.listitem, monumenti);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                double latitude = monumenti.get(pos).getLatitude() + .0002;
                double longitude;
                longitude = monumenti.get(pos).getLongitude();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 19.0f));
                monumenti.get(pos).getMarker().showInfoWindow();
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        ArrayList<Monumento> monumenti = null;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);
        //ESPANDO IL PANNELLO SLIDING


        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        try {
            //CARICO I MONUMENTI DALLA PAGINA PHP
            monumenti = new Monumento().monumentoFromJson(new GetFromServer().execute().get(),getBaseContext());
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
                addMarker( );
                setCustomInfoWindows();
            }

        }
    }

    private void setCustomInfoWindows() {
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this,monumenti);
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                Intent intent = new Intent(HomeMonumenti.this ,  Information.class);
                int i=0;
                //TO DO
                while (!arg0.getTitle().equals(monumenti.get(i).getMarker().getTitle())) i++;
                intent.putExtra("monumenti",monumenti.get(i));
                startActivity(intent);
            }
        });

    }

    private void setDistances(double lat,double lon){
        for (Monumento monumento : monumenti) {
            monumento.setDistanceToMonument(lat,lon);
            }
    }



    private void addMarker( ){
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
