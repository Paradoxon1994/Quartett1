package com.app.quartett.quartett2.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quartett.quartett2.MainActivity;
import com.app.quartett.quartett2.MyPreferenceFragment;
import com.app.quartett.quartett2.R;
import com.app.quartett.quartett2.model.Card;
import com.app.quartett.quartett2.model.Deck;
import com.app.quartett.quartett2.model.Game;
import com.app.quartett.quartett2.model.Player;
import com.app.quartett.quartett2.model.Property;
import com.app.quartett.quartett2.model.Value;



import junit.framework.Assert;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;


public class InGame extends AppCompatActivity{


    // Textviews including the attribute names
    public Button attr1TextView, attr2TextView, attr3TextView, attr4TextView, attr5TextView, attr6TextView;

    // Textviews including the values from the attribute names
    public TextView attr1ValueTextView, attr2ValueTextView, attr3ValueTextView, attr4ValueTextView, attr5ValueTextView, attr6ValueTextView;

    //cardname
    public TextView nameTextView;

    //imageview for the image of te given element
    public ImageView cardPictureInGameImageView;

    //number of the ongoing round
    public TextView numberOfRoundTextView, cardsLeftCounter;

    //continue button
    public ImageButton continueButton;

    //lists
    public ArrayList<TextView> attributeTextViews;
    public ArrayList<TextView> attributeValueTextViews;

    //imageviews upper/lower value
    public ImageView attr1ImageView, attr2ImageView, attr3ImageView, attr4ImageView, attr5ImageView, attr6ImageView;

    private Game game;
    private Player player;
    private Deck deck;
    private Player ki;

    private int playerSelection;


    Card playerCard;
    Card kiCard;
    Value playerValue;
    Value kiValue;
    Property property;

