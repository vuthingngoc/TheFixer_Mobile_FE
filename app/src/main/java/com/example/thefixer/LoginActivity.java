package com.example.thefixer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText edtId, edtPassword;
    private Button btnLogin, btnSignup;
    private static final int CODE = 2212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUI();
        setupListeners();
        setTitle("Login Page");

    }

    private void setupUI() {
        edtId = findViewById(R.id.edtId);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername();
            }
        });
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
                ArrayList<AccountDTO> accountList = bundle.getParcelableArrayList("account");
                boolean check = false;
                for (AccountDTO dto : accountList) {
                    if (dto.getUsername().equals(edtId.getText().toString()) && dto.getPassword().equals(edtPassword.getText().toString())) {
                        Toast.makeText(this, "Login succeed", Toast.LENGTH_SHORT).show();
                        bundle.putBoolean("isLogged", true);
                        bundle.putString("role", dto.getRole());
                        Intent i = this.getIntent();
                        intent.putExtra("info", bundle);
                        check = true;
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        this.setResult(RESULT_OK, intent);
                        this.finish();
                        break;
                    }
                }
                if (!check) {
                    Toast.makeText(this, "Wrong ID or password!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast t = Toast.makeText(this, "Data is not valid", Toast.LENGTH_SHORT);
                edtId.setError("Data is not valid");
            }
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void clickToSignup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void clickToNewPass(View view) {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("info");
        if (bundle != null) {
            intent = new Intent(LoginActivity.this, FogetActivity.class);
            intent.putExtra("info", bundle);
            startActivity(intent);
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CODE) {
//            if (resultCode == RESULT_OK) {
//                Bundle bundle = data.getBundleExtra("info");
//            }
//        }
//    }

}