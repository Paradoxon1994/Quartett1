package com.app.quartett.quartett2.view;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quartett.quartett2.R;

public class Tab1MainMenu extends Fragment{


    //imageview containing the image of the selected theme
    public static ImageView themeImageView;

    //textviews containing data from statistics
    public TextView numberOfGamesNumberTextView, wonNumberTextView, averageCardsNumberTextView;

    //buttons to start game and select category
    public Button newGame, categories;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.tab1mainmenu, container, false);

        //initializing stuff
        initialize(rootView);


        return rootView;
    }

    @Override
    public void onStart(){

        super.onStart();

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Categories.class);
                startActivity(intent);
            }
        });

    }

    public static void switchTheme(){
        if(Categories.getSelectedDeck() == "bike"){
            themeImageView.setImageResource(R.drawable.bike1);
        } else {
            themeImageView.setImageResource(R.drawable.tuning1);
        }
    }

    private void initialize(View v) {

        //theme image
        themeImageView = (ImageView) v.getRootView().findViewById(R.id.themeImageView);

        //buttons
        categories = (Button) v.getRootView().findViewById(R.id.categories);

    }

}
