package com.app.quartett.quartett2.view;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.quartett.quartett2.MainActivity;
import com.app.quartett.quartett2.R;

public class Categories extends AppCompatActivity {

    //imageviews for themes
    public ImageView theme1ImageView, theme2ImageView;

    //selected Deck
    private static String selectedDeck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selectedDeck = "bikes";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        //initialization
        initialize(findViewById(android.R.id.content));


    }

    @Override
    protected void onStart(){
        super.onStart();

        theme1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDeck = "bikes";
                Tab1MainMenu.switchTheme();
                finish();
            }
        });

        theme2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDeck = "tuning";
                Tab1MainMenu.switchTheme();
                finish();
            }
        });

    }


    public static String getSelectedDeck(){
        return selectedDeck;
    }

    public void initialize(View v){
        theme1ImageView = (ImageView) v.getRootView().findViewById(R.id.theme1ImageView);
        theme2ImageView = (ImageView) v.getRootView().findViewById(R.id.theme2ImageView);
    }
}
