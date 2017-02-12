package com.example.sam.vo2max;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by aranrashid on 2017-02-11.
 */

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY = "CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+"("+ UserContract.NewUserInfo.USER_NAME+" TEXT,"+ UserContract.NewUserInfo.USER_AGE+" TEXT,"+ UserContract.NewUserInfo.USER_WEIGHT+" TEXT);"; //Om ej fungerar flytta upp

    public UserDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.e("DATABASE OPERATIONS", "Database created / opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
            Log.e("DATABASE OPERATIONS", "Table created...");
    }

    public void addInformations(String name, String age, String weight, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContract.NewUserInfo.USER_AGE,age);
        contentValues.put(UserContract.NewUserInfo.USER_WEIGHT,weight);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);  //null ar till for att ej skapa en ny rad om de ej behovs
        Log.e("DATABASE OPERATIONS", "One row inserted...");
    }

    public Cursor getInformations(SQLiteDatabase db)
    {
        Cursor cursor;
            String[] projections = {UserContract.NewUserInfo.USER_NAME, UserContract.NewUserInfo.USER_AGE, UserContract.NewUserInfo.USER_WEIGHT};
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion) {

    }
}
