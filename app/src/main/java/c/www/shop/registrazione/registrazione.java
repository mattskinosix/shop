package c.www.shop.registrazione;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import c.www.shop.R;
import c.www.shop.homepage.Home;

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
