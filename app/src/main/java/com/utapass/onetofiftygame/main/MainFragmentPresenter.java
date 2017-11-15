package com.utapass.onetofiftygame.main;

import java.util.ArrayList;

public interface MainFragmentPresenter {

    ArrayList<Integer> getRandomData();

    void startTimer();

    void stopTimer();

    String getGameResult();

    void onNumItemClicked(int num);
}
