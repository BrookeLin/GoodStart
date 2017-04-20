package com.example.brookelin.goodstart;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * This code was derived form a tutorial on build alarm clocks by Anna Xu on youtube
 */
public class AlarmActivity extends AppCompatActivity
{
    /**
     * The Manager.
     */
    AlarmManager manager;
    /**
     * The Picker.
     */
    TimePicker picker;
    /**
     * The Text view.
     */
    TextView text_view;
    /**
     * The Context.
     */
    Context context;

    /*
    Creates Alarm manager and GUI for alarm
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alarm);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        // Initialize alarm manager
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Initialize time picker
       // picker = (TimePicker) findViewById(R.id.timePicker);

        // Initialize on button
       // Button alarmOn = (Button) findViewById(R.id.alarmOn);

        // Initialize off button
        //Button alarmOff = (Button) findViewById(R.id.alarmOff);
    }

    /* Initializes another menu
    @param menu
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

}
