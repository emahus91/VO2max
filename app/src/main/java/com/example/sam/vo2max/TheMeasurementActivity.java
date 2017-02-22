package com.example.sam.vo2max;

import android.graphics.Color;
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
        Bundle user_info = intent.getExtras(), power_values =intent.getExtras(),vo2max_liter_values =intent.getExtras(),
               vo2max_mliter_values =intent.getExtras(),antal_vandor =intent.getExtras() ;

        //Intent intent = getIntent();
        final String[] userInfo= user_info.getStringArray(SecondActivity.USER_KEY2);
        final double[] powerValues= power_values.getDoubleArray(SecondActivity.POWER_KEY);
        final double[] vo2max_liter_Values = vo2max_liter_values.getDoubleArray(SecondActivity.VO2MAX_LITER_KEY);
        final double[] vo2max_mliter_Values = vo2max_mliter_values.getDoubleArray(SecondActivity.VO2MAX_MLITER_KEY);
        final double[] antalVandor = antal_vandor.getDoubleArray(SecondActivity.ANTAL_VANDOR_KEY);

        //double vo2Max_5 = intent.getDoubleExtra(SecondActivity.VO2_MAX, 0); // or double number = getIntent().getExtras().getInt("NEW_NUMBER");
        //Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(25);
        //textView.setBackgroundColor(Color.BLUE);
        DecimalFormat formatVal= new DecimalFormat("##.##");
        //textView.setText(formatVal.format(powerValues[0]));

        assert userInfo != null; assert powerValues != null; assert vo2max_liter_Values != null; assert vo2max_mliter_Values != null;
        assert antalVandor != null;
        textView.setText("NAME:  " + userInfo[0] + "\nN1:  " + antalVandor[0] +"\nN2:  " + antalVandor[1] +"\nN3:  "
                + antalVandor[2] + "\nP3: "+ formatVal.format(powerValues[0]) + " \nP4:  "+ formatVal.format(powerValues[1])
                + "\nP5:  "+ formatVal.format(powerValues[2]) + "\n[L3]: " +formatVal.format(vo2max_liter_Values[0])
                + "\n[L4]: " +formatVal.format(vo2max_liter_Values[1])+ "\n[L5]: " +formatVal.format(vo2max_liter_Values[2])+ "\n[ML3]: "
                + formatVal.format(vo2max_mliter_Values[0]) + "\n[ML4]: " + formatVal.format(vo2max_mliter_Values[1]) +"\n[ML5]: " + formatVal.format(vo2max_mliter_Values[2]));
        setContentView(textView);



    }
}
