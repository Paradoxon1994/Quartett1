package com.app.quartett.quartett2;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Tab2CardOverview extends Fragment{


    // Textviews including the attribute names
    public TextView attr1OverviewTextView, attr2OverviewTextView, attr3OverviewTextView, attr4OverviewTextView, attr5OverviewTextView;

    // Textviews including the values from the attribute names
    public TextView attr1ValueOverviewTextView, attr2ValueOverviewTextView, attr3ValueOverviewTextView, attr4ValueOverviewTextView, attr5ValueOverviewTextView;


    //the 2 imagebuttons displaying the arrows
    public ImageButton rightArrowImageButton, leftArrowImageButton;

    //first number is the position of the actual card, the second is the number of max cards
    public TextView firstNumberTextView, secondNumberTextView;

    //Imageview containing the Image of the car
    public ImageView carPictureImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab2cardoverview, container, false);
        return rootView;
    }

}