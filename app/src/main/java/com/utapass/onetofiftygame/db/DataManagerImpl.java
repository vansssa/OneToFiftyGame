package com.utapass.onetofiftygame.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.utapass.onetofiftygame.App;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.utapass.onetofiftygame.db.HistoryContract.TABLE_NAME;

public class DataManagerImpl implements DataManager {

    private final HistorySqlLiteOpenHelper helper;

    public DataManagerImpl() {
        this.helper = App.getSqlLiteHelper();
    }

    @Override
    public ArrayList readFromDB() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor= db.query(TABLE_NAME, null, null, null, null, null, HistoryContract.CDATE + " DESC");
        cursor.moveToFirst();
        ArrayList<History> histories = new ArrayList<>();

        while(!cursor.isAfterLast()) {
            History history = new History(cursor);
            histories.add(history);
            cursor.moveToNext();
        }
        cursor.close();
        return histories;
    }

    @Override
    public void updateDB(String result) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HistoryContract.CDATE, getTodayDate());
        values.put(HistoryContract.CTIME, result);
        db.insert(TABLE_NAME, null, values);
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        String dateFormat = "yyyy-MM-dd kk:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(calendar.getTime());
    }
}
