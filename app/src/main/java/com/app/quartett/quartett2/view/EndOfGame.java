package com.app.quartett.quartett2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.quartett.quartett2.MainActivity;
import com.app.quartett.quartett2.R;


public class EndOfGame extends AppCompatActivity{

    //buttons for main menu and play again
    public Button mainMenuButton, playAgainButton;

    //button for sharing your awesome sh*t
    public ImageButton shareButton;

    //editable textViews needed in this activity
    public TextView roundsPlayedTextView, roundsWonTextView, kiCardsLeftTextView, playerCardsLeftTextView, gameStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endofgame);

        //initialization
        initialization(findViewById(android.R.id.content));

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndOfGame.this, MainActivity.class);
                startActivity(intent);



            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(EndOfGame.this,InGame.class);

                startActivity(intent);
            }
        });



    }

    protected void onStart(){
        super.onStart();

        //saving data from
        getDataFromIntent(getIntent());

    }

    private void getDataFromIntent(Intent intent) {

        roundsPlayedTextView.setText(intent.getStringExtra("roundsPlayed"));
        roundsWonTextView.setText(intent.getStringExtra("roundsWon"));
        kiCardsLeftTextView.setText(intent.getStringExtra("kiCardsLeft"));
        playerCardsLeftTextView.setText(intent.getStringExtra("playerCardsLeft"));
        gameStateTextView.setText(intent.getStringExtra("won"));


    }

    private void initialization(View v) {
        //buttons: play again/main menu
        mainMenuButton = (Button) v.getRootView().findViewById(R.id.mainMenuButton);
        playAgainButton = (Button) v.getRootView().findViewById(R.id.playAgainButton);

        //share ur shit button
        shareButton = (ImageButton) v.getRootView().findViewById(R.id.shareButton);

        //informations textviews
        roundsPlayedTextView = (TextView) v.getRootView().findViewById(R.id.roundsPlayedTextView);
        roundsWonTextView = (TextView) v.getRootView().findViewById(R.id.roundsWonTextView);
        kiCardsLeftTextView = (TextView) v.getRootView().findViewById(R.id.opponentPlayerRoundsWonTextView);
        playerCardsLeftTextView = (TextView) v.getRootView().findViewById(R.id.playerRoundsWonTextView);
        gameStateTextView = (TextView) v.getRootView().findViewById(R.id.gameStateTextView);


    }
}
