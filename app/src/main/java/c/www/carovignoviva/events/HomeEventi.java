package c.www.carovignoviva.events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import c.www.carovignoviva.Server.GetFromServer;
import c.www.carovignoviva.R;

public class HomeEventi extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventi);

            //CREO LA LISTA CON I DATI CONTENUTI NEL VETTORE DI MONUMENTI
            ListView listView = findViewById(R.id.Listeventi);
        ArrayList<Event> events=null;
        try {
            events = new Event().eventoFromJson(new GetFromServer().execute("events.php").get());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // creo e istruisco l'adattatore
            final CustomListViewEvents adapter = new CustomListViewEvents(this, R.layout.list_item_evento,events);
            listView.setAdapter(adapter);
        final ArrayList<Event> finalEvents = events;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                    Intent intent = new Intent(HomeEventi.this ,  EventInfo.class);
                    intent.putExtra("Citta", finalEvents.get(pos));
                    startActivity(intent);
                }

        });

    }


}
