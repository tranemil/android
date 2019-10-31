package com.myvanier.strawhats.myvanier.dbController;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.myvanier.strawhats.myvanier.dbController.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentController extends DatabaseController{

    private String DB_TABLE_NAME = "Student";

    private static final String TAG = StudentController.class.getSimpleName();

    public StudentController(Context context) {
        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    public List<Student> readAll() {
        List<Student> students = new ArrayList<Student>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            String querry = "SELECT * FROM " + DB_TABLE_NAME;
            Log.i(TAG , querry);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Student student = new Student();
                student.setfName(cursor.getString(cursor.getColumnIndex("fName")));
                student.setlName(cursor.getString(cursor.getColumnIndex("lName")));
                students.add(student);
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
        return students;
    }
}
