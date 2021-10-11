package com.example.thefixer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.thefixer.R;
import com.example.thefixer.databinding.FragmentAccountBinding;

public class AccountFragment extends AppCompatActivity {
    private Button btnEditAccount, btnChangePassword, btnLogout;

    public void clickToEditAccount(View view) {
        Intent intent = new Intent(AccountFragment.this, EditAccountActivity.class);
        startActivity(intent);
    }

    public void clickToEditPassword(View view) {
        Intent intent = new Intent(AccountFragment.this, EditAccountActivity.class);
        startActivity(intent);
    }

    public void clickToLogout(View view) {
        Intent intent = new Intent(AccountFragment.this, LoginActivity.class);
        startActivity(intent);
    }
}