package com.example.brookelin.goodstart;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.provider.AlarmClock;

import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Build;
import android.os.Bundle;

import java.sql.Time;

import android.util.Log;
import java.util.Calendar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
public class Alarm extends Activity
{
    AlarmManager manager;
    Alarm instance;
    TimePicker timePicker;
    Context context;

    // create public method instance
    public Alarm instance()
    {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
        this.context = this;

        // Create intent
        //Intent intent = new Intent(this.context, AlarmReceiver.class);

        // Initialize manager
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Initialize time picker
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        // Initialize calendar
        final Calendar calendar = Calendar.getInstance();

        // Initialize add alarm button
        /*
        FloatingActionButton addAlarmBtn = (FloatingActionButton) findViewById(R.id.addAlarmBtn);
        addAlarmBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();

            }
        });*/

        // Initialize save button and onClickListen for save button
        Button saveAlarm = (Button) findViewById(R.id.saveAlarm);
        saveAlarm.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
            }
        });

    }

    // Call onStart method
    /*
    public void onStart()
    {
        super.onStart();
        instance = this;
    }
    */


}
