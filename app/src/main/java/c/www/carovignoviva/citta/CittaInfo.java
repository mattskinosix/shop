package c.www.carovignoviva.citta;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import c.www.carovignoviva.R;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class CittaInfo extends Activity  {


    Citta citta;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_evento);


        Intent intent=getIntent();
            citta =(Citta)intent.getSerializableExtra("Citta");
            TextView text= findViewById(R.id.descrizione_info_evento);
        text.setText(citta.getDescription());
        TextView titolo= findViewById(R.id.titolo_info_evento);
            titolo.setText(citta.getNome());

        ImageView img = findViewById(R.id.image_evento);
        Picasso.get().load(citta.getImage())
                .transform(new RoundedCornersTransformation(50,2))
                .into(img);
        ImageButton navigatore= findViewById(R.id.navigatore_evento);
            navigatore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ citta.getNome());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


            }
        });
    }





}
