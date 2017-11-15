package com.utapass.onetofiftygame.history;

import com.utapass.onetofiftygame.db.DataManagerImpl;

import java.util.ArrayList;

public class HistoryPresenterImpl implements HistoryPresenter {

    private final DataManagerImpl model;

    public HistoryPresenterImpl() {
        model = new DataManagerImpl();
    }

    @Override
    public ArrayList getHistory() {
        return model.readFromDB();
    }
}
