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

        // Create intent to ringtone service
        Intent serviceIntent = new Intent(context, AlarmAudioService.class);

        // Pass extra string from alarm activity to the ringtone playing service
        serviceIntent.putExtra("extra",getYourString);

        // Start ringtone service
        context.startService(serviceIntent);



    }
}
