package c.www.carovignoviva.homepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import c.www.carovignoviva.R;
import c.www.carovignoviva.login.login.LoginActivity;
import c.www.carovignoviva.registrazione.registrazione;

public class Home extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        View login = findViewById(R.id.login);
        login.setOnClickListener(loginButtonListener);
        View registrazione =findViewById(R.id.registrazione);
        registrazione.setOnClickListener(registrazioneButtonListener);
    }



    private View.OnClickListener registrazioneButtonListener = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.registrazione) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(c.www.carovignoviva.homepage.Home.this , registrazione.class);
                //start the second Activity
                startActivity(intent);

            }

        }
    };

    private View.OnClickListener loginButtonListener = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.login) {
                //define a new Intent for the second Activity
                Intent intent = new Intent(c.www.carovignoviva.homepage.Home.this, LoginActivity.class);
                //start the second Activity
                startActivity(intent);

            }


        }

    };



}