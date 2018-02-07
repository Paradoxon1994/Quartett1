package com.app.quartett.quartett2.view;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.quartett.quartett2.MainActivity;
import com.app.quartett.quartett2.R;
import com.app.quartett.quartett2.Utilities;
import com.app.quartett.quartett2.model.Card;
import com.app.quartett.quartett2.model.Deck;
import com.app.quartett.quartett2.model.Image;
import com.app.quartett.quartett2.model.Property;
import com.app.quartett.quartett2.model.Value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Categories extends AppCompatActivity {

    //imageviews for themes
    public ImageView theme1ImageView, theme2ImageView, theme3ImageView, theme4ImageView;

    public FloatingActionButton floatingActionButton;

    public TextView textView;

    public ProgressBar progressBar;

    //selected Deck
    private static String selectedDeck;

    private int deckToDownload=0;

    //switchedDeck
    public static boolean switchedDecks = false;

    private ArrayList<Deck> extraDecks;


    String basicUrl =  "http://quartett.af-mba.dbis.info/decks/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        extraDecks=new ArrayList<>();
        setContentView(R.layout.categories);

        //initialization
        initialize(findViewById(android.R.id.content));

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadDecks();

        //progressBar.setVisibility(View.INVISIBLE);
        theme1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDeck = "bikes";
                MainActivity.setLoadedDeck(readDeck(selectedDeck));
                Tab1MainMenu.switchTheme();
                switchedDecks = true;
                finish();
            }
        });

        theme2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDeck = "tuning";
                MainActivity.setLoadedDeck(readDeck(selectedDeck));
                Tab1MainMenu.switchTheme();
                switchedDecks = true;
                finish();
            }
        });
        theme3ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                selectedDeck = "bettsport";
                if(!MainActivity.getLoadedDeck().getName().equals("Bettsport")&&extraDecks.size()!=0) {

                    MainActivity.setLoadedDeck(extraDecks.get(getIndexFromId(deckToDownload)));
                    Tab1MainMenu.switchTheme();
                    switchedDecks = true;
                    finish();
                }else {

                    if (MainActivity.extraDeck != null) {
                        MainActivity.setLoadedDeck(MainActivity.extraDeck);
                        Tab1MainMenu.switchTheme();
                        switchedDecks = true;
                        finish();
                    } else {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Categories.this);
                        builder1.setMessage("Oops something went wrong, try downloading the deck again");

                        AlertDialog alertboys = builder1.create();
                        alertboys.show();

                    }
                }

            }
        });

        if(extraDecks.size()!=0){
            theme3ImageView.setImageResource(R.drawable.bettsport1);
        }



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDecks();

                if(extraDecks.size()!=0) {

                    CharSequence names [] = new CharSequence[1];
                    int i =0;
                    for(Deck d : extraDecks){

                        if(!extraDecks.get(i).getName().equals("Tuning") && !extraDecks.get(i).getName().equals("Bikes")  ){
                            names[i] = extraDecks.get(i).getName() + " . "+ extraDecks.get(i).getId();
                        }


                            i++;


                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(Categories.this);
                    builder.setTitle("Select deck to download:");
                    builder.setSingleChoiceItems(names, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            deckToDownload=extraDecks.get(which).getId();
                            dialogInterface.cancel();
                            extraDecks.get(getIndexFromId(deckToDownload)).setDownloaded(true);
                            progressBar.setVisibility(View.VISIBLE);
                            loadDeck(deckToDownload);


                            loadImage();

                            MainActivity.extraDeck=extraDecks.get(getIndexFromId(deckToDownload));
                        }
                    });


                    AlertDialog alert1 = builder.create();
                    alert1.show();


                }


            }
        });

        if(MainActivity.getLoadedDeck().getName().equals("Bettsport")){
            loadImage();
        }else if(MainActivity.extraDeck!=null){
            //i know its nonsense just trying sth that actually helped but idk so its gonna stay for now xD
            loadImage();
        }



    }



    private void loadImage(){
                theme3ImageView.setImageResource(R.drawable.bettsport1);

    }

    public Deck readDeck(String deckName) {
        Deck deck = new Deck();
        try {


            ArrayList<Card> cardArrayList = new ArrayList<>();
            ArrayList<Property> propertyArrayList = new ArrayList<>();
            JSONObject obj = new JSONObject(Utilities.loadJSONFromAsset(this, deckName));

            deck.setName(obj.getString("name"));
            deck.setDescription(obj.getString("description"));

            JSONArray cards = obj.getJSONArray("cards");

            JSONArray properties = obj.getJSONArray("properties");


            for (int i = 0; i < cards.length(); i++) {
                Card card = new Card();
                ArrayList<Image> images = new ArrayList<>();
                ArrayList<Value> values = new ArrayList<>();
                JSONObject jcard = cards.getJSONObject(i);
                card.setId(jcard.getInt("id"));


                card.setName(jcard.getString("name"));

                JSONArray jimages = jcard.getJSONArray("images");
                JSONArray jvalues = jcard.getJSONArray("values");

                for (int j = 0; j < jimages.length(); j++) {

                    JSONObject jimage = jimages.getJSONObject(j);
                    Image image = new Image(jimage.getInt("id"), jimage.getString("filename"));

                    images.add(image);
                    //s+= jimage.getInt("id") + jimage.getString("filename");

                }

                for (int k = 0; k < jvalues.length(); k++) {
                    JSONObject jvalue = jvalues.getJSONObject(k);
                    Value value = new Value(jvalue.getDouble("value"), jvalue.getInt("propertyId"));
                    values.add(value);
                }
                card.setImages(images);

                card.setValues(values);


                cardArrayList.add(card);


            }


            deck.setCards(cardArrayList);

            for (int i = 0; i < properties.length(); i++) {
                JSONObject jproperty = properties.getJSONObject(i);
                Property property = new Property(jproperty.getString("text"),
                        jproperty.getInt("compare"), jproperty.getInt("id"), jproperty.getString("unit"), jproperty.getInt("precision"));

                propertyArrayList.add(property);
            }


            deck.setProperties(propertyArrayList);





        } catch (JSONException je) {
            je.printStackTrace();
        }

        return deck;
    }



    public static String getSelectedDeck(){
        return selectedDeck;
    }

    public static void setSelectedDeck(String s){
        selectedDeck = s;
    }

    public void loadDecks(){

        setUpConnection(basicUrl,0,-1,-1);

    }

    public void loadDeck(int deckId){

        String url = basicUrl +Integer.toString(deckId);
        setUpConnection(url,1,deckId,-1);

    }

    public void loadCards(int deckId){
        String url = basicUrl + Integer.toString(deckId) + "/cards";
        setUpConnection(url,2,deckId,-1);
    }



    public void loadAttributes(int deckId, int cardId){

        String url = basicUrl + Integer.toString(deckId) + "/cards/" + Integer.toString(cardId) + "/attributes";

        setUpConnection(url,4,deckId,cardId);

    }

    private void loadProperties(int deckId,int cardId) {
        String url = basicUrl + Integer.toString(deckId) + "/cards/" + Integer.toString(cardId) + "/attributes";

        setUpConnection(url,3,deckId,cardId);

    }

    public void loadImages(int deckId, int cardId){
        String url = basicUrl + Integer.toString(deckId) + "/cards/" + Integer.toString(cardId) + "/images";
        setUpConnection(url,5,deckId,cardId);




    }





    public void setUpConnection(String url, final int uid, final int deckId, final int cardId){

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = null;
        JsonObjectRequest jsObjRequest=null;

        if(uid!=1){
            jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    handleJsonObject(response, uid, deckId, cardId);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }){@Override
            public Map< String, String > getHeaders() throws AuthFailureError {
                HashMap < String, String > headers = new HashMap<>();
                //maybe we need admin:password here

                headers.put("Content-Type", "application/json");
                String creds = String.format("%s:%s","admin","c3R1ZGVudDphZm1iYQ==");
                String auth = "Basic c3R1ZGVudDphZm1iYQ==";
                headers.put("Authorization",auth);

                return headers;
            }
            };
        }else {
            jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            handleJSONObject(response, uid, deckId, cardId);

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //show something went wrong
                            error.printStackTrace();

                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<>();
                    //maybe we need admin:password here
                    headers.put("Content-Type", "application/json");
                    String creds = String.format("%s:%s","admin","c3R1ZGVudDphZm1iYQ==");
                    String auth = "Basic c3R1ZGVudDphZm1iYQ==";
                    headers.put("Authorization",auth);

                    return headers;
                }
            };
        }


        //test
        if(uid!=1){
            queue.add(jsonArrayRequest);

        }else {
            queue.add(jsObjRequest);
        }





    }


    private void handleJsonObject(JSONArray response, int uid, int deckId, int cardId) {
        switch(uid) {
            case 0:
                for(int i =0;i< response.length();i++) {
                    try {


                        Deck d = new Deck();
                        d.setName(response.getJSONObject(i).getString("name"));
                        d.setId(response.getJSONObject(i).getInt("id"));


                        if(extraDecks.size()<3){
                            extraDecks.add(d);

                        }



                    }catch (JSONException jsone){
                        jsone.printStackTrace();
                    }
                }
                break;


            case 2:

                for (int i=0;i<response.length();i++){
                    try {
                    Card c = new Card();
                    c.setId(response.getJSONObject(i).getInt("id"));
                    c.setName(response.getJSONObject(i).getString("name"));



                    extraDecks.get(getIndexFromId(deckToDownload)).getCards().add(c);



                    }catch (JSONException jsone){
                        jsone.printStackTrace();
                    }

                }

                loadProperties(deckToDownload,extraDecks.get(getIndexFromId(deckToDownload)).getCards().get(0).getId());

                int j =0;

                for(Card c:extraDecks.get(getIndexFromId(deckToDownload)).getCards()){
                    j++;

                    loadAttributes(deckToDownload,c.getId());
                    loadImages(deckToDownload,c.getId());

                    if(j==extraDecks.get(getIndexFromId(deckToDownload)).getCards().size()-1){
                        progressBar.setVisibility(View.GONE);
                    }

                }

                break;

            case 3:

                for(int i =0;i<response.length();i++){
                    try {
                    Property property = new Property();

                    property.setText(response.getJSONObject(i).getString("name"));
                    if(response.getJSONObject(i).getString("what_wins").equals("higher_wins")){
                        property.setCompare(1);
                    }else{
                        property.setCompare(-1);
                    }

                    property.setId(i);
                    property.setUnit(response.getJSONObject(i).getString("unit"));


                    extraDecks.get(getIndexFromId(deckId)).getProperties().add(property);
                    }catch (JSONException jsone){
                        jsone.printStackTrace();
                    }
                }

                break;

            case 4:


                for(int i=0;i<response.length();i++){

                    try {

                        Value v = new Value();
                        v.setPropertyId(i);
                        v.setValue(response.getJSONObject(i).getDouble("value"));



                    extraDecks.get(getIndexFromId(deckToDownload)).getCards().get(getIndexFromIdCards(cardId,extraDecks.get(getIndexFromId(deckToDownload)).getCards())).getValues().add(v);
                    }catch (JSONException jsone){
                        jsone.printStackTrace();
                    }
                }


                break;

            case 5:
                for(int i=0;i<response.length();i++){

                    try {
                        String str=response.getJSONObject(i).getString("image");
                        int index=str.lastIndexOf('/');
                        str= str.substring(index+1,str.length());
                        extraDecks.get(getIndexFromId(deckToDownload)).getCards().get(getIndexFromIdCards(cardId,extraDecks.get(getIndexFromId(deckToDownload)).getCards())).getImages().add(new Image(i,str));
                    }catch (JSONException jsone){
                        jsone.printStackTrace();
                    }
                }
                //textView.setText(extraDecks.get(getIndexFromId(deckToDownload)).toString());

                break;
        }
    }



    private void handleJSONObject(JSONObject obj,int uid,int deckId, int cardId) {

        try {


            switch (uid) {


                case 1:


                    extraDecks.get(getIndexFromId(deckId)).setDescription(obj.getString("description"));


                    extraDecks.get(getIndexFromId(deckId)).setImage(obj.getString("image"));

                    loadCards(deckId);

                    break;




            }

        }catch (JSONException jsone){
            jsone.printStackTrace();
        }


    }

    public int getIndexFromId(int uid){
        int i =0;
        for(Deck d:extraDecks){

            if(d.getId()==uid){
                return i;
            }
            i++;
        }

        return 5;
    }

    public int getIndexFromIdCards(int uid,ArrayList<Card> cards){
        int i =0;
        for(Card c : cards){
            if(c.getId()==uid){
                return i;
            }
            i++;
        }
        return 1;
    }

    public void initialize(View v){
        theme1ImageView = (ImageView) v.getRootView().findViewById(R.id.theme1ImageView);
        theme2ImageView = (ImageView) v.getRootView().findViewById(R.id.theme2ImageView);
        theme3ImageView = (ImageView) v.getRootView().findViewById(R.id.theme3ImageView);
        theme4ImageView = (ImageView) v.getRootView().findViewById(R.id.theme4ImageView);

        floatingActionButton = (FloatingActionButton) v.getRootView().findViewById(R.id.floatingActionButton);

        textView = (TextView) v.getRootView().findViewById(R.id.textView);

        progressBar = (ProgressBar) v.getRootView().findViewById(R.id.indeterminateBar);




    }
}
