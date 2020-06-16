package com.example.medicalfile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="medicalFile.db";
    public static final String TABLE_NAME="client";
    public static final String COL_1="ID";
    public static final String COL_2="firstname";
    public static final String COL_3="lastname";
    public static final String COL_4="password";
    public static final String COL_5="mail";
    public static final String COL_6="phone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE client (ID INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, password TEXT, mail TEXT, phone TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String firstname, String lastname, String password, String mail, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("password", password);
        contentValues.put("mail", mail);
        contentValues.put("phone", phone);

        long res = db.insert ("client", null, contentValues);
        db.close();
        return res;

    }

    public boolean checkUser(String mail, String password)
    {
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_5 + "=?" + " and " + COL_4 + "=?";
        String[] selectionArgs = {mail, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)

            return  true;
        else
            return false;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return  data;
    }

    public Cursor getName(String mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE mail = "+"'"+ mail +"'";

        Cursor data = db.rawQuery(query, null);
        return  data;


//        String[] columns = {COL_1};
//        SQLiteDatabase db = getReadableDatabase();
//        String selection = COL_5 + "=?" + " and " + COL_4 + "=?";
//        String[] selectionArgs = {mail, password};
//        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
//        int count = cursor.getCount();
//        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = 1";
//        cursor.close();
//        db.close();
    }

}
