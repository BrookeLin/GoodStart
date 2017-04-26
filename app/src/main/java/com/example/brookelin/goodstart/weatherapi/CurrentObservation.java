package com.example.brookelin.goodstart.weatherapi;

/**
 * Created by aaronn spiewak on 4/20/17.
 */

public class CurrentObservation {


    /* Temperature in F */
    public double temp_f;

    public double feelslike_f;

    public String relative_humidity;

    /* Basic weather string (e.g. "Partly Cloudy") */
    public String weather;

    public String wind_string;

    public double getTempF() {

        return temp_f;
    }

    public double getFeelsLikeF() {return feelslike_f;}

    public String getRelativeHumidity() {

        return relative_humidity;
    }

    public String getWindString() {

        return wind_string;
    }

    public String getWeather() {

        return weather;
    }
}
