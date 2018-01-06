package com.app.quartett.quartett2;

import android.content.Intent;

import com.app.quartett.quartett2.model.Card;
import com.app.quartett.quartett2.model.Deck;
import com.app.quartett.quartett2.model.Game;
import com.app.quartett.quartett2.model.Player;
import com.app.quartett.quartett2.model.Property;
import com.app.quartett.quartett2.model.Value;
import com.app.quartett.quartett2.view.InGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jens on 04/01/2018.
 */

public class GameController {

    private Game game;
    private Player player;
    private Deck deck;
    private Player ki;

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
        Boolean playersTurn=false;


        while (game.getRoundCounter()<game.getMaxRounds()&& player.getCards().size()!=0 &&ki.getCards().size()!=0){

            playerCard=player.getCards().get(0);
            kiCard=ki.getCards().get(0);

            if(playersTurn){
                //TODO: show card to player and get his selection + assign playerValue and kiValue
                //property =deck.getProperties().get(playerValue.getPropertyId());
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

            }else if(!playerWonRound){
                ki.addCard(player.removeCard());
            }else{
                player.addCard(player.removeCard());
                ki.addCard(ki.removeCard());
            }

            //TODO: showing the player if he won or lost

            //TODO: get next card and repeat
        }
        //TODO: add stats to player profile with same name, possibly create stats file


        //returning if player won the game or not
        return true;
    }


    public GameController(Game game, Player player, Deck deck) {
        this.game = game;
        this.player = player;
        this.deck = deck;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Deck getDeck() {
        return deck;
    }
}
