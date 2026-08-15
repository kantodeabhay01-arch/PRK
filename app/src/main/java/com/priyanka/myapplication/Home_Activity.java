package com.priyanka.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.priyanka.myapplication.fragment.ExploreFragment;
import com.priyanka.myapplication.fragment.HomeFragment;
import com.priyanka.myapplication.fragment.ProfileFragment;
import com.priyanka.myapplication.fragment.SessionsFragment;

public class Home_Activity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    ExploreFragment exploreFragment = new ExploreFragment();
    SessionsFragment sessionsFragment = new SessionsFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Home");

        // Bottom Navigation
        bottomNavigationView = findViewById(R.id.HomeBottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Open Home Fragment by default
        if (savedInstanceState == null) {
            loadFragment(homeFragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.HomeFrameLayout, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.itemHome) {
            loadFragment(homeFragment);

        } else if (id == R.id.itemExplore) {
            loadFragment(exploreFragment);

        } else if (id == R.id.itemSession) {
            loadFragment(sessionsFragment);

        } else if (id == R.id.Profile) {
            loadFragment(profileFragment);

        } else {
            return false;
        }

        return true;
    }
}