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

        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        // Create instance of media player to play ringtone at the time the alarm is set
        alarm_media = MediaPlayer.create(this,R.raw.woodpecker);
        alarm_media.start();

        // If-else statement to cancel the alarm while it is sounding


        return  START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_SHORT).show();
    }

}
