package c.www.carovignoviva;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

public class RetriveImageInternet extends AsyncTask<String,Void,Drawable> {

    @Override
    protected Drawable doInBackground(String... strings) {
        try {
            InputStream is = (InputStream) new URL(strings[0]).getContent();
            return Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            Log.i("POOOOOOOOOOOOO","ECCEZIONEEEEE");
            e.printStackTrace();
            return null;
        }
    }
}