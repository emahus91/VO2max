package com.example.sam.vo2max;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String USER_NAME = "com.example.sam.vo2max.NAME";
    public final static String USER_AGE = "com.example.sam.vo2max.AGE";
    public final static String USER_WEIGHT = "com.example.sam.vo2max.WEIGHT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}