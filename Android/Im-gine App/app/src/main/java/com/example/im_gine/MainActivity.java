package com.example.im_gine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.im_gine.ui.MainActivity.fragments.login.LoginFragment;
import com.example.im_gine.ui.MainActivity.fragments.login.LoginViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import model.ResultActivity;

public class MainActivity extends AppCompatActivity {

    // UI variables
    private AppBarConfiguration appBarConfiguration;

    // Fragments communication
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_chat, R.id.navigation_addContent, R.id.navigation_profile, R.id.navigation_settings, R.id.navigation_login)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MAINACTIVITYRESULT", "triggered the section");
        Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        fragment.onActivityResult(requestCode, resultCode, data);
//        LoginFragment loginFragment = new LoginFragment();
//        loginFragment.onActivityResult(requestCode, resultCode, data);
    }
}
