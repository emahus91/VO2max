package com.example.sam.vo2max;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.sam.vo2max.SecondActivity.NEW_NUMBER;

public class TheMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent intent = getIntent();

        Intent mIntent = getIntent();
        double number = mIntent.getDoubleExtra(NEW_NUMBER, 0); //double number = getIntent().getExtras().getInt("NEW_NUMBER");

        //String sName = intent.getStringExtra(SecondActivity.USER_NAME2);
        //String sAge = intent.getStringExtra(SecondActivity.USER_AGE2);
        //String sWeight = intent.getStringExtra(SecondActivity.USER_WEIGHT2);

        //Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);

       // textView.setText("Your name is " + sName + " and you are " + sAge + " years old and you weight" + sWeight + " kg");
       textView.setText("power is " + number);
        setContentView(textView);


    }
}
