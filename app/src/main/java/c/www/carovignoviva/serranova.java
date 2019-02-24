package c.www.carovignoviva;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Iterator;

import c.www.carovignoviva.utility.CustomInfoWindowGoogleMap;

public class serranova extends AppCompatActivity implements OnMapReadyCallback {
    static public final int REQUEST_LOCATION = 1;
    public GoogleMap mMap;
    Context context;

    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private Object mLastKnownLocation;
    private LocationManager locationManager;
    // String provider = locationManager.getBestProvider(new Criteria(), false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.selezione);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        final getInfoMarkerSerra markerCarovig = new getInfoMarkerSerra(mMap);
        ArrayList<Marker> nameproducts = markerCarovig.markersCarovigno;


        final ArrayList<String> listp = new ArrayList<>();
        final Iterator<Marker> markerIterator = nameproducts.iterator();
        while (markerIterator.hasNext()) {
            listp.add(markerIterator.next().getTitle());
        }
        // recupero la lista dal layout
        ListView listView = (ListView) findViewById(R.id.listv);

        // creo e istruisco l'adattatore
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listp);

        // inietto i dati

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerCarovig.markersCarovigno.get(pos).getPosition(), 20.0f));
                markerCarovig.markersCarovigno.get(pos).showInfoWindow();
            }
        });
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                AlertDialog ad = new AlertDialog.Builder(serranova.this).create();
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


}
