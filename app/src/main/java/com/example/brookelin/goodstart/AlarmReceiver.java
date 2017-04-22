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

        // Create intent to ringtone service
        Intent serviceIntent = new Intent(context, AlarmAudioService.class);

        // Start ringtone service
        context.startService(serviceIntent);



    }
}
