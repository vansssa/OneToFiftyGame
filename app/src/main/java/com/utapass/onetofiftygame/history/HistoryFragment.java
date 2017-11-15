package com.utapass.onetofiftygame.history;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.utapass.onetofiftygame.R;
import com.utapass.onetofiftygame.Navigation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends Fragment {

    @BindView(R.id.history_recyclerView)  RecyclerView recyclerView;

    private HistoryPresenterImpl historyPresenter;
    private Unbinder unbinder;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        historyPresenter = new HistoryPresenterImpl();
        initUI(historyPresenter.getHistory());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        setHomeUp(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Navigation.goToBack(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI(ArrayList arrayList){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.history_title);
        HistoryRecycleAdapter historyAdapter = new HistoryRecycleAdapter(getActivity().getBaseContext(), arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(historyAdapter);
        setHomeUp(true);
    }

    public void setHomeUp(boolean isDisplay){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(isDisplay);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(isDisplay);
    }
}

