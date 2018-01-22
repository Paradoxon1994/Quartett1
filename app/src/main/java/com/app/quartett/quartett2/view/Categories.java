package com.app.quartett.quartett2.view;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Categories extends AppCompatActivity {

    //imageviews for themes
    public ImageView theme1ImageView, theme2ImageView, theme3ImageView, theme4ImageView;

    //selected Deck
    private static String selectedDeck;

    //switchedDeck
    public static boolean switchedDecks = false;


    String basicUrl =  "http://quartett.af-mba.dbis.info/decks/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        //initialization
        initialize(findViewById(android.R.id.content));

    }

    @Override
    protected void onStart() {
        super.onStart();

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
        String url = basicUrl + Integer.toString(deckId);
        setUpConnection(url,1,deckId,-1);

    }

    public void loadCards(int deckId){
        String url = basicUrl + Integer.toString(deckId) + "cards";
        setUpConnection(url,2,deckId,-1);
    }

    public void loadCard(int deckId,int cardId){
        String url = basicUrl + Integer.toString(deckId) + "cards" + Integer.toString(cardId);
        setUpConnection(url,3,deckId,cardId);
    }

    public void loadAttributes(int deckId, int cardId){

        String url = basicUrl + Integer.toString(deckId) + "cards" + Integer.toString(cardId) + "attributes";

        setUpConnection(url,4,deckId,cardId);

    }

    public void loadImages(int deckId, int cardId){
        String url = basicUrl + Integer.toString(deckId) + "cards" + Integer.toString(cardId) + "images";
        setUpConnection(url,5,deckId,cardId);
    }





    public void setUpConnection(String url, final int uid, final int deckId, final int cardId){

        RequestQueue queue = Volley.newRequestQueue(this);


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        handleJSONObject(response,uid,deckId,cardId);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //show something went wrong
                        error.printStackTrace();

                    }
                }) {@Override
        public Map< String, String > getHeaders() throws AuthFailureError {
            HashMap < String, String > headers = new HashMap<>();
            //maybe we need admin:password here
            String encodedCredentials = Base64.encodeToString("c3R1ZGVudDphZm1iYQ==".getBytes(), Base64.NO_WRAP);
            headers.put("Authorization", "Basic " + encodedCredentials);
            headers.put("Content-Type", "application/json");

            return headers;
        }
        };


        queue.add(jsObjRequest);




    }

    private void handleJSONObject(JSONObject obj,int uid,int deckId, int cardId) {

        switch(uid) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }


    }

    public void initialize(View v){
        theme1ImageView = (ImageView) v.getRootView().findViewById(R.id.theme1ImageView);
        theme2ImageView = (ImageView) v.getRootView().findViewById(R.id.theme2ImageView);
        theme3ImageView = (ImageView) v.getRootView().findViewById(R.id.theme3ImageView);
        theme4ImageView = (ImageView) v.getRootView().findViewById(R.id.theme4ImageView);
    }
}
