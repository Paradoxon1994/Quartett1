package com.app.quartett.quartett2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jens on 04/01/2018.
 */

public class Player {
    private String name;
    private ArrayList<Card> cards;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Player(String name, ArrayList<Card> cards) {

        this.name = name;
        this.cards = cards;
    }

    public Player(String name){
        this.name = name;
    }

    public Card removeCard(){
        /*Card a=null;
        for(Iterator<Card> iter = cards.listIterator();iter.hasNext();){
            a =iter.next();
            if(a.getId()==id){
                iter.remove();
            }
        }
        return a;*/

        Card a = cards.get(0);
        cards.remove(0);
        return a;
    }


    public void addCard(Card card){
        cards.add(cards.size()-1,card);
    }
}
