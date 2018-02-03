package com.app.quartett.quartett2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.RadioButton;



public class Settings extends Activity{

    //numberpickers for user to select stuff
    public NumberPicker numberOfRoundsNumberPicker, RoundTimeNumberPicker;

    //radiobuttons for difficultyPreference
    public RadioButton easyRadioButton, mediumRadioButton, difficultRadioButton;

    //radiobutton for notification
    public RadioButton notificationRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addPreferencesFromResource(R.xml.fragment_preference);


    }


}
