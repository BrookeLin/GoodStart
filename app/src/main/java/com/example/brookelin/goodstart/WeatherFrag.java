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

    //public FindLocation cityStateInfo;

    public String cityname;
    public TextView address;
    //public CurrentObservation curobs;

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
        address= (TextView) weather.findViewById(R.id.cityState);

        cityname= "600 S Clyde Morris Blvd Daytona Beach, FL 32114";
                //gpsIntent.returnCit();

        //Get variables from location activity
        //Bundle bundle = getArguments();
        //cityname= ((MainActivity)getActivity()).getCityState();

       address.setText(cityname+"\n");


        return weather;
    }

}