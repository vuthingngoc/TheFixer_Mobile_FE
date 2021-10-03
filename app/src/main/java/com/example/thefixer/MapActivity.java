package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney1 = new LatLng(10.8316482, 106.6754297);
        map.addMarker(new MarkerOptions().position(sydney1).title("ĐH FPT"));
        map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney1));

        LatLng sydney2 = new LatLng(10.8398768, 106.7931315);
        map.addMarker(new MarkerOptions().position(sydney2).title("LXO"));
        map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney2));

        LatLng sydney3 = new LatLng(10.8485446, 106.7713973);
        map.addMarker(new MarkerOptions().position(sydney3).title("Ngã tư TĐ"));
        map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney3));

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                String markerTitle = marker.getTitle();
                Intent intent = new Intent(MapActivity.this, FixerInfomationActivity.class);
                intent.putExtra("title", markerTitle);
                startActivity(intent);
                return false;
            }
        });
    }
}