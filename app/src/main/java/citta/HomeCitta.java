package citta;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import c.www.carovignoviva.R;

public class HomeCitta extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventi);

            //CREO LA LISTA CON I DATI CONTENUTI NEL VETTORE DI MONUMENTI
            ListView listView = findViewById(R.id.Listeventi);
        ArrayList<Citta> cityes=null;
        try {
            cityes = new Citta().eventoFromJson(new GetFromServer().execute().get());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // creo e istruisco l'adattatore
            final CustomListViewCitta adapter = new CustomListViewCitta(this, R.layout.list_item_citta,cityes);
            listView.setAdapter(adapter);
        final ArrayList<Citta> finalEvents = cityes;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                    Intent intent = new Intent(HomeCitta.this ,  CittaInfo.class);
                    intent.putExtra("Citta", finalEvents.get(pos));
                    startActivity(intent);
                }

        });

    }

    private static  class  GetFromServer extends AsyncTask<Void, Void, String> {
        private String dati;

        private String mostroDati(InputStream ists) throws IOException {
            if (ists != null) {
                StringBuilder sb = new StringBuilder();
                String line;

                try {
                    BufferedReader r1 = new BufferedReader(new InputStreamReader(
                            ists, StandardCharsets.UTF_8));
                    while ((line = r1.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } finally {
                    ists.close();
                }
                return sb.toString();
            } else {
                return "";
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            String datiLetti = "ciao";
            try {

                // Creao l'oggetto URL che rappresenta l'indirizzo della pagina da richiamare
                URL paginaURL = new URL("http://79.42.27.192:8000/citta.php");

                // creo l'oggetto HttpURLConnection e apro la connessione al server
                HttpURLConnection client = (HttpURLConnection) paginaURL.openConnection();
                client.setAllowUserInteraction(false);
                client.setInstanceFollowRedirects(true);
                client.setRequestMethod("GET");
                client.connect();
                // Recupero le Information inviate dal server
                InputStream risposta = client.getInputStream();
                datiLetti = mostroDati(risposta);
                Log.i("CIAO", datiLetti);
            } catch (Exception e) {
                Log.i("CIAO", "ECCEZIONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                e.printStackTrace();
            }
            //strings[0]=datiLetti;

            return datiLetti;
        }


    }
}
