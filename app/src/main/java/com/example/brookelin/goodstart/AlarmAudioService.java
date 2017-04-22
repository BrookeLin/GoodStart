package com.example.brookelin.goodstart;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Brand on 4/21/2017.
 */

public class AlarmAudioService extends Service {

    MediaPlayer alarm_media;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Local Service","Received start id " + startId + ":" + intent);

        // Fetch extra values
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone extra is ", state);



        // This converts extra strings from intent to start IDs, values 0 or 1
        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                Log.e("Start id is ", state);
                break;
            default:
                startId = 0;
                break;
        }


        // if else statements

        // If there is no music playing and the user toggles the alarm to enabled
        // music should start playing
        if(!this.isRunning && startId == 1){
            Log.e("there is no music","you want on");

            // create instance of the media player
            alarm_media = MediaPlayer.create(this, R.raw.flute_alarm);
            // Starts ringtone
            alarm_media.start();

            this.isRunning = true;
            this.startId = 0;

        }
        /* if there is music playing and the user toggles the alarm to disabled,
        * music should stop playing */
        else if(this.isRunning && startId == 0){
            Log.e("there is music", "and you want it to end");

            // Stop the ringtone
            alarm_media.stop();
            alarm_media.reset();

            this.isRunning = false;
            this.startId = 0;
        }
        /* the next two statements are for the user pressing random buttons*/
        /* If there is no music playing and alarm toggles to off, do nothing*/
        else if(this.isRunning && startId == 0){
            Log.e("there is no music","and you want end");

            this.isRunning = false;
            this.startId = 0;

        }
        /* If there is no music playing and alarm toggles to on, do nothing*/
        else if(!this.isRunning && startId == 1) {
            Log.e("there is music","you want start");

            this.isRunning = true;
            this.startId = 1;


        }
        // Just to catch an odd event
        else {
            Log.e("else","somehow you got here");

        }

        // Create instance of media player to play ringtone at the time the alarm is set
        alarm_media = MediaPlayer.create(this,R.raw.flute_alarm);
        alarm_media.start();

        // If-else statement to cancel the alarm while it is sounding


        return  START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("on destroy called","yay");

        super.onDestroy();
        this.isRunning = false;

    }

}
