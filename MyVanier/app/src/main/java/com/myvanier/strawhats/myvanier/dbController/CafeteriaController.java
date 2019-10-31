package com.myvanier.strawhats.myvanier.dbController;

import android.content.Context;
import android.database.Cursor;

import com.myvanier.strawhats.myvanier.dbController.Model.Cafeteria;

import java.util.ArrayList;
import java.util.List;

public class CafeteriaController extends DatabaseController
{
    private static final String DB_TABLE_NAME = "cafeteria";
    private static final String TAG = CafeteriaController.class.getSimpleName();

    public CafeteriaController(Context context) {
        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    /**
     * Displays all the records from the cafeteria database
     * @return the records
     */
    @Override
    public List<Cafeteria> readAll() {
        List<Cafeteria> cafeteriaList = new ArrayList<Cafeteria>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Cafeteria caf = new Cafeteria();
                caf.setFoodName(cursor.getString(cursor.getColumnIndex("Name")));
                caf.setFoodType(cursor.getString(cursor.getColumnIndex("Type")));
                caf.setPrice(cursor.getDouble(cursor.getColumnIndex("Price")));
                caf.setCafeteria(cursor.getString(cursor.getColumnIndex("Cafe")));
                caf.setFood_id(cursor.getInt(cursor.getColumnIndex("Food_Id")));

                cafeteriaList.add(caf);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e){

        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return cafeteriaList;
    }


    /**
     * Displays records from the cafeteria database table, depending on the parameters inserted by
     * the user
     * @param cafe
     * @param type
     * @return the records
     */
    public List<Cafeteria> readFiltered(String cafe, String type) {
        List<Cafeteria> cafeteriaList = new ArrayList<Cafeteria>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE Cafe = ? AND Type = ?", new String[]{cafe,type});
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Cafeteria caf = new Cafeteria();
                caf.setFoodName(cursor.getString(cursor.getColumnIndex("Name")));
                caf.setFoodType(cursor.getString(cursor.getColumnIndex("Type")));
                caf.setPrice(cursor.getDouble(cursor.getColumnIndex("Price")));
                caf.setCafeteria(cursor.getString(cursor.getColumnIndex("Cafe")));
                caf.setFood_id(cursor.getInt(cursor.getColumnIndex("Food_Id")));

                cafeteriaList.add(caf);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e){

        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return cafeteriaList;
    }
}
