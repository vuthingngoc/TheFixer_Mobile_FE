package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    private TextView txtFixerDetailCategory;
    private String device, problem;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView btnNo, btnYes;
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
        AccountDTO dto1 = new AccountDTO("anna", "123456", "user");
        AccountDTO dto2 = new AccountDTO("fixer", "123456", "fixer");
        ArrayList<AccountDTO> listAccount = new ArrayList<>();
        listAccount.add(dto1);
        listAccount.add(dto2);
        boolean isLogged = true;
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("account", listAccount);
        bundle.putBoolean("isLogged", isLogged);
        bundle.putString("role", "user");
        return bundle;
    }

    private void cancelService() {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = createAdmin();
        intent.putExtra("info", bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void createCancelServices(View v) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        ToggleButton toggle1 = (ToggleButton) contactPopupView.findViewById(R.id.toggleButton1);
        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    toggle1.setTextColor(Color.RED);
                    toggle1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_linearlayout_computer));
                } else {
                    toggle1.setTextColor(Color.BLACK);
                    toggle1.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_linearlayout_vehicle));
                }
            }
        });

        ToggleButton toggle4 = (ToggleButton) contactPopupView.findViewById(R.id.toggleButton4);
        toggle4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    toggle4.setTextColor(Color.RED);
                    toggle4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_linearlayout_computer));
                } else {
                    toggle4.setTextColor(Color.BLACK);
                    toggle4.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_linearlayout_vehicle));
                }
            }
        });

        ToggleButton toggle2 = (ToggleButton) contactPopupView.findViewById(R.id.toggleButton2);
        toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    toggle2.setTextColor(Color.RED);
                    toggle2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_linearlayout_computer));
                } else {
                    toggle2.setTextColor(Color.BLACK);
                    toggle2.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_linearlayout_vehicle));
                }
            }
        });

        ToggleButton toggle3 = (ToggleButton) contactPopupView.findViewById(R.id.toggleButton3);
        toggle3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    toggle3.setTextColor(Color.RED);
                    toggle3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_linearlayout_computer));
                } else {
                    toggle3.setTextColor(Color.BLACK);
                    toggle3.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_linearlayout_vehicle));
                }
            }
        });

        btnYes = contactPopupView.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                cancelService();
            }
        });

        btnNo = contactPopupView.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}