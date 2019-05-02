package c.www.carovignoviva.CustomUtility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import c.www.carovignoviva.CircleTrasform;
import c.www.carovignoviva.Monumento;
import c.www.carovignoviva.R;
import c.www.carovignoviva.RetriveImageInternet;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter{

    private final Context context;
    private ArrayList<Monumento> monumenti;
    public CustomInfoWindowGoogleMap(Context ctx, ArrayList<Monumento> monument){
        monumenti=monument;
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        @SuppressLint("InflateParams") View view =((Activity)context).getLayoutInflater().inflate(R.layout.info_windows, null);
        TextView title = view.findViewById(R.id.title);
       // TextView details = view.findViewById(R.id.details);
        ImageView img = view.findViewById(R.id.imageView2);

       // TextView description = view.findViewById(R.id.description);
       // TextView trasport = view.findViewById(R.id.transport);
        int i=0;
        while (!marker.getTitle().equals(monumenti.get(i).getMarker().getTitle())) i++;
        title.setText(marker.getTitle());

        Picasso.get().load(monumenti.get(i).getImage())
                .transform(new CircleTrasform()).into(img);

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}

