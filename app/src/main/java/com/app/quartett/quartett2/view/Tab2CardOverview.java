package com.app.quartett.quartett2.view;


import android.content.Context;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Vibrator;
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
import com.app.quartett.quartett2.ShakeDetector;
import com.app.quartett.quartett2.model.Card;
import com.app.quartett.quartett2.model.Property;
import com.app.quartett.quartett2.model.Value;

import junit.framework.Assert;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

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
    public ArrayList<TextView> attributeValueOverviews;
    public ArrayList<TextView> attributeOverviews;

    public String actualPicture;

    //shake-stuff
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    //cardoverview number
    private int currentPosition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // ShakeDetector initialization
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        try{
            mAccelerometer = mSensorManager
                    .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } catch (Exception e){
            e.printStackTrace();
        }

        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                handleShakeEvent(count);
            }
        });

        //TODO: change
        actualPicture = "bikes";
        View rootView = inflater.inflate(R.layout.tab2cardoverview, container, false);

        //initializing
        initialize(rootView);

        //setting up right dialogs for the first time when app is started
        Card initialCard = MainActivity.getLoadedDeck().getCards().get(0);

        Categories.setSelectedDeck("bikes");

        //setting up right number of carddeck
        firstNumberTextView.setText("1");
        secondNumberTextView.setText(String.valueOf(MainActivity.getLoadedDeck().getCards().size()));
        cardPictureImageView.setImageResource(R.drawable.bikes1);

        //nameTextField.setText(initialCard.getName());

        //load card stats into UI
        loadCard(initialCard);

        return rootView;
    }

    @Override
    public void onStart() {

        super.onStart();


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


    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public void onResume(){

        super.onResume();

        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);

        if (Categories.switchedDecks) {
            Categories.switchedDecks = false;
            secondNumberTextView.setText(String.valueOf(MainActivity.getLoadedDeck().getCards().size()));
            firstNumberTextView.setText("1");
            if(MainActivity.getLoadedDeck().getCards().size()!= 0 ){
                loadCard(MainActivity.getLoadedDeck().getCards().get(0));
            }

            if (Categories.getSelectedDeck() .equals("bikes")) {
                cardPictureImageView.setImageResource(R.drawable.bikes1);
            } else if(Categories.getSelectedDeck().equals("tuning") ) {
                cardPictureImageView.setImageResource(R.drawable.tuning1);
            }else{
                cardPictureImageView.setImageResource(R.drawable.bettsport1);
            }
        }
    }


    private void handleShakeEvent(int count) {
        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        int numberOfCards = MainActivity.getLoadedDeck().getCards().size();
        int n = new Random().nextInt(numberOfCards-1);
        Card c = MainActivity.getLoadedDeck().getCards().get(n);
        currentPosition = n+1;
        actualPicture = Categories.getSelectedDeck();
        actualPicture += n;
        cardPictureImageView.setImageResource(getDrawable(getContext(), actualPicture));
        firstNumberTextView.setText(String.valueOf(currentPosition));
        loadCard(c);
    }

    public void loadCard(Card card) {

        nameTextField.setText(card.getName());
        //setting up right properties with values
        int i = 0;
        NumberFormat nf = new DecimalFormat("##.##");
        for (Property p : MainActivity.getLoadedDeck().getProperties()) {
            attributeOverviews.get(i).setText(p.getText());
            for (Value v:card.getValues()) {
                if(v.getPropertyId() == p.getId()){
                    attributeValueOverviews.get(i).setText(String.valueOf(nf.format(v.getValue())));
                    attributeValueOverviews.get(i).append(" " + p.getUnit());
                }
            }
            i++;
        }
        if(MainActivity.getLoadedDeck().getProperties().size()<=5){
            attr6OverviewTextView.setText("");
            attr6ValueOverviewTextView.setText("");
        }
    }


    private void nextCard() {

        currentPosition = Integer.parseInt(firstNumberTextView.getText().toString());
        int nextPosition;
        if(currentPosition == MainActivity.getLoadedDeck().getCards().size()) {
            nextPosition = 1;
        } else {
            nextPosition = currentPosition + 1;
        }

        //selecting the correct picture
        actualPicture = Categories.getSelectedDeck();
        actualPicture += nextPosition;
        cardPictureImageView.setImageResource(getDrawable(getContext(), actualPicture));
        firstNumberTextView.setText(String.valueOf(nextPosition));
        Card nextCard = MainActivity.getLoadedDeck().getCards().get(nextPosition-1);
        loadCard(nextCard);
    }

    private void previousCard() {

        currentPosition = Integer.parseInt(firstNumberTextView.getText().toString());
        int nextPosition;
        if(currentPosition == 1) {
            nextPosition = MainActivity.getLoadedDeck().getCards().size();
        } else {
            nextPosition = currentPosition - 1;
        }

        //selection the correct picture
        actualPicture = Categories.getSelectedDeck();
        actualPicture += nextPosition;
        cardPictureImageView.setImageResource(getDrawable(getContext(), actualPicture));
        firstNumberTextView.setText(String.valueOf(nextPosition));
        Card previousCard = MainActivity.getLoadedDeck().getCards().get(nextPosition-1);
        loadCard(previousCard);
    }


    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
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
        attributeOverviews = new ArrayList<>();
        attributeOverviews.add(attr1OverviewTextView);
        attributeOverviews.add(attr2OverviewTextView);
        attributeOverviews.add(attr3OverviewTextView);
        attributeOverviews.add(attr4OverviewTextView);
        attributeOverviews.add(attr5OverviewTextView);
        attributeOverviews.add(attr6OverviewTextView);

        //attribute values
        attr1ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr1ValueOverviewTextView);
        attr2ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr2ValueOverviewTextView);
        attr3ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr3ValueOverviewTextView);
        attr4ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr4ValueOverviewTextView);
        attr5ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr5ValueOverviewTextView);
        attr6ValueOverviewTextView = (TextView) v.getRootView().findViewById(R.id.attr6ValueOverviewTextView);


        //save textview values in array
        attributeValueOverviews = new ArrayList<>();
        attributeValueOverviews.add(attr1ValueOverviewTextView);
        attributeValueOverviews.add(attr2ValueOverviewTextView);
        attributeValueOverviews.add(attr3ValueOverviewTextView);
        attributeValueOverviews.add(attr4ValueOverviewTextView);
        attributeValueOverviews.add(attr5ValueOverviewTextView);
        attributeValueOverviews.add(attr6ValueOverviewTextView);

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

        actualPicture = MainActivity.deckName;
    }

}