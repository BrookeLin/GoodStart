package com.example.brookelin.goodstart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * GoodStart's main activity is defined by a swipeable tab layout and
 * is stored within this class. This class calls each tab and displays
 * the layout of each separate tab to the user by use of fragments.
 *
 * Source used to aid in creating this class:
 * http://www.techotopia.com/index.php/Creating_an_Android_Tabbed_Interface_using_the_TabLayout_Component
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the view to the main activity
        setContentView(R.layout.activity_main);

        // Create the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the tab layout
        TabLayout layout = (TabLayout) findViewById(R.id.tabs);

        layout.addTab(layout.newTab().setText("Alarm"));
        layout.addTab(layout.newTab().setText("Weather"));
        layout.addTab(layout.newTab().setText("Help"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        final PagerAdapter adapter = new SectionsPager(
                getSupportFragmentManager(), layout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout));

        layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        //startActivity(new Intent(this, FindLocation.class));

    }

    public void useGPS(View view){
        Intent gpsIntent= new Intent(this, FindLocation.class);
        startActivity(gpsIntent);
    }




    /** Called when the user taps the add alarm button */
    public void addAlarm(View view) {
        Intent alarmIntent = new Intent(this, AlarmActivity.class);
        startActivity(alarmIntent);
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

}
