package riosorti.com.mapsheridan;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMapD;
    private GoogleMap mMapH;
    private GoogleMap mMapT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMapD = googleMap;
        mMapH = googleMap;
        mMapT = googleMap;

        LatLng sheridanD = new LatLng(43.6560676, -79.7410584);
        LatLng sheridanH = new LatLng(43.5910195, -79.6489782);
        LatLng sheridanT = new LatLng(43.4689298, -79.7021133);

        mMapD.addMarker(new MarkerOptions()
                .position(sheridanD)
                .title("Sheridan Davis campus")
                .snippet("Enrollment 2017-9,588 Students")
//                .snippet("Enrollment 2017-9,588 Students"+"\n"+"Hours: M-F 7:30 am to 4:30 pm"+"\n"+"test")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMapD.moveCamera(CameraUpdateFactory.newLatLng(sheridanD));

        mMapH.addMarker(new MarkerOptions()
                .position(sheridanH)
                .title("Sheridan HMC campus")
                .snippet("Enrollemnt 2017: 6, 423 Students")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mMapH.moveCamera(CameraUpdateFactory.newLatLng(sheridanH));

        mMapT.addMarker(new MarkerOptions()
                .position(sheridanT)
                .title("Sheridan Trafalgar campus")
                .snippet("Enrollement 2017: 10, 425 Students")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMapT.moveCamera(CameraUpdateFactory.newLatLng(sheridanT));

        CircleOptions circleOptions = new CircleOptions()
                .center(sheridanH)
                .radius(15000)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY)
                .strokeWidth(8);
//                .fillColor(8) ;
        Circle circle = mMapH.addCircle(circleOptions);


    }
}
