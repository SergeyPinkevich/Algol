package com.example.algol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.algol.database.AlgolDbSchema.MainMenuTable;
import com.example.algol.database.SimpleDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerMenuAdapter adapter;
    private List<String> items;
    private SQLiteDatabase mDatabase;
    private Cursor mCursor;

    private DrawerLayout drawerLayout;
    private View content;
    private RecyclerView recyclerView;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        readFromDatabase();

        adapter = new RecyclerMenuAdapter(items, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setListener(new RecyclerMenuAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MenuActivity.this, AlgorithmDetailsActivity.class);
                intent.putExtra(AlgorithmDetailsActivity.ALGORITHM_ID, position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void readFromDatabase() {
        items = new ArrayList<>();
        try {
            SQLiteOpenHelper databaseHelper = new SimpleDatabaseHelper(this);
            mDatabase = databaseHelper.getReadableDatabase();

            Algorithm.algorithms = new ArrayList<>();

            mCursor = mDatabase.query(MainMenuTable.NAME, null, null, null, null, null, null);
            if (mCursor.moveToFirst()) {
                while (mCursor.isAfterLast() == false) {
                    Algorithm temp = new Algorithm(mCursor.getInt(0), mCursor.getString(2), mCursor.getString(1), mCursor.getString(3));
                    Algorithm.algorithms.add(temp);
                    items.add(mCursor.getString(2));
                    mCursor.moveToNext();
                }
            }
            close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database is unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Reset SearchView
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchItem.collapseActionView();
                // Set Title to search query
                MenuActivity.this.setTitle(query);
                searchByQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchByQuery(newText);
                return true;
            }
        });
        return true;
    }

    public void searchByQuery(String query) {
        ArrayList newList = new ArrayList();
        if (query.equals(""))
            newList.addAll(items);
        else {
            query = query.toLowerCase();
            readFromDatabase();
            for (String name : items) {
                String temp = name.toLowerCase();
                if (temp.contains(query))
                    newList.add(name);
            }
        }
        adapter.setFilter(newList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        close();
    }

    public void close() {
        mCursor.close();
        mDatabase.close();
    }
}