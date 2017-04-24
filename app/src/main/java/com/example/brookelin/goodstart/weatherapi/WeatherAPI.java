package com.example.brookelin.goodstart.weatherapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;

/**
 * Created by aaronnspiewak on 3/20/17.
 */
public class WeatherAPI {

    /**
     * The constant API_KEY.
     */
    public static final String API_KEY = "babfa7850fe7050f";
    /**
     * The constant ENDPOINT.
     */
    public static final String ENDPOINT = "http://api.wunderground.com/api/" + API_KEY + "/conditions/q/";

    /**
     * The entry point of application.
     *
     * @param a the input arguments
     * @throws Exception the exception
     */
// TODO TESTING ONLY
    /*
     * Main method called to access other class methods to obtain weather data
     */
    public static void main(String[] a) throws Exception {
        CurrentObservation obs = getWeather(38.000, -84.000);
        System.out.println(obs.getTempF());
        System.out.println(obs.getWeather());

    }

    /**
     * Gets weather.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @throws Exception the exception
     */
/*
     *Obtains weather data based upon  coordinates.
     * @param latitude  Lattitude coordinate of target weather destination
     * @param longitude Longitudal coordinate of target weather destination
     * @return The current conditions for the destination.
     */
    public static CurrentObservation getWeather(double latitude, double longitude) throws Exception {
        String responseJSON;
        //make http request
        responseJSON = getHTML(ENDPOINT + latitude +','+ longitude + ".json");

        //parse http response
        return parseWeatherJSON(responseJSON);

    }

    /**
     * Gets html.
     *
     * @param urlToRead the url to read
     * @return the html
     * @throws Exception the exception
     */
// Courtesy of http://stackoverflow.com/a/1485730
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line + '\n');
        }
        rd.close();
        return result.toString();
    }

    /**
     * Parse weather json.
     *
     * @param responseJSON the response json
     */
    public static CurrentObservation parseWeatherJSON(String responseJSON){
        // Create GSON object
        Gson gson = new Gson();

        // Create ConditionsAPIResponse object from JSON data
        ConditionsAPIResponse response = gson.fromJson(responseJSON, ConditionsAPIResponse.class);
        return response.getCurrentObservation();

    }

}
