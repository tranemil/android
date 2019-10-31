package com.myvanier.strawhats.myvanier.dbController;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class OpenDatabaseHelper extends SQLiteAssetHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "teachers.db";

    public OpenDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
