package c.www.carovignoviva;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;

import c.www.carovignoviva.utility.InfoWindowData;
import c.www.carovignoviva.utility.ListData;

public class getInfoMarkerCarovigno extends AppCompatActivity implements Serializable {
    public ArrayList<Marker> markersCarovigno;
    Context context;
    ArrayList<InfoWindowData> data = new ArrayList<>();
    public static ArrayList<ListData> datalist = new ArrayList<>();


    public  getInfoMarkerCarovigno(GoogleMap mMap){
        context=this;
        markersCarovigno=new ArrayList<>();

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Chiesa Sant'Anna")
                .position(new LatLng(40.706963, 17.657863))));
        data.add(new InfoWindowData());
        data.get(0).setDescription("Questa piccola chiesetta fu realizzata annessa al castello dalla famiglia Imperiali che ivi dimorarono tra il 1732 ed il 1782. La chiesa fu successivamente utilizzata come cappella privata dalla famiglia Dentice, nella quale accedevano attraverso un passaggio che correva sull’arco antico di Porta Ostuni: essi accedevano direttamente nella chiesa prendendo posto in alto, al posto della cantoria. Il corpo di fabbrica si è sviluppato in tempi diversi, ma alla fine il risultato è lodevole: sia la cupola quanto i pavimenti sono originali.");
        data.get(0).setDetails("Chiesa Medievale");
        data.get(0).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(0).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(0).setTag(data.get(0));
        InfoWindowData data3= (InfoWindowData) markersCarovigno.get(0).getTag();
        data3.getDescription();//controllare se funziona


        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Porta Brindisi")
                .position(new LatLng(40.70623995957796,  17.65837401151657))));
        data.add(new InfoWindowData());
        data.get(1).setDescription("Affiancata alla Torre del Civile risulta composta da due porte susseguenti: quella del periodo Angioino più interna del XVIII secolo, con arco a sesto acuto e coronata con beccatelli, con agibilità a mezzo corridoio ricavato sulla stessa per l'uso della caditoia; quella del periodo rinascimentale – più precisamente eretta nel periodo feudale dei Loffreda - che è a tutto sesto e che precede la porta più antica. Si doveva comunque trattare dell’ingresso per carri Ha conservato gli innesti originari del portone. Sul frontale, si scorgono scalpellinati due stemmi che dovrebbero essere quello dei Loffreda (a sinistra) e dell'Università di Carovigno (a destra\n");
        data.get(1).setDetails("nope");
        data.get(1).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(1).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(1).setTag(data.get(1));



        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Torre del Civile")
                .position(new LatLng(40.70623995957596,  17.65837401151656))));
        data.add(new InfoWindowData());
        data.get(2).setDescription("Così chiamata perché anticamente vi erano esposte le armi della città della famiglia Imperiali, signori di Carovigno prima dalla metà del XVIII secolo");
        data.get(2).setDetails("nope");
        data.get(2).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(2).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(2).setTag(data.get(2));


        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Porta del Prete")
                .position(new LatLng(40.707251277954455,  17.659096478082233))));
        data.add(new InfoWindowData());
        data.get(3).setDescription("È un accesso secondario, probabilmente ricavato in tempi vicini all'eversione della feudalità. La stradina è caratterizzata da vari Archi, detti  Archi del Prete. Si Presume fosse il passaggio pedonale per l’ingresso diretto al borgo medievale, data la sua angustia e facilità di difesa.");
        data.get(3).setDetails("nope");
        data.get(3).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(3).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(3).setTag(data.get(3));


        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Torre Circolare")
                .position(new LatLng(40.70745968609935,  17.658763884164387))));
        data.add(new InfoWindowData());
        data.get(4).setDescription("La torre Circolare si trova nella via del Prete. Anch'essa presenta una grossa stivatura nella quale è stato prodotto un forno. È un secondo terminale della mura");
        data.get(4).setDetails("nope");
        data.get(4).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(4).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(4).setTag(data.get(4));

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Castello di Serranova")
                .position(new LatLng(40.69838898637995,  17.762210369110107))));
        data.add(new InfoWindowData());
        data.get(5).setDescription("Il Castello di Serranova, fatto costruire dal feudatario Ottavio Serra nel 1629, insiste\n" +
                "su un nucleo più antico del 1350 del quale ha conservato l&#39;originario ingresso al\n" +
                "primo piano, dal lato di mare. Si presenta con una torre quadrata, a lieve\n" +
                "controscarpa e tutta percorsa, lungo il perimetro del terrazzo, da un sistema di\n" +
                "caditoie; ad essa di addossa un edificio più recente che l&#39;edera rampicante ha fuso\n" +
                "in un unico corpo omogeneo.\n" +
                "Questa masseria, in origine denominata &quot; Difesa del Palombaro&quot;, fu poi intitolata\n" +
                "Serranova per averla il Barone Serra nel seicento posseduta e migliorata. Nei pressi\n" +
                "del Castello sorge la cappella del XVII° sec., a navata unica, con volta a botte, con tre\n" +
                "altari e fonte battesimale. Sul portale d&#39;ingresso è inserita l&#39;arma della famiglia\n" +
                "Imperiale, Principi di Francavilla e Signori di Carovigno nel settecento. Sull&#39;altare\n" +
                "maggiore è presente una tela di autori ignoti, del Seicento, raffigurante i santi\n" +
                "Crisante e Doria, sotto la cui protezione il Barone Ottavio Serra radunò alcune\n" +
                "famiglie abitanti la masseria di Serranova. In un altare è inserito un miracoloso\n" +
                "Crocifisso ligneo del sec. XVII.");
        data.get(5).setDetails("nope");
        data.get(5).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(5).getTitle()," Apertura: 13:00 19:00"));
        markersCarovigno.get(5).setTag(data.get(5));

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Chiesa del Soccorso")
                .position(new LatLng(40.70199312564566,  17.661781457553843))));
        data.add(new InfoWindowData());
        data.get(6).setDescription("Fu la prima sede dei Padri Carmelitani in Carovigno. Questo insediamento religioso\n" +
                "sorto nel 1588 nella zona valliva denominata &quot;Pacifico&quot;, lungo la strada che da\n" +
                "Ostuni conduce a Brindisi, ha condizionato lo sviluppo urbano di Carovigno tra\n" +
                "Cinquecento e Seicento, determinandone l&#39;andamento in direzione sud-est. Il luogo\n" +
                "malsano dove sorsero convento e chiesa, tuttavia costrinsero ben presto i Padri\n" +
                "Carmelitani a trasferirsi in una zona più adatta, nei pressi della &quot;terra&quot;; così il\n" +
                "Convento del Soccorso fu denominato &quot;Carmine Minore&quot;, in quanto grancia del\n" +
                "cosidetto &quot;Carmine Maggiore&quot;. Dopo la soppressione avvenuta nel 1809 fino alla\n" +
                "sua riattivazione, il Convento del Soccorso conobbe un periodo di totale abbandono.\n" +
                "Agli inizi del nostro secolo fu destinato ad ospitare l&#39;industria del tabacco e\n" +
                "successivamente un ospizio per gli anziani. Solo nel 1961 la Chiesa del Soccorso è\n" +
                "stata riaperta al culto. A navata unica, voltata a botte, presenta sul portale\n" +
                "d&#39;ingresso una lunetta affrescata raffigurante la Vergine del Monte Carmelo\n" +
                "sostenuta da due angeli fra i Santi Elia ed Eliseo.");
        data.get(6).setDetails("nope");
        data.get(6).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(6).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(6).setTag(data.get(6));

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Chiesa di Belvedere")
                .position(new LatLng(40.71631208798973,  17.702312618494034))));
        data.add(new InfoWindowData());
        data.get(7).setDescription("Situata su un&#39;altura a quattro miglia da Carovigno, il complesso monumentale è solo\n" +
                "l&#39;emergenza esterna di un più antico insediamento in rupe. Esso si presenta\n" +
                "formato da una chiesa sub-divo affiancata da fabbriche di varie epoche e da un\n" +
                "sistema di grotte naturali disposte su due diversi livelli cui si accede da un arco\n" +
                "esistente sul lato destro della cappella. Il tratto della scalinata presenta un vano\n" +
                "voltato a botte con un ingresso murato sulla destra e un&#39;absidiola affrescata sulla\n" +
                "sinistra. Uno degli affreschi raffigura una Vergine con Bambino datata 1530. Da\n" +
                "questo vano, dopo una breve scalinata, si accede alla cripta superiore che contiene\n" +
                "un&#39;edicola datata 1501 e commissionata dai Loffreda, feudatari di Carovigno, allo\n" +
                "scultore ostunese Giovanni Lombardo. Questa edicola cinquecentesca inquadra un\n" +
                "affresco della &quot;Vergine di Belvedere&quot; che reca in mano un uccellino. Prima di\n" +
                "scendere nella cripta inferiore sotto l&#39;arco di accesso si possono intravedere\n" +
                "frammenti di un affresco raffigurante l&#39;Arcangelo: questo confermerebbe\n" +
                "\n" +
                "l&#39;originario toponino &quot;S.Angelo di Luco&quot;, presente in un documento del 1160 e\n" +
                "riferibile proprio all&#39;attuale santuario di S. Maria di Belvedere, altrove denominata\n" +
                "&quot;S.Maria di Lucola&quot; (doc. del 1710). L&#39;ultima e più profonda cripta a circa 12 m.\n" +
                "contiene altri due affreschi della Vergine col Bambino, di cui quello più antico\n" +
                "tributario del gotico senese, è ascrivibile al tardo trecento. L&#39;altro affresco,\n" +
                "inquadrato, in un altare barocco rappresenta ancora la Madonna di Belvedere che,\n" +
                "sebbene rimaneggiata più volte, reca ancora i segni della sua origine\n" +
                "quattrocentesca. La definitiva sistemazione in stile neoclassico della cappella sub\n" +
                "divo risale al 1875, per volere del Conte Alfredo Dentice, come attesta una lapide\n" +
                "sulla facciata.");
        data.get(7).setDetails("nope");
        data.get(7).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(7).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(7).setTag(data.get(7));

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Cappella di Serranova - Santissima Maria " +
                        "Goretti")
                .position(new LatLng(40.69274368477265,  17.763261795043945))));
        data.add(new InfoWindowData());
        data.get(8).setDescription("Particolare del complesso feudale è la cappella del XVII secolo che si trova nei pressi del castello. È\n" +
                "costituita da una navata con tre altari e fu elevata a parrocchia per volere di Ottavio Serra nella prima metà\n" +
                "del ‘600. In uno di questi altari è conservato il famoso &quot;Crocifisso di Serranova&quot;, un crocifisso in legno del\n" +
                "1700 al quale vengono attribuiti poteri miracolosi, tanto che su di esso la tradizione popolare ha\n" +
                "tramandato più di una leggenda. Esso sarebbe giunto da una imbarcazione il cui equipaggio, salvatosi per\n" +
                "miracolo da un violento temporale sulla costa di Torre Guaceto, volle fare dono alla cappella di Serranova\n" +
                "come ex voto per la grazia ricevuta. A Maggio di ogni anno il &quot;Crocifisso di Serranova&quot; viene portato in\n" +
                "processione per scongiurare eventi calamitosi o siccitosi.\n" +
                "Accanto a questo altare c&#39;è una spada che, secondo la tradizione popolare, avrebbe salvato da sola alcuni\n" +
                "cittadini del borgo antico dall&#39;attacco dei turchi, tagliando la testa ad uno di essi e facendo di conseguenza\n" +
                "fuggire gli altri.\n" +
                "Infine, a testimoniare l&#39;intensa attività agricola di tipo oleario che caratterizzava la zona sin dal medioevo,\n" +
                "vi è vicino alla cappella, scavato nella roccia, un antichissimo &quot;Trappeto&quot;, (antica denominazione di\n" +
                "frantoio), dove le olive venivano lavorate e trasformate in olio.");
        data.get(8).setDetails("nope");
        data.get(8).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(8).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(8).setTag(data.get(8));

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Albero del Crocifisso")
                .position(new LatLng(40.700396488394325,  17.762182570311552))));
        data.add(new InfoWindowData());
        data.get(9).setDescription("ALBERO LEGGENDARIO: Si racconta che il crocifisso della chiesa di serranova e la spada nel castella di serranova siano stati rinvenuti in usto maestoso ulivo");
        data.get(9).setDetails("nope");
        data.get(9).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(9).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(9).setTag(data.get(9));

        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Mansio ad speluncae")
                .position(new LatLng(40.75792020829802,  17.6970297851949))));
        data.add(new InfoWindowData());
        data.get(10).setDescription("QUALCOSA");
        data.get(10).setDetails("nope");
        data.get(10).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(10).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(10).setTag(data.get(10));



        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Torre Santa Sabina")
                .position(new LatLng(40.75829404157074,  17.703351974487305))));
        data.add(new InfoWindowData());
        data.get(11).setDescription("In località S. Sabina, resti di popolamento preistorico attestano una frequentazione\n" +
                "del sito fin da epoche remote. All&#39;età romana sembra, invece, risalire la\n" +
                "localizzazione presso Torre S. Sabina, della &quot;mansio ad Speluncae&quot;, vale a dire la\n" +
                "stazione postale della via Traiana che forniva ospitalità ai viandanti e ricovero ai\n" +
                "cavalli. Il nome &quot;speluncae&quot; denota la presenza di grotte e anfratti che, fino a tutto il\n" +
                "Basso Impero, erano frequentati da coloro che transitavano lungo l&#39;importante via\n" +
                "romana.\n" +
                "Il ruolo storico assolto dalla &quot;mansio&quot; in età romana e tardo antica fu perpetuata in\n" +
                "età medievale da Ostedale dei Cavalli teutonici, documentato dal 1226 presso il\n" +
                "porticciolo di S. Sabina. Ma le incursioni dei pirati e dei saraceni in un luogo molto\n" +
                "frequentato da pellegrini, viaggiatori e mercanti ( vi era la Dogana ), rese\n" +
                "necessaria, verso la fine del XIII secolo, la costruzione di una torre, che nel 1396,\n" +
                "risultava già diroccata. Un nuovo programma di opere di fortificazione, compiuto\n" +
                "nel XVI secolo ad opera del Vicerè De Riberia per meglio presidiare la costa fece sì\n" +
                "che la nuova torre di S. Sabina, sorgesse, ex-novo, mezzo miglio distante dalla\n" +
                "prima.\n" +
                "Della antica torre angioina oggi non resta alcuna traccia mentre è in buono stato di\n" +
                "conservazione il presidio cinquecentesco, restaurato di recente e sede di abitazione\n" +
                "privata. Ha una Tipologia stellare a quattro spigoli orientati verso i quattro punti\n" +
                "cardinali. E&#39; alta m.11,31, è leggermente scorpata, ha un coronamento merlato e\n" +
                "rappresenta, nel suo insieme un tipico esempio di adattamento del sistema\n" +
                "difensivo all&#39;uso delle armi da fuoco");
        data.get(11).setDetails("nope");
        data.get(11).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(11).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(11).setTag(data.get(11));



        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Chiesa Sant'Angelo")
                .position(new LatLng(40.70696787136217,  17.657852321863174))));
        data.add(new InfoWindowData());
        data.get(12).setDescription("La Chiesetta di S. Angelo, forse costruita sul sito di un probabile tempio pagano, ha\n" +
                "un&#39;origine altomedievale a giudicare dall&#39;intitolazione all&#39;Arcangelo, tale culto,\n" +
                "infatti, diffuso tra Longobardi e Bizantini, fu praticato anche nella chiesa rupestre di\n" +
                "S.Angelo di Luco sita fuori le mura, a quattro miglia dal centro abitato e\n" +
                "successivamente denominata S. Maria di Belvedere.\n" +
                "Ubicata al centro della primitiva &quot;Terra&quot; di Carovigno, invece, la chiesetta di S.\n" +
                "Angelo presenta sul suo ingresso principale un&#39;iscrizione del 1814, da cui si ricava\n" +
                "la notizia del suo ruolo di prima chiesa parrocchiale di Carovigno. Menzionata, la\n" +
                "prima volta nel 1558, essa risulta ormai da tempo soppiantata dalla Chiesa Madre.\n" +
                "Nel Seicento la chiesa di S. Angelo è inagibile perchè priva di copertura. La Curia\n" +
                "Vescovile, onde evitare la rovina completa, nel 1722 affidò la chiesa alla\n" +
                "Confraternita dell&#39;Immacolata, di cui è sede fin da allora.\n" +
                "Lo stato attuale dell&#39;edificio è il risultato di un pessimo restauro dell&#39;ottocento che\n" +
                "dell&#39;assetto originario non ha lasciato tracce se non nello schema a navata unica e in\n" +
                "un richiamo all&#39;antica copertura a tetto, leggibile sul lato est dell&#39;edificio.");
        data.get(12).setDetails("nope");
        data.get(12).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(12).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(12).setTag(data.get(12));



        markersCarovigno.add(mMap.addMarker(new MarkerOptions()
                .title("Torre Guaceto")
                .position(new LatLng(40.715191908398225,  17.79982566833496))));
        data.add(new InfoWindowData());
        data.get(13).setDescription("Anche il popolamento di Guaceto risale all&#39;età preistorica: ritrovamenti di selce e di\n" +
                "ceramica ne sono testimonianza. All&#39;epoca romana, invece, sono ascrivibili altre\n" +
                "scoperte: alcune fornaci per la Cottura di vasi in argilla nei pressi della torre e\n" +
                "grandi ancore di piombo trovate sul fondo della rada e ora conservate presso il\n" +
                "Museo Provinciale di Brindisi. Dopo la caduta dell&#39;Impero Romano, Guaceto entra\n" +
                "in una fase di abbandono e di impaludamento e si registrano frequenti scorrerie di\n" +
                "Saraceni. Ad essi si deve il toponomo &quot;Guaceto&quot;, originatosi dalla radice araba &quot;Gau&quot;\n" +
                "che significa corso d&#39; acqua dolce. Intorno alla metà del Cinquecento il sito era\n" +
                "ancora indicato col nome &quot;Saracinopoli seu Guacito&quot;. Di qui la necessità di rendere\n" +
                "più protetta la zona attraverso la costruzione di una torre, posta a difesa del\n" +
                "litorale. Essa sorge su un lieve promontorio, tra due spendide insenature. Ha una\n" +
                "base quadrata, è leggermente scorpata ed ha grandi caditoie per lato, tipiche delle\n" +
                "torri costiere del Regno in terra d&#39;Otranto. Attualmente è sede di un osservatorio\n" +
                "presidiato dal W.W.F. che vigila sull&#39;intera zona umida circostante e sulla riserva\n" +
                "marina, entrambe di enorme interesse internazionale. La fitta presenza di\n" +
                "esemplari, anche rari, della flora e della fauna della zona umida ha reso necessaria\n" +
                "\n" +
                "l&#39;apertura di un piccolo museo all&#39;interno della torre che registra, ogni anno,\n" +
                "tantissime presenze tra scolaresche, studiosi e semplici curiosi.");
        data.get(13).setDetails("nope");
        data.get(13).setImage("@mipmap/chiesa1");
        datalist.add(new ListData("@mipmap/chiesa1",markersCarovigno.get(13).getTitle(),"Sempre Aperto"));
        markersCarovigno.get(13).setTag(data.get(13));







        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markersCarovigno.get(0).getPosition(),16.0f));

        }

}
