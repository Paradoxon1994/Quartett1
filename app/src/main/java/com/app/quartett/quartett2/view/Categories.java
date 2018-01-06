package com.app.quartett.quartett2.view;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Categories extends AppCompatActivity {

    //imageviews for themes
    public ImageView theme1ImageView, theme2ImageView;

    //selected Deck
    private static String selectedDeck;


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
                Tab2CardOverview test = new Tab2CardOverview();
                finish();
            }
        });

        theme2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDeck = "tuning";
                MainActivity.setLoadedDeck(readDeck(selectedDeck));
                Tab1MainMenu.switchTheme();
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

    public void initialize(View v){
        theme1ImageView = (ImageView) v.getRootView().findViewById(R.id.theme1ImageView);
        theme2ImageView = (ImageView) v.getRootView().findViewById(R.id.theme2ImageView);
    }
}
