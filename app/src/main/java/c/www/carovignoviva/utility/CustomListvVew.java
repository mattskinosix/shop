package c.www.carovignoviva.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import c.www.carovignoviva.R;
import c.www.carovignoviva.getInfoMarkerCarovigno;

public class CustomListvVew extends ArrayAdapter<Marker> {


    private int  resourceLayout;
    private Context mContext;
    public CustomListvVew(Context context, int textViewResourceId,
                         ArrayList <Marker> objects) {
        super(context, textViewResourceId, objects);
        mContext=context;
        resourceLayout=textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  getInfoMarkerCarovigno markerCarovig ;
       // ArrayList<ListData> nameproducts = markerCarovig.datalist;
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Marker p = getItem(position);

        if (p != null) {
            TextView ttvisite = (TextView) v.findViewById(R.id.textViewListVisite);
            TextView tt2 = (TextView) v.findViewById(R.id.TextViewListTitle);

            if (tt2 != null) {
                tt2.setText(p.getTitle());
           }

            if (ttvisite != null) {
                ttvisite.setText("Orario: Sempre Aperti");
            }

            int imageId =mContext.getResources().getIdentifier("@mipmap/chiesa1",
                        "drawable", mContext.getPackageName());
            ImageView img=(ImageView)v.findViewById(R.id.imageViewMonument);
            img.setImageResource(imageId);


        }


        return v;
    }




}
