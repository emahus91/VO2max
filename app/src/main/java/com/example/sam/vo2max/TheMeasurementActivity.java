package com.example.sam.vo2max;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;

public class TheMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String sName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        String sAge = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        String sWeight = intent.getStringExtra(MainActivity.EXTRA_MESSAGE3);

        //Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);

        textView.setText("Your name is " + sName + " and you are " + sAge + " years old and you weight" + sWeight + " kg");
        setContentView(textView);

    }
}
