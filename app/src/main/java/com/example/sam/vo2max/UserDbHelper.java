package com.example.sam.vo2max;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by aran & samuel on 2017-02-11.
 */

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1; // TODO andra versioner?
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+"" +
                    "("+ UserContract.NewUserInfo.USER_NAME +" TEXT,"
                    + UserContract.NewUserInfo.POWER_3 +" TEXT,"
                    + UserContract.NewUserInfo.POWER_4 +" TEXT,"
                    + UserContract.NewUserInfo.POWER_5 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_LITER_3 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_LITER_4 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_LITER_5 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_MLITER_3 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_MLITER_4 +" TEXT,"
                    + UserContract.NewUserInfo.VO2MAX_MLITER_5 +" TEXT,"
                    + UserContract.NewUserInfo.ANTAL_VANDOR_3 +" TEXT,"
                    + UserContract.NewUserInfo.ANTAL_VANDOR_4 +" TEXT,"
                    + UserContract.NewUserInfo.ANTAL_VANDOR_5 +" TEXT,"
                    + UserContract.NewUserInfo.DATE_TIME_STAMP+" TEXT);"; //Om ej fungerar flytta upp


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

    public void addInformations(String name, String power3, String power4,
                                String power5, String vo2max_liter_3, String vo2max_liter_4,
                                String vo2max_liter_5,String vo2max_mliter_3,String vo2max_mliter_4,
                                String vo2max_mliter_5,String antal_vandor_3,String antal_vandor_4,String antal_vandor_5,String date_time_stamp, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContract.NewUserInfo.POWER_3,power3);
        contentValues.put(UserContract.NewUserInfo.POWER_4,power4);
        contentValues.put(UserContract.NewUserInfo.POWER_5,power5);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_LITER_3, vo2max_liter_3);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_LITER_4, vo2max_liter_4);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_LITER_5, vo2max_liter_5);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_MLITER_3, vo2max_mliter_3);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_MLITER_4, vo2max_mliter_4);
        contentValues.put(UserContract.NewUserInfo.VO2MAX_MLITER_5, vo2max_mliter_5);
        contentValues.put(UserContract.NewUserInfo.ANTAL_VANDOR_3, antal_vandor_3);
        contentValues.put(UserContract.NewUserInfo.ANTAL_VANDOR_4, antal_vandor_4);
        contentValues.put(UserContract.NewUserInfo.ANTAL_VANDOR_5, antal_vandor_5);
        contentValues.put(UserContract.NewUserInfo.DATE_TIME_STAMP,date_time_stamp);
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
                                    UserContract.NewUserInfo.VO2MAX_LITER_3,
                                    UserContract.NewUserInfo.VO2MAX_LITER_4,
                                    UserContract.NewUserInfo.VO2MAX_LITER_5,
                                    UserContract.NewUserInfo.VO2MAX_MLITER_3,
                                    UserContract.NewUserInfo.VO2MAX_MLITER_4,
                                    UserContract.NewUserInfo.VO2MAX_MLITER_5,
                                    UserContract.NewUserInfo.ANTAL_VANDOR_3,
                                    UserContract.NewUserInfo.ANTAL_VANDOR_4,
                                    UserContract.NewUserInfo.ANTAL_VANDOR_5,
                                    UserContract.NewUserInfo.DATE_TIME_STAMP};
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    //code for the search-function/class
    public Cursor getContact(String user_id, SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {UserContract.NewUserInfo.USER_NAME,UserContract.NewUserInfo.POWER_3,
                UserContract.NewUserInfo.POWER_4, UserContract.NewUserInfo.POWER_5,
                UserContract.NewUserInfo.VO2MAX_LITER_3,UserContract.NewUserInfo.VO2MAX_LITER_4,
                UserContract.NewUserInfo.VO2MAX_LITER_5,UserContract.NewUserInfo.VO2MAX_MLITER_3,
                UserContract.NewUserInfo.VO2MAX_MLITER_4,UserContract.NewUserInfo.VO2MAX_MLITER_5,
                UserContract.NewUserInfo.ANTAL_VANDOR_3,UserContract.NewUserInfo.ANTAL_VANDOR_4,
                UserContract.NewUserInfo.ANTAL_VANDOR_5};
        String selection = UserContract.NewUserInfo.USER_NAME +" LIKE ?";
        String[] selection_args = {user_id};
        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }


    //code for the delete information function
    public void deleteInformation(String user_name, SQLiteDatabase sqLiteDatabase)
    {
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {user_name};
        sqLiteDatabase.delete(UserContract.NewUserInfo.TABLE_NAME,selection,selection_args);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion) {

    }
}
