package com.example.emre.sqlitestudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by emre on 5.5.2017.
 */

public class EmployeeDBContext extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "student.db";

    private static final String TABLE_NAME = "STUDENT";
    private static final String COLUMN_NAME_ID = "ID";
    private static final String COLUMN_NAME_NAME = "NAME";
    private static final String COLUMN_NAME_SURNAME = "SURNAME";
    private static final String COLUMN_NAME_AGE = "AGE";
    private static final String COLUMN_NAME_GPA = "GPA";
    private static final String COLUMN_NAME_GENDER = "GENDER";
    private static final String COLUMN_NAME_CLASS = "CLASS";


    public EmployeeDBContext(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //        CREATE TABLE IF NOT EXISTS EMPLOYEE (
        //                ID      INTEGER PRIMARY KEY AUTOINCREMENT,
        //                NAME    TEXT,
        //                SURNAME TEXT,
        //                AGE     INTEGER,
        //                GPA     DOUBLE,
        //                GENDER  TEXT
        //                CLASS   INTEGER
        //        );

        String create_table_command =
                " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_NAME + " TEXT, " +
                        COLUMN_NAME_SURNAME + " TEXT, " +
                        COLUMN_NAME_AGE  +  " INTEGER, " +
                        COLUMN_NAME_GPA + " DOUBLE, " +
                        COLUMN_NAME_GENDER + " TEXT, " +
                        COLUMN_NAME_CLASS + " INTEGER " +
                        " ); ";

        db.execSQL(create_table_command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddEmployee(Employee newStudent){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, newStudent.getName() );
        values.put(COLUMN_NAME_SURNAME, newStudent.getSurname() );
        values.put(COLUMN_NAME_AGE, newStudent.getAge());
        values.put(COLUMN_NAME_GPA, newStudent.getGpa());
        values.put(COLUMN_NAME_GENDER, newStudent.getGender() );
        values.put(COLUMN_NAME_CLASS, newStudent.getSinif() );

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void DeleteEmployee(Employee deleteStudent){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,
                COLUMN_NAME_ID + " = ? ",
                new String[] { deleteStudent.getId().toString() } );
        db.close();
    }

    public void UpdateEmployee(Employee updateStudent){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, updateStudent.getName() );
        values.put(COLUMN_NAME_SURNAME, updateStudent.getSurname() );
        values.put(COLUMN_NAME_AGE, updateStudent.getAge());
        values.put(COLUMN_NAME_GPA, updateStudent.getGpa());
        values.put(COLUMN_NAME_GENDER, updateStudent.getGender() );
        values.put(COLUMN_NAME_CLASS, updateStudent.getSinif() );

        db.update(TABLE_NAME,
                values,
                COLUMN_NAME_ID + " = ? ",
                new String[] { updateStudent.getId().toString() });

        db.close();
    }

    public Employee GetEmployee(Long id){
        //SELECT * FROM TABLE_NAME WHERE ID = id
        String select_command =
                "SELECT * FROM " + TABLE_NAME +
                        " WHERE " + COLUMN_NAME_ID + " = " + id.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Employee student = null;

        if(cursor.moveToFirst()){
            // create a new Employee object and fill it
            Long studentID  = cursor.getLong(
                    cursor.getColumnIndex(COLUMN_NAME_ID) );

            String name = cursor.getString(
                    cursor.getColumnIndex(COLUMN_NAME_NAME));

            String surname = cursor.getString(
                    cursor.getColumnIndex(COLUMN_NAME_SURNAME));

            Integer age = cursor.getInt(
                    cursor.getColumnIndex(COLUMN_NAME_AGE));

            Double gpa = cursor.getDouble(
                    cursor.getColumnIndex(COLUMN_NAME_GPA));

            String gender = cursor.getString(
                    cursor.getColumnIndex(COLUMN_NAME_GENDER));

            Long sclass = cursor.getLong(
                    cursor.getColumnIndex(COLUMN_NAME_CLASS));

            student = new Employee(studentID,name,surname,age,gpa,gender,sclass);
        }

        cursor.close();
        db.close();


        return student;
    }

    public ArrayList<Employee> GetAllEmployees(){

        //SELECT * FROM TABLE_NAME
        String select_all_command = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(select_all_command,null);

        ArrayList<Employee> studentList = new ArrayList<>();

        while(cursor.moveToNext()){

            Long studentID  = cursor.getLong(
                    cursor.getColumnIndex(COLUMN_NAME_ID) );

            String name = cursor.getString(
                    cursor.getColumnIndex(COLUMN_NAME_NAME));

            String surname = cursor.getString(
                    cursor.getColumnIndex(COLUMN_NAME_SURNAME));

            Integer age = cursor.getInt(
                    cursor.getColumnIndex(COLUMN_NAME_AGE));

            Double gpa = cursor.getDouble(
                    cursor.getColumnIndex(COLUMN_NAME_GPA));

            String gender = cursor.getString(
                    cursor.getColumnIndex(COLUMN_NAME_GENDER));

            Long sclass = cursor.getLong(
                    cursor.getColumnIndex(COLUMN_NAME_CLASS));

            Employee student = new Employee(studentID,name,surname,age,gpa,gender,sclass);

            studentList.add(student);
        }

        cursor.close();
        db.close();

        return studentList;
    }

    public void RemoveAllEmployees(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,null,null);

        db.close();
    }

}

