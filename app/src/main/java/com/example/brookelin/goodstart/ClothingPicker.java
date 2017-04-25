package com.example.brookelin.goodstart;

/**
 * Created by BrookeLin on 4/15/2017.
 */

public class ClothingPicker {

    //PRECIP: 0 is none, 1 is light rain, 2 is heavy rain, 3 is snow, etc

    public Integer pants;   //1 is shorts, 2 is pants
    public Integer tops;    // 1 is tank top, 2 is short sleeve, 3 is long sleeve, 4 is sweatshirt, 5 is winter jacket
    public Integer accessory;  // 0 is sunglasses, 1 is umbrella, 2 is raincoat, 3 is winter hat/mittens/scarf?



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


    public int pickTops(Boolean hotPref, Double highTemp, Boolean windy){

        if(hotPref){
            if(windy){
                tops=5;        //wear winter coat
                if(highTemp>45){
                    tops=4;     //wear sweatshirt
                    if(highTemp>57){
                        tops=3;     //wear long sleeve
                        if (highTemp>68){
                            tops=2;     //wear short sleeve
                            if(highTemp>75){
                                tops=1;  //wear tank top
                            }
                        }
                    }
                }

            }
            if(!windy){
                tops=5;        //wear winter coat

                if(highTemp>45){
                    tops=4;     //wear sweatshirt
                    if(highTemp>57){
                        tops=3;     //wear long sleeve
                        if (highTemp>68){
                            tops=2;     //wear short sleeve
                            if(highTemp>75){
                                tops=1;  //wear tank top
                            }
                        }
                    }
                }

            }
        }else{
            if(windy){
                tops=5;        //wear winter coat

                if(highTemp>50){
                    tops=4;     //wear sweatshirt
                    if(highTemp>63){
                        tops=3;     //wear long sleeve
                        if (highTemp>74){
                            tops=2;     //wear short sleeve
                            if(highTemp>83){
                                tops=1;  //wear tank top
                            }
                        }
                    }
                }

            }
            if(!windy) {
                tops = 5;        //wear winter coat

                if (highTemp > 48) {
                    tops = 4;     //wear sweatshirt
                    if (highTemp > 60) {
                        tops = 3;     //wear long sleeve
                        if (highTemp > 72) {
                            tops = 2;     //wear short sleeve
                            if (highTemp > 80) {
                                tops = 1;  //wear tank top
                            }
                        }
                    }
                }

            }
        }
        return tops;
    }

    //PRECIP: 0 is none/sunny, 1 is light rain, 2 is heavy rain, 3 is snow, etc
    public int pickAcces(int precip){
        switch (precip){

            case 0:
                accessory=0;  //wear sunglasses
                break;
            case 1:
                accessory=1;   //bring umbrella
                break;
            case 2:
                accessory=2;   //bring raincoat
                break;
            case 3:
                accessory=3;   //bring hat/mittens/scarf?
                break;
        }



        return accessory;
    }

    //public void pickClothes()
}
