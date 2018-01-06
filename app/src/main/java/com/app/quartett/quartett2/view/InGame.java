package com.app.quartett.quartett2.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quartett.quartett2.GameController;
import com.app.quartett.quartett2.R;


public class InGame extends AppCompatActivity{


    // Textviews including the attribute names
    public TextView attr1TextView, attr2TextView, attr3TextView, attr4TextView, attr5TextView, attr6TextView;

    // Textviews including the values from the attribute names
    public TextView attr1ValueTextView, attr2ValueTextView, attr3ValueTextView, attr4ValueTextView, attr5ValueTextView, attr6ValueTextView;


    //imageview for the image of te given element
    public ImageView cardPictureInGameImageView;

    //number of the ongoing round
    public TextView numberOfRoundTextView;

    //continue button
    public Button continueButton;

    private GameController gameController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingame);

        //initialization
        initialization(findViewById(android.R.id.content));




    }

    private void initialization(View v) {

        //atttribute name textviews
        attr1TextView = (TextView) v.getRootView().findViewById(R.id.attr1TextView);
        attr2TextView = (TextView) v.getRootView().findViewById(R.id.attr2TextView);
        attr3TextView = (TextView) v.getRootView().findViewById(R.id.attr3TextView);
        attr4TextView = (TextView) v.getRootView().findViewById(R.id.attr4TextView);
        attr5TextView = (TextView) v.getRootView().findViewById(R.id.attr5TextView);
        attr6TextView = (TextView) v.getRootView().findViewById(R.id.attr6TextView);

        //atttribute value textviews
        attr1ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr1ValueTextView);
        attr2ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr2ValueTextView);
        attr3ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr3ValueTextView);
        attr4ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr4ValueTextView);
        attr5ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr5ValueTextView);
        attr6ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr6ValueTextView);

        //card image
        cardPictureInGameImageView = (ImageView) v.getRootView().findViewById(R.id.cardNameTextView);

        //number of rounds
        numberOfRoundTextView = (TextView) v.getRootView().findViewById(R.id.numberOfRoundTextView);

        //continue
        continueButton = (Button) v.getRootView().findViewById(R.id.continueButton);
    }
}
