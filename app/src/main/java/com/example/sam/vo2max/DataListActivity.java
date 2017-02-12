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

                String name, age, weight;
                name = cursor.getString(0);
                age = cursor.getString(1);
                weight = cursor.getString(2);

                DataProvider dataProvider = new DataProvider(name, age, weight);
                listDataAdapter.add(dataProvider);

            }while (cursor.moveToNext());
        }

    }
}
