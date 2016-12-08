package com.example.algol;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Сергей Пинкевич on 05.11.2016.
 */

public class RecyclerMenuAdapter extends RecyclerView.Adapter {

    private List<String> items;
    private Context mContext;
    private Listener mListener;

    public interface Listener {
        void onClick(int position);
    }

    public RecyclerMenuAdapter(List<String> items, Context context) {
        this.items = items;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }

    @Override
    public RecyclerMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CardView cardView = (CardView)holder.itemView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.recycler_item_circle);
        TextView textView = (TextView)cardView.findViewById(R.id.recycler_item_title);
        textView.setText(items.get(position));
        TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(textView.getText().charAt(0)), getRandomColor());
        imageView.setBackground(drawable);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onClick(position);
            }
        });
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public int getRandomColor() {
        int colors[] = mContext.getResources().getIntArray(R.array.randomColors);
        return colors[new Random().nextInt(colors.length)];
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setFilter(ArrayList newList) {
        items = new ArrayList<>();
        items.addAll(newList);
        notifyDataSetChanged();
    }
}