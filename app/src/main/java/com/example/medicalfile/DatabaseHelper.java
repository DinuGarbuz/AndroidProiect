package com.example.medicalfile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="File.db";
    public static final String TABLE_NAME="client";
    public static final String TABLE_Fisa="fisaMedicala";
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

        db.execSQL("CREATE TABLE fisaMedicala (ID INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, " +
                "age TEXT, sex TEXT, height TEXT, weight TEXT, blood TEXT,  geneticDiseases TEXT, allergens TEXT, clientID INTEGER)");
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

    public long addFisaMedicala(String firstname, String lastname, String age , String sex ,
                                String height , String weight , String blood , String geneticDiseases , String allergens, int clientID )

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("age", age);
        contentValues.put("sex", sex);
        contentValues.put("height", height);
        contentValues.put("weight", weight);
        contentValues.put("blood", blood);
        contentValues.put("geneticDiseases ", geneticDiseases );
        contentValues.put("allergens", allergens);
        contentValues.put("clientID", clientID);

        long res = db.insert ("fisaMedicala", null, contentValues);
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
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE mail = " + "'" + mail + "'";

        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getFisa() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_Fisa;//";

        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
