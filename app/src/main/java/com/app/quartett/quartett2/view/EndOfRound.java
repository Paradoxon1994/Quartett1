package com.app.quartett.quartett2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quartett.quartett2.MainActivity;
import com.app.quartett.quartett2.R;

import static java.security.AccessController.getContext;


public class EndOfRound extends AppCompatActivity {

    //round overview
    public TextView gameStateTextView;

    //card names
    public TextView cardNameTextView, opponentCardNameTextView;

    //imageviews of the 2 cards
    public ImageView ownCardImageView, opponentCardImageView;

    //textviews for editing attribute values
    public TextView ownAttrNameTextView, ownAttrValueTextView, opponentAttrNameTextView, opponentAttrValueTextView;

    //button for continuing
    public Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endofround);

        //initialization
       initialization(findViewById(android.R.id.content));

    }

    /*protected void onStart(){
       super.onStart();

        //saving data from
        getDataFromIntent(getIntent());

    }*/

    private void getDataFromIntent(Intent intent){
        cardNameTextView.setText(intent.getStringExtra("playerCardName"));
        opponentCardNameTextView.setText(intent.getStringExtra("kiCardName"));
        ownCardImageView.setImageResource(this.getResources().getIdentifier(Categories.getSelectedDeck() + intent.getStringExtra("1"), "drawable", getPackageName()));
        opponentCardImageView.setImageResource(getResources().getIdentifier(Categories.getSelectedDeck() + intent.getStringExtra("kiCardImagePath"), "drawable", getApplicationContext().getPackageName()));
        ownAttrNameTextView.setText(intent.getStringExtra("property"));
        opponentAttrNameTextView.setText(intent.getStringExtra("property"));

        //saving values
        double playerCardValue = 0;
        intent.getDoubleExtra("playerCardValue", playerCardValue);
        opponentAttrValueTextView.setText(String.valueOf(playerCardValue));
        double kiCardValue = 0;
        intent.getDoubleExtra("kiCardValue", kiCardValue);
        opponentAttrValueTextView.setText(String.valueOf(kiCardValue));

        //ownAttrValueTextView.setText(String.valueOf(intent.getStringExtra("playerCardValue")));
        gameStateTextView.setText(intent.getStringExtra("won"));

        //cardimages
        ownCardImageView.setImageResource(getResources().getIdentifier("R.id.Categories.getSelectedDeck()" + intent.getStringExtra("1"), null, null));
        opponentCardImageView.setImageResource(getResources().getIdentifier("R.id.Categories.getSelectedDeck()" + intent.getStringExtra("1"), null, null));

    }

    private void initialization(View v) {

        //won or lost display
        gameStateTextView = (TextView) v.getRootView().findViewById(R.id.gameStateTextView);

        //cardnames
        cardNameTextView = (TextView) v.getRootView().findViewById(R.id.cardNameTextView);
        opponentCardNameTextView = (TextView) v.getRootView().findViewById(R.id.opponentCardNameTextView);

        //images of the 2 cards
        ownCardImageView = (ImageView) v.getRootView().findViewById(R.id.ownCardImageView);
        opponentCardImageView = (ImageView) v.getRootView().findViewById(R.id.opponentCardImageView);

        //attributes and values
        ownAttrNameTextView = (TextView) v.getRootView().findViewById(R.id.ownAttrNameTextView);
        ownAttrValueTextView = (TextView) v.getRootView().findViewById(R.id.ownAttrValueTextView);
        opponentAttrNameTextView = (TextView) v.getRootView().findViewById(R.id.opponentAttrNameTextView);
        opponentAttrValueTextView = (TextView) v.getRootView().findViewById(R.id.opponentAttrValueTextView);

        //continue button
        continueButton = (Button) v.getRootView().findViewById(R.id.continueButton);
    }
}
