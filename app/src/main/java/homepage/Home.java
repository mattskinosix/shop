package homepage;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import c.www.carovignoviva.R;
import citta.HomeCitta;
import events.HomeEventi;
import monuments.HomeMonumenti;

public class Home extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        View monumento = findViewById(R.id.button_monumenti);
        monumento.setOnClickListener(monumentsButtonListener);
        View events =findViewById(R.id.button_events);
        events.setOnClickListener(eventsButtonListener);
        View citta =findViewById(R.id.button_citta);
        citta.setOnClickListener(cittaButtonListener);
    }



    private View.OnClickListener monumentsButtonListener = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.button_monumenti) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(Home.this , HomeMonumenti.class);
                //start the second Activity
                startActivity(intent);

            }

        }
    };

    private View.OnClickListener eventsButtonListener = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.button_events) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(Home.this, HomeEventi.class);
                //start the second Activity
                startActivity(intent);

            }


        }

    };

    private View.OnClickListener cittaButtonListener = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.button_citta) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(Home.this, HomeCitta.class);
                //start the second Activity
                startActivity(intent);

            }


        }

    };


}