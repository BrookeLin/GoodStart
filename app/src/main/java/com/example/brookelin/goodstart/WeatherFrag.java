package com.example.brookelin.goodstart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brookelin.goodstart.weatherapi.CurrentObservation;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFrag extends Fragment {

    public FindLocation cityStateInfo;

    public TextView cityState;
    public String cityname;
    public CurrentObservation curobs;

    public WeatherFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       /* ImageView bottomsImage = (ImageView) View.findViewById(R.id.imageView5);
        bottomsImage.setImageResource(R.drawable.pants);*/


        // Inflate the layout for this fragment
        View weather = inflater.inflate(R.layout.fragment_weather, container, false);

        //Get variables from location activity
        //Bundle bundle = getArguments();
        //cityname= ((MainActivity)getActivity()).getCityState();

        //cityname= mApp.getaddress();
       // cityState.setText(cityname + "\n");

       // curobs=mApp.getcurrobs();

        return weather;
    }

}