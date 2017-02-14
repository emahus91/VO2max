package com.example.sam.vo2max;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class TheMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent= getIntent();
        Bundle user_Info= intent.getExtras(),power_Values=intent.getExtras() ;

        //Intent intent = getIntent();
        final String[] userInfo= user_Info.getStringArray(SecondActivity.USER_INFO2);
        final double[] powerValues= power_Values.getDoubleArray(SecondActivity.POWER_VALUES);
        double vo2Max_5 = intent.getDoubleExtra(SecondActivity.VO2_MAX, 0); // or double number = getIntent().getExtras().getInt("NEW_NUMBER");
        //Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        DecimalFormat formatVal= new DecimalFormat("##.##");
        //textView.setText(formatVal.format(powerValues[0]));

       // textView.setText("power is " + power5MPT_3 + "Your name is " + sName + " and you are " + sAge + " years old and you weight" + sWeight + " kg");
textView.setText("NAME:  " + userInfo[0] + "\nAGE: " +userInfo[1]+ "\nWEIGHT: " + userInfo[2] + " \nPower_3: "+
        formatVal.format(powerValues[0]) + " \nPower_4:  "+ formatVal.format(powerValues[1])+ " \nPower_5:  "+ formatVal.format(powerValues[2]) + " \nVO2max_5:  "+ formatVal.format(vo2Max_5));

        setContentView(textView);



    }
}
