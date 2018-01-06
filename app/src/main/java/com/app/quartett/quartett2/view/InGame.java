package com.app.quartett.quartett2.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.quartett.quartett2.GameController;
import com.app.quartett.quartett2.MainActivity;
import com.app.quartett.quartett2.MyPreferenceFragment;
import com.app.quartett.quartett2.R;
import com.app.quartett.quartett2.Settings;
import com.app.quartett.quartett2.model.Card;
import com.app.quartett.quartett2.model.Deck;
import com.app.quartett.quartett2.model.Game;
import com.app.quartett.quartett2.model.Player;
import com.app.quartett.quartett2.model.Property;
import com.app.quartett.quartett2.model.Value;

import org.w3c.dom.Text;

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
    public TextView numberOfRoundTextView;

    //continue button
    public Button continueButton;

    //arrays
    public ArrayList<TextView> attributeTextViews;
    public ArrayList<TextView> attributeValueTextViews;

    private Game game;
    private Player player;
    private Deck deck;
    private Player ki;

    private int playerSelection;

    boolean playerCardSelected = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingame);

        //initialization
        initialization(findViewById(android.R.id.content));


    }

    public void onStart(){
        super.onStart();
        //get stuff for starting game
        game = new Game(MyPreferenceFragment.getDifficulty(), MyPreferenceFragment.getNumberOfRounds(), MyPreferenceFragment.getSelectionTime());
        player = new Player("Name");
        deck = MainActivity.getLoadedDeck();
        startGame();

    }



    public boolean startGame(){

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

        Card playerCard;
        Card kiCard;
        Value playerValue=new Value();
        Value kiValue = new Value();
        Property property =new Property();


        Boolean playerWonRound=false;
        Boolean playersTurn=true;


        while (game.getRoundCounter()<game.getMaxRounds()&& player.getCards().size()!=0 &&ki.getCards().size()!=0){

            playerCard=player.getCards().get(0);
            kiCard=ki.getCards().get(0);


            if(playersTurn){
                //TODO: show card to player and get his selection + assign playerValue and kiValue
                //property =deck.getProperties().get(playerValue.getPropertyId());
                loadCard(playerCard);

            }else{
                //TODO: ki selection (possibly with difficulty settings kept in mind)

                //simple ki selection for testing(maybe easy setting because random):

                Random rand = new Random();
                int n = rand.nextInt(kiCard.getValues().size()-1);

                kiValue=kiCard.getValues().get(n);
                playerValue=playerCard.getValues().get(n);

                property =deck.getProperties().get(kiValue.getPropertyId());
            }


            //checking who won
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
            if(playerWonRound){
                player.addCard(ki.removeCard());
                Intent intent = new Intent(this, EndOfRound.class);
                intent.putExtra("playerCardName", playerCard.getName());
                intent.putExtra("kiCardName", kiCard.getName());
                intent.putExtra("playerCardImagePath", playerCard.getImages().get(0).getFilename());
                intent.putExtra("kiCardImagePath", kiCard.getImages().get(0).getFilename());
                intent.putExtra("property", property.getText());
                intent.putExtra("playerCardValue", playerValue.getValue());
                intent.putExtra("kiCardValue", kiValue.getValue());
                intent.putExtra("won", playerWonRound);
                startActivity(intent);

            }else if(!playerWonRound){
                ki.addCard(player.removeCard());
                player.addCard(ki.removeCard());
                Intent intent = new Intent(this, EndOfRound.class);
                intent.putExtra("playerCardName", playerCard.getName());
                intent.putExtra("kiCardName", kiCard.getName());
                intent.putExtra("playerCardImagePath", playerCard.getImages().get(0).getFilename());
                intent.putExtra("kiCardImagePath", kiCard.getImages().get(0).getFilename());
                intent.putExtra("property", property.getText());
                intent.putExtra("playerCardValue", playerValue.getValue());
                intent.putExtra("kiCardValue", kiValue.getValue());
                intent.putExtra("won", playerWonRound);
                startActivity(intent);
            }else{
                player.addCard(player.removeCard());
                ki.addCard(ki.removeCard());
            }

            if(playersTurn){
                playersTurn = false;
            } else {
                playersTurn = true;
            }

            //TODO: showing the player if he won or lost

            //TODO: get next card and repeat
        }
        //TODO: add stats to player profile with same name, possibly create stats file


        //returning if player won the game or not
        return true;
    }

    private int playerSelectedCard() {
        return 1;
    }

    public void loadCard(Card card) {

        //attr1OverviewTextView.setText(actualPicture);

        nameTextView.setText(card.getName());
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

        //card name
        nameTextView = (TextView) v.getRootView().findViewById(R.id.nameTextView);

        //card image
        cardPictureInGameImageView = (ImageView) v.getRootView().findViewById(R.id.cardNameTextView);

        //number of rounds
        numberOfRoundTextView = (TextView) v.getRootView().findViewById(R.id.numberOfRoundTextView);

        //continue
        continueButton = (Button) v.getRootView().findViewById(R.id.continueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSelection = playerSelectedCard();
            }
        });

    }
}
