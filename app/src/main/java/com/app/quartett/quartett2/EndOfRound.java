package com.app.quartett.quartett2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class EndOfRound extends Activity {

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
        setContentView(R.layout.endofgame);


    }
}
