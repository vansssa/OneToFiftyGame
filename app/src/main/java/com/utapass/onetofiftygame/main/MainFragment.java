package com.utapass.onetofiftygame.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utapass.onetofiftygame.Navigation;
import com.utapass.onetofiftygame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment extends Fragment implements MainFragmentView, CardHolder.callback {

    @BindView(R.id.tx_timers)
    TextView txTimer;
    @BindView(R.id.tx_hint)
    TextView txHint;
    @BindView(R.id.tx_result)
    TextView txResult;
    @BindView(R.id.card_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_fab)
    FloatingActionButton fab;

    private Unbinder unbinder;
    private MainFragmentPresenterImpl mainFragmentPresenter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFragmentPresenter = new MainFragmentPresenterImpl(this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mainFragmentPresenter.stopTimer();
    }

    @OnClick(R.id.refresh_fab)
    public void refreshNum() {
        mainFragmentPresenter.stopTimer();
        txTimer.setText(String.valueOf(0));
        txResult.setVisibility(View.INVISIBLE);
        initUI();
    }

    @Override
    public void onItemClickListener(int nowNumber) {
        mainFragmentPresenter.onNumItemClicked(nowNumber);
    }

    @Override
    public void showGameResult(String result) {
        txResult.setVisibility(View.VISIBLE);
        txResult.setText(result);
    }

    @Override
    public void showGameTimer(String gameResult) {
        txTimer.setText(gameResult);
    }

    @Override
    public void showGameHint(int num) {
        txHint.setText(String.valueOf(num));
    }

    private void initUI() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        txHint.setText(String.valueOf(1));
        CardRecycleAdapter adapter = new CardRecycleAdapter(getActivity().getBaseContext(), mainFragmentPresenter.getRandomData(), mainFragmentPresenter.numberOfColumns);
        adapter.setCallback(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), mainFragmentPresenter.numberOfColumns));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                Navigation.goToHistory(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}