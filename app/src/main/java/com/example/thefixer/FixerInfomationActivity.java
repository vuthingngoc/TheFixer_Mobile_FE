package com.example.thefixer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FixerInfomationActivity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixer_infomation);
        text = findViewById(R.id.marker);
        String title =  getIntent().getStringExtra("title");
        text.setText("title");
    }
}