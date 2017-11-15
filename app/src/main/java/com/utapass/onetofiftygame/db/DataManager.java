package com.utapass.onetofiftygame.db;

import java.util.ArrayList;

public interface DataManager {

    ArrayList readFromDB();

    void updateDB(String result);
}
