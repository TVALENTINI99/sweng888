package thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.BroadcastReceiver.MapBroadcastReceiver;
import thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.R;
import thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.model.MapLocation;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private final String LOG_MAP = "GOOGLE_MAPS";

    private LatLng currentLatLng;
    private Marker currentMapMarker;

    private IntentFilter intentFilter = null;
    private MapBroadcastReceiver broadcastReceiverMap = null;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        intentFilter = new IntentFilter("thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.action.MAP_LOCATION_BROADCAST");
        broadcastReceiverMap = new MapBroadcastReceiver();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        Intent intent = getIntent();

        MapLocation location = (MapLocation)intent.getSerializableExtra("MAP_LOCATION");

        currentLatLng = location.getLatLang();

        googleMap.addMarker(new MarkerOptions()
                .position(currentLatLng)
                .title(location.getTitle())
        );

        mapCameraConfiguration();
        useMapClickListener();
        useMarkerClickListener();
        useMapLongClickListener();
        useCameraMoveListener();
        //Test the connection
        firebaseLoadData();
        //load data
        loadData();
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Register the Broadcast Receiver.
        registerReceiver(broadcastReceiverMap, intentFilter);
    }

    @Override
    protected void onStop() {
        // Unregister the Broadcast Receiver
        unregisterReceiver(broadcastReceiverMap);
        super.onStop();
    }

    private void mapCameraConfiguration() {

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentLatLng)
                .zoom(14)
                .bearing(0)
                .build();

        // Camera that makes reference to the maps view
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        mMap.animateCamera(cameraUpdate, 3000, new GoogleMap.CancelableCallback() {

            @Override
            public void onFinish() {
                Log.i(LOG_MAP, "googleMap.animateCamera:onFinish is active");
            }

            @Override
            public void onCancel() {
                Log.i(LOG_MAP, "googleMap.animateCamera:onCancel is active");
            }});
    }

    /** Step 3 - Reusable code
     This method is called everytime the use wants to place a new marker on the map. **/
    private MarkerOptions createCustomMapMarkers(LatLng latlng, String title, String snippet){

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng) // coordinates
                .title(title) // location name
                .snippet(snippet); // location description

        // Update the global variable (currentMapMarker)
        return markerOptions;
    }

    // Step 4 - Define a new marker based on a Map click (uses onMapClickListener)
    private void useMapClickListener(){

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latltn) {
                Log.i(LOG_MAP, "setOnMapClickListener");

                if(currentMapMarker != null){
                    // Remove current marker from the map.
                    currentMapMarker.remove();
                }
                // The current marker is updated with the new position based on the click.
                createCustomMapMarkers(
                        new LatLng(latltn.latitude, latltn.longitude),
                        "New Marker",
                        "Listener onMapClick - new position"
                                +"lat: "+latltn.latitude
                                +" lng: "+ latltn.longitude);
            }
        });
    }

    // Step 5 - Use OnMarkerClickListener for displaying information about the MapLocation
    private void useMarkerClickListener(){
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            // If FALSE, when the map should have the standard behavior (based on the android framework)
            // When the marker is clicked, it wil focus / centralize on the specific point on the map
            // and show the InfoWindow. IF TRUE, a new behavior needs to be specified in the source code.
            // However, you are not required to change the behavior for this method.
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i(LOG_MAP, "setOnMarkerClickListener");

                return false;
            }
        });
    }
    private void useCameraMoveListener() {
        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                Log.i(LOG_MAP, "setOnCameraMoveStartedListener");

                Toast.makeText(getApplicationContext(), "Camera is moving", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void useMapLongClickListener() {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.i(LOG_MAP, "setOnMapLongClickListener");

                Toast.makeText(getApplicationContext(), "Clicked on Location " + latLng.latitude + ", " + latLng.longitude, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createMarkersFromFirebase(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference reference = firebaseDatabase.getReference();

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                // Get the current location (based on the selected dataset available on firebase
                MapLocation currentLocation = dataSnapshot.getValue(MapLocation.class);

                // Adding a new element from the collection
                MarkerOptions markerOptions = createCustomMapMarkers(currentLocation.getLatLang(),currentLocation.getTitle(),currentLocation.getDescription());
                mMap.addMarker(markerOptions);
                triggerBroadcastMessageFromFirebase(Double.valueOf(currentLocation.getLatitude()),Double.valueOf(currentLocation.getLongitude()),currentLocation.getTitle());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadData(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference reference = firebaseDatabase.getReference();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists())
                {
                    reference.child("a").setValue(new MapLocation("New York","City never sleeps", String.valueOf(39.953348), String.valueOf(-75.163353)));
                    reference.child("b").setValue(new MapLocation("Paris","City of lights", String.valueOf(48.856788), String.valueOf(2.351077)));
                    reference.child("c").setValue(new MapLocation("Las Vegas","City of dreams", String.valueOf(36.167114), String.valueOf(-115.149334)));
                    reference.child("d").setValue(new MapLocation("Tokyo","City of technology", String.valueOf(35.689506), String.valueOf(139.691700)));
                }
                createMarkersFromFirebase();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(LOG_MAP, "onCancelled", databaseError.toException());
            }
        });

    }
    private void firebaseLoadData(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                MapLocation currentLocation = dataSnapshot.getValue(MapLocation.class);
                Log.d("TAG", currentLocation.getLatitude().toString());
                Log.d("TAG", currentLocation.getLongitude().toString());
                Log.d("TAG", currentLocation.getTitle());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void triggerBroadcastMessageFromFirebase(Double lat,Double longitude, String location){

        Intent broadcast = new Intent(MapActivity.this, MapBroadcastReceiver.class);

        broadcast.putExtra("LAT", lat);
        broadcast.putExtra("LONG", longitude);
        broadcast.putExtra("LOCATION", location);

        sendBroadcast(broadcast);

    }
}
