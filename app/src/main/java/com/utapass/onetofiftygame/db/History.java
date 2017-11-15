package com.utapass.onetofiftygame.db;

import android.database.Cursor;
import static com.utapass.onetofiftygame.db.HistoryContract.CDATE;
import static com.utapass.onetofiftygame.db.HistoryContract.CTIME;

public class History {

    private final String cDate;
    private final String cTime;

    public History(Cursor cursor) {
        cDate = cursor.getString(cursor.getColumnIndex(CDATE));
        cTime = cursor.getString(cursor.getColumnIndex(CTIME));
    }

    public String getcDate() {
        return cDate;
    }

    public String getcTime() {
        return cTime;
    }
}
