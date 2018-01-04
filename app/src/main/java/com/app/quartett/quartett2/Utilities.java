package com.app.quartett.quartett2;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jens on 02/01/2018.
 */

public class Utilities {


    public static String loadJSONFromAsset(Context context,String deckName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(deckName+".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



}
