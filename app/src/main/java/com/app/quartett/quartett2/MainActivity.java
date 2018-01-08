package com.app.quartett.quartett2;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.quartett.quartett2.model.Card;
import com.app.quartett.quartett2.model.Deck;
import com.app.quartett.quartett2.model.Image;
import com.app.quartett.quartett2.model.Property;
import com.app.quartett.quartett2.model.Value;
import com.app.quartett.quartett2.view.Tab1MainMenu;
import com.app.quartett.quartett2.view.Tab2CardOverview;
import com.app.quartett.quartett2.view.Tab3Manual;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private static Deck loadedDeck;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static String deckName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.fragment_preference,false);


        deckName = "bikes";




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



        //Floating Action Button if needed
        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        loadedDeck = readDeck("bikes");
    }


    public  Deck readDeck(String deckName) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.preferences:
            {
                Intent intent = new Intent();
                intent.setClassName(this, "com.app.quartett.quartett2.MyPreferenceActivity");
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }



    //deleted PlaceholderFragment class from here

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //returning current tabs
            switch (position) {
                case 0:
                    Tab1MainMenu tab1 = new Tab1MainMenu();
                    return tab1;
                case 1:
                    Tab2CardOverview tab2 = new Tab2CardOverview();
                    return tab2;
                case 2:
                    Tab3Manual tab3 = new Tab3Manual();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "MAIN MENU";
                case 1:
                    return "CARD OVERVIEW";
                case 2:
                    return "MANUAL";
            }
            return null;
        }
    }

    public Deck changeDeckRead(String s){
        return readDeck(s);
    }

    public static Deck getLoadedDeck(){
        return loadedDeck;
    }

    public static void setLoadedDeck(Deck d) {loadedDeck = d;}



    /*public static void setLoadedDeck(String deck){
        loadedDeck = readDeck(deck);
    }*/

}