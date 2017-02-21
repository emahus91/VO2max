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
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+"" +
                    "("+ UserContract.NewUserInfo.USER_NAME+" TEXT,"
                    + UserContract.NewUserInfo.POWER_3 +" TEXT,"
                    + UserContract.NewUserInfo.POWER_4 +" TEXT,"
                    + UserContract.NewUserInfo.POWER_5 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_3 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_4 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_5 +" TEXT);"; //Om ej fungerar flytta upp


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

    public void addInformations(String name, String power3, String power4, String power5, String vo2max3, String vo2max4, String vo2max5, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContract.NewUserInfo.POWER_3,power3);
        contentValues.put(UserContract.NewUserInfo.POWER_4,power4);
        contentValues.put(UserContract.NewUserInfo.POWER_5,power5);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_3,vo2max3);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_4,vo2max4);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_5,vo2max5);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);  //null ar till for att ej skapa en ny rad om de ej behovs
        Log.e("DATABASE OPERATIONS","One row inserted...");
    }

    public Cursor getInformations(SQLiteDatabase db)
    {
        Cursor cursor;
            String[] projections = {UserContract.NewUserInfo.USER_NAME,
                                    UserContract.NewUserInfo.POWER_3,
                                    UserContract.NewUserInfo.POWER_4,
                                    UserContract.NewUserInfo.POWER_5,
                                    UserContract.NewUserInfo.VO2MAX_3,
                                    UserContract.NewUserInfo.VO2MAX_4,
                                    UserContract.NewUserInfo.VO2MAX_5};
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    //code for the search-function/class
    public Cursor getContact(String user_name, SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {UserContract.NewUserInfo.POWER_3, UserContract.NewUserInfo.POWER_4};
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {user_name};
        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion) {

    }
}
