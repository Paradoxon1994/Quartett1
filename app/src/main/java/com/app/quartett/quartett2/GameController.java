package com.app.quartett.quartett2;

import com.app.quartett.quartett2.model.Card;
import com.app.quartett.quartett2.model.Deck;
import com.app.quartett.quartett2.model.Game;
import com.app.quartett.quartett2.model.Player;
import com.app.quartett.quartett2.model.Property;

import java.util.ArrayList;

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



        Card playerCard;
        Card kiCard;
        Property playerProperty;
        Property kiProperty;


        while (game.getRoundCounter()<game.getMaxRounds()&& player.getCards().size()!=0 &&ki.getCards().size()!=0){
            //TODO: check if -1 is right
            playerCard=player.getCards().get(player.getCards().size()-1);
            kiCard=ki.getCards().get(ki.getCards().size()-1);


            //TODO: show card to player and get his selection

            //TODO: checking who won

            /*TODO: removing card from player who lost and adding the same card to player who won
                    functions for that already implemented
            */

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
