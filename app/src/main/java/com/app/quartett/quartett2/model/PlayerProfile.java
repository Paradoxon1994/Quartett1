package com.app.quartett.quartett2.model;

/**
 * Created by Jens on 04/01/2018.
 */

public class PlayerProfile {
    private String name;
    private int numberOfCards;
    private int numberOfGamesPlayed;
    private int numberOfGamesWon;
    private double avgCards;

    public PlayerProfile(String name) {
        this.name = name;
    }

    public PlayerProfile(){

    }

    @Override
    public String toString() {
        return "PlayerProfile{" +
                "name='" + name + '\'' +
                ", numberOfCards=" + numberOfCards +
                ", numberOfGamesPlayed=" + numberOfGamesPlayed +
                ", numberOfGamesWon=" + numberOfGamesWon +
                ", avgCards=" + avgCards +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
        this.numberOfGamesPlayed = numberOfGamesPlayed;
    }

    public int getNumberOfGamesWon() {
        return numberOfGamesWon;
    }

    public void setNumberOfGamesWon(int numberOfGamesWon) {
        this.numberOfGamesWon = numberOfGamesWon;
    }

    public double getAvgCards() {
        return avgCards;
    }

    public void setAvgCards(double avgCards) {
        this.avgCards = avgCards;
    }
}
