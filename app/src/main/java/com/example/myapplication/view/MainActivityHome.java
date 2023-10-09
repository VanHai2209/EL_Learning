package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.dialog.DialogLogout;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.view.fragment.FlashCardFragment;
import com.example.myapplication.view.fragment.GameFragment;
import com.example.myapplication.view.fragment.HomeFragment;
import com.example.myapplication.view.fragment.MyListFragment;
import com.example.myapplication.view.fragment.OnFragmentInteractionListener;
import com.example.myapplication.view.fragment.ProfileFragment;
import com.example.myapplication.view.fragment.GrammarFragment;
import com.example.myapplication.R;
import com.example.myapplication.view.fragment.RankFragment;
import com.example.myapplication.view.fragment.SearchFragment;
import com.example.myapplication.view.fragment.TopicFragment;
import com.example.myapplication.viewModel.HomeViewModel;
import com.google.android.material.navigation.NavigationView;

public class MainActivityHome extends AppCompatActivity implements OnFragmentInteractionListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar home_toolbar;
    TextView txtUsername, txtEmail;
    HomeViewModel homeViewModel;
    String email, token_login, idPerson;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        sharedPreferences = getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        email = sharedPreferences.getString("Email", null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        txtUsername = header.findViewById(R.id.txtUserName);
        txtEmail = header.findViewById(R.id.txtEmail);
        home_toolbar = findViewById(R.id.home_toolbar);
        home_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.ic_menu){
                    homeViewModel = new ViewModelProvider(MainActivityHome.this).get(HomeViewModel.class);
                    homeViewModel.getInforUser(email, token_login);
                    homeViewModel.getData().observe(MainActivityHome.this, new Observer<GetInforResponse>() {
                        @Override
                        public void onChanged(GetInforResponse getInforResponse) {
                            txtUsername.setText(getInforResponse.getDataUser().getUsername());
                            txtEmail.setText(getInforResponse.getDataUser().getEmail());
                        }
                    });
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
                else if (itemId == R.id.nav_grammar){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    GrammarFragment grammarFragment = new GrammarFragment();
                    fragmentTransaction.replace(R.id.fragment_container, grammarFragment);
                    fragmentTransaction.commit();
                    home_toolbar.setTitle("Grammar");
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
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
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
                    MyListFragment myListFragment = MyListFragment.newInstance(token_login, idPerson);
                    fragmentTransaction.replace(R.id.fragment_container, myListFragment);
                    fragmentTransaction.commit();
                }
                else if (itemId == R.id.nav_flashcard) {
                    home_toolbar.setTitle("Flash Card");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FlashCardFragment flashCardFragment = FlashCardFragment.newInstance(token_login, idPerson);
                    fragmentTransaction.replace(R.id.fragment_container, flashCardFragment);
                    fragmentTransaction.commit();
                }
                else{
                    DialogLogout dialogLogout = new DialogLogout(MainActivityHome.this, new DialogLogout.DialogCallback() {
                        @Override
                        public void onCancelClicked() {

                        }

                        @Override
                        public void onLogoutClicked() {
                            Intent intent = new Intent(MainActivityHome.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            Toast.makeText(MainActivityHome.this, "Log Out !", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialogLogout.setCanceledOnTouchOutside(false);
                    dialogLogout.show();

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

        }
    }

    @Override
    public void onClicked(String nameFragment) {
        home_toolbar.setTitle(nameFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(nameFragment.equals("Rank")){
            navigationView.setCheckedItem(R.id.nav_rank);
            RankFragment rankFragment = new RankFragment();
            fragmentTransaction.replace(R.id.fragment_container, rankFragment);
            fragmentTransaction.commit();
        } else if (nameFragment.equals("Topic")) {
            navigationView.setCheckedItem(R.id.nav_topic);
            TopicFragment topicFragment = new TopicFragment();
            fragmentTransaction.replace(R.id.fragment_container, topicFragment);
            fragmentTransaction.commit();
        }
        else {
            navigationView.setCheckedItem(R.id.nav_search);
            SearchFragment searchFragment = new SearchFragment();
            fragmentTransaction.replace(R.id.fragment_container, searchFragment);
            fragmentTransaction.commit();
        }
    }
}