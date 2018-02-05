package com.app.quartett.quartett2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

public class MyPreferenceFragment extends PreferenceFragment
{

    //numberpickers of the rounds and time
    private static NumberPickerPreference numberOfRounds, selectionTime;

    //difficultyPreference
    private static ListPreference difficultyPreference;

    //checkbox for notification
    private static CheckBoxPreference notification;




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

        if(difficultyPreference==null){
           return 2;
        }else{
            if(difficultyPreference.getValue().equals("EASY")) {
                return 1;
            } else if (difficultyPreference.getValue().equals("MEDIUM")){
                return 2;
            } else {
                return 3;
            }

        }


    }

    public static int getNumberOfRounds() {

        if(numberOfRounds==null){
            return 30;
        }else{
            return numberOfRounds.getValue();
        }


        }

    public static int getSelectionTime() {

        if(selectionTime==null){
            return 10;
        }else{
            return selectionTime.getValue();
        }



    }

    //TODO: see if notification is on/off
    public CharSequence getNotification() { return notification.getTitle(); }

    private void initialization(){
        numberOfRounds = (NumberPickerPreference) findPreference("numberOfRounds");
        selectionTime = (NumberPickerPreference) findPreference("selectionTime");
        difficultyPreference = (ListPreference) findPreference("difficultyPreference");
        notification = (CheckBoxPreference) findPreference("notification");


    }
}