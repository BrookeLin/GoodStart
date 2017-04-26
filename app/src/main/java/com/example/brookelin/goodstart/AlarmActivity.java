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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import static java.lang.Math.toIntExact;
import android.widget.ToggleButton;
import android.speech.tts.TextToSpeech;

import com.example.brookelin.goodstart.weatherapi.CurrentObservation;
import com.example.brookelin.goodstart.weatherapi.WeatherAPI;

import java.util.Calendar;
import java.util.Locale;

public class AlarmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AlarmManager alarm_manager;
    TextView update_text;
    Calendar calendar;
    TimePicker timePicker;
    Spinner audio_spinner;
    PendingIntent alarmPending;
    TextToSpeech TTS;
    private static AlarmActivity inst;
    Context context;
    int choose_audio;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // outputting whatever id the user has selected
        Toast.makeText(parent.getContext(),"the spinner item is " + id, Toast.LENGTH_SHORT).show();
        choose_audio = (int) id;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        this.context = this;

        TTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            public void onInit(int status){
                if(status != TextToSpeech.ERROR){
                    TTS.setLanguage(Locale.US);
                }
            }
        });

        // Initialize alarm manager
        alarm_manager = (AlarmManager)getSystemService(ALARM_SERVICE);

        // Initialize time picker
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        // Initialize calendar
        calendar = Calendar.getInstance();

        // Initialize the check boxes for days alarm is meant to repeat
        final CheckBox sunday = (CheckBox) findViewById(R.id.Sunday);
        final CheckBox monday = (CheckBox) findViewById(R.id.monday);
        final CheckBox tuesday = (CheckBox) findViewById(R.id.tuesday);
        final CheckBox wednesday = (CheckBox) findViewById(R.id.wednesday);
        final CheckBox thursday = (CheckBox) findViewById(R.id.thursday);
        final CheckBox friday = (CheckBox) findViewById(R.id.friday);
        final CheckBox saturday = (CheckBox) findViewById(R.id.saturday);

        /*Initialize the text box that tells the user the alarm is enabled
         * and create the onClickListener for it as well.
         */
        update_text = (TextView) findViewById(R.id.alarm_information);

        // Create an intent for the toggle button to the AlarmReceiver class
        final Intent enabledIntent = new Intent(this.context, AlarmReceiver.class);

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

        // Initialize the on button and create an onClickListener for it
        Button alarm_on = (Button) findViewById(R.id.onButton);
        alarm_on.setOnClickListener(new View.OnClickListener()
        {
            @TargetApi(Build.VERSION_CODES.M)
            public void onClick(View v)
            {
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


                // Series of if statements to start alarm if the day of the week is selected
                // Get the information on what day it is
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                Log.e("the day of the week is", Integer.toString(day));

                if(day == 1 && sunday.isChecked()){
                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed on button
                    enabledIntent.putExtra("extra","alarm on");

                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("The audio is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);

                } else if(day == 2 && monday.isChecked()){
                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed on button
                    enabledIntent.putExtra("extra","alarm on");

                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("The audio is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);
                } else if(day == 3 && tuesday.isChecked()){
                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed on button
                    enabledIntent.putExtra("extra","alarm on");

                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("The audio is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);
                } else if(day == 4 && wednesday.isChecked()){
                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed on button
                    enabledIntent.putExtra("extra","alarm on");

                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("The audio is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);
                } else if(day == 5 && thursday.isChecked()){
                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed on button
                    enabledIntent.putExtra("extra","alarm on");

                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("The audio is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);
                } else if(day == 6 && friday.isChecked()){
                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed on button
                    enabledIntent.putExtra("extra","alarm on");

                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("The audio is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);
                } else if(day == 7 && saturday.isChecked()){
                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed on button
                    enabledIntent.putExtra("extra","alarm on");

                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("The audio is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);
                } else {
                    // Update text to show the alarm is enabled
                    set_alarm_text("Alarm set to: " + hour_information + ":" + minute_information);

                    // Extra string into enabledIntent to tell clock you pressed on button
                    enabledIntent.putExtra("extra","alarm on");

                    enabledIntent.putExtra("choose_audio", choose_audio);
                    Log.e("The audio is",String.valueOf(choose_audio));

                    /* Create the pending intent that will delay the alarm intent until
                    * the specified time*/
                    alarmPending = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                            enabledIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    /* Set the alarm manager */
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmPending);
                }


            }
        });

        // Initialize and create onClickListener for the off button
        Button alarm_off = (Button) findViewById(R.id.offButton);
        alarm_off.setOnClickListener(new View.OnClickListener()
        {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                // Update text to show the alarm is disabled
                set_alarm_text("Alarm has been disabled");

                //Tells the clock that you turned alarm off
                enabledIntent.putExtra("extra","alarm off");



                // Stop the ringtone when button is clicked
                sendBroadcast(enabledIntent);

                enabledIntent.putExtra("",choose_audio);

                //Do the TextToSpeech thang

                CurrentObservation outside = new CurrentObservation();
/*                try {
                     outside = WeatherAPI.getWeather(29, -81);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                //WeatherAPI no working, using the same class type I insert placeholder data
                outside.temp_f = 81.5;
                outside.feelslike_f = 78;
                outside.weather = "Sunny";
                outside.wind_string = "11";

                //Create the strings based on values
                // Later have it set for the settings (only relevant strings are passed
                String temp = "The temperature is " + outside.temp_f+ " degrees.";
                String feelslike = "It feels like" + outside.feelslike_f + "degrees.";
                String weather = "It is" + outside.weather;
                String wind = " and the Winds are " + outside.wind_string + " miles per hour.";
                //String clothes = "You should wear a t-shirt, shorts and a light jacket for the morning.";
                String speakThis = "Good morning!" + temp + feelslike + weather + wind;

                //pick the clothing options and pass through strings
                ClothingPicker pick = new ClothingPicker();
                boolean windy;
                if(Double.parseDouble(outside.wind_string) >10){
                    windy = true;
                }else{
                    windy = false;
                }
                pick.pants = pick.pickShorts(false, outside.temp_f, windy);
                String pants = " ";
                if (pick.pants == 1){
                    pants = " shorts";
                }else {
                    pants = " pants";
                }
                String top = " ";
                pick.tops = pick.pickTops(false, outside.temp_f, windy);

                if(pick.tops == 1) {
                    top = " tank top";
                }else if (pick.tops == 2) {
                    top = " short sleeve shirt";
                }else if (pick.tops == 3) {
                    top = " long sleeve shirt";
                }else if (pick.tops == 4) {
                    top = " sweat shirt";
                }else if (pick.tops == 5) {
                    top = " winter coat";
                }

                //combine strings one final time
                String clothes = "You should wear " + pants + " and a" + top + ". Have a splendid day!";
                String speakStuff = speakThis + clothes;
                //speak the stuff
                TTS.speak(speakStuff, TextToSpeech.QUEUE_FLUSH, null);
                //apparently you can't have two of those... Even if QUEUE_FLUSH is changed
                //to QUEUE_ADD, which should work. But I'm out of time.
                //TTS.speak(clothes, TextToSpeech.QUEUE_ADD, null);

            }
        });

    }

    private void set_alarm_text(String update) {
        update_text.setText(update);
    }





    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
