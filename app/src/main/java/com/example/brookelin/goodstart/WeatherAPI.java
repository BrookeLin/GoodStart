package com.example.brookelin.goodstart;

/**
 * Created by aaronnspiewak on 3/20/17.
 */

public class WeatherAPI {

    public static final String API_KEY = "babfa7850fe7050f";
    public static final String ENDPOINT = "http://api.wunderground.com/api/" + API_KEY + "/geolookup/q/";

    public void getWeather(double latitude, double longitude){
        //make http request
            //set the URL
                //basically endpoint + coordinates
            //open http stream / make request
        //parse http response
    }
}
