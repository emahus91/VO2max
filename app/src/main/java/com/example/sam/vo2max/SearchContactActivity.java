package com.example.sam.vo2max;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class SearchContactActivity extends AppCompatActivity {

    TextView Display_Age, Display_Weight;
    EditText Search_Name;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlayout);

        Search_Name = (EditText)findViewById(R.id.search_name);
        Display_Age = (TextView)findViewById(R.id.display_age);
        Display_Weight = (TextView)findViewById(R.id.display_weight);
        Display_Age.setVisibility(View.GONE);
        Display_Weight.setVisibility(View.GONE);
    }

    public void searchContact(View view)
    {
        search_name = Search_Name.getText().toString();
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = userDbHelper.getContact(search_name, sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            String AGE = cursor.getString(0);
            String WEIGHT = cursor.getString(1);

            Display_Age.setText(AGE);
            Display_Weight.setText(WEIGHT);
            Display_Age.setVisibility(View.VISIBLE);
            Display_Weight.setVisibility(View.VISIBLE);
        }
    }
}

