package com.example.brookelin.goodstart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPager extends FragmentPagerAdapter {

    int tabCount;

    public SectionsPager(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    // TODO add comments please! :)
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Alarm tab_alarm = new Alarm();
                return tab_alarm;
            case 1:
                WeatherFrag tab_weather = new WeatherFrag();
                return tab_weather;
            case 2:
                Help tab_help = new Help();
                return tab_help;
            default:
                WeatherFrag default_weather = new WeatherFrag();
                return default_weather;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}