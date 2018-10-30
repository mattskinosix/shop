package c.www.carovignoviva;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import c.www.carovignoviva.utility.InfoWindowData;

public class getInfoMarkerSerra extends AppCompatActivity {
    public ArrayList<Marker> markersCarovigno;
    Context context;
    ArrayList<InfoWindowData> data = new ArrayList<>();
    public getInfoMarkerSerra(GoogleMap mMap){
        context=this;
        markersCarovigno=new ArrayList<>();

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Paesello serranova")
                .position(new LatLng(40.691340, 17.762190))));
        data.add(new InfoWindowData());
        data.get(0).setDescription("Questa piccola chiesetta fu realizzata annessa al castello dalla famiglia Imperiali che ivi dimorarono tra il 1732 ed il 1782. La chiesa fu successivamente utilizzata come cappella privata dalla famiglia Dentice, nella quale accedevano attraverso un passaggio che correva sull’arco antico di Porta Ostuni: essi accedevano direttamente nella chiesa prendendo posto in alto, al posto della cantoria. Il corpo di fabbrica si è sviluppato in tempi diversi, ma alla fine il risultato è lodevole: sia la cupola quanto i pavimenti sono originali.");
        data.get(0).setDetails("Chiesa Medievale");
        markersCarovigno.get(0).setTag(data.get(0));

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Porta Brindisi")
                .position(new LatLng(40.70623995957796,  17.65837401151657))));
        data.add(new InfoWindowData());
        data.get(1).setDescription("Affiancata alla Torre del Civile risulta composta da due porte susseguenti: quella del periodo Angioino più interna del XVIII secolo, con arco a sesto acuto e coronata con beccatelli, con agibilità a mezzo corridoio ricavato sulla stessa per l\\'uso della caditoia; quella del periodo rinascimentale – più precisamente eretta nel periodo feudale dei Loffreda - che è a tutto sesto e che precede la porta più antica. Si doveva comunque trattare dell’ingresso per carri Ha conservato gli innesti originari del portone. Sul frontale, si scorgono scalpellinati due stemmi che dovrebbero essere quello dei Loffreda (a sinistra) e dell'Università di Carovigno (a destra</string>\n");
        data.get(1).setDetails("nope");
        markersCarovigno.get(1).setTag(data.get(1));



        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Torre del Civile")
                .position(new LatLng(40.70623995957596,  17.65837401151656))));
        data.add(new InfoWindowData());
        data.get(2).setDescription("Così chiamata perché anticamente vi erano esposte le armi della città della famiglia Imperiali, signori di Carovigno prima dalla metà del XVIII secolo");
        data.get(2).setDetails("nope");
        markersCarovigno.get(2).setTag(data.get(2));


        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Porta del Prete")
                .position(new LatLng(40.707251277954455,  17.659096478082233))));
        data.add(new InfoWindowData());
        data.get(3).setDescription("È un accesso secondario, probabilmente ricavato in tempi vicini all'eversione della feudalità. La stradina è caratterizzata da vari Archi, detti  Archi del Prete. Si Presume fosse il passaggio pedonale per l’ingresso diretto al borgo medievale, data la sua angustia e facilità di difesa.");
        data.get(3).setDetails("nope");
        markersCarovigno.get(3).setTag(data.get(3));


        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Torre Circolare")
                .position(new LatLng(40.70745968609935,  17.658763884164387))));
        data.add(new InfoWindowData());
        data.get(4).setDescription("La torre Circolare si trova nella via del Prete. Anch'essa presenta una grossa stivatura nella quale è stato prodotto un forno. È un secondo terminale della mura");
        data.get(4).setDetails("nope");
        markersCarovigno.get(4).setTag(data.get(4));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markersCarovigno.get(0).getPosition(),17.0f));

        }

}
