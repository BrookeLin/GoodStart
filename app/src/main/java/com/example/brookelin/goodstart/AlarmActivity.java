package com.example.brookelin.goodstart;

import android.app.AlarmManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AlarmActivity extends AppCompatActivity {
    AlarmManager alarm_manager;
    TextView update_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        update_text = (TextView) findViewById(R.id.alarm_information);

        ToggleButton toggle_alarm = (ToggleButton) findViewById(R.id.toggleButton);
        toggle_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((ToggleButton) v).isChecked();

                if(checked){
                    set_alarm_text("Alarm has been enabled");
                } else {
                    set_alarm_text("Alarm has been disabled");
                }
            }

        });

    }

    private void set_alarm_text(String update) {
        update_text.setText(update);
    }
}
