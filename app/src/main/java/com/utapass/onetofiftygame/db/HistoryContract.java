package com.utapass.onetofiftygame.db;

public class HistoryContract {

    public static final int version = 1;
    public static final String name = "history.db";
    public static final String createDB = "CREATE TABLE history (_id INTEGER PRIMARY KEY, cdate DATETIME NOT NULL, ctime VARCHAR) ";
    public static final String CDATE = "cdate";
    public static final String CTIME = "ctime";
    public static final String TABLE_NAME = "history";

}
