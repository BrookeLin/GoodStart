package com.example.brookelin.goodstart;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    AlarmManager alarm_manager;
    TextView update_text;
    Calendar calendar;
    TimePicker timePicker;
    PendingIntent alarmPending;
    private static AlarmActivity inst;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        this.context = this;

        // Initialize alarm manager
        alarm_manager = (AlarmManager)getSystemService(ALARM_SERVICE);

        // Initialize time picker
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        // Initialize calendar
        calendar = Calendar.getInstance();

        /*Initialize the text box that tells the user the alarm is enabled
         * and create the onClickListener for it as well.
         */
        update_text = (TextView) findViewById(R.id.alarm_information);

        // Create an intent for the toggle button to the AlarmReceiver class
        final Intent enabledIntent = new Intent(this.context, AlarmReceiver.class);

        /* Initialize the toggle button and create the on click listener for it.
         * When the toggle button is on, the alarm will be set and it will be canceled when
         * the alarm has been turned off.*/
        ToggleButton toggle_alarm = (ToggleButton) findViewById(R.id.toggleButton);

        /* onClickListener for the toggle button*/
        toggle_alarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                // Create boolean value that checks if the toggle button is enabled or not
                boolean checked = ((ToggleButton) v).isChecked();

                if(checked){

                    /* Set the calendar instance with the hour and minute the user has picked
                     * then get the integer values of the hour as well as the minute*/
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                    int hour = timePicker.getHour();
                    int minute = timePicker.getMinute();

                    /* Convert the integer values to strings to display time alarm is set to the
                       user. */
                    String hour_information = String.valueOf(hour);
                    String minute_information = String.valueOf(minute);

                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);

                } else {
                    alarm_manager.cancel(alarmPending);
                    // Update text to show the alarm is disabled
                    set_alarm_text("Alarm has been disabled");

                    // Cancel alarm intent when toggle is disabled
                    //alarm_manager.cancel(alarmPending);
                }
            }

        });

    }

    private void set_alarm_text(String update) {
        update_text.setText(update);
    }
}
