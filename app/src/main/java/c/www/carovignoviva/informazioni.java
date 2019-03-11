package c.www.carovignoviva;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

import c.www.carovignoviva.utility.InfoWindowData;

public class informazioni extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informazioni_complete);

        Intent intent=getIntent();
        InfoWindowData city=(InfoWindowData)intent.getSerializableExtra("City");
        ImageView img = findViewById(R.id.imageView4);

        //   TextView description = view.findViewById(R.id.description);
        // TextView trasport = view.findViewById(R.id.transport);

        TextView text= findViewById(R.id.textView9);

        text.setText(city.getDescription());
       // InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();
        int imageId = getResources().getIdentifier(city.getImage(),
                "drawable", getPackageName());
        img.setImageResource(imageId);


    }
}
