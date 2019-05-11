package homepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import events.HomeEventi;
import monuments.HomeMonumenti;
import c.www.carovignoviva.R;

public class Home extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.home);
        View castello = findViewById(R.id.castello);
        castello.setOnClickListener(castleButton);
        View mare =findViewById(R.id.mare);
        mare.setOnClickListener(mareButton);
    }




    private View.OnClickListener castleButton = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.castello) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(Home.this , HomeMonumenti.class);
                //start the second Activity
                startActivity(intent);

            }

        }
    };

    private View.OnClickListener mareButton = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.mare) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(Home.this, HomeEventi.class);
                //start the second Activity
                startActivity(intent);

            }

        }
    };


}