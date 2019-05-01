package c.www.carovignoviva;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

import c.www.carovignoviva.utility.CustomInfoWindowGoogleMap;
import c.www.carovignoviva.utility.CustomListvView;

public class carovigno extends FragmentActivity implements OnMapReadyCallback {
    static public final int REQUEST_LOCATION = 1;
    public GoogleMap mMap;
    SlidingUpPanelLayout slidingUpPanelLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slideup);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
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
        ListView listView = (ListView) findViewById(R.id.listv);

        // creo e istruisco l'adattatore
        final CustomListvView adapter = new CustomListvView(this, R.layout.listitem, nameproducts);

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
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        /*
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this, monumenti);
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                Intent intent = new Intent(carovigno.this ,  informazioni.class);
                intent.putExtra("City",markerCarovig.data.get(markerCarovig.markersCarovigno.indexOf(arg0)));
                startActivity(intent);
                /*
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

*/


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


}
