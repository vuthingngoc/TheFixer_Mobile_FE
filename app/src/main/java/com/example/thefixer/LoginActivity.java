package com.example.thefixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText edtId, edtPassword;
    private Button btnLogin, btnSignup;
    private String idAdmin = "admin";
    private String passwordAdmin = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUI();
        setupListeners();
    }
    private void setupUI() {
        edtId = findViewById(R.id.edtId);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
    }
    private void setupListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername();
            }
        });
//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
//                startActivity(i);
//            }
//        });
    }

    void checkUsername() {
        boolean isValid = true;
        if (isEmpty(edtId)) {
            edtId.setError("You must enter ID Account to login!");
            isValid = false;
        }


        if (isEmpty(edtPassword)) {
            edtPassword.setError("You must enter password to login!");
            isValid = false;
        } else {
            if (edtPassword.getText().toString().length() < 4) {
                edtPassword.setError("Password must be at least 4 chars long!");
                isValid = false;
            }
        }

        if (isValid) {
            Intent intent = this.getIntent();
            Bundle bundle = intent.getBundleExtra("info");
            if (bundle != null) {
                String idValue = bundle.getString("id");
                String passwordValue = bundle.getString("password");
                if (idValue.equals(edtId.getText().toString()) && passwordValue.equals(edtPassword.getText().toString())) {
                    //everything checked we open new activity
                    Toast t = Toast.makeText(this, "Login succeed", Toast.LENGTH_SHORT);
                    t.show();
                    bundle.putBoolean("isLogged",true);
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("info", bundle);
                    startActivity(i);
                    this.finish();
                } else {
                    Toast t = Toast.makeText(this, "Wrong ID or password!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void clickToSignup(View view) {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }
}