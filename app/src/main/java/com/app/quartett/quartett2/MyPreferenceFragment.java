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
    private NumberPickerPreference numberOfRounds, selectionTime;

    //difficultyPreference
    private ListPreference difficultyPreference;

    //checkbox for notification
    private CheckBoxPreference notification;




    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragment_preference);

        //init stuff
        initialization();
    }

    public static int getDifficulty() {
        /*if(difficultyPreference.getValue().equals("EASY")) {
            return 1;
        } else if (difficultyPreference.getValue().equals("MEDIUM")){
            return 2;
        } else {
            return 3;
        }*/
        return 1;
    }

    public static int getNumberOfRounds() {
        //return numberOfRounds.getValue();
        return 30;
        }

    public static int getSelectionTime() {
        //return selectionTime.getValue();
        return 10;
    }

    //TODO: see if notification is on/off
    public CharSequence getNotification() { return notification.getTitle(); }

    private void initialization(){
        numberOfRounds = (NumberPickerPreference) findPreference("numberOfRounds");
        selectionTime = (NumberPickerPreference) findPreference("selectionTime");
        difficultyPreference = (ListPreference) findPreference("difficultyPreference");
        notification = (CheckBoxPreference) findPreference("notification");


        //difficultyPreference.setValue("Medium");
    }
}