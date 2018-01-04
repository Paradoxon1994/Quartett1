package com.app.quartett.quartett2.model;

/**
 * Created by Jens on 04/01/2018.
 */

public class Game {

    private int difficulty;
    private int maxRounds;
    private int roundTime;
    private int roundCounter;


    public Game(){

    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public Game(int difficulty, int maxRounds, int roundTime) {

        this.difficulty = difficulty;
        this.maxRounds = maxRounds;
        this.roundTime = roundTime;
    }
}
