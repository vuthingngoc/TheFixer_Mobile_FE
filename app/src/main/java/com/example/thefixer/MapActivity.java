package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
    private TextView txtFixerDetailCategory;
    private String device, problem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txtFixerDetailCategory = findViewById(R.id.txtFixerDetailCategory);
        Intent intent = getIntent();
        txtFixerDetailCategory.setText(intent.getStringExtra("SERVICES"));
        device = intent.getStringExtra("DEVICE");
        problem = intent.getStringExtra("PROBLEM");
        setTitle("Map Page");
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
        Intent intent = new Intent(this, ConfirmActivity.class);
        intent.putExtra("SERVICES", txtFixerDetailCategory.getText().toString());
        intent.putExtra("PROBLEM", problem);
        intent.putExtra("DEVICE", device);
        startActivity(intent);
    }

    public void clickToChatFixer(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    private Bundle createAdmin() {
        String id = "anna";
        String password = "123456";
        String name = "Admin";
        String email = "admin@gmail.com";
        String phone = "123";
        String address = "admin";
        boolean isLogged = true;
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("password", password);
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("phone", phone);
        bundle.putString("address", address);
        bundle.putBoolean("isLogged", isLogged);
        return bundle;
    }

    private void cancelService() {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = createAdmin();
        intent.putExtra("info", bundle);
        startActivity(intent);
    }

    public void clickToCancelService(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Cancel Service")
                .setMessage("The Fixer is coming do to cancel this service?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        cancelService();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}