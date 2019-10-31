package com.myvanier.strawhats.myvanier.dbController;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 *  A helper class for querying an SQLite database.
 *
 *  Created by Sleiman on 3/16/2019
 */
public class DBAccessController {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DBAccessController instance;

    /**
     * Private constructor to avoid object creation from outside this class.
     * We want to make sure there is only one signle instance of this class accessing the dabase.
     * @param context
     */
    private DBAccessController(Context context) {
        this.openHelper = new OpenDatabaseHelper(context);
    }

    /**
     * Creates and returns  a singleton instance of DBAccessController.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static synchronized DBAccessController getInstance(Context context) {
        if (instance == null) {
            instance = new DBAccessController(context);
        }
        return instance;
    }

    /**
     * Opens a connection to the specified database.
     */
    public SQLiteDatabase openDatabase() {

        this.database = openHelper.getWritableDatabase();
        return this.database;
    }

    /**
     * Closes the database connection.
     */
    public void closeDatabase() {
        if (database != null) {
            this.database.close();
        }
    }

}
