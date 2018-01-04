package com.app.quartett.quartett2;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import java.util.List;

public class MyPreferenceActivity extends Activity {


    //Code that creates separate Header for Fragments
    /*@Override
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.fragment_preference, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName)
    {
        return MyPreferenceFragment.class.getName().equals(fragmentName);
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPreferenceFragment())
                .commit();


    }

}
