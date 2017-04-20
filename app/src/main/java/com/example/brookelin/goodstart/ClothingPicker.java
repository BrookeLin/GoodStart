package com.example.brookelin.goodstart;

/**
 * Created by BrookeLin on 4/15/2017.
 */

public class ClothingPicker {

    //PRECIP: 0 is none, 1 is light rain, 2 is heavy rain, 3 is snow, etc

    public Integer pants;   //1 is shorts, 2 is pants
    public Integer tops;    // 1 is tank top, 2 is short sleeve, 3 is long sleeve, 4 is sweatshirt, 5 is winter jacket
    public Integer accessory;  //1 is raincoat/umbrella, 2 is sunglasses, 3 is winter hat



    //Boolean hotPref, Double highTemp, Double lowTemp, Integer precip, Integer humidity, Boolean windy

    public int pickShorts(Boolean hotPref, Double highTemp, Boolean windy){
        pants=7;
        if(hotPref){
            if( highTemp>=62 && !windy)
                pants=1;   //wear shorts
            if(highTemp>=65 && windy)
                pants=1;
            if(highTemp<62 && !windy)
                pants=2;   //wear pants
            if(highTemp<65 && windy)
                pants=2;


    }else{
            if( highTemp>=68 && !windy)
                pants=1;   //wear shorts
            if(highTemp>=72 && windy)
                pants=1;
            if(highTemp<68 && !windy)
                pants=2;   //wear pants
            if(highTemp<72 && windy)
                pants=2;
        }
        return pants;
    }


    public int pickTops(Boolean hotPref, Double highTemp, Integer humidity, Boolean windy){
        tops=0;
        if(hotPref){
            if( (highTemp>=76 && !windy && humidity>50) || (highTemp>=80 && windy&& humidity>50))
                tops=1;   //wear tank top
            if((highTemp<76 && !windy && highTemp>65 && humidity<=75) ||(highTemp<80 && windy && humidity <= 50) )
                tops=2;   //wear short sleeve
            if(highTemp<80 && windy)
                tops=2;


        }else{
            if( highTemp>=68 && !windy)
                tops=1;   //wear shorts
            if(highTemp>=72 && windy)
                tops=1;
            if(highTemp<68 && !windy)
                tops=2;   //wear pants
            if(highTemp<72 && windy)
                tops=2;
        }
        return tops;
    }
}
