package com.myvanier.strawhats.myvanier;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.IndoorLevel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapsindoors.mapssdk.MapControl;
import com.mapsindoors.mapssdk.MapsIndoors;

import java.util.List;

public class IndoorMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapControl myMapControl;
    private static final LatLng VANIER = new LatLng(45.514807, -73.674516);

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MapsIndoors.initialize(
                getApplicationContext(),
                "57e4e4992e74800ef8b69718",
                "REMOVED API KEY"
        );
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        SupportMapFragment mapFragment = new SupportMapFragment();

////
////        // Add a marker in Sydney and move the camera
////        LatLng sydney = new LatLng(-34, 151);
////        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Create a new MapControl instance
        myMapControl = new MapControl( this, mapFragment, googleMap );

// Initialize MapControl to get the locations on the map, etc.
        myMapControl.init( errorCode -> {
            if( errorCode == null ) {
                runOnUiThread( () -> {
                    // Animate the camera to show the venue
                    mMap.setBuildingsEnabled(true);
                    mMap.setIndoorEnabled(true);
                    mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
                    mMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.animateCamera( CameraUpdateFactory.newLatLngZoom( VANIER, 19f ) );
                });
            }
        });
    }
}
