package com.example.sam.vo2max;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

    public final static String USER_INFO = "com.example.sam.vo2max.USER_INFO";
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

                EditText etName = (EditText) findViewById(R.id.NameEditTextID);
                EditText etAge = (EditText) findViewById(R.id.AgeEditTextID);
                EditText etWeight = (EditText) findViewById(R.id.WeightEditTextID);

                String sName = etName.getText().toString(); //
                String sAge = etAge.getText().toString(); // TODO Must put in field(toast), for choosing the right Power formula
                String sWeight = etWeight.getText().toString(); //TODO Must put in field(toast), to avoid crash when calculating(SecondActivity)

                //kontrollera om den inmatade Åldern stämmer
                if ((Double.valueOf(sAge)) >= 45 && (Double.valueOf(sAge)) <= 90){ //means old adults b/w 45 & 90 years old
                    Toast.makeText(MainActivity.this, "Old and wise",Toast.LENGTH_LONG).show();}
                else if((Double.valueOf(sAge)) < 45 && (Double.valueOf(sAge)) >= 10){
                    Toast.makeText(MainActivity.this, "Young and Idiot!",Toast.LENGTH_LONG).show();}
                else if(etAge.length() == 0){
                    etAge.setBackgroundColor(Color.BLUE);
                    Toast.makeText(MainActivity.this, "HALLÅÅ! Glömt något?",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    etAge.setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Wrong Age range",Toast.LENGTH_LONG).show();
                    
                    return;
                }



                Intent intent1 = new Intent(MainActivity.this,SecondActivity.class);
                Bundle user_Info= new Bundle();

                user_Info.putStringArray(USER_INFO, new String[]{sName, sAge,sWeight}); //String[0]= sName
                intent1.putExtras(user_Info);

                startActivity(intent1);
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