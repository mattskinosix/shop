package c.www.carovignoviva.citta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Citta implements Serializable {
    private String codice;
    private String nome;
    private String image;
    private String description;

    ArrayList<Citta> eventoFromJson(String json) throws JSONException {
        JSONArray jsonarray = new JSONArray(json);
        ArrayList<Citta> events=new ArrayList<> ();
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            Citta evento=new Citta();
            evento.codice = jsonobject.getString("codice");
            evento.nome = jsonobject.getString("nome");
            evento.image=jsonobject.getString("immagine");
            evento.description=jsonobject.getString("descrizione");
            events.add(evento);
        }
        return events;
    }

    public String getCodice() {
        return codice;
    }

    String getNome() {
        return nome;
    }



    String getImage() {
        return image;
    }

    String getDescription() {
        return description;
    }
}
