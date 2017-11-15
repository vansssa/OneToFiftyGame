package com.utapass.onetofiftygame.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utapass.onetofiftygame.R;
import com.utapass.onetofiftygame.db.History;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryRecycleAdapter extends RecyclerView.Adapter<HistoryRecycleAdapter.historyViewHolder> {

    private final Context context;
    private final ArrayList arrayList;

    public HistoryRecycleAdapter(Context baseContext, ArrayList arrayList) {
        this.context = baseContext;
        this.arrayList = arrayList;
    }

    @Override
    public HistoryRecycleAdapter.historyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_recycleview_item, parent, false);
        return new HistoryRecycleAdapter.historyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(historyViewHolder holder, int position) {
        holder.initContent((History)arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class historyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_date) TextView mdate;
        @BindView(R.id.tx_spent_time) TextView mtime;

        public historyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void initContent(History history) {
            mdate.setText(history.getcDate());
            mtime.setText(history.getcTime() + "  Sec");
        }
    }
}
