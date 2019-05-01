package c.www.carovignoviva;

import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class Monumento  implements Serializable {
    private String codice;
    private String nome;
    private Double longitude;
    private Double latitude;
    private String description;
    private String image;
    private String detail;
    private String orario;
    private transient Marker marker;

    public ArrayList<Monumento>  monumentoFromJson(String json) throws JSONException {
        JSONArray jsonarray = new JSONArray(json);
        ArrayList<Monumento> monumenti=new ArrayList<> ();
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            Monumento monumento=new Monumento();
            monumento.codice = jsonobject.getString("codice");
            monumento.nome = jsonobject.getString("nome");
            monumento.longitude = jsonobject.getDouble("longitude");
            monumento.latitude= jsonobject.getDouble("latitude");
            monumento.description = jsonobject.getString("description");
            monumento.image = jsonobject.getString("image");
            monumento.detail= jsonobject.getString("detail");
            monumento.orario= jsonobject.getString("orario");
            monumenti.add(monumento);
        }
        return monumenti;
    }

    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getDetail() {
        return detail;
    }

    public String getOrario() {
        return orario;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
