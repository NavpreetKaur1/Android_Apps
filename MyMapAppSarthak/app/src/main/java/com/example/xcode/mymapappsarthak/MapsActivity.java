    package com.example.xcode.mymapappsarthak;

    import android.graphics.Color;
    import android.support.v4.app.FragmentActivity;
    import android.os.Bundle;

    import com.google.android.gms.maps.CameraUpdateFactory;
    import com.google.android.gms.maps.GoogleMap;
    import com.google.android.gms.maps.OnMapReadyCallback;
    import com.google.android.gms.maps.SupportMapFragment;
    import com.google.android.gms.maps.model.CircleOptions;
    import com.google.android.gms.maps.model.LatLng;
    import com.google.android.gms.maps.model.MarkerOptions;
    import com.google.android.gms.maps.model.BitmapDescriptor;
    import com.google.android.gms.maps.model.BitmapDescriptorFactory;
    import com.google.android.gms.maps.model.Polygon;
    import com.google.android.gms.maps.model.PolygonOptions;

    public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

        private GoogleMap dMap;
        private GoogleMap tMap;
        private GoogleMap hMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
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
            dMap = googleMap;
            tMap = googleMap;
            hMap = googleMap;

            // Add a marker in Sydney and move the camera
            //LatLng sydney = new LatLng(-34, 151);
            LatLng sheridanD = new LatLng(43.6560676, -79.7410584);
            LatLng sheridanH = new LatLng(43.5910195, -79.6489782);
            LatLng sheridanT = new LatLng(43.4689298, -79.7021133);

            hMap.addMarker(new MarkerOptions().position(sheridanH).title("Marker at Sheridan HMC ").snippet("HMC is the newest Campus").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            dMap.addMarker(new MarkerOptions().position(sheridanD).title("Marker at Sheridan Davis").snippet("Davis is the oldest Campus").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            tMap.addMarker(new MarkerOptions().position(sheridanT).title("Marker at Sheridan Trafalgar").snippet("Trafalgar is the best campus").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


            CircleOptions circleOptions = new CircleOptions();

            // Specifying the center of the circle
            circleOptions.center(sheridanH);

            // Radius of the circle
            circleOptions.radius(15000);

            // Border color of the circle
            circleOptions.strokeColor(Color.BLACK);

            // Fill color of the circle
            circleOptions.fillColor(0x30ff0000);

            // Border width of the circle
            circleOptions.strokeWidth(2);

            // Adding the circle to the GoogleMap
            googleMap.addCircle(circleOptions);

            // SphericalUtil.computeDistanceBetween(latLngFrom, latLngTo);

            PolygonOptions rectOptions = new PolygonOptions()
                    .add(sheridanD)
                    .add(sheridanH)
                    .add(sheridanT);
            Polygon polyline = hMap.addPolygon(rectOptions);

            //load the traffic now
            googleMap.setTrafficEnabled(true);

            dMap.moveCamera(CameraUpdateFactory.newLatLng(sheridanD));
            hMap.moveCamera(CameraUpdateFactory.newLatLng(sheridanH));
            tMap.moveCamera(CameraUpdateFactory.newLatLng(sheridanT));
            dMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
    //        hMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
    //        tMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
        }
    }
