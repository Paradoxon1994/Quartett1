package com.app.quartett.quartett2.model;

/**
 * Created by Jens on 02/01/2018.
 */

public class Property {
    private String text;
    private int compare;
    private int id;
    private String unit;
    private int precision;

    @Override
    public String toString() {
        return "Property{" +
                "text='" + text + '\'' +
                ", compare=" + compare +
                ", id=" + id +
                ", unit='" + unit + '\'' +
                ", precision=" + precision +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCompare() {
        return compare;
    }

    public void setCompare(int compare) {
        this.compare = compare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Property(String text, int compare, int id, String unit, int precision) {

        this.text = text;
        this.compare = compare;
        this.id = id;
        this.unit = unit;
        this.precision = precision;
    }
}
