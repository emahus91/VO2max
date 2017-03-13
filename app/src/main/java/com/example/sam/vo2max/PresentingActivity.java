package com.example.sam.vo2max;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;

//import static com.example.sam.vo2max.R.id.textViewID;


public class PresentingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_activityt_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setTitle("HOME");
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setLogo(R.drawable.ic_home);
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);

        TextView tvUserID = (TextView) findViewById(R.id.UserTextViewID);
        TextView tvLaps3 =  (TextView) findViewById(R.id.Laps3TextViewID);
        TextView tvLaps4 =  (TextView) findViewById(R.id.Laps4TextViewID);
        TextView tvLaps5 =  (TextView) findViewById(R.id.Laps5TextViewID);
        TextView tvPower3 = (TextView) findViewById(R.id.Power3TextViewID);
        TextView tvPower4 = (TextView) findViewById(R.id.Power4TextViewID);
        TextView tvPower5 = (TextView) findViewById(R.id.Power5TextViewID);
        TextView tvVo2maxl3 = (TextView) findViewById(R.id.Vo2maxl3TextViewID);
        TextView tvVo2maxl4 = (TextView) findViewById(R.id.Vo2maxl4TextViewID);
        TextView tvVo2maxl5 = (TextView) findViewById(R.id.Vo2maxl5TextViewID);
        TextView tvVo2maxml3 = (TextView) findViewById(R.id.Vo2maxml3TextViewID);
        TextView tvVo2maxml4 = (TextView) findViewById(R.id.Vo2maxml4TextViewID);
        TextView tvVo2maxml5 = (TextView) findViewById(R.id.Vo2maxml5TextViewID);

        Intent intent= getIntent();
        Bundle user_info = intent.getExtras(), power_values =intent.getExtras(),vo2max_liter_values =intent.getExtras(),
               vo2max_mliter_values =intent.getExtras(),antal_vandor =intent.getExtras() ;

        final String[] userInfo= user_info.getStringArray(CalculationActivity.USER_KEY2);
        final double[] powerValues= power_values.getDoubleArray(CalculationActivity.POWER_KEY);
        final double[] vo2max_liter_Values = vo2max_liter_values.getDoubleArray(CalculationActivity.VO2MAX_LITER_KEY);
        final double[] vo2max_mliter_Values = vo2max_mliter_values.getDoubleArray(CalculationActivity.VO2MAX_MLITER_KEY);
        final double[] antalVandor = antal_vandor.getDoubleArray(CalculationActivity.ANTAL_VANDOR_KEY);

        //double vo2Max_5 = intent.getDoubleExtra(CalculationActivity.VO2_MAX, 0); // or double number = getIntent().getExtras().getInt("NEW_NUMBER");
        //Create the text view
       // TextView textView = new TextView(this);
        //textView.setTextSize(25);
        //textView.setTextColor(Color.BLUE);
        //textView.setBackgroundColor(Color.BLUE);
        DecimalFormat formatVal= new DecimalFormat("##.##");//textView.setText(formatVal.format(powerValues[0]));

        assert userInfo != null; assert powerValues != null; assert vo2max_liter_Values != null; assert vo2max_mliter_Values != null; assert antalVandor != null;
        tvUserID.setText(" ID:  "+ userInfo[0]);
        tvLaps3.setText(" #Laps (3 min):  " + antalVandor[0]);
        tvLaps4.setText(" #Laps (4 min):  " + antalVandor[1]);
        tvLaps5.setText(" #Laps (5 min):  " + antalVandor[2]);

        tvPower3.setText(" Power (3MPT):  "+ formatVal.format(powerValues[0])+ " [W]");
        tvPower4.setText(" Power (4MPT):  "+ formatVal.format(powerValues[1])+ " [W]");
        tvPower5.setText(" Power (5MPT):  "+ formatVal.format(powerValues[2])+ " [W]");


        tvVo2maxl3.setText(" V02max (3 min): " + formatVal.format(vo2max_liter_Values[0]) + " [l/min]");
        tvVo2maxl4.setText(" V02max (4 min): " + formatVal.format(vo2max_liter_Values[1]) + " [l/min]");
        tvVo2maxl5.setText(" V02max (5 min): " + formatVal.format(vo2max_liter_Values[2]) + " [l/min]");

        tvVo2maxml3.setText(" V02max (3 min): " + formatVal.format(vo2max_mliter_Values[0]) +" [ml/kg/min]");
        tvVo2maxml4.setText(" V02max (4 min): " + formatVal.format(vo2max_mliter_Values[1]) +" [ml/kg/min]");
        tvVo2maxml5.setText(" V02max (5 min): " + formatVal.format(vo2max_mliter_Values[2]) +" [ml/kg/min]");

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MenyActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    //tar bort funktionen att gå bakåt via mobilens egna knappar
    @Override
    public void onBackPressed()
    {
        // super.onBackPressed(); // Comment this super call to avoid calling finish() //tom rad bara
    }
}
