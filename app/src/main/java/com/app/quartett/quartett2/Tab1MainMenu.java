package com.app.quartett.quartett2;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Tab1MainMenu extends Fragment{


    //imageview containing the image of the selected theme
    public ImageView themeImageView;

    //textviews containing data from statistics
    public TextView numberOfGamesNumberTextView, wonNumberTextView, averageCardsNumberTextView;

    //buttons to start game and select category
    public Button newGame, categories;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.tab1mainmenu, container, false);
        return rootView;
    }

}
