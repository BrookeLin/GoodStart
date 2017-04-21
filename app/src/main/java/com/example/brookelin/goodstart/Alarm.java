package com.example.brookelin.goodstart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Alarm extends Fragment implements View.OnClickListener {
    Button addAlarm;
    TextView update_text;


    public Alarm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View alarmView = inflater.inflate(R.layout.fragment_alarm, container, false);

        update_text = (TextView) alarmView.findViewById(R.id.text_view);

        addAlarm = (Button) alarmView.findViewById(R.id.alarmButton);
        addAlarm.setOnClickListener(this);

        return alarmView;
    }

    @Override
    public void onClick(View v) {
        set_alarm_text("The button has been clicked");
    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }
}