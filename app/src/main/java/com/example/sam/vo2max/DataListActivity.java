package com.example.sam.vo2max;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_activity);
        listView = (ListView) findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();

        cursor = userDbHelper.getInformations(sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            do{

                String name, age, weight,power3,power4,power5,vo2max3,vo2max4,vo2max5;
                name = cursor.getString(0);
                power3 = cursor.getString(1);
                power4 = cursor.getString(2);
                power5 = cursor.getString(3);
                vo2max3 = cursor.getString(4);
                vo2max4 = cursor.getString(5);
                vo2max5 = cursor.getString(6);

                DataProvider dataProvider = new DataProvider(name,power3,power4,power5,vo2max3,vo2max4, vo2max5);
                listDataAdapter.add(dataProvider);

            }while (cursor.moveToNext());
        }

    }
}
