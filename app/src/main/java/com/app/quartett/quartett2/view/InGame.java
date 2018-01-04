package com.app.quartett.quartett2.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quartett.quartett2.GameController;
import com.app.quartett.quartett2.R;


public class InGame extends Activity {


    // Textviews including the attribute names
    public TextView attr1TextView, attr2TextView, attr3TextView, attr4TextView, attr5TextView;

    // Textviews including the values from the attribute names
    public TextView attr1ValueTextView, attr2ValueTextView, attr3ValueTextView, attr4ValueTextView, attr5ValueTextView;


    //imagebutton for the image of te given element
    public ImageButton carPictureInGameImageView;

    //number of the ongoing round
    public TextView numberOfRoundTextView;

    private GameController gameController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingame);


    }
}
