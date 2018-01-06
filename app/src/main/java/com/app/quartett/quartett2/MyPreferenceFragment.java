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

        //init stuff
        initialization();

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragment_preference);








    }

    public String getDifficulty() {
        return difficultyPreference.getValue();
    }

    public int getNumberOfRounds() { return numberOfRounds.getValue(); }

    public int getSelectionTime() { return selectionTime.getValue(); }

    //TODO: see if notification is on/off
    public CharSequence getNotification() { return notification.getTitle(); }

    private void initialization(){
        numberOfRounds = (NumberPickerPreference) getPreferenceManager().findPreference("numberOfRounds");
        selectionTime = (NumberPickerPreference) getPreferenceManager().findPreference("selectionTime");
        difficultyPreference = (ListPreference) getPreferenceManager().findPreference("difficultyPreference");
        notification = (CheckBoxPreference) getPreferenceManager().findPreference("notification");

        //difficultyPreference.setValue("Medium");
    }
}