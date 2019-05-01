package c.www.carovignoviva;

import org.json.JSONException;

import java.util.ArrayList;

import c.www.carovignoviva.Monumento;

public class Monumenti {
    ArrayList<Monumento> monumentos;

    public ArrayList<Monumento> Monumenti (String json){
        try {
            monumentos=new Monumento().monumentoFromJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return monumentos;
    }

}
