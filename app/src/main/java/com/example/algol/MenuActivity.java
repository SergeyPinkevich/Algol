package com.example.algol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.algol.auth.LoginActivity;
import com.example.algol.navigation.HomeFragment;
import com.example.algol.navigation.NotificationFragment;
import com.example.algol.navigation.SettingsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    private FragmentTransaction mFragmentTransaction;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

    private FirebaseAuth mAuth;
    private TextView mUserEmailText;
    private String mUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setupNavigationDrawer();

        setupNavigationView();

//        setUserEmail();

        setNewFragment(new HomeFragment());
    }

    private void setUserEmail() {
        mAuth = FirebaseAuth.getInstance();
        mUserEmail = mAuth.getCurrentUser().getEmail();
        View headerView = mNavigationView.getHeaderView(0);
        mUserEmailText = (TextView)headerView.findViewById(R.id.login_text);
        mUserEmailText.setText(mUserEmail);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setupNavigationDrawer() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void setupNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setNewFragment(new HomeFragment());
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        setNewFragment(new SettingsFragment());
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_notifications:
                        setNewFragment(new NotificationFragment());
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_logout:
                        logout();
                        break;
                }
                return false;
            }
        });
    }

    private void logout() {
        mDrawerLayout.closeDrawers();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MenuActivity.this, LoginActivity.class));
        finish();
    }

    private void setNewFragment(Fragment fragment) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_container, fragment);
        mFragmentTransaction.commit();
    }
}