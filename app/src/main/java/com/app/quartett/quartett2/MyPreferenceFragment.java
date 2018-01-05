package com.app.quartett.quartett2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

public class MyPreferenceFragment extends PreferenceFragment
{

    //numberpickers of the rounds and time
    public NumberPickerPreference numberOfRounds, selectionTime;

    //difficultyPreference
    public ListPreference difficultyPreference;

    //checkbox for notification
    public CheckBoxPreference notification;




    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragment_preference);

        /*if(selectionTime != null){
            test.setText(String.valueOf(selectionTime.getValue()));
        }*/




    }

    public String getDifficulty() {
        return difficultyPreference.getValue();
    }
}