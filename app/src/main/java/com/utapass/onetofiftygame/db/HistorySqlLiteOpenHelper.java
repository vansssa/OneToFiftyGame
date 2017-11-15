package com.utapass.onetofiftygame.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;
import static com.utapass.onetofiftygame.db.HistoryContract.name;

public class HistorySqlLiteOpenHelper extends SQLiteOpenHelper {

    public HistorySqlLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(HistoryContract.createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
