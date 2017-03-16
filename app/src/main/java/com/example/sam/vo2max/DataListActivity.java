package com.example.sam.vo2max;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    private MediaPlayer backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_activity_layout);
        listView = (ListView) findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listDataAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();

        cursor = userDbHelper.getInformations(sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            do{
                String name,power3,power4,power5, vo2max_liter_3, vo2max_liter_4,
                        vo2max_liter_5,vo2max_mliter_3, vo2max_mliter_4, vo2max_mliter_5,
                        antal_vandor_3, antal_vandor_4, antal_vandor_5, date_time_stamp;
                name = cursor.getString(0);
                power3 = cursor.getString(1);
                power4 = cursor.getString(2);
                power5 = cursor.getString(3);
                vo2max_liter_3 = cursor.getString(4);
                vo2max_liter_4 = cursor.getString(5);
                vo2max_liter_5 = cursor.getString(6);
                vo2max_mliter_3 = cursor.getString(7);
                vo2max_mliter_4 = cursor.getString(8);
                vo2max_mliter_5 = cursor.getString(9);
                antal_vandor_3 = cursor.getString(10);
                antal_vandor_4 = cursor.getString(11);
                antal_vandor_5 = cursor.getString(12);
                date_time_stamp = cursor.getString(13);

                DataProvider dataProvider = new DataProvider(name,power3,power4,power5, vo2max_liter_3, vo2max_liter_4,
                                                            vo2max_liter_5,vo2max_mliter_3, vo2max_mliter_4,
                                                            vo2max_mliter_5,antal_vandor_3,antal_vandor_4,antal_vandor_5,date_time_stamp);
                listDataAdapter.add(dataProvider);

            }while (cursor.moveToNext());
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(myIntent, 0);
        //stopPlaying(backButton);
        //backButton = MediaPlayer.create(CalculationActivity.this, R.raw.back_button);
        //backButton.start();
        return true;
    }

    private void stopPlaying(MediaPlayer mp) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
