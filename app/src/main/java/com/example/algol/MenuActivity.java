package com.example.algol;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.algol.database.AlgolDbSchema.MainMenuTable;

import com.example.algol.database.SimpleDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerMenuAdapter adapter;
    private List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        readFromDatabase();

        adapter = new RecyclerMenuAdapter(items, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void readFromDatabase() {
        items = new ArrayList<>();
        try {
            SQLiteOpenHelper databaseHelper = new SimpleDatabaseHelper(this);
            SQLiteDatabase database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(MainMenuTable.NAME, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {
                    items.add(cursor.getString(2));
                    cursor.moveToNext();
                }
            }
            cursor.close();
            database.close();
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}