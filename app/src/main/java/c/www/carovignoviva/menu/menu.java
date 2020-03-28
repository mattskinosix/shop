package c.www.carovignoviva.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import c.www.carovignoviva.R;
import c.www.carovignoviva.categorie.MainActivity;
import c.www.carovignoviva.homepage.Home;
import c.www.carovignoviva.registrazione.registrazione;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_menu);
        Button productInStock= findViewById(R.id.product_in_stock);
        productInStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, MainActivity.class);
                //start the second Activity
                startActivity(intent);
            }
        }


        );
    }
}
