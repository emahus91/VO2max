package com.example.sam.vo2max;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE1 = "com.example.sam.vo2max.MESSAGE1";
    public final static String EXTRA_MESSAGE2 = "com.example.sam.vo2max.MESSAGE2";
    public final static String EXTRA_MESSAGE3 = "com.example.sam.vo2max.MESSAGE3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewMeasurement = (Button) findViewById(R.id.StartNewMeasurementID);
        btnNewMeasurement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TheMeasurementActivity.class);

                EditText etName = (EditText) findViewById(R.id.NameEditTextID);
                EditText etAge = (EditText) findViewById(R.id.AgeEditTextID);
                EditText etWeight = (EditText) findViewById(R.id.WeightEditTextID);

                String sMessage, sAge, sWeight;

                sMessage = etName.getText().toString();
                sAge = etAge.getText().toString();
                sWeight = etWeight.getText().toString();

                intent.putExtra(EXTRA_MESSAGE1, sMessage);
                intent.putExtra(EXTRA_MESSAGE2, sAge);
                intent.putExtra(EXTRA_MESSAGE3, sWeight);

                startActivity(intent);
            }
        });

    }

}