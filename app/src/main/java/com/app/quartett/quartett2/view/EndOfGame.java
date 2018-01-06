package com.app.quartett.quartett2.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.quartett.quartett2.R;


public class EndOfGame extends AppCompatActivity{

    //buttons for main menu and play again
    public Button mainMenuButton, playAgainButton;

    //button for sharing your awesome sh*t
    public Button shareButton;

    //editable textViews needed in this activity
    public TextView roundsPlayedTextView, roundsWonTextView, opponentPlayerRoundsWonTextView, playerRoundsWonTextView, gameStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endofgame);

        //initialization
        initialization(findViewById(android.R.id.content));


    }

    private void initialization(View v) {
        //buttons: play again/main menu
        mainMenuButton = (Button) v.getRootView().findViewById(R.id.mainMenuButton);
        playAgainButton = (Button) v.getRootView().findViewById(R.id.playAgainButton);

        //share ur shit button
        shareButton = (Button) v.getRootView().findViewById(R.id.shareButton);

        //informations textviews
        roundsPlayedTextView = (TextView) v.getRootView().findViewById(R.id.roundsPlayedTextView);
        roundsWonTextView = (TextView) v.getRootView().findViewById(R.id.roundsWonTextView);
        opponentPlayerRoundsWonTextView = (TextView) v.getRootView().findViewById(R.id.opponentPlayerRoundsWonTextView);
        playerRoundsWonTextView = (TextView) v.getRootView().findViewById(R.id.playerRoundsWonTextView);
        gameStateTextView = (TextView) v.getRootView().findViewById(R.id.gameStateTextView);


    }
}
