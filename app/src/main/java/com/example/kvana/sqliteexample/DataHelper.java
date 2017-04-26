package com.example.kvana.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kvana on 4/25/17.
 */

public class DataHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "student.db";
    private static  final String TABLE_NAME = "student_table";
    private static  final String ID = "ID";
    private static  final String NAME = "NAME";
    private static  final String SURNAME = "SURNAME";
    private static  final String MARKS = "MARKS";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(name,name);
        contentValues.put(surname,surname);
        contentValues.put(marks,marks);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
}
