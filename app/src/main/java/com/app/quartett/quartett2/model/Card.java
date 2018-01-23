package com.app.quartett.quartett2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jens on 02/01/2018.
 */

public class Card {

    private int id;
    private String name;
    private ArrayList<Value> values;
    private ArrayList<Image> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(ArrayList<Value> values) {
        this.values = values;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public Card(int id, String name, ArrayList<Value> values, ArrayList<Image> images) {

        this.id = id;
        this.name = name;
        this.values = values;
        this.images = images;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", values=" + values +
                ", images=" + images +
                '}';
    }

    public Card() {
        images = new ArrayList<>();
        values = new ArrayList<>();

    }
}
