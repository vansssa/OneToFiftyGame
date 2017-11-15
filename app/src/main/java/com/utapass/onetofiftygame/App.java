package com.utapass.onetofiftygame;

import android.app.Application;
import android.content.Context;

import com.utapass.onetofiftygame.db.HistorySqlLiteOpenHelper;

public class App extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    public static HistorySqlLiteOpenHelper getSqlLiteHelper(){
        return new HistorySqlLiteOpenHelper(App.getContext());
    }
}
