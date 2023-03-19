package com.example.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyecto1.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MainFragment());

        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_settings:;
                        replaceFragment(new SettingsFragment());
                        break;
                    case R.id.navigation_main:;
                        replaceFragment(new MainFragment());
                        break;
                    case R.id.navigation_recetas:;
                        replaceFragment(new RecetasFragment());

                        break;
                }
                return true;
            }
        });
    }
    //Metodo para reemplazar los fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}