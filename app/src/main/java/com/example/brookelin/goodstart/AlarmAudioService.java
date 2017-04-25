package com.example.brookelin.goodstart;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Local Service","Received start id " + startId + ":" + intent);

        // Fetch extra values
        String state = intent.getExtras().getString("extra");

        // Fetch audio choice integer values
        Integer audio_choice_id = intent.getExtras().getInt("choose_audio");


        /* Create the notification for when the alarm goes off*/
        NotificationManager alarm_notification = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent_main = new Intent(this, AlarmActivity.class);

        PendingIntent notification_pending = PendingIntent.getActivity(this,0,intent_main,0);

        // Make the notification parameters
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("GoodStart Alarm")
                .setContentText("Turn off")
                .setContentIntent(notification_pending)
                .build();

        // This converts extra strings from intent to start IDs, values 0 or 1
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


        // if else statements

        // If there is no music playing and the user toggles the alarm to enabled
        // music should start playing
        if(!this.isRunning && startId == 1){
            Log.e("there is no music","you want on");

            this.isRunning = true;
            this.startId = 0;

            // Play the audio chosen by the user
            if(audio_choice_id == 0){
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.calm_wakeup);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            } else if(audio_choice_id == 1){
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.early_sunrise);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            } else if(audio_choice_id == 2){
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.flute_alarm);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            } else if(audio_choice_id == 3){
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.gentleness);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            } else if(audio_choice_id == 4) {
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.good_morning);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            } else if(audio_choice_id == 5) {
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.loving_you);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            } else if(audio_choice_id == 6) {
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.my_blue_sky);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            }

            else if(audio_choice_id == 7) {
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.rainfall);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            } else if(audio_choice_id == 8) {
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.sunshine);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();

            } else if(audio_choice_id == 9) {
                // create instance of the media player
                alarm_media = MediaPlayer.create(this, R.raw.trapdoor___top);
                alarm_media.setLooping(true);
                // Starts ringtone
                alarm_media.start();
            }

            // Set up notification start command
            alarm_notification.notify(0, notification);

        }



        /* if there is music playing and the user presses the off button,
        * music should stop playing */
        else if(this.isRunning && startId == 0){
            Log.e("there is music", "and you want it to end");

            this.isRunning = false;
            this.startId = 0;

            // Stop the ringtone
            alarm_media.stop();
            alarm_media.reset();
        }
        /* the next two statements are for the user pressing random buttons*/
        /* If there is no music playing and alarm off is pressed, do nothing*/
        else if(!this.isRunning && startId == 0){
            Log.e("there is no music","and you want end");

            this.isRunning = false;
            this.startId = 0;

        }
        /* If there is music playing and the alarm is turned on, do nothing*/
        else if(this.isRunning && startId == 1) {
            Log.e("there is music","you want start");

            this.isRunning = true;
            this.startId = 1;
        }
        // Just to catch an odd event
        else {
            Log.e("else","somehow you got here");

        }

        return  START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("on destroy called","yay");

        super.onDestroy();
        this.isRunning = false;

    }

}
