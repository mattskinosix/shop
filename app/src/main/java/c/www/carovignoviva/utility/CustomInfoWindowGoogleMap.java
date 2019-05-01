package c.www.carovignoviva.utility;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import c.www.carovignoviva.Monumento;
import c.www.carovignoviva.R;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter{

    private final Context context;
    private ArrayList<Monumento> monumenti;
    public CustomInfoWindowGoogleMap(Context ctx, ArrayList<Monumento> monument){
        monumenti=monument;
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view =((Activity)context).getLayoutInflater().inflate(R.layout.info_windows, null);
        TextView title = view.findViewById(R.id.title);
       // TextView details = view.findViewById(R.id.details);
        ImageView img = view.findViewById(R.id.imageView2);

       // TextView description = view.findViewById(R.id.description);
       // TextView trasport = view.findViewById(R.id.transport);

        title.setText(marker.getTitle());
        int imageId = context.getResources().getIdentifier(monumenti.get(0).getImage().toLowerCase(),
                "drawable", context.getPackageName());
        img.setImageResource(imageId);

        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {/*
        View view =((Activity)context).getLayoutInflater().inflate(R.layout.info_windows, null);

        TextView title = view.findViewById(R.id.title);
        TextView details = view.findViewById(R.id.details);
        ImageView img = view.findViewById(R.id.pic);

        TextView description = view.findViewById(R.id.description);
        TextView trasport = view.findViewById(R.id.transport);

        title.setText(marker.getTitle());
        details.setText(marker.getSnippet());ImageView img = view.findViewById(R.id.imageView2);


        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        int imageId = context.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
                "drawable", context.getPackageName());
        img.setImageResource(imageId);

        details.setText(infoWindowData.getDetails());
        description.setText(infoWindowData.getDescription());
        trasport.setText(infoWindowData.getTransport());
        */
        return null;
    }
}

