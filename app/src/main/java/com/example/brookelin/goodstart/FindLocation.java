package com.example.brookelin.goodstart;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brookelin.goodstart.weatherapi.CurrentObservation;
import com.example.brookelin.goodstart.weatherapi.WeatherAPI;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by BrookeLin on 4/15/2017.
 * Code portions courtesy of
 */


public class FindLocation extends Activity implements LocationListener {
    public LocationManager locationManager;
    public int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
    protected LocationListener locationListener;
    protected Context context;
    protected Double latitude, longitude;
    protected boolean gps_enabled;
    public Location location;
    public String cityState= null;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.locationfinder);
        this.context = this;
        String mprovider;

       /* // Initialize the back button
        final Intent backIntent = new Intent(this.context, MainActivity.class);

        Button back_button = (Button) findViewById(R.id.backButton);
        back_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                backIntent.putExtra("location", cityState);
                startActivity(backIntent);


            }
        });
        */


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //Criteria criteria = new Criteria();

        mprovider = locationManager.GPS_PROVIDER;

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 50, 50, this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "No Location Provider Found", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onLocationChanged(Location location) {
        // Update location texts
        TextView longitudeView = (TextView) findViewById(R.id.longTextV);
        TextView latitudeView = (TextView) findViewById(R.id.latTextV);
        TextView cityStateTxt= (TextView) findViewById(R.id.cityname);


        //Get and display lat/long
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        latitudeView.setText("Current Latitude:\n" + latitude + "\n\n");
        longitudeView.setText("Current Longitude:\n" + longitude + "\n");


        //Get and display Address
        cityState=getCitStat();
        cityStateTxt.setText(cityState);

        //WeatherFrag.setArguments(bundle);


        // Also update weather info (pursuant of SRS v1.0 Section 4.3.2)
        CurrentObservation obs = new CurrentObservation();
        WeatherAPI WAPI = new WeatherAPI();

        try {
            obs=WAPI.getWeather(latitude, longitude);
            //vars.setcurrobs(obs);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public String returnCit(){

        return cityState;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    /*
    public void backButton(View view) {
        Intent backIntent = new Intent(this.context, MainActivity.class);
        backIntent.putExtra("location", cityState);
        startActivity(backIntent);
    }*/

    public void imagesWeather(View view) {

    }

    //Getter for latitude
    public Double getLatitude() {
        return latitude;
    }

    //Getter for longitude
    public Double getLongitude() {
        return longitude;
    }

    //Getter for city,state,country
    //Coutesy Haiko Rupp via stackoverflow.com
    public String getCitStat() {

        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());

        List<Address> addresses = null;
        try {
            addresses = geoCoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String add = "";
        if (addresses.size() > 0) {
            for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                add += addresses.get(0).getAddressLine(i) + "\n";
        }

        return add;
    }
}



