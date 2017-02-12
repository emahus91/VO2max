package com.example.sam.vo2max;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import static com.example.sam.vo2max.MainActivity.USER_NAME;
import static com.example.sam.vo2max.MainActivity.USER_WEIGHT;


//TODO import Chronometer class

public class SecondActivity extends AppCompatActivity {

    public final static String USER_NAME2 = "com.example.sam.vo2max.USER_NAME2";
    public final static String USER_AGE2 = "com.example.sam.vo2max.USER_AGE2";
    public final static String USER_WEIGHT2 = "com.example.sam.vo2max.USER_WEIGHT2";
    public final static String NEW_NUMBER ="com.example.sam.vo2max.NEW_NUMBER ";

    private long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // TODO behöver all metoder skrivas inom onCreate metoden?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_main);

        final ValueSelector valueSelector = (ValueSelector) findViewById(R.id.valueSelector);
        valueSelector.setMinValue(0);
        valueSelector.setMaxValue(80);

        //INITIATING VIEWS
        final Chronometer simpleChronometer= (Chronometer) findViewById(R.id.simpleChronometer);
        final EditText etVandor3= (EditText) findViewById(R.id.Vandor3EditTextID);
        final EditText etVandor4= (EditText) findViewById(R.id.Vandor4EditTextID);
        final EditText etVandor5= (EditText) findViewById(R.id.Vandor5EditTextID);
        final EditText etBorg3= (EditText) findViewById(R.id.Borg3EditTextID);
        final EditText etBorg4= (EditText) findViewById(R.id.Borg4EditTextID);
        final EditText etBorg5= (EditText) findViewById(R.id.Borg5EditTextID);

        Button btnUpdate = (Button) findViewById(R.id.UpdateButtonID);
        Button btnStart = (Button) findViewById(R.id.StartButtonID);
        Button btnStop = (Button) findViewById(R.id.StopButtonID);
        Button btnRestart=(Button) findViewById(R.id.RestartButtonID);


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
                //TODO if sats om stop knappen trycks 2 gånger.....(fixa stopknapp bug)
                simpleChronometer.stop();
                timeWhenStopped = simpleChronometer.getBase() - SystemClock.elapsedRealtime();
            }
        });

        // // perform click  event on restart button to set the base time on chronometer
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleChronometer.stop();
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
            }
        });

        simpleChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer)
            {
                if ("03:00".equals(chronometer.getText())) {
                       int value = valueSelector.getValue();
                     etVandor3.setText(String.valueOf(value));
                }

                if ("04:00".equals(chronometer.getText())) {
                    int value = valueSelector.getValue();
                    etVandor4.setText(String.valueOf(value));
                }

                if ("05:00".equals(chronometer.getText())) {
                    int value = valueSelector.getValue();
                    etVandor5.setText(String.valueOf(value));
                }
            }
        });

            //calculatePower(view);

//TODO Hämta data från main activity Och skicka vidare till Thirdactivity(använda globala variabler istället?)
        // Update knappen
        btnUpdate.setOnClickListener(new View.OnClickListener() { // TODO ersätts av onchronometerticklistener efter test
            @Override
            public void onClick(View view) {

                double power5MPT_3, power5MPT_4, power5MPT_5, vo2Max, Height =1.30, tA =9.82; //[W], [l/min], ,[m], [m/s²]
                double tid_3min =180, tid_3min4 =240 , tid_5min =300; //[s]

                Intent intent = getIntent();
                String sName = intent.getStringExtra(USER_NAME);
                String sAge = intent.getStringExtra(MainActivity.USER_AGE);
                String sWeight = intent.getStringExtra(MainActivity.USER_WEIGHT);

                power5MPT_3= (((Double.valueOf(sWeight) * tA) * (Double.valueOf(etVandor3.getText().toString())* Height)) / tid_3min);
                //TODO kopiera över till inten 3(3dje aktivitet)?

                Intent intent2= new Intent(SecondActivity.this,TheMeasurementActivity.class);

                intent2.putExtra(NEW_NUMBER, power5MPT_3);
                //intent2.putExtra(USER_NAME2, sName);
                //intent2.putExtra(USER_AGE2, sAge);
                //intent2.putExtra(USER_WEIGHT2, sWeight);
                startActivity(intent2);
            }
        });

    }


    //TODO skicka all data(name & age & weight(kan direkt överföras från main activity till third?), vandor och borg) till 3dje activity och beräkna beräkna Power, V02 och spara i databas,
    //

     //Todo fixa Borg knapparna(behöver vi ha + - knappar??)

    //TODO when switching between activities? (ex när användaren trycker tillbaka(till main activity/second activity) hur hantera chronometern?)
    //Todo onResume();
    //Todo onPause();  Chronometern?
    //Todo onDestroy(); finns det värden som behöver sparas?


}
