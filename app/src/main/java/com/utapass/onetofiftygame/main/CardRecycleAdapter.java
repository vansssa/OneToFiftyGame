package com.utapass.onetofiftygame.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utapass.onetofiftygame.R;

import java.util.ArrayList;

public class CardRecycleAdapter extends RecyclerView.Adapter<CardHolder> {

    private final Context context;
    private final ArrayList<Integer> subData;
    private final ArrayList<Integer> initData;
    private CardHolder.callback callback;

    public CardRecycleAdapter(Context context, ArrayList<Integer> data, int number) {
        this.context = context;
        this.initData = new ArrayList<>(data.subList(0, number * number));
        this.subData = new ArrayList<>(data.subList(number * number, data.size()));
    }

    public void setCallback(CardHolder.callback callback) {
        this.callback = callback;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new CardHolder(view,callback,subData);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        holder.initContent(initData.get(position));
    }

    @Override
    public int getItemCount() {
        return initData.size();
    }
}
