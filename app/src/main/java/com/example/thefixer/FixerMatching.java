package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class FixerMatching extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    LatLng mylocation, userLocation;
    LinearLayout llFixerMap, llFixerInfo;
    TextView txtTheFixer;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView btnNo, btnYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixer_matching);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFixerMatching);
        mapFragment.getMapAsync(this);

        llFixerInfo = findViewById(R.id.llFixerInfo);
        llFixerMap = findViewById(R.id.llFixerMap);
        txtTheFixer = findViewById(R.id.txtTheFixer);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        mylocation = new LatLng(10.8351754, 106.8053879);
        map.addMarker(new MarkerOptions().position(mylocation));
        map.moveCamera(CameraUpdateFactory.newLatLng(mylocation));

        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        map.addCircle(new CircleOptions()
                .center(mylocation)
                .radius(1000)
                .strokeWidth(0)
                .fillColor(Color.argb(80,122,202,68)));
    }


    public void clickToMoveToMyLocation(View view) {
        map.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
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
        bundle.putString("role", "fixer");
        return bundle;
    }

    public void clickToUnready(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = createAdmin();
        intent.putExtra("info", bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

    public void clickToGetBooked(View view) {
        map.clear();
        mylocation = new LatLng(10.8351754, 106.8053879);
        map.addMarker(new MarkerOptions().position(mylocation));
        map.moveCamera(CameraUpdateFactory.newLatLng(mylocation));

        userLocation = new LatLng(10.831255, 106.805956);
        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.user_circle)).position(userLocation));
        map.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
        map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);

        llFixerMap.removeView(llFixerInfo);
        llFixerMap.removeView(txtTheFixer);



    }

    private void cancelService() {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = createAdmin();
        intent.putExtra("info", bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

    public void clickToCancelFixer(View view) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_fixer, null);

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