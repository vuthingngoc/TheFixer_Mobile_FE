package com.example.thefixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FixerInfoActivity extends AppCompatActivity {

    private TextView txtFixerDetailName, txtFixerDetailCategory, txtFixerDetailRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixer_info);
        txtFixerDetailName = findViewById(R.id.txtFixerDetailName);
        txtFixerDetailCategory = findViewById(R.id.txtFixerDetailCategory);
        txtFixerDetailRating = findViewById(R.id.txtFixerDetailRating);
        Intent intent = this.getIntent();
        String[] info = intent.getStringExtra("info").split("-");
        txtFixerDetailName.setText(info[0]);
        txtFixerDetailCategory.setText(info[1]);
        txtFixerDetailRating.setText(info[2]);
    }
}