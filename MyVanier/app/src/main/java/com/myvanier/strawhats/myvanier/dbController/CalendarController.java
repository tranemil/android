package com.myvanier.strawhats.myvanier.dbController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.myvanier.strawhats.myvanier.dbController.Model.Calendar;

import java.util.ArrayList;
import java.util.List;

public class CalendarController extends DatabaseController
{
    private static final String DB_TABLE_NAME = "Calendar";

    public CalendarController(Context context) {
        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    /**
     * Displays all the records from the calender database
     * @return the records
     */
    @Override
    public List<Calendar> readAll() {
        List<Calendar> calendarList = new ArrayList<Calendar>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Calendar cal = new Calendar();
                cal.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                cal.setEvent(cursor.getString(cursor.getColumnIndex("Event")));


                calendarList.add(cal);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e){

        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return calendarList;
    }


    public void addEvent(Calendar calEvent) {
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        sqLiteDatabase.beginTransaction();

        try {
            ContentValues event = new ContentValues();
            event.put("Date", calEvent.getDate());
            event.put("Event", calEvent.getEvent());
            sqLiteDatabase.insert(DB_TABLE_NAME, null, event);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.endTransaction();
            this.databaseAccessHelper.closeDatabase();
        }
    }

    public List<Calendar> readBySelectedDate(String date)
    {
        List<Calendar> calendarList = new ArrayList<Calendar>();
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try
        {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE Date = ?", new String[]{date});
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Calendar cal = new Calendar();
                cal.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                cal.setEvent(cursor.getString(cursor.getColumnIndex("Event")));


                calendarList.add(cal);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e){

        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return calendarList;
    }

    public void deleteEvent(Calendar calendar)
    {
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            sqLiteDatabase.delete(DB_TABLE_NAME, "date = ? AND event = ?", new String[]{calendar.getDate(),calendar.getEvent()});
        }
        catch (Exception e){

        }
        finally {
            sqLiteDatabase.close();
            this.databaseAccessHelper.closeDatabase();
        }
    }
}
