package com.example.thefixer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmFixedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_fixed);
    }
    public void clickToConfirm(View view) {
        Intent intent = new Intent("Confirm");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message = "Send OK";
                Toast.makeText(ConfirmFixedActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter("Confirm"));
        finish();
    }
}