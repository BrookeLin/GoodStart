package com.example.brookelin.goodstart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFrag extends Fragment {


    public WeatherFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       /* ImageView bottomsImage = (ImageView) View.findViewById(R.id.imageView5);
        bottomsImage.setImageResource(R.drawable.pants);*/

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }







}