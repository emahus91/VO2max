package com.example.sam.vo2max;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;


public class TheMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setTitle("HOME");
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setLogo(R.drawable.ic_home);
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);



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
        textView.setText("Test Personens ID:  " + userInfo[0] + "\nAntal Vändor(3 min):  " + antalVandor[0] +"\nAntal Vändor(4 min):  " + antalVandor[1] +"\nAntal Vändor(5 min):  "
                + antalVandor[2] + "\nPower (3MPT): "+ formatVal.format(powerValues[0])+ " [W] " + " \nPower (4MPT):  "+ formatVal.format(powerValues[1]) + " [W]"
                + "\nPower (5MPT):  "+ formatVal.format(powerValues[2])+ " [W]" + "\nV02max(3 min): " +formatVal.format(vo2max_liter_Values[0]) +" [l/min]"
                + "\nV02max(4 min): " +formatVal.format(vo2max_liter_Values[1])+ "[ l/min]"+ "\nV02max(5 min): " +formatVal.format(vo2max_liter_Values[2])+ " [l/min]" + "\nV02max(3 min): "
                +" [ml/kg/min]" + formatVal.format(vo2max_mliter_Values[0]) + "\nV02max(4 min): " + formatVal.format(vo2max_mliter_Values[1]) +" [ml/kg/min]" +"\nV02max(4 min): " + formatVal.format(vo2max_mliter_Values[2])+" [ml/kg/min]");
        setContentView(textView);



    }
   // public boolean onOptionsItemSelected(MenuItem item) {
    //    switch (item.getItemId()) {
    //        case android.R.id.home:
     //           Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
     //           startActivityForResult(myIntent, 0);
       //         return true;
     //       default:
         //       return super.onOptionsItemSelected(item);
     //   }
    //}

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
