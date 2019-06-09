package c.www.carovignoviva.Server;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class  GetFromServer extends AsyncTask<String, Void, String> {
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
    protected String doInBackground(String... voids) {
        String datiLetti = "ciao";
        try {

            // Creao l'oggetto URL che rappresenta l'indirizzo della pagina da richiamare
            URL paginaURL = new URL("http://mattskinosix.servehttp.com:8000/"+voids[0]);
            Log.wtf("stringaaaa    ","http://mattskinosix.servehttp.com:8000/"+voids[0]);
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

