package com.example.algol;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Сергей Пинкевич on 05.11.2016.
 */

public class RecyclerMenuAdapter extends RecyclerView.Adapter {

    private ArrayList data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mTitle;
        private ListView mList;
        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.recycler_icon);
            mTitle = (TextView) itemView.findViewById(R.id.recycler_title);
            mList = (ListView) itemView.findViewById(R.id.recycler_list);
        }
    }

    @Override
    public RecyclerMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}