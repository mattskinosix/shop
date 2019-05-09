package c.www.carovignoviva;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import c.www.carovignoviva.CustomUtility.ViewPagerAdapter;

public class Information extends Activity implements OnStreetViewPanoramaReadyCallback {


    private StreetViewPanorama streetView;
    Monumento city;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informazioni_complete);


        Intent intent=getIntent();
            city=(Monumento)intent.getSerializableExtra("City");


            //   TextView description = view.findViewById(R.id.description);
            // TextView trasport = view.findViewById(R.id.transport);
            final StreetViewPanoramaFragment streetViewPanoramaFragment =
                    (StreetViewPanoramaFragment) getFragmentManager()
                            .findFragmentById(R.id.streetviewpanorama);
            streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
            TextView text= findViewById(R.id.descrizione_info_complete);
        text.setText(city.getDescription());
        TextView titolo= findViewById(R.id.titolo_info_complete);
            titolo.setText(city.getNome());
        ViewPager viewPager = findViewById(R.id.Imagepager);
       String[] imageUrls = new String[]{
                "https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg"
        };
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, city.getImage());
        viewPager.setAdapter(adapter);

        ImageButton navigatore= findViewById(R.id.navigatore);
        ImageButton streetbutton= findViewById(R.id.streetview);

            navigatore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:"+city.getLatitude()+","+city.getLongitude()+"?q="+city.getNome());
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
