package com.example.brookelin.goodstart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//import com.google.gson.JsonParser;

/**
 * Created by aaronnspiewak on 3/20/17.
 */

public class WeatherAPI {

    public static final String API_KEY = "babfa7850fe7050f";
    public static final String ENDPOINT = "http://api.wunderground.com/api/" + API_KEY + "/conditions/q/";

    // TODO TESTING ONLY
    public static void main(String[] a) throws Exception {
        String url, result;
        url = ENDPOINT + "38.000,-84.000.json";
        result = getHTML(url);
        System.out.println(url);
        System.out.println(result);
    }

    public void getWeather(double latitude, double longitude) throws Exception {
        String responseJSON;
        //make http request
        responseJSON = getHTML(ENDPOINT + latitude +','+ longitude + ".json");

        //parse http response
        parseWeatherJSON(responseJSON);

    }

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

    public static void parseWeatherJSON(String responseJSON){
        //JsonParser parser = new JsonParser();

    }

}
