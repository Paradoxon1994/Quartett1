package com.app.quartett.quartett2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class EndOfGame extends Activity{

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


    }
}
