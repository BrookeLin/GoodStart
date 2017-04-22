package com.example.brookelin.goodstart;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AlarmManager alarm_manager;
    TextView update_text;
    Calendar calendar;
    TimePicker timePicker;
    Spinner audio_spinner;
    PendingIntent alarmPending;
    private static AlarmActivity inst;
    Context context;
    long choose_audio;

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

        // create the spinner for the user to choose the alarm
        // Initialize spinner for the alarm options
        audio_spinner = (Spinner) findViewById(R.id.spinner);

        // Create array adapter using string array and default spinner layout
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.alarm_sounds, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        audio_spinner.setAdapter(arrayAdapter);

        // Create onClickListener for the spinner
        audio_spinner.setOnItemSelectedListener(this);

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

                    // Use if statement to display time of the alarm properly
                    if(hour >= 13){
                        hour_information = String.valueOf(hour - 12);
                    }

                    if(minute < 10){
                        minute_information = "0" + String.valueOf(minute);
                    }

                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed toggle button
                    enabledIntent.putExtra("extra","alarm on");

                    // put in extra long value into enabledIntent to tell the clock
                    // that the user want a certain value from the spinner
                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("Whale ID is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);


                } else if(!checked){
                    // Update text to show the alarm is disabled
                    set_alarm_text("Alarm has been disabled");

                    // Cancel alarm intent when toggle is disabled
                    alarm_manager.cancel(alarmPending);

                    //Tells the clock that you turned alarm off
                    enabledIntent.putExtra("extra","alarm off");

                    enabledIntent.putExtra("",choose_audio);


                    // Stop the ringtone
                    //sendBroadcast(enabledIntent);
                }


            }

        });

    }

    private void set_alarm_text(String update) {
        update_text.setText(update);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // outputting whatever id the user has selected
        Toast.makeText(parent.getContext(),"the spinner item is " + id, Toast.LENGTH_SHORT).show();
        choose_audio = id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
