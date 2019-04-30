package c.www.carovignoviva;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import c.www.carovignoviva.utility.InfoWindowData;

public class informazioni extends Activity implements OnStreetViewPanoramaReadyCallback {


    private StreetViewPanorama streetView;
    InfoWindowData city;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informazioni_complete);

        Intent intent=getIntent();
        city=(InfoWindowData)intent.getSerializableExtra("City");
        ImageView img = findViewById(R.id.imageView4);

        //   TextView description = view.findViewById(R.id.description);
        // TextView trasport = view.findViewById(R.id.transport);
        final StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
        TextView text= findViewById(R.id.textView9);

        text.setText(city.getDescription());
       // InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();
        int imageId = getResources().getIdentifier(city.getImage(),
                "drawable", getPackageName());
        img.setImageResource(imageId);
        ImageButton navigatore= findViewById(R.id.navigatore);
        ImageButton streetbutton= findViewById(R.id.streetview);

        navigatore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:"+city.getLatitude()+","+city.getLongitude()+"?q="+city.getTitle());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


            }
        });

        streetbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FrameLayout frame= findViewById(R.id.framefull);
                frame.setVisibility(View.VISIBLE);
            }
        });

        streetbutton.setVisibility(View.VISIBLE);
        ImageButton close= findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FrameLayout frame= findViewById(R.id.framefull);
                frame.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(new LatLng(city.getLatitude(),city.getLongitude()));
        streetView=streetViewPanorama;


    }
}
