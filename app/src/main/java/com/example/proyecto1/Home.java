package com.example.proyecto1;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyecto1.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
    private String lastFragment;

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            lastFragment = savedInstanceState.getString("fragment_act");
            if (lastFragment != null) {
                restoreLastFragment();
            } else {
                replaceFragment(new MainFragment());
            }
        } else {
            replaceFragment(new MainFragment());
        }
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_settings:;
                        lastFragment = "settings";
                        replaceFragment(new SettingsFragment());
                        break;
                    case R.id.navigation_main:;
                        lastFragment = "main";
                        replaceFragment(new MainFragment());
                        break;
                    case R.id.navigation_recetas:;
                        lastFragment = "recetas";
                        replaceFragment(new RecetasFragment());
                        break;
                }
                return true;
            }
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("fragment_act", lastFragment);
    }

    //Metodo para reemplazar los fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    private void restoreLastFragment() {
        switch (lastFragment) {
            case "settings":
                replaceFragment(new SettingsFragment());
                break;
            case "main":
                replaceFragment(new MainFragment());
                break;
            case "recetas":
                replaceFragment(new RecetasFragment());
                break;
        }

}}