    int roundCounter=0;
    int roundsWonCounter=0;
    Boolean playerWonRound=false;
    Boolean playersTurn=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingame);

        //initialization
        initialization(findViewById(android.R.id.content));

        game = new Game(MyPreferenceFragment.getDifficulty(), MyPreferenceFragment.getNumberOfRounds(), MyPreferenceFragment.getSelectionTime());
        player = new Player("Name");
        deck = MainActivity.getLoadedDeck();
        ki=new Player("ki");
        ArrayList<Card> playerCards = new ArrayList<>();
        ArrayList<Card> kiCards = new ArrayList<>();

        for(int i=0;i<(deck.getCards().size()/2);i++){
            playerCards.add(deck.getCards().get(i));
        }

        for (int i=deck.getCards().size()/2;i<deck.getCards().size();i++){
            kiCards.add(deck.getCards().get(i));
        }

        player.setCards(playerCards);
        ki.setCards(kiCards);


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kiValue = kiCard.getValues().get(playerSelection-1);
                playerValue = playerCard.getValues().get(playerSelection-1);

                property = deck.getProperties().get(playerValue.getPropertyId());

                endOfRoundStuff();




            }
        });

        //TODO: choose another attribute and enable buttons

        attr1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr2TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                attr6TextView.setEnabled(false);

                playerSelection=1;
            }
        });


        attr2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                attr6TextView.setEnabled(false);
                playerSelection=2;
            }
        });
        attr3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setEnabled(false);
                attr2TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                attr6TextView.setEnabled(false);
                playerSelection=3;
            }
        });

        attr4TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setEnabled(false);
                attr2TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                attr6TextView.setEnabled(false);
                playerSelection=4;
            }
        });
        attr5TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setEnabled(false);
                attr2TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr6TextView.setEnabled(false);
                playerSelection=5;
            }
        });

        attr6TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setEnabled(false);
                attr2TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                playerSelection=6;
            }
        });








    }

    public void onStart(){
        super.onStart();
        //get stuff for starting game


        startRound();

    }



    public boolean startRound(){
        
        //while (game.getRoundCounter()<game.getMaxRounds()&& player.getCards().size()!=0 &&ki.getCards().size()!=0){
        
        if(roundCounter<game.getMaxRounds()&& player.getCards().size()!=0 &&ki.getCards().size()!=0) {

            //TODO: abbruchbedingung für max runden
            roundCounter++;
            numberOfRoundTextView.setText(Integer.toString(roundCounter));
            cardsLeftCounter.setText(Integer.toString(player.getCards().size()));
            //numberOfRoundTextView.setText( Integer.toString(roundCounter)+ " Cards left:  " + player.getCards().size());
            continueButton.setEnabled(false);
            attr1TextView.setEnabled(true);
            attr2TextView.setEnabled(true);
            attr3TextView.setEnabled(true);
            attr4TextView.setEnabled(true);
            attr5TextView.setEnabled(true);
            attr6TextView.setEnabled(true);
            playerCard = player.getCards().get(0);
            kiCard = ki.getCards().get(0);


            if (playersTurn) {
                //TODO: show card to player and get his selection + assign playerValue and kiValue
                //property =deck.getProperties().get(playerValue.getPropertyId());
                loadCard(playerCard);


            } else {
                //TODO: ki selection (possibly with difficulty settings kept in mind)

                //simple ki selection for testing(maybe easy setting because random):

                Random rand = new Random();
                int n = rand.nextInt(kiCard.getValues().size() - 1);

                kiValue = kiCard.getValues().get(n);

                playerValue = playerCard.getValues().get(n);

                property = deck.getProperties().get(kiValue.getPropertyId());

                //here

                endOfRoundStuff();


            }


            playersTurn = !playersTurn;



            //}
            //TODO: add stats to player profile with same name, possibly create stats file

        }else{
            showEndOfGameDialog();
        }

        //returning if player won the game or not
        return true;
    }

    private void showEndOfGameDialog() {
    }

    private void endOfRoundStuff() {
        if(property.getCompare()==1){
            if(playerValue.getValue()>kiValue.getValue()){
                playerWonRound=true;

            }else if(kiValue.getValue()>playerValue.getValue()){
                playerWonRound=false;
            }

        }else if(property.getCompare()==-1){
            if(playerValue.getValue()<kiValue.getValue()){
                playerWonRound=true;

            }else if(kiValue.getValue()<playerValue.getValue()){
                playerWonRound=false;
            }
        }

        //distributing cards win lose draw
        if(playerValue.getValue()==kiValue.getValue()){
            player.addCard(player.removeCard());
            ki.addCard(ki.removeCard());

            showEndOfRoundDialog("DRAW!");

        }
        else if(playerWonRound){
            player.addCard(ki.removeCard());
            player.addCard(player.removeCard());

            showEndOfRoundDialog("YOU WIN!");

        }else if(!playerWonRound){
            ki.addCard(player.removeCard());
            ki.addCard(ki.removeCard());

            showEndOfRoundDialog("YOU LOST!");
        }

    }

    private void showEndOfRoundDialog(String s) {
        Intent intent = new Intent(this, EndOfRound.class);
        intent.putExtra("playerCardName", playerCard.getName());
        intent.putExtra("kiCardName", kiCard.getName());
        intent.putExtra("playerCardImagePath", playerCard.getImages().get(0).getFilename());
        intent.putExtra("kiCardImagePath", kiCard.getImages().get(0).getFilename());
        intent.putExtra("property", property.getText());
        intent.putExtra("playerCardValue", Double.toString(playerValue.getValue()));

        intent.putExtra("kiCardValue", Double.toString(kiValue.getValue()));
        intent.putExtra("won", s);
        startActivity(intent);
    }



    public void loadCard(Card card) {

        //attr1OverviewTextView.setText(actualPicture);
        nameTextView.setText(card.getName());

        String uri = "@drawable/"+ deck.getName().toLowerCase()+playerCard.getImages().get(0).getFilename();
        uri= uri.substring(0,uri.lastIndexOf("."));
        int imageRes = getResources().getIdentifier(uri,null,getPackageName());

        Drawable res = getResources().getDrawable(imageRes);

        cardPictureInGameImageView.setImageDrawable(res);



        //setting up right properties with values
        int i = 0;
        NumberFormat nf = new DecimalFormat("##.##");
        for (Property p : MainActivity.getLoadedDeck().getProperties()) {
            attributeTextViews.get(i).setText(p.getText());
            for (Value v:card.getValues()) {
                if(v.getPropertyId() == p.getId()){
                    attributeValueTextViews.get(i).setText(String.valueOf(nf.format(v.getValue())));
                    attributeValueTextViews.get(i).append(" " + p.getUnit());
                }
            }
            i++;
        }


    }


    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }


    private void initialization(View v) {

        //atttribute name textviews
        attr1TextView = (Button) v.getRootView().findViewById(R.id.attr1TextView);
        attr2TextView = (Button) v.getRootView().findViewById(R.id.attr2TextView);
        attr3TextView = (Button) v.getRootView().findViewById(R.id.attr3TextView);
        attr4TextView = (Button) v.getRootView().findViewById(R.id.attr4TextView);
        attr5TextView = (Button) v.getRootView().findViewById(R.id.attr5TextView);
        attr6TextView = (Button) v.getRootView().findViewById(R.id.attr6TextView);

        attributeTextViews = new ArrayList<>();
        attributeTextViews.add(attr1TextView);
        attributeTextViews.add(attr2TextView);
        attributeTextViews.add(attr3TextView);
        attributeTextViews.add(attr4TextView);
        attributeTextViews.add(attr5TextView);
        attributeTextViews.add(attr6TextView);


        //attribute value textviews
        attr1ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr1ValueTextView);
        attr2ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr2ValueTextView);
        attr3ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr3ValueTextView);
        attr4ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr4ValueTextView);
        attr5ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr5ValueTextView);
        attr6ValueTextView = (TextView) v.getRootView().findViewById(R.id.attr6ValueTextView);


        attributeValueTextViews = new ArrayList<>();
        attributeValueTextViews.add(attr1ValueTextView);
        attributeValueTextViews.add(attr2ValueTextView);
        attributeValueTextViews.add(attr3ValueTextView);
        attributeValueTextViews.add(attr4ValueTextView);
        attributeValueTextViews.add(attr5ValueTextView);
        attributeValueTextViews.add(attr6ValueTextView);

        //imageViews for upper/lower value
        attr1ImageView = (ImageView) v.getRootView().findViewById(R.id.attr1ImageView);
        attr2ImageView = (ImageView) v.getRootView().findViewById(R.id.attr2ImageView);
        attr3ImageView = (ImageView) v.getRootView().findViewById(R.id.attr3ImageView);
        attr4ImageView = (ImageView) v.getRootView().findViewById(R.id.attr4ImageView);
        attr5ImageView = (ImageView) v.getRootView().findViewById(R.id.attr5ImageView);
        attr6ImageView = (ImageView) v.getRootView().findViewById(R.id.attr6ImageView);

        //card name
        nameTextView = (TextView) v.getRootView().findViewById(R.id.nameTextView);

        //card image
        cardPictureInGameImageView = (ImageView) v.getRootView().findViewById(R.id.cardPictureInGameImageView);

        //number of rounds
        numberOfRoundTextView = (TextView) v.getRootView().findViewById(R.id.numberOfRoundTextView);

        //cards left
        cardsLeftCounter = (TextView) v.getRootView().findViewById(R.id.cardsLeftCounter);

        //continue
        continueButton = (ImageButton) v.getRootView().findViewById(R.id.continueButton);



    }
}
