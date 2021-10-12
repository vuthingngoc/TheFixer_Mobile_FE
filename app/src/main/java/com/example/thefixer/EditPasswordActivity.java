package com.example.thefixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditPasswordActivity extends AppCompatActivity {
    private EditText edtOldPassword, edtNewPassword, edtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        setTitle("Edit Your Password");
        edtOldPassword = findViewById(R.id.edtOldPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirm = findViewById(R.id.edtConfirm);

        Intent intent = this.getIntent();
        String[] info = intent.getStringExtra("password").split("-");
        edtOldPassword.setText(info[0]);
        edtNewPassword.setText(info[1]);
        edtConfirm.setText(info[2]);

    }

    public void clickToSaveInfo(View view) {
        String password = edtNewPassword.getText().toString();
        Intent intent = this.getIntent();
        intent.putExtra("PASSWORD", password);
        this.setResult(RESULT_OK, intent);
        finish();
    }
}