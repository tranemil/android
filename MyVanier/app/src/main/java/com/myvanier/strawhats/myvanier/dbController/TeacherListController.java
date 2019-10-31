package com.myvanier.strawhats.myvanier.dbController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.myvanier.strawhats.myvanier.dbController.Model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherListController extends TeacherController
{
    /**
     * Instantiates the teacher list Controller
     * @param context
     */
    public TeacherListController(Context context)
    {
        super(context);
        setTableName("PersonalList");
    }

//    protected String DB_TABLE_NAME = "PersonalList";
//    private static final String TAG = TeacherController.class.getSimpleName();
//
//    /**
//     * Instantiates the teacher Controller
//     * @param context
//     */
//    public TeacherListController(Context context) {
//        this.databaseAccessHelper = DBAccessController.getInstance(context);
//    }
//
//    /**
//     * Sets the name of the table
//     * @param table
//     */
//    public void setTableName(String table)
//    {
//        DB_TABLE_NAME = table;
//    }
//
//    /**
//     * Selects every entry in the database
//     * @return a list containing every entry in the database
//     */
//    public List<Teacher> readAll() {
//        List<Teacher> teacherList = new ArrayList<Teacher>();
//
//        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
//        try {
//            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null);
//            cursor.moveToFirst();
//
//            while (!cursor.isAfterLast()) {
//                Teacher teacher = new Teacher();
//                teacher.setName(cursor.getString(cursor.getColumnIndex("teacher_name")));
//                teacher.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
//                teacher.setPhone(cursor.getString(cursor.getColumnIndex("phone_number")));
//                teacher.setEmail(cursor.getString(cursor.getColumnIndex("teacher_email")));
//                teacher.setExtension(cursor.getString(cursor.getColumnIndex("extension")));
//                //New
//                teacher.setOffice(cursor.getString(cursor.getColumnIndex("office")));
//
//                teacherList.add(teacher);
//                cursor.moveToNext();
//            }
//            cursor.close();
//        }
//        catch(Exception e){
//
//        }
//        finally {
//            this.databaseAccessHelper.closeDatabase();
//        }
//        return teacherList;
//    }
//
//    /**
//     * Updates the information of teacherOld with the information of teacherUpdated
//     * @param teacherOld
//     * @param teacherUpdated
//     */
//    public void updateTeacher(Teacher teacherOld, Teacher teacherUpdated) {
//        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
//
//        try {
//            ContentValues values = new ContentValues();
//            values.put("teacher_name", teacherUpdated.getName());
//            values.put("phone_number", teacherUpdated.getPhone());
//            values.put("teacher_email", teacherUpdated.getEmail());
//            values.put("extension", teacherUpdated.getExtension());
//            //New
//            values.put("office",teacherUpdated.getOffice());
//
//            sqLiteDatabase.update(DB_TABLE_NAME, values, "teacher_email=?", new String[]{teacherOld.getEmail()});
//
//        }
//        catch (Exception e) {
//
//        }
//        finally {
//            databaseAccessHelper.closeDatabase();
//        }
//    }
//
//    /**
//     * Removes the specified teacher from the teacher
//     * @param teacher
//     */
//    public void removeTeacher(Teacher teacher) {
//        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
//
//        try {
//            sqLiteDatabase.delete(DB_TABLE_NAME, "teacher_email=?", new String[]{teacher.getEmail()});
//        }
//        catch (Exception e){
//
//        }
//        finally {
//            sqLiteDatabase.close();
//            this.databaseAccessHelper.closeDatabase();
//        }
//    }
//
//    /**
//     * Inserts the specified teacher in the database
//     * @param teacher
//     */
//    public void createTeacher(Teacher teacher) {
//        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
//        sqLiteDatabase.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put("teacher_name", teacher.getName());
//            values.put("department", teacher.getDepartment());
//            values.put("phone_number", teacher.getPhone());
//            values.put("teacher_email", teacher.getEmail());
//            values.put("extension", teacher.getExtension());
//            //New
//            values.put("office",teacher.getOffice());
//            sqLiteDatabase.insert(DB_TABLE_NAME, null, values);
//
//            sqLiteDatabase.setTransactionSuccessful();
//        }
//        catch (Exception e) {
//
//        }
//        finally {
//            sqLiteDatabase.endTransaction();
//            this.databaseAccessHelper.closeDatabase();
//        }
//    }

}
