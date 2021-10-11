package com.example.thefixer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class EditAccountActivity extends AppCompatActivity {
    private EditText edtFirstName, edtLastName, edtDOB, edtPhone, edtAddress;
    private TextView txtFirstName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        setTitle("Edit Your Account Information");
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtDOB = findViewById(R.id.edtDOB);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);

        txtFirstName = findViewById(R.id.txtAccountName);


        Intent intent = this.getIntent();
        String[] info = intent.getStringExtra("info").split("-");
        edtFirstName.setText(info[0]);
        edtLastName.setText(info[1]);
        edtDOB.setText(info[2]);
        edtPhone.setText(info[3]);
        edtAddress.setText(info[4]);
    }

    public void clickToSaveInfoAccount(View view) {
        String info = edtFirstName.getText().toString()
                + "-" + edtLastName.getText().toString()
                + "-" + edtDOB.getText().toString()
                + "-" + edtPhone.getText().toString()
                + "-" + edtAddress.getText().toString();
        Intent intent = this.getIntent();
        intent.putExtra("INFO_ACCOUNT", info);
        this.setResult(RESULT_OK, intent);
        finish();
    }
}