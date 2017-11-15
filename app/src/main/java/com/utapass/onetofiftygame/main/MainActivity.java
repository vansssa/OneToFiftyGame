package com.utapass.onetofiftygame.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.utapass.onetofiftygame.Navigation;
import com.utapass.onetofiftygame.R;
import com.utapass.onetofiftygame.history.HistoryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        attachFragment(new MainFragment(), Navigation.MAIN_FRAGMENT);
    }

    public void attachFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_fragment, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Navigation.HISTORY_FRAGMENT);
        if (fragment instanceof HistoryFragment) {
            Navigation.goToBack(fragment);
        }
        else
            super.onBackPressed();
    }
}
