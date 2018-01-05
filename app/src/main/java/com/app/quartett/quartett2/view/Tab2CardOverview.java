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
import com.app.quartett.quartett2.model.Property;
import com.app.quartett.quartett2.model.Value;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Tab2CardOverview extends Fragment{


    // Textviews including the attribute names
    public TextView attr1OverviewTextView, attr2OverviewTextView, attr3OverviewTextView, attr4OverviewTextView, attr5OverviewTextView, attr6OverviewTextView;

    // Textviews including the values from the attribute names
    public TextView attr1ValueOverviewTextView, attr2ValueOverviewTextView, attr3ValueOverviewTextView, attr4ValueOverviewTextView, attr5ValueOverviewTextView, attr6ValueOverviewTextView;


    //the 2 imagebuttons displaying the arrows
    public ImageButton rightArrowImageButton, leftArrowImageButton;

    //first number is the position of the actual card, the second is the number of max cards
    public TextView firstNumberTextView, secondNumberTextView;

    //Imageview containing the Image of the car
    public ImageView cardPictureImageView;

    //textView containing the name of the card
    public TextView nameTextField;


    //arrays for easier value saving
    public TextView[] attributeValueOverviews;
    public TextView[] attributeOverviews;

    public Deck deck;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab2cardoverview, container, false);

        //initializing
        initialize(rootView);

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //setting up right dialogs for the first time when app is started
        deck = MainActivity.getDeck();
        Card initialCard = deck.getCards().get(0);

        //setting up right number of carddeck
        firstNumberTextView.setText("1");
        secondNumberTextView.setText(String.valueOf(deck.getCards().size()));
        cardPictureImageView.setBackgroundResource(R.drawable.bike1);


        nameTextField.setText(initialCard.getName());

        //load card stats into UI
        loadCard(initialCard);



        rightArrowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCard();
            }
        });

        leftArrowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousCard();
            }
        });

    }

    private void loadCard(Card card) {

        nameTextField.setText(card.getName());

        //setting up right properties with values
        int i = 0;
        NumberFormat nf = new DecimalFormat("##.##");
        for (Property p : deck.getProperties()) {
            attributeOverviews[i].setText(p.getText());
            for (Value v:card.getValues()) {
                if(v.getPropertyId() == p.getId()){
                    attributeValueOverviews[i].setText(String.valueOf(nf.format(v.getValue())));
                    attributeValueOverviews[i].append(" " + p.getUnit());
                }
            }
            i++;
        }
    }


    private void nextCard() {

        int currentPosition = Integer.parseInt(firstNumberTextView.getText().toString());
        int nextPosition;
        if(currentPosition == deck.getCards().size()) {
            nextPosition = 1;
        } else {
            nextPosition = currentPosition + 1;
        }

        firstNumberTextView.setText(String.valueOf(nextPosition));
        Card card = deck.getCards().get(nextPosition-1);
        loadCard(card);

    }

    private void previousCard() {

        int currentPosition = Integer.parseInt(firstNumberTextView.getText().toString());
        int nextPosition;
        if(currentPosition == 1) {
            nextPosition = deck.getCards().size();
        } else {
            nextPosition = currentPosition - 1;
        }

        firstNumberTextView.setText(String.valueOf(nextPosition));
        Card card = deck.getCards().get(nextPosition);
        loadCard(card);
    }


    public void initialize(View v){
        //declaring stuff
        //attribute names
        attr1OverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr1OverviewTextView);
        attr2OverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr2OverviewTextView);
        attr3OverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr3OverviewTextView);
        attr4OverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr4OverviewTextView);
        attr5OverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr5OverviewTextView);
        attr6OverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr6OverviewTextView);

        //save Textviews in array
        attributeOverviews = new TextView[6];
        attributeOverviews[0] = attr1OverviewTextView;
        attributeOverviews[1] = attr2OverviewTextView;
        attributeOverviews[2] = attr3OverviewTextView;
        attributeOverviews[3] = attr4OverviewTextView;
        attributeOverviews[4] = attr5OverviewTextView;
        attributeOverviews[5] = attr6OverviewTextView;

        //attribute values
        attr1ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr1ValueOverviewTextView);
        attr2ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr2ValueOverviewTextView);
        attr3ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr3ValueOverviewTextView);
        attr4ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr4ValueOverviewTextView);
        attr5ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr5ValueOverviewTextView);
        attr6ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr6ValueOverviewTextView);


        //save textview values in array
        attributeValueOverviews = new TextView[6];
        attributeValueOverviews[0] = attr1ValueOverviewTextView;
        attributeValueOverviews[1] = attr2ValueOverviewTextView;
        attributeValueOverviews[2] = attr3ValueOverviewTextView;
        attributeValueOverviews[3] = attr4ValueOverviewTextView;
        attributeValueOverviews[4] = attr5ValueOverviewTextView;
        attributeValueOverviews[5] = attr6ValueOverviewTextView;

        //cardimage
        cardPictureImageView = (ImageView) v.getRootView().findViewById(R.id.cardPictureInGameImageView);

        //cardname
        nameTextField = (TextView) v.getRootView().findViewById(R.id.nameTextView);

        //imagebuttons
        rightArrowImageButton = (ImageButton) v.getRootView().findViewById(R.id.rightArrowImageButton);
        leftArrowImageButton = (ImageButton) v.getRootView().findViewById(R.id.leftArrowImageButton);

        //scores
        firstNumberTextView = (TextView) v.getRootView().findViewById(R.id.firstNumberTextView);
        secondNumberTextView = (TextView) v.getRootView().findViewById(R.id.secondNumberTextView);





    }

}