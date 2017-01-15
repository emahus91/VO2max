package com.example.sam.vo2max;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button NewMeasurement = (Button) findViewById(R.id.StartNewMeasurementID);
        NewMeasurement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intentone = new Intent(MainActivity.this, TheMeasurementActivity.class);
                startActivity(intentone);
            }
        });

    }
}