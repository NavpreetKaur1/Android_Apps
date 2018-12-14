package com.midterm.sharma.midtermsharma030202551;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //Button and Text view ang Google map variables
    private GoogleMap tMap;
    private Button torontoButton;
    private Button otttawaButton;
    private TextView animationText;
    //Fixed Lat Long for Toronto and Ottawa
    LatLng Toronto = new LatLng(43.6532, -79.3832);
    LatLng Ottawa = new LatLng(45.4215, -75.6972);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //find button, text view etc elements on create
        torontoButton = findViewById(R.id.buttonToronto);
        otttawaButton = findViewById(R.id.buttonOttawa);
        animationText = findViewById(R.id.animationView);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        //instantiate new map object
        tMap = googleMap;
        //Set map locations and markers
        LatLng Toronto = new LatLng(43.6532, -79.3832);
        LatLng Ottawa = new LatLng(45.4215, -75.6972);
        tMap.addMarker(new MarkerOptions().position(Toronto).title("Marker in Toronto"));
        tMap.addMarker(new MarkerOptions().position(Ottawa).title("Marker in Ottawa"));
        tMap.moveCamera(CameraUpdateFactory.newLatLng(Ottawa));
        tMap.animateCamera( CameraUpdateFactory.zoomTo( 7.0f ) );
    }

    //When Toronto Button is clicked the camera animates to Toronto
    public void onClickT(View view) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(Toronto)
                .zoom(16)
                .bearing(90)
                .tilt(30)
                .build();
        tMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 30000, null);

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(Toronto);

        tMap.animateCamera(center);
        //display animation text in the text box using strings.xml resource
        animationText.setText(getResources().getString(R.string.torontoAnimate));
    }

    //When Ottawa button is clicked the camera animates to Ottawa
    public void onClickO(View view) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(Ottawa)
                .zoom(16)
                .bearing(90)
                .tilt(30)
                .build();
        tMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 10000, null);

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(Ottawa);
        //animate map to new Location
        tMap.animateCamera(center);
        //display animation text in the text box using string.xml resource
        animationText.setText(getResources().getString(R.string.ottawaAnimate));
    }

}
