package com.example.thefixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class EditInfoActivity extends AppCompatActivity {
    private EditText edtInfoAddress, edtInfoName, edtInfoPhone;
    private TextView txtInfoAddress, txtInfoName, txtInfoPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        setTitle("Edit Your Information");
        edtInfoAddress = findViewById(R.id.edtInfoAddress);
        edtInfoName = findViewById(R.id.edtInfoName);
        edtInfoPhone = findViewById(R.id.edtInfoPhone);

        txtInfoAddress = findViewById(R.id.txtInfoAddress);
        txtInfoName = findViewById(R.id.txtInfoName);
        txtInfoPhone = findViewById(R.id.txtInfoPhone);

        Intent intent = this.getIntent();
        String[] info = intent.getStringExtra("info").split("-");
        edtInfoAddress.setText(info[0]);
        edtInfoName.setText(info[1]);
        edtInfoPhone.setText(info[2]);
        
    }

    public void clickToSaveInfo(View view) {
        String info = edtInfoAddress.getText().toString() + "-" + edtInfoName.getText().toString() + "-" + edtInfoPhone.getText().toString();
        Intent intent = this.getIntent();
        intent.putExtra("INFO", info);
        this.setResult(RESULT_OK, intent);
        finish();
    }
}