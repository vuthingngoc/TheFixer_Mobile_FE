package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class ConfirmActivity extends AppCompatActivity {

    private ProgressDialog myProgress;
    private TextView edtDate, edtProduct, edtProblem, txtFixerDetailCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        edtDate = findViewById(R.id.edtDate);
        edtProduct = findViewById(R.id.edtProduct);
        edtProblem = findViewById(R.id.edtProblem);
        Date date = new Date();
        edtDate.setText(date.toString());
        Intent intent = getIntent();
        edtProduct.setText("Mobile");
        edtProblem.setText("Not active!");
        txtFixerDetailCategory = findViewById(R.id.txtFixerDetailCategory);
        txtFixerDetailCategory.setText(intent.getStringExtra("CATEGORY"));
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

    public void clickToConfirm(View view) {
        myProgress = new ProgressDialog(ConfirmActivity.this);
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
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = createAdmin();
        intent.putExtra("info", bundle);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            myProgress.incrementProgressBy(1);
        }
    };
}