package com.example.sam.vo2max;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;



//TODO import activity 1 class (kolla på demo appen från github om hur man anropar alla klaser till SecondActivity
//TODO import Chronometer class

public class SecondActivity extends AppCompatActivity
        implements Chronometer.OnChronometerTickListener {

    private long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // TODO behöver all metoder skrivas inom onCreate metoden?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_main);

        final ValueSelector valueSelector = (ValueSelector) findViewById(R.id.valueSelector);
        valueSelector.setMinValue(0);
        valueSelector.setMaxValue(100);

        //INITIATING VIEWS
        final Chronometer simpleChronometer= (Chronometer) findViewById(R.id.simpleChronometer);
        final EditText etVandor3= (EditText) findViewById(R.id.editTextVandor3);
        Button btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        Button btnStart = (Button) findViewById(R.id.buttonStart);
        Button btnStop = (Button) findViewById(R.id.buttonStop);
        Button btnrestart=(Button) findViewById(R.id.buttonRestart);

        btnUpdate.setOnClickListener(new View.OnClickListener() { // TODO ersätts av onchronometerticklistener efter test
            @Override
            public void onClick(View view) {
               int value = valueSelector.getValue();
                // TODO Kopiera valueselector värdet till editTextVander3, editTextVander4 och editTextVander3 när chronometern räknat 3, 4 och 5 min
                //valueBar.setValue(value);
                etVandor3.setText(String.valueOf(value));
            }
        });
        simpleChronometer.setOnChronometerTickListener(this);
        //simpleChronometer.setBase(SystemClock.elapsedRealtime() - (2* 60000 + 0 * 1000)); //making chrono start from a specific time
        // perform click  event on start button to start a chronometer
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO if sats så att chro. inte "räknar tillbax"om start knappen trycks igen
                simpleChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                simpleChronometer.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO if sats om stop knappen trycks 2 gånger.....
                simpleChronometer.stop();
                timeWhenStopped = simpleChronometer.getBase() - SystemClock.elapsedRealtime();
            }
        });

        // // perform click  event on restart button to set the base time on chronometer
        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleChronometer.stop();
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
            }
        });
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {

    }
    //TODO WHEN pressed back? (när användaren trycker tillbaka(till main activity) hur hantera chronometern?)
    //Todo onResume();
    //Todo onPause();
    //Todo onDestroy();


}
