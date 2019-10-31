package com.myvanier.strawhats.myvanier.dbController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.myvanier.strawhats.myvanier.LibraryActivity;
import com.myvanier.strawhats.myvanier.dbController.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryController extends DatabaseController
{
    protected String DB_TABLE_NAME = "Library";
    private static final String TAG = LibraryController.class.getSimpleName();

    /**
     * Instantiates the library Controller
     * @param context
     */
    public LibraryController(Context context)
    {
        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    /**
     * Sets the name of the table
     * @param table
     */
    public void setTableName(String table) {
        DB_TABLE_NAME = table;
    }

    /**
     * Selects every entry in the database
     * @return a list containing every entry in the database
     */
    public List<Book> readAll() {
        List<Book> bookList = new ArrayList<Book>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                book.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
                bookList.add(book);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e){

        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return bookList;
    }

    /**
     * Updates the information of bookOld with the information of bookUpdated
     * @param bookOld
     * @param bookUpdated
     */
    public void updateBook(Book bookOld, Book bookUpdated) {
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("title", bookUpdated.getTitle());
            values.put("author", bookUpdated.getAuthor());

            sqLiteDatabase.update(DB_TABLE_NAME, values, "title=?", new String[]{bookOld.getTitle()});

        }
        catch (Exception e) {

        }
        finally {
            databaseAccessHelper.closeDatabase();
        }
    }

    /**
     * Removes the specified book from the library table
     * @param book
     */
    public void removeBook(Book book) {
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            sqLiteDatabase.delete(DB_TABLE_NAME, "title=?", new String[]{book.getTitle()});
        }
        catch (Exception e){

        }
        finally {
            sqLiteDatabase.close();
            this.databaseAccessHelper.closeDatabase();
        }
    }

    /**
     * Inserts the specified book in the library table
     * @param book
     */
    public void createBook(Book book) {
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("title", book.getTitle());
            values.put("author", book.getAuthor());

            sqLiteDatabase.insert(DB_TABLE_NAME, null, values);

            sqLiteDatabase.setTransactionSuccessful();
        }
        catch (Exception e) {

        }
        finally {
            sqLiteDatabase.endTransaction();
            this.databaseAccessHelper.closeDatabase();
        }
    }


    /**
     * Selects entries from the database where name of the book matches the search String
     * @param search
     * @return a list with the selected entries
     */
    public List<Book> readAllFiltered(String search) {
        List<Book> bookList = new ArrayList<>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE title like ?", new String[]{"%"+search+"%"});
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                book.setAuthor(cursor.getString(cursor.getColumnIndex("author")));

                bookList.add(book);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e){

        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }
        return bookList;
    }
}
