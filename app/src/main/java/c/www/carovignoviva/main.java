package c.www.carovignoviva;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




         setContentView(R.layout.home);
        View castello = findViewById(R.id.castello);
        castello.setOnClickListener(castleButton);
        View mare =findViewById(R.id.mare);
        mare.setOnClickListener(mareButton);
        View spec=findViewById(R.id.specchiolla);
        spec.setOnClickListener(specButton);
        View serr=findViewById(R.id.serranova);
        serr.setOnClickListener(serrButton);
    }




    private View.OnClickListener castleButton = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.castello) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(main.this , carovigno.class);
                intent.putExtra("City","carovigno");
                //start the second Activity
                startActivity(intent);

            }

        }
    };

    private View.OnClickListener mareButton = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.mare) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(main.this, mare.class);
                intent.putExtra("City","mare");
                //start the second Activity
                startActivity(intent);

            }

        }
    };

    private View.OnClickListener serrButton = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.serranova) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(main.this, serranova.class);
                intent.putExtra("City","serranova");
                //start the second Activity
                startActivity(intent);

            }

        }
    };
    private View.OnClickListener specButton = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.specchiolla) {
                //define a new Intent for the second Activity

                Intent intent = new Intent(main.this, specchiolla.class);
                //start the second Activity
                intent.putExtra("City","specchiolla");
                startActivity(intent);

            }

        }
    };

}

