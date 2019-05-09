package c.www.carovignoviva;

import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {
    private String codice;
    private String nome;
    private String luogo;
    private String ora_inizio;
    private String ora_fine;
    private String image;

    public ArrayList<Event> monumentoFromJson(String json) throws JSONException {
        JSONArray jsonarray = new JSONArray(json);
        ArrayList<Event> events=new ArrayList<> ();
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            Event evento=new Event();
            evento.codice = jsonobject.getString("codice");
            evento.nome = jsonobject.getString("nome");
            evento.luogo= jsonobject.getString("luogo");
            evento.ora_inizio= jsonobject.getString("ora_inizio");
            evento.ora_fine= jsonobject.getString("ora_fine");
            evento.image=jsonobject.getString("immagine");

            events.add(evento);
        }
        return events;
    }

    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getOra_inizio() {
        return ora_inizio;
    }

    public String getOra_fine() {
        return ora_fine;
    }

    public String getImage() {
        return image;
    }
}
