package c.www.carovignoviva.registrazione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import c.www.carovignoviva.R;
import c.www.carovignoviva.homepage.Home;
import c.www.carovignoviva.menu.menu;

public class registrazione extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registrazione);
        Button registrationButton= findViewById(R.id.registation_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registrazione.this, Home.class);
                //start the second Activity
                startActivity(intent);

            }
        });
    }
}
