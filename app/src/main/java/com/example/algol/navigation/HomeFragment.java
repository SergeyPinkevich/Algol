package com.example.algol.navigation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.algol.Algorithm;
import com.example.algol.AlgorithmDetailsActivity;
import com.example.algol.NewAlgorithmActivity;
import com.example.algol.R;
import com.example.algol.RecyclerMenuAdapter;
import com.example.algol.database.AlgorithmRepo;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private AlgorithmRepo mAlgorithmRepo;
    private ArrayList<Algorithm> algorithmsList;
    private List<String> items;
    private RecyclerView mRecyclerView;
    private RecyclerMenuAdapter adapter;
    private FloatingActionButton mSubmit;

    public HomeFragment() {
        // Empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        readFromDatabase(inflater.getContext());

        adapter = new RecyclerMenuAdapter(items, getActivity().getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mSubmit = (FloatingActionButton) rootView.findViewById(R.id.add_algorithm);
        mSubmit.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), NewAlgorithmActivity.class);
            startActivity(intent);
        });

        adapter.setListener(position -> {
                Intent intent = new Intent(inflater.getContext(), AlgorithmDetailsActivity.class);
                String name = items.get(position);
                for (Algorithm a : algorithmsList)
                    if (a.getName() == name)
                        intent.putExtra(AlgorithmDetailsActivity.ALGORITHM_ID, a.getId());
                startActivity(intent);
            }
        );

        return rootView;
    }

    public void readFromDatabase(Context context) {
        items = new ArrayList<>();

        mAlgorithmRepo = new AlgorithmRepo(context);
        algorithmsList = mAlgorithmRepo.getAlgorithmsList();
        for (Algorithm a : algorithmsList)
            items.add(a.getName());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
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
                getActivity().setTitle(query);
                searchByQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchByQuery(newText);
                return true;
            }
        });
    }

    public void searchByQuery(String query) {
        ArrayList<String> newList = new ArrayList();
        if (query.equals(""))
            for (Algorithm algorithm : algorithmsList)
                newList.add(algorithm.getName());
        else {
            query = query.toLowerCase();
            readFromDatabase(getActivity().getApplicationContext());
            for (String name : items) {
                String temp = name.toLowerCase();
                if (temp.contains(query))
                    newList.add(name);
            }
        }
        items = newList;
        adapter.setFilter(newList);
    }
}
