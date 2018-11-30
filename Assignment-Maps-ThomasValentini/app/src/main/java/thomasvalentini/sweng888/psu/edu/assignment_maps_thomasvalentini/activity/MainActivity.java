package thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.BroadcastReceiver.MapBroadcastReceiver;
import thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.R;
import thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.model.MapLocation;

public class MainActivity extends AppCompatActivity {

    //Ui Component Variables
    private EditText mLatitudeEditText;
    private EditText mLongitudeEditText;
    private EditText mLocationEditText;

    private Button mNavigationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLatitudeEditText = findViewById(R.id.LatitudeEditText);
        mLongitudeEditText = findViewById(R.id.LongitudeEditText);
        mLocationEditText = findViewById(R.id.LocationEditText);

        mNavigationButton = findViewById(R.id.NavigationButton);

        mNavigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lat = mLatitudeEditText.getText().toString();
                String longitude = mLongitudeEditText.getText().toString();
                String location = mLocationEditText.getText().toString();

                MapLocation mapLocation=new MapLocation(location,lat,longitude);

                triggerBroadcast(Double.valueOf(lat),Double.valueOf(longitude),location);

                viewMap(mapLocation);
            }
        });
    }
    private void triggerBroadcast(Double lat,Double longitude, String location){

        Intent broadcast = new Intent(MainActivity.this, MapBroadcastReceiver.class);

        broadcast.putExtra("LAT", lat);
        broadcast.putExtra("LONG", longitude);
        broadcast.putExtra("LOCATION", location);

        sendBroadcast(broadcast);

    }
    private void viewMap(MapLocation location){

        Intent map = new Intent(MainActivity.this, MapActivity.class);

        map.putExtra("MAP_LOCATION", location);

        startActivity(map);
    }
}