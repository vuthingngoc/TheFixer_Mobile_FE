package com.example.thefixer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.thefixer.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Bundle bundle = createAdmin();
    private static final int CODE = 2212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("info");
        if (bundle == null) {
            checkLogin();
        } else {
            if (bundle.getBoolean("isLogged") == false) {
                checkLogin();
            }
        }


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_activity, R.id.navigation_inbox, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }


    private Bundle createAdmin() {
        String id = "admin";
        String password = "admin";
        String name = "Admin";
        String email = "admin@gmail.com";
        String phone = "123";
        String address = "admin";
        boolean isLogged = false;
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("password", password);
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("phone", phone);
        bundle.putString("address", address);
        bundle.putBoolean("isLogged", isLogged);
        return bundle;
    }

    private void checkLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("info", bundle);
        //startActivity(intent);
        startActivityForResult(intent, CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra("info");
            }
        }
    }

    public void clickToCheckFixerInfo(View view) {
        Intent intent = new Intent(this, FixerInfoActivity.class);
        String info = view.getTag().toString();
        intent.putExtra("info", info);
        startActivity(intent);
    }

    public void clickToLiveFix(View view) {
        Intent intent = new Intent(this, LiveFixCategoryActivity.class);
        startActivity(intent);
    }

    public void clickToChat(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}