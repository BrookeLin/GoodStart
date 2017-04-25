package com.example.brookelin.goodstart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Brand on 3/22/2017.
 */


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent wakeIntent){

        Log.e("You are in the receiver", "yay");

        // Fetch extra string from the intent
        String getYourString = wakeIntent.getExtras().getString("extra");
        Log.e("What is the key",getYourString);

        // Fetch extra longs from intent in AlarmActivity
        // This tells the app which value the user picked from the dropdown menu
        int get_audio_choice = wakeIntent.getExtras().getInt("choose_audio");
        // Log.e("Audio choices",get_audio_choice.toString());

        //bitch

        // Create intent to ringtone service
        Intent serviceIntent = new Intent(context, AlarmAudioService.class);

        // Pass extra string from receiver to the ringtone playing service
        serviceIntent.putExtra("extra",getYourString);

        // Pass extra integer from the receiver to the AlarmAudioService
        serviceIntent.putExtra("choose_audio",get_audio_choice);


        // Start ringtone service
        context.startService(serviceIntent);



    }
}
