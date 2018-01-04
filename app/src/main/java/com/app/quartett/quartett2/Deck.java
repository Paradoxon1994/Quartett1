package com.app.quartett.quartett2;

import java.util.List;

/**
 * Created by Jens on 02/01/2018.
 */

public class Deck {

    private String name;
    private String description;
    private List<Card> cards;
    private List<Property> properties;

    public Deck(String name, String description, List<Card> cards, List<Property> properties) {
        this.name = name;
        this.description = description;
        this.cards = cards;
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cards=" + cards +
                ", properties=" + properties +
                '}';
    }

    public Deck (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
