package com.app.quartett.quartett2.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

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

import org.w3c.dom.Text;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
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

    private int numberOfProperties;


    Card playerCard;
    Card kiCard;
    Value playerValue;
    Value kiValue;
    Property property;

    int roundCounter=0;
    int roundsWonCounter=0;
    Boolean playerWonRound=false;
    Boolean playersTurn=true;





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
                attr1TextView.setBackgroundColor(Color.RED);
                attr2TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr3TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr4TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr5TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr6TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));



                /*attr1TextView.setPressed(true);
                attr2TextView.setPressed(false);
                attr3TextView.setPressed(false);
                attr4TextView.setPressed(false);
                attr5TextView.setPressed(false);
                attr6TextView.setPressed(false);*/
                /*attr2TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                attr6TextView.setEnabled(false);*/

                playerSelection=1;
            }
        });


        attr2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr2TextView.setBackgroundColor(Color.RED);
                attr3TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr4TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr5TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr6TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                /*attr1TextView.setPressed(false);
                attr2TextView.setPressed(true);
                attr3TextView.setPressed(false);
                attr4TextView.setPressed(false);
                attr5TextView.setPressed(false);
                attr6TextView.setPressed(false);*/
                /*attr1TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                attr6TextView.setEnabled(false);*/
                playerSelection=2;
            }
        });
        attr3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr2TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr3TextView.setBackgroundColor(Color.RED);
                attr4TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr5TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr6TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));

                /*attr1TextView.setPressed(false);
                attr2TextView.setPressed(false);
                attr3TextView.setPressed(true);
                attr4TextView.setPressed(false);
                attr5TextView.setPressed(false);
                attr6TextView.setPressed(false);*/
               /* attr1TextView.setEnabled(false);
                attr2TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                attr6TextView.setEnabled(false);*/
                playerSelection=3;
            }
        });

        attr4TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr2TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr3TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr4TextView.setBackgroundColor(Color.RED);
                attr5TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr6TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                /*attr1TextView.setPressed(false);
                attr2TextView.setPressed(false);
                attr3TextView.setPressed(false);
                attr4TextView.setPressed(true);
                attr5TextView.setPressed(false);
                attr6TextView.setPressed(false);*/
                /*attr1TextView.setEnabled(false);
                attr2TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr5TextView.setEnabled(false);
                attr6TextView.setEnabled(false);*/
                playerSelection=4;
            }
        });
        attr5TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr2TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr3TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr4TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr5TextView.setBackgroundColor(Color.RED);
                attr6TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                /*attr1TextView.setPressed(false);
                attr2TextView.setPressed(false);
                attr3TextView.setPressed(false);
                attr4TextView.setPressed(false);
                attr5TextView.setPressed(true);
                attr6TextView.setPressed(false);*/
                /*attr1TextView.setEnabled(false);
                attr2TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr6TextView.setEnabled(false);*/
                playerSelection=5;
            }
        });

        attr6TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.setEnabled(true);
                attr1TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr2TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr3TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr4TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr5TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
                attr6TextView.setBackgroundColor(Color.RED);
                /*attr1TextView.setPressed(false);
                attr2TextView.setPressed(false);
                attr3TextView.setPressed(false);
                attr4TextView.setPressed(false);
                attr5TextView.setPressed(false);
                attr6TextView.setPressed(true);*/
                /*attr1TextView.setEnabled(false);
                attr2TextView.setEnabled(false);
                attr3TextView.setEnabled(false);
                attr4TextView.setEnabled(false);
                attr5TextView.setEnabled(false);*/
                playerSelection=6;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    public void onStart(){
        super.onStart();
        //get stuff for starting game

        startRound();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void startRound(){



        int numberOfProperties = MainActivity.getLoadedDeck().getProperties().size();
        for(int i=0; i < numberOfProperties; i++){
            attributeTextViews.get(i).setEnabled(true);
            attributeValueTextViews.get(i).setEnabled(true);
        }

        if(roundCounter<game.getMaxRounds()&& player.getCards().size()!=0 &&ki.getCards().size()!=0) {


            roundCounter++;
            numberOfRoundTextView.setText("R:" + Integer.toString(roundCounter));
            cardsLeftCounter.setText(Integer.toString(player.getCards().size()));
            //numberOfRoundTextView.setText( Integer.toString(roundCounter)+ " Cards left:  " + player.getCards().size());
            continueButton.setEnabled(false);
            attr1TextView.setEnabled(true);
            attr2TextView.setEnabled(true);
            attr3TextView.setEnabled(true);
            attr4TextView.setEnabled(true);
            attr5TextView.setEnabled(true);
            attr6TextView.setEnabled(true);
            attr1TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
            attr2TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
            attr3TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
            attr4TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
            attr5TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));
            attr6TextView.setBackgroundColor(Color.parseColor("#ff0099cc"));


            playerCard = player.getCards().get(0);
            kiCard = ki.getCards().get(0);


            if (playersTurn) {

                //property =deck.getProperties().get(playerValue.getPropertyId());
                loadCard(playerCard);


            } else {



                switch (game.getDifficulty()){
                    case 1:
                        difficultyEasy();
                        break;
                    case 2:
                        difficultyMedium();
                        break;
                    case 3:
                        difficultyHard();
                        break;
                }
                endOfRoundStuff();
            }


            //determine who is next







        }else{
            if(player.getCards().size()>ki.getCards().size()){
                showEndOfGameDialog("YOU WON");
                MainActivity.numberOfGames++;
                MainActivity.numberOfGamesWon++;

                MainActivity.allCards+=player.getCards().size();
                MainActivity.avgCards=MainActivity.allCards/MainActivity.numberOfGames;


            }else if(player.getCards().size()<ki.getCards().size()){
                showEndOfGameDialog("YOU LOST");
                MainActivity.numberOfGames++;
                MainActivity.allCards+=player.getCards().size();
                MainActivity.avgCards=MainActivity.allCards/MainActivity.numberOfGames;
            }else{
                showEndOfGameDialog("DRAW");
                MainActivity.numberOfGames++;
                MainActivity.allCards+=player.getCards().size();
                MainActivity.avgCards=MainActivity.allCards/MainActivity.numberOfGames;
            }


        }

        //returning if player won the game or not

    }
    //hard difficulty
    private void difficultyHard() {

        Random rand = new Random();
        int n = 0;

        int prob = rand.nextInt(100);
        kiValue = kiCard.getValues().get(n);
        playerValue = playerCard.getValues().get(n);

        if (prob > 80) {
            while (playerValue.getValue() < kiValue.getValue() && n<kiCard.getValues().size()-1) {

                n++;
                kiValue = kiCard.getValues().get(n);
                playerValue = playerCard.getValues().get(n);
            }
        } else {
            while (playerValue.getValue() >= kiValue.getValue()  && n<kiCard.getValues().size()-1) {
                n++;
                kiValue = kiCard.getValues().get(n);
                playerValue = playerCard.getValues().get(n);
            }

        }
            property = deck.getProperties().get(kiValue.getPropertyId());



    }

    //medium difficulty
    private void difficultyMedium() {

        Random rand = new Random();
        int n = 0;
        int prob = rand.nextInt(100);
        kiValue = kiCard.getValues().get(n);
        playerValue = playerCard.getValues().get(n);

        if (prob > 50) {
            while (playerValue.getValue() < kiValue.getValue()&& n<kiCard.getValues().size()-1) {
                n++;
                kiValue = kiCard.getValues().get(n);
                playerValue = playerCard.getValues().get(n);
            }
        } else {
            while (playerValue.getValue() >= kiValue.getValue()&& n<kiCard.getValues().size()-1) {
                n++;
                kiValue = kiCard.getValues().get(n);
                playerValue = playerCard.getValues().get(n);
            }
        }
            property = deck.getProperties().get(kiValue.getPropertyId());
            //determine who chooses property


    }

    //easy difficulty
    private void difficultyEasy() {
        Random rand = new Random();
        int n = 0;
        int prob = rand.nextInt(100);
        kiValue = kiCard.getValues().get(n);
        playerValue = playerCard.getValues().get(n);

        if (prob > 15) {
            while (playerValue.getValue() < kiValue.getValue()&& n<kiCard.getValues().size()-1) {
                n++;
                kiValue = kiCard.getValues().get(n);
                playerValue = playerCard.getValues().get(n);
            }
        } else {
            while (playerValue.getValue() >= kiValue.getValue()&& n<kiCard.getValues().size()-1) {
                n++;
                kiValue = kiCard.getValues().get(n);
                playerValue = playerCard.getValues().get(n);
            }
        }
            property = deck.getProperties().get(kiValue.getPropertyId());
            //determine who chooses property


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
            roundsWonCounter++;
            showEndOfRoundDialog("YOU WON!");

        }else if(!playerWonRound){
            ki.addCard(player.removeCard());
            ki.addCard(ki.removeCard());
            showEndOfRoundDialog("YOU LOST!");
        }

        if(!playerWonRound){
            playersTurn=false;
        }else {
            playersTurn= true;
        }




    }

    private void showEndOfGameDialog(String s) {

        Intent intent = new Intent(this,EndOfGame.class);

        intent.putExtra("roundsPlayed",Integer.toString(roundCounter));
        intent.putExtra("playerCardsLeft",Integer.toString(player.getCards().size()));
        intent.putExtra("kiCardsLeft",Integer.toString(ki.getCards().size()));
        intent.putExtra("roundsWon",Integer.toString(roundsWonCounter));
        intent.putExtra("won",s);


        startActivity(intent);
        finish();

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
        String uri="";
        if(playerCard.getImages().get(0)!=null){
            uri = "@drawable/"+ deck.getName().toLowerCase()+playerCard.getImages().get(0).getFilename();

        }

        uri= uri.substring(0,uri.lastIndexOf("."));
        int imageRes = getResources().getIdentifier(uri,null,getPackageName());

        Drawable res = getResources().getDrawable(imageRes);

        cardPictureInGameImageView.setImageDrawable(res);




        if(deck.getProperties().get(0).getCompare()==1){
            attr1ImageView.setImageResource(R.drawable.uparrow);
        }else{
            attr1ImageView.setImageResource(R.drawable.downarrow);
        }
        if(deck.getProperties().get(1).getCompare()==1){
            attr2ImageView.setImageResource(R.drawable.uparrow);
        }else{
            attr2ImageView.setImageResource(R.drawable.downarrow);
        }
        if(deck.getProperties().get(2).getCompare()==1){
            attr3ImageView.setImageResource(R.drawable.uparrow);
        }else{
            attr3ImageView.setImageResource(R.drawable.downarrow);
        }
        if(deck.getProperties().get(3).getCompare()==1){
            attr4ImageView.setImageResource(R.drawable.uparrow);
        }else{
            attr4ImageView.setImageResource(R.drawable.downarrow);
        }
        if(deck.getProperties().get(4).getCompare()==1){
            attr5ImageView.setImageResource(R.drawable.uparrow);
        }else{
            attr5ImageView.setImageResource(R.drawable.downarrow);
        }
        if(deck.getProperties().size()>5){
            if(deck.getProperties().get(5).getCompare()==1){
                attr6ImageView.setImageResource(R.drawable.uparrow);
            }else{
                attr6ImageView.setImageResource(R.drawable.downarrow);
            }
        }

        if(MainActivity.getLoadedDeck().getProperties().size()<=5){
            attr6TextView.setVisibility(View.INVISIBLE);
            attr6ValueTextView.setText("");

        }


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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
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

        for(int j=0; j < 5; j++){
            attributeValueTextViews.get(j).setEnabled(false);
            attributeTextViews.get(j).setEnabled(false);
        }

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
