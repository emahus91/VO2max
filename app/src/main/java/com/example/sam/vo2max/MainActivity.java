package com.example.sam.vo2max;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ContactName, ContactAge, ContactWeight;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    public final static String USER_NAME = "com.example.sam.vo2max.NAME";
    public final static String USER_AGE = "com.example.sam.vo2max.AGE";
    public final static String USER_WEIGHT = "com.example.sam.vo2max.WEIGHT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContactName = (EditText) findViewById(R.id.NameEditTextID);
        ContactName = (EditText) findViewById(R.id.AgeEditTextID);
        ContactName = (EditText) findViewById(R.id.WeightEditTextID);

        Button btnNewMeasurement = (Button) findViewById(R.id.StartNewMeasurementID);
        btnNewMeasurement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);

                EditText etName = (EditText) findViewById(R.id.NameEditTextID);
                EditText etAge = (EditText) findViewById(R.id.AgeEditTextID);
                EditText etWeight = (EditText) findViewById(R.id.WeightEditTextID);

                String sName, sAge, sWeight;

                sName = etName.getText().toString();
                sAge = etAge.getText().toString();
                sWeight = etWeight.getText().toString();

                intent.putExtra(USER_NAME, sName);
                intent.putExtra(USER_AGE, sAge);
                intent.putExtra(USER_WEIGHT, sWeight);

                startActivity(intent);
            }
        });

    }

    public void addContact(View view)
    {
        String name = ContactName.getText().toString();
        String age = ContactAge.getText().toString();
        String weight = ContactWeight.getText().toString();
        userDbHelper = new UserDbHelper(context);
        sqLiteDatabase = userDbHelper.getWritableDatabase();
        userDbHelper.addInformations(name, age, weight, sqLiteDatabase);
            Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();
                userDbHelper.close();
    }

    public void viewContact(View view)
    {
        Intent intent = new Intent(this, DataListActivity.class);
        startActivity(intent);
    }

}