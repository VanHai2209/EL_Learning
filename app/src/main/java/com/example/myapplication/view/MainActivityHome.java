package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.myapplication.view.fragment.GameFragment;
import com.example.myapplication.view.fragment.HomeFragment;
import com.example.myapplication.view.fragment.ProfileFragment;
import com.example.myapplication.view.fragment.GrammarFragment;
import com.example.myapplication.R;
import com.example.myapplication.view.fragment.RankFragment;
import com.example.myapplication.view.fragment.SearchFragment;
import com.example.myapplication.view.fragment.TopicFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivityHome extends AppCompatActivity  {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar home_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        home_toolbar = findViewById(R.id.home_toolbar);
        home_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.ic_menu){
                    drawerLayout.openDrawer(navigationView);
                    return true;
                }
                return false;
            }
        });
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.nav_home){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    HomeFragment homeFragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.fragment_container, homeFragment);
                    fragmentTransaction.commit();
                    home_toolbar.setTitle("EL Learning");
                }
                else if (itemId == R.id.nav_program){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    GrammarFragment grammarFragment = new GrammarFragment();
                    fragmentTransaction.replace(R.id.fragment_container, grammarFragment);
                    fragmentTransaction.commit();
                    home_toolbar.setTitle("Program");
                }
                else if (itemId == R.id.nav_search){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SearchFragment searchFragment = new SearchFragment();
                    fragmentTransaction.replace(R.id.fragment_container, searchFragment);
                    fragmentTransaction.commit();
                    home_toolbar.setTitle("Dictionary");
                }
                else if (itemId == R.id.nav_game) {
                    home_toolbar.setTitle("Game");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    GameFragment gameFragment = new GameFragment();
                    fragmentTransaction.replace(R.id.fragment_container, gameFragment);
                    fragmentTransaction.commit();
                }
                else if (itemId == R.id.nav_profile) {
                    home_toolbar.setTitle("Profile");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ProfileFragment profileFragment = new ProfileFragment();
                    fragmentTransaction.replace(R.id.fragment_container, profileFragment);
                    fragmentTransaction.commit();
                }
                else if (itemId == R.id.nav_rank) {
                    home_toolbar.setTitle("Rank");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    RankFragment rankFragment = new RankFragment();
                    fragmentTransaction.replace(R.id.fragment_container, rankFragment);
                    fragmentTransaction.commit();
                }
                else if(itemId == R.id.nav_topic){
                    home_toolbar.setTitle("Topic");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    TopicFragment topicFragment = new TopicFragment();
                    fragmentTransaction.replace(R.id.fragment_container, topicFragment);
                    fragmentTransaction.commit();
                }
                else if (itemId == R.id.nav_myList) {
                    home_toolbar.setTitle("My List");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SearchFragment searchFragment = new SearchFragment();
                    fragmentTransaction.replace(R.id.fragment_container, searchFragment);
                    fragmentTransaction.commit();
                }
                else{
                    Toast.makeText(MainActivityHome.this, "Log Out !", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}