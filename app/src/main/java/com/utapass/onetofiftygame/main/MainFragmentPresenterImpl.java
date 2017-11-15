package com.utapass.onetofiftygame.main;

import android.os.Handler;
import android.os.Message;

import com.utapass.onetofiftygame.db.DataManagerImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

public class MainFragmentPresenterImpl implements MainFragmentPresenter {

    private DataManagerImpl model;
    private Timer timer;
    private MainFragmentView mainFragmentView;
    int numberOfTotal = 50;
    int numberOfColumns = 5;
    long startTime, period;

    public MainFragmentPresenterImpl(Object object) {
        model = new DataManagerImpl();
        this.mainFragmentView = (MainFragmentView) object;
    }

    @Override
    public ArrayList<Integer> getRandomData() {
        return getRandomData(numberOfTotal);
    }

    @Override
    public void startTimer() {
        period = 0;
        startTime = System.currentTimeMillis();
        timer = new Timer(true);
        timer.schedule(new MyTimerTask(), 0, 100);
    }

    @Override
    public void stopTimer() {
        if (timer != null)
            timer.cancel();
    }

    @Override
    public String getGameResult() {
        return period / 1000 + ":" + period % 1000;
    }

    @Override
    public void onNumItemClicked(int nowNumber) {
        mainFragmentView.showGameHint(nowNumber + 1);
        if (nowNumber == 1) {
            startTimer();
        }
        if (nowNumber == 50) {
            mainFragmentView.showGameHint(nowNumber);
            mainFragmentView.showGameResult("Finish! Your spend time : " + getGameResult());
            stopTimer();
            model.updateDB(getGameResult());
        }
    }

    private ArrayList<Integer> getRandomData(int total) {
        int matrix = numberOfColumns * numberOfColumns;
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            data.add(i + 1);
        }
        Collections.shuffle(data.subList(0, matrix));
        Collections.shuffle(data.subList(matrix, data.size() - 1));
        return data;
    }

    private class MyTimerTask extends java.util.TimerTask {
        @Override
        public void run() {
            period = System.currentTimeMillis() - startTime;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mainFragmentView.showGameTimer(getGameResult());
                    break;
            }
        }
    };
}
