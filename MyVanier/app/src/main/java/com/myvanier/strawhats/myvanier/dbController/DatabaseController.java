package com.myvanier.strawhats.myvanier.dbController;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class DatabaseController {
    protected DBAccessController databaseAccessHelper;
    protected SQLiteDatabase sqLiteDatabase;


    public abstract List readAll();

}
