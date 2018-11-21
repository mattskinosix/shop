package c.www.carovignoviva;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import c.www.carovignoviva.utility.CustomInfoWindowGoogleMap;
import c.www.carovignoviva.utility.CustomListvVew;

public class carovigno extends FragmentActivity implements OnMapReadyCallback , OnStreetViewPanoramaReadyCallback {
    static public final int REQUEST_LOCATION = 1;
    public GoogleMap mMap;
    StreetViewPanorama streetView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selezione);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        /*
        Intent intent=getIntent();
        String city=intent.getStringExtra("City");
        if (city=="carovigno"){


        }

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getQuotes();
        databaseAccess.close();
          */
        final getInfoMarkerCarovigno markerCarovig = new getInfoMarkerCarovigno(mMap);
        ArrayList<Marker> nameproducts = markerCarovig.markersCarovigno;

        // definisco un ArrayList
/*
        final ArrayList<String> listp = new ArrayList<>();
        final Iterator<Marker> markerIterator = nameproducts.iterator();
        while (markerIterator.hasNext()) {
            listp.add(markerIterator.next().getTitle());
        }
        */
        // recupero la lista dal layout
        final FrameLayout frame=findViewById(R.id.frame);
        final ViewGroup.LayoutParams params=frame.getLayoutParams();

        ListView listView = (ListView) findViewById(R.id.listv);

        // creo e istruisco l'adattatore
        final CustomListvVew adapter = new CustomListvVew(this, R.layout.listitem, nameproducts);

        // inietto i dati

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {

                Double latitude=markerCarovig.markersCarovigno.get(pos).getPosition().latitude;
                latitude+=.0002;
                Double longitude=markerCarovig.markersCarovigno.get(pos).getPosition().longitude;

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 19.0f));
                markerCarovig.markersCarovigno.get(pos).showInfoWindow();
                streetView.setPosition( markerCarovig.markersCarovigno.get(pos).getPosition());
            }
        });

        FloatingActionButton button= findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(params.height==ViewGroup.LayoutParams.MATCH_PARENT  && params.width==ViewGroup.LayoutParams.MATCH_PARENT){
                    params.height=1200;
                    params.width=ViewGroup.LayoutParams.MATCH_PARENT;
                    frame.setLayoutParams(params);
                }else{
                params.height=ViewGroup.LayoutParams.MATCH_PARENT;
                params.width=ViewGroup.LayoutParams.MATCH_PARENT;
                frame.setLayoutParams(params);

            }}
        });
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                AlertDialog ad = new AlertDialog.Builder(carovigno.this).create();
                ad.setMessage(markerCarovig.data.get(markerCarovig.markersCarovigno.indexOf(arg0)).getDescription());
                ad.show();

            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            mMap.setMyLocationEnabled(true);
        }



        // Turn on the My Location layer and the related control on the map.
       // updateLocationUI();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true); // <-- Start Beemray here
            } else {
                // Permission was denied or request was cancelled
            }
        }
    }


    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {

        streetViewPanorama.setPosition(new LatLng(40.706963, 17.657863));
        streetView=streetViewPanorama;

    }
}
