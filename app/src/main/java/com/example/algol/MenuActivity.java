package com.example.algol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.algol.database.AlgorithmRepo;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerMenuAdapter adapter;
    private List<String> items;
    private AlgorithmRepo mAlgorithmRepo;

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

        adapter.setListener(new RecyclerMenuAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MenuActivity.this, AlgorithmDetailsActivity.class);
                intent.putExtra(AlgorithmDetailsActivity.ALGORITHM_ID, position);
                startActivity(intent);
            }
        });
    }

    public void readFromDatabase() {
        items = new ArrayList<>();

        mAlgorithmRepo = new AlgorithmRepo(this);
        ArrayList<Algorithm> algorithmsList = mAlgorithmRepo.getAlgorithmsList();
        for (Algorithm a : algorithmsList)
            items.add(a.getName());
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
}