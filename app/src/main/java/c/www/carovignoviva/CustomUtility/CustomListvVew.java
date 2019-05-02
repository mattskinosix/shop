package c.www.carovignoviva.CustomUtility;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import c.www.carovignoviva.CircleTrasform;
import c.www.carovignoviva.Monumento;
import c.www.carovignoviva.R;
import c.www.carovignoviva.RetriveImageInternet;

public class CustomListvVew extends ArrayAdapter<Monumento> {


    private int  resourceLayout;
    private Context mContext;
    public CustomListvVew(Context context, int textViewResourceId,
                         ArrayList <Monumento> objects) {
        super(context, textViewResourceId, objects);
        mContext=context;
        resourceLayout=textViewResourceId;
    }


    @Override
    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Monumento p = getItem(position);

        if (p != null) {
            TextView ttvisite = v.findViewById(R.id.textViewListVisite);
            TextView tt2 = v.findViewById(R.id.TextViewListTitle);

            if (tt2 != null) {
                tt2.setText(p.getNome());
           }

            if (ttvisite != null) {
                ttvisite.setText(p.getOrario());
            }
            ImageView img= v.findViewById(R.id.imageViewMonument);
            Picasso.get().load(p.getImage())
                    .transform(new CircleTrasform()).into(img);
            //img.setImageDrawable(new RetriveImageInternet().execute(p.getImage()).get(););


        }


        return v;
    }




}
