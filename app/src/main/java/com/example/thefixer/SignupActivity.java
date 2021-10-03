package com.example.thefixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private EditText edtId, edtPassword, edtName, edtEmail, edtPhone, edtAddress;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
//        btnSignup = findViewById(R.id.btnSignup);
//
//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkDataEntered();
//            }
//        });
    }

    private void setupUI() {
        edtId = findViewById(R.id.edtId);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
    }

    public void clickToSignup(View view) {
        if (checkDataEntered()) {
            Bundle bundle = new Bundle();
            bundle.putString("id", edtId.getText().toString());
            bundle.putString("password", edtPassword.getText().toString());
            bundle.putString("name", edtName.getText().toString());
            bundle.putString("email", edtEmail.getText().toString());
            bundle.putString("phone", edtPhone.getText().toString());
            bundle.putString("address", edtAddress.getText().toString());
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("info", bundle);
            startActivity(intent);
        }

    }

    private boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private boolean checkDataEntered() {
        boolean check = true;
        if (isEmpty(edtId)) {
            Toast t = Toast.makeText(this, "You must enter ID Account to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        }
        if (isEmpty(edtPassword)) {
            Toast t = Toast.makeText(this, "You must enter Password to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        }
        if (isEmpty(edtName)) {
            Toast t = Toast.makeText(this, "You must enter name to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        }
        if (isEmpty(edtEmail)) {
            Toast t = Toast.makeText(this, "You must enter Email to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        }
        if (isEmail(edtEmail) == false) {
            edtEmail.setError("Enter valid email!");
            check = false;
        }
        if (isEmpty(edtPhone)) {
            Toast t = Toast.makeText(this, "You must enter Phone to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        }
        if (isEmpty(edtAddress)) {
            Toast t = Toast.makeText(this, "You must enter Address to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        }
        return check;
    }


}