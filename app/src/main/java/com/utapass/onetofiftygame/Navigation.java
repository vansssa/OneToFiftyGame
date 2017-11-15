package com.utapass.onetofiftygame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.utapass.onetofiftygame.history.HistoryFragment;

public class Navigation {

    public static final String HISTORY_FRAGMENT = "HISTORY_FRAGMENT";
    public static final String MAIN_FRAGMENT = "MAIN_FRAGMENT";
    public static final String HIDE = "HIDE";
    public static final String SHOW = "SHOW";

    public static void goToHistory(Fragment fragment) {
        hideFragment(fragment, MAIN_FRAGMENT);
        setFragment(fragment, new HistoryFragment(), HISTORY_FRAGMENT);
    }

    public static void goToBack(Fragment fragment) {
        fragment.getActivity().getSupportFragmentManager().popBackStack();
        showFragment(fragment,MAIN_FRAGMENT);
        setTitle(fragment,MAIN_FRAGMENT);
    }
    
    private static void showFragment(Fragment mainActivity, String tag){
        toFragment(mainActivity,tag,SHOW);
    }

    private static void hideFragment(Fragment mainActivity, String tag){
        toFragment(mainActivity,tag,HIDE);
    }

    private static void toFragment(Fragment fragment, String tag, String category){
        FragmentTransaction fragmentTransaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
        Fragment toFragment = fragment.getActivity().getSupportFragmentManager().findFragmentByTag(tag);

        if(category.equals(HIDE))
            fragmentTransaction.hide(toFragment);
        if(category.equals(SHOW))
            fragmentTransaction.show(toFragment);
        fragmentTransaction.commit();
    }

    private static void setTitle(Fragment fragment, String tag){
        if(tag.equals(MAIN_FRAGMENT))
            ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        if(tag.equals(HISTORY_FRAGMENT))
            ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().setTitle(R.string.history_title);
    }

    public static void setFragment(Fragment fragment, Fragment toFragment ,String tag) {
        FragmentTransaction fragmentTransaction = fragment.getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_fragment, toFragment, tag);
        if (tag.equals(HISTORY_FRAGMENT))
            fragmentTransaction.addToBackStack(HISTORY_FRAGMENT);
        fragmentTransaction.commit();
    }

}
