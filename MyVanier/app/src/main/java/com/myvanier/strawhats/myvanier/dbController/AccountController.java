package com.myvanier.strawhats.myvanier.dbController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.myvanier.strawhats.myvanier.dbController.Model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountController extends DatabaseController {

    private String DB_TABLE_NAME = "Account";

    public AccountController(Context context) {
        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    public List<Account> readAll() {
        List<Account> list = new ArrayList<Account>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Account account = new Account();
                account.setUsername(cursor.getString(cursor.getColumnIndex("username")).toLowerCase());
                account.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                account.setProfileName(cursor.getString(cursor.getColumnIndex("name")));

                list.add(account);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return list;
    }

    public void createAccount(Account account) {
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        sqLiteDatabase.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put("username", account.getUsername());
            values.put("password", account.getPassword());
            values.put("name", account.getProfileName());

            sqLiteDatabase.insert(DB_TABLE_NAME, null, values);

            sqLiteDatabase.setTransactionSuccessful();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sqLiteDatabase.endTransaction();
            this.databaseAccessHelper.closeDatabase();
        }
    }


    public List<Account> ifUsernameExist(Account account) {
        List<Account> list = new ArrayList<Account>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE username=? collate nocase ", new String[]{account.getUsername()});
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                account.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                account.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                account.setProfileName(cursor.getString(cursor.getColumnIndex("name")));

                list.add(account);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return list;
    }

    public String readSameUsername(String username) {
        List<Account> list = new ArrayList<Account>();
        String name = "";
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE username=? collate nocase ", new String[]{username});
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                name = cursor.getString(cursor.getColumnIndex("name"));

                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return name;
    }

}

