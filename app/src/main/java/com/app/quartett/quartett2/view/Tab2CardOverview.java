package com.app.quartett.quartett2.view;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quartett.quartett2.MainActivity;
import com.app.quartett.quartett2.R;
import com.app.quartett.quartett2.model.Card;
import com.app.quartett.quartett2.model.Deck;

import java.io.File;

public class Tab2CardOverview extends Fragment{


    // Textviews including the attribute names
    public TextView attr1OverviewTextView, attr2OverviewTextView, attr3OverviewTextView, attr4OverviewTextView, attr5OverviewTextView;

    // Textviews including the values from the attribute names
    public TextView attr1ValueOverviewTextView, attr2ValueOverviewTextView, attr3ValueOverviewTextView, attr4ValueOverviewTextView, attr5ValueOverviewTextView;


    //the 2 imagebuttons displaying the arrows
    public ImageButton rightArrowImageButton, leftArrowImageButton;

    //first number is the position of the actual card, the second is the number of max cards
    public TextView firstNumberTextView, secondNumberTextView;

    //Imageview containing the Image of the car
    public ImageView cardPictureImageView;

    public Deck deck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab2cardoverview, container, false);

        //declaring stuff
        //attribute names
        attr1OverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr1OverviewTextView);
        attr2OverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr2OverviewTextView);
        attr3OverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr3OverviewTextView);
        attr4OverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr4OverviewTextView);
        attr5OverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr5OverviewTextView);

        //attribute values
        attr1ValueOverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr1ValueOverviewTextView);
        attr2ValueOverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr2ValueOverviewTextView);
        attr3ValueOverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr3ValueOverviewTextView);
        attr4ValueOverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr4ValueOverviewTextView);
        attr5ValueOverviewTextView = (TextView) rootView.getRootView().findViewById(R.id.attr5ValueOverviewTextView);

        //cardimage
        cardPictureImageView = (ImageView) rootView.getRootView().findViewById(R.id.cardPictureInGameImageView);

        //imagebuttons
        rightArrowImageButton = (ImageButton) rootView.getRootView().findViewById(R.id.rightArrowImageButton);
        leftArrowImageButton = (ImageButton) rootView.getRootView().findViewById(R.id.leftArrowImageButton);

        //scores
        firstNumberTextView = (TextView) rootView.getRootView().findViewById(R.id.firstNumberTextView);
        secondNumberTextView = (TextView) rootView.getRootView().findViewById(R.id.secondNumberTextView);

        //imagefiles
        Bitmap myBitmap = BitmapFactory.decodeFile("bikes/1.jpg");



        //setting up right dialogs for the first time when app is
        deck = MainActivity.getDeck();
        Card c = deck.getCards().get(0);
        String test = c.getValues().toString();
        attr1OverviewTextView.setText("attr1");
        cardPictureImageView.setImageBitmap(BitmapFactory.decodeFile("bikes/1.jpg"));
        //cardPictureImageView.setImageURI(Uri.parse("file:bikes/2.jpg"));

        return rootView;
    }

    public void showCards () {
        deck = MainActivity.getDeck();
        //Card first = deck.getCards().get(1);
        //for (Card c:deck.getCards()) {
        //    cardPictureImageView.setImageResource(first.getImages().indexOf(1));
        //}
        Card c = deck.getCards().get(0);
        String test = c.getValues().toString();
        attr1OverviewTextView.setText(test);
    }

}