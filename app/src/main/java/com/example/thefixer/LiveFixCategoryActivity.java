package com.example.thefixer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LiveFixCategoryActivity extends AppCompatActivity {

    private static final int CREATE = 65580;
    private TextView txtInfoAddress, txtInfoName, txtInfoPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_fix_category);
        setTitle("Category Select");
        txtInfoAddress = findViewById(R.id.txtInfoAddress);
        txtInfoName = findViewById(R.id.txtInfoName);
        txtInfoPhone = findViewById(R.id.txtInfoPhone);
    }

    public void clickToEditInfo(View view) {
        Intent intent = new Intent(this, EditInfoActivity.class);
        String info = txtInfoAddress.getText().toString() + "-" + txtInfoName.getText().toString() + "-" + txtInfoPhone.getText().toString();
        intent.putExtra("info", info);
        startActivityForResult(intent, CREATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATE) {
            if(resultCode == RESULT_OK) {
                String result = data.getStringExtra("INFO");
                String[] info = result.split("-");
                txtInfoAddress.setText(info[0]);
                txtInfoName.setText(info[1]);
                txtInfoPhone.setText(info[2]);
            }
        }
    }

    public void clickToSelectDevice(View view) {
        Intent intent = new Intent(this, DiviceDetailActivity.class);
        String deviceSelected = view.getTag().toString();
        String userInfo = txtInfoAddress.getText().toString() + "-" + txtInfoName.getText().toString() + "-" + txtInfoPhone.getText().toString();
        intent.putExtra("USER_INFO", userInfo);
        intent.putExtra("USER_DEVICE", deviceSelected);
        startActivity(intent);
    }
}