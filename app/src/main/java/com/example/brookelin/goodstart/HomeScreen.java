package com.example.brookelin.goodstart;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * The type Home screen.
 */
public class HomeScreen extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The Text.
     */
    public String text;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     *Creates tab layout, toolbar, and basic gui structure elements of the application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter. Add container
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    /**
     * Access alarm.
     *
     * @param item the item
     */
    public void accessAlarm(MenuItem item)
    {
        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        startActivity(intentAlarm);

        // pending intent delays the alarm
        // until the time it's supposed to go off is reacher
        PendingIntent pending = PendingIntent.getBroadcast(HomeScreen.this, 0,
                intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    /**
     * Opens up new page with setting options
     *
     * @param item Menu item selected (in this case settings)
     */
    public void accessSettings(MenuItem item){
        Intent intentSettings = new Intent(this, SettingsActivity.class);
        startActivity(intentSettings);
    }

    /**
     *Creates menu bar
     * @param menu
     * @return True if Menu is created successfully
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    /**
     * Determines the menu item which is selected
     * @param item Specific menu item
     * @return True if specific menu item is selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Instantiates a new Placeholder fragment.
         */
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         *
         * @param sectionNumber the section number
         * @return the placeholder fragment
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        /**
         * Fills each page's view with text
         * @param inflater
         * @param container
         * @param savedInstanceState
         * @return a Rootview
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_home_screen, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.text_view);

            //EDIT TEXT INSIDE
            textView.setText("Alarm set to:");
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        /**
         * Instantiates a new Sections pager adapter.
         *
         * @param fm the fm
         */
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         *Creates fragment for specific page
         * @param position Which tab it is
         * @return a Fragment for each tab
         */
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        /**
         *Sets the number of tabs
         * @return Number of tabs
         */
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        /**
         *Sets the tab titles
         * @param position Where each tab is located
         * @return String title of tab
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ALARMS";
                case 1:
                    return "HOME";
                case 2:
                    return "HELP";
            }
            return null;
        }
    }
}
