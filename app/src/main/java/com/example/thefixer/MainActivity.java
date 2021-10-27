package com.example.thefixer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.thefixer.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

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

//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_activity, R.id.navigation_home, R.id.navigation_activity, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }


    private Bundle createAdmin() {
        AccountDTO dto1 = new AccountDTO("anna", "123456", "user");
        AccountDTO dto2 = new AccountDTO("fixer", "123456", "fixer");
        ArrayList<AccountDTO> listAccount = new ArrayList<>();
        listAccount.add(dto1);
        listAccount.add(dto2);
        boolean isLogged = false;
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("account", listAccount);
        bundle.putBoolean("isLogged", isLogged);
        bundle.putString("role", "user");
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
                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
                BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
                if (bundle.getString("role").equals("fixer")) {
                    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                            R.id.navigation_fixer_activity, R.id.navigation_fixer_homepage, R.id.navigation_inbox, R.id.navigation_account)
                            .build();
                    navController.setGraph(R.navigation.fixer_navigation);
                    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
                    NavigationUI.setupWithNavController(binding.navView, navController);

                    bottomNavigationView.getMenu().clear();
                    bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu_fixer);
                } else {
                    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                            R.id.navigation_activity, R.id.navigation_home, R.id.navigation_activity, R.id.navigation_account)
                            .build();
                    navController.setGraph(R.navigation.mobile_navigation);
                    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
                    NavigationUI.setupWithNavController(binding.navView, navController);
                    bottomNavigationView.getMenu().clear();
                    bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu);
                }
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

    public void clickToEditAccount(View view) {
        Intent intent = new Intent(this, EditAccountActivity.class);
        startActivity(intent);
    }

    public void clickToChat(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void clickToGetReady(View view) {
        Intent intent = new Intent(this, FixerMatching.class);
        startActivity(intent);
    }

    public void clickToLogout(View view) {
        checkLogin();
    }
}