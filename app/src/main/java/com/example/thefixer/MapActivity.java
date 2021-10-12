package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    private ProgressDialog myProgress;
    private TextView txtFixerDetailCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txtFixerDetailCategory = findViewById(R.id.txtFixerDetailCategory);
        Intent intent = getIntent();
        txtFixerDetailCategory.setText(intent.getStringExtra("USER_DEVICE").toString());
        // product = intent.getStringExtra("USER_DEVICE");
        //String problem = intent.getStringExtra("Problem");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney1 = new LatLng(10.8316482, 106.6754297);
        map.addMarker(new MarkerOptions().position(sydney1));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney1));

        LatLng sydney2 = new LatLng(10.8351754, 106.8053879);
        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.fixer)).position(sydney2));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney2));

        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);



       /* map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                String markerTitle = marker.getTitle();
                Intent intent = new Intent(MapActivity.this, ConfirmFixedActivity.class);
                startActivity(intent);
                return false;
            }
        });*/
    }

    public void clickToConfirm(View view) {
        myProgress = new ProgressDialog(MapActivity.this);
        myProgress.setMessage("Loading...");
        myProgress.setTitle("Please wait...");
        myProgress.setProgressStyle(myProgress.STYLE_HORIZONTAL);
        myProgress.setProgress(0);
        myProgress.setMax(10);
        myProgress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (myProgress.getProgress() < myProgress.getMax()) {
                        Thread.sleep(1000);
                        System.out.println("runningggg");
                        handler.sendMessage(handler.obtainMessage());
                    }

                    if (myProgress.getProgress() >= myProgress.getMax()) {
                        myProgress.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Intent intent = new Intent(this, ConfirmActivity.class);
        intent.putExtra("CATEGORY", txtFixerDetailCategory.getText().toString());
        startActivity(intent);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            myProgress.incrementProgressBy(1);
        }
    };

    public void clickToChatFixer(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}