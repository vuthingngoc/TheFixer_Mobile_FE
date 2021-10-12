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

public class FogetActivity extends AppCompatActivity {

    private EditText edtId, edtPassword, edtEmail, edtPhone, edtConfirm;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget);
        setupUI();
    }

    private void setupUI() {
        edtId = findViewById(R.id.edtId);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirm = findViewById(R.id.edtConfirm);
    }

    public void clickToChange(View view) {
        if (checkDataEntered()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("info", bundle);
            Toast t = Toast.makeText(this, "Change succeed", Toast.LENGTH_SHORT);
            t.show();
            this.finish();
            startActivity(intent);
        }

    }


    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    private boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean checkDataEntered() {
        boolean check = true;
        if (isEmpty(edtId)) {
            edtId.setError("You must enter ID Account to Change!");
            Toast t = Toast.makeText(this, "You must enter ID Account to Change!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (isEmpty(edtEmail)) {
            Toast t = Toast.makeText(this, "You must enter Email to change!", Toast.LENGTH_SHORT);
            edtEmail.setError("You must enter Email to change!");
            t.show();
            check = false;
        } else if (!isEmail(edtEmail)) {
            edtEmail.setError("Enter valid email!");
            Toast t = Toast.makeText(this, "Enter valid email!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (isEmpty(edtPhone)) {
            edtPhone.setError("You must enter Phone to register!");
            Toast t = Toast.makeText(this, "You must enter Phone to change!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (isEmpty(edtPassword)) {
            edtPassword.setError("You must enter Password to change!");
            Toast t = Toast.makeText(this, "You must enter Password to change!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        }  else if (edtPassword.getText().toString().length() < 4) {
            edtPassword.setError("Password must be at least 4 chars long!");
            Toast t = Toast.makeText(this, "Password must be at least 4 chars long!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else if (!edtPassword.getText().toString().equals(edtConfirm.getText().toString())) {
            Toast t = Toast.makeText(this, "Confirmation is not correct!", Toast.LENGTH_SHORT);
            t.show();
            edtConfirm.setError("Confirmation is not correct!");
            check = false;
        } else if (edtConfirm.getText().toString().length() < 4) {
            edtConfirm.setError("Confirm must be at least 4 chars long!");
            Toast t = Toast.makeText(this, "Password must be at least 4 chars long!", Toast.LENGTH_SHORT);
            t.show();
            check = false;
        } else {
            Intent intent = this.getIntent();
            bundle = intent.getBundleExtra("info");
            if (bundle != null) {
                String idValue = bundle.getString("id");
                String emailValue = bundle.getString("email");
                String phoneValue = bundle.getString("phone");
                if (!idValue.equals(edtId.getText().toString())) {
                    Toast t = Toast.makeText(this, "Your id is not exist!", Toast.LENGTH_SHORT);
                    edtId.setError("Your id is not exist!");
                    t.show();
                    check = false;
                } else if (!emailValue.equals(edtEmail.getText().toString())) {
                    Toast t = Toast.makeText(this, "Your email is wrong!", Toast.LENGTH_SHORT);
                    edtEmail.setError("You must enter your register Email!");
                    t.show();
                    check = false;
                } else if (!phoneValue.equals(edtPhone.getText().toString())) {
                    Toast t = Toast.makeText(this, "Your phone is wrong!", Toast.LENGTH_SHORT);
                    edtPhone.setError("You must enter your register Phone!");
                    t.show();
                    check = false;
                } else {
                    //everything checked we open new activity
                    bundle.putString("password", edtPassword.getText().toString());
                }
            } else {
                Toast t = Toast.makeText(this, "Data is not valid", Toast.LENGTH_SHORT);
                edtId.setError("Data is not valid");
                t.show();
                check = false;
            }
        }
        return check;
    }
}