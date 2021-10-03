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
    private EditText edtId, edtPassword, edtName, edtEmail, edtPhone, edtAddress, edtConfirm;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setupUI();
    }

    private void setupUI() {
        edtId = findViewById(R.id.edtId);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirm = findViewById(R.id.edtConfirm);
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
            Toast t = Toast.makeText(this, "Signup succeed", Toast.LENGTH_SHORT);
            t.show();

//            Intent intent = this.getIntent();
//            intent.putExtra("info", bundle);
//            this.setResult(RESULT_OK, intent);
            this.finish();
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
        if (isEmpty(edtName)) {
            Toast t = Toast.makeText(this, "You must enter name to register!", Toast.LENGTH_SHORT);
            t.show();
            edtName.setError("You must enter name to register!");
            check = false;
        } else if (isEmpty(edtEmail)) {
            Toast t = Toast.makeText(this, "You must enter Email to register!", Toast.LENGTH_SHORT);
            edtEmail.setError("You must enter Email to register!");
            t.show();
            check = false;
        } else if (isEmail(edtEmail) == false) {
            edtEmail.setError("Enter valid email!");
            Toast t = Toast.makeText(this, "Enter valid email!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (isEmpty(edtId)) {
            edtId.setError("You must enter ID Account to register!");
            Toast t = Toast.makeText(this, "You must enter ID Account to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (isEmpty(edtAddress)) {
            edtAddress.setError("You must enter Address to register!");
            Toast t = Toast.makeText(this, "You must enter Address to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (isEmpty(edtPhone)) {
            edtPhone.setError("You must enter Phone to register!");
            Toast t = Toast.makeText(this, "You must enter Phone to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (isEmpty(edtPassword)) {
            edtPassword.setError("You must enter Password to register!");
            Toast t = Toast.makeText(this, "You must enter Password to register!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (edtPassword.getText().toString().length() < 4) {
            edtPassword.setError("Password must be at least 4 chars long!");
            Toast t = Toast.makeText(this, "Password must be at least 4 chars long!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (!edtPassword.getText().toString().equals(edtConfirm.getText().toString())) {
            Toast t = Toast.makeText(this, "Confirmation is not correct!", Toast.LENGTH_SHORT);
            t.show();
            edtPassword.setError("Confirmation is not correct!");
            check = false;
        }else if (edtConfirm.getText().toString().length() < 4) {
            edtConfirm.setError("Confirm must be at least 4 chars long!");
            Toast t = Toast.makeText(this, "Password must be at least 4 chars long!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        }
        return check;
    }


}