package com.example.sam.vo2max;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;

import java.text.DecimalFormat;


public class TheMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//TODO Presenting temporarly the user information in a more userfriendly way(Tableview)
        Intent intent= getIntent();
        Bundle user_Info= intent.getExtras(),power_Values=intent.getExtras(),vo2max_Values=intent.getExtras() ;

        //Intent intent = getIntent();
        final String[] userInfo= user_Info.getStringArray(SecondActivity.USER_KEY2);
        final double[] powerValues= power_Values.getDoubleArray(SecondActivity.POWER_KEY);
        final double[] vo2maxValues= vo2max_Values.getDoubleArray(SecondActivity.VO2MAX_KEY);
        //double vo2Max_5 = intent.getDoubleExtra(SecondActivity.VO2_MAX, 0); // or double number = getIntent().getExtras().getInt("NEW_NUMBER");
        //Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        DecimalFormat formatVal= new DecimalFormat("##.##");
        //textView.setText(formatVal.format(powerValues[0]));

        assert userInfo != null;
        assert powerValues != null;
        assert vo2maxValues != null;
        textView.setText("NAME:  " + userInfo[0] + "\nAGE: " +userInfo[1]+ "\nWEIGHT: " + userInfo[2] + " \nPower_3: "+ formatVal.format(powerValues[0]) +
        " \nPower_4:  "+ formatVal.format(powerValues[1])+ " \nPower_5:  "+ formatVal.format(powerValues[2]) + " \nVo2max_3: "+ formatVal.format(vo2maxValues[0]) +
                        " \nVo2max_4:  "+ formatVal.format(vo2maxValues[1])+ " \nVo2max_5:  "+ formatVal.format(vo2maxValues[2]));

        setContentView(textView);



    }
}
