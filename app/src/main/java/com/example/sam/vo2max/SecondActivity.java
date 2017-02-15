package com.example.sam.vo2max;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity {

    public final static String POWER_VALUES ="com.example.sam.vo2max.POWER_VALUES ";
    public final static String USER_INFO2 ="com.example.sam.vo2max.USER_INFO2";
    public final static String VO2_MAX ="com.example.sam.vo2max.USER_VO2_MAX";



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
                // skapar lokala variabler inför beräkningen
                double power5MPT_3, power5MPT_4, power5MPT_5, vo2Max_5=0, ageLimit=45; //[W]
                final double tid_3min =180, tid_4min =240 , tid_5min =300, Height =1.30, tA =9.82; //[s],[l/min], ,[m], [m/s²]

                //Tar emot user information från Main activity

                Intent intent = getIntent();
                Bundle user_Info=intent.getExtras();

                final String[] userInfo= user_Info.getStringArray(MainActivity.USER_INFO);

                //3 ekvationer för beräkning av Power(utförd arbete) vid minut 3 4 och 5.
                power5MPT_3= (((Double.valueOf(userInfo[2]) * tA) * (Double.valueOf(etVandor3.getText().toString())* Height)) / tid_3min);
                power5MPT_4= (((Double.valueOf(userInfo[2]) * tA) * (Double.valueOf(etVandor4.getText().toString())* Height)) / tid_4min);
                power5MPT_5= (((Double.valueOf(userInfo[2]) * tA) * (Double.valueOf(etVandor5.getText().toString())* Height)) / tid_5min);

                if ((Double.valueOf(userInfo[1])) >= ageLimit && (Double.valueOf(userInfo[1])) <= 90){ //means old adults b/w 45 & 90 years old
                        vo2Max_5 = (power5MPT_5 - 7.9398)/36.637;}
                    else if((Double.valueOf(userInfo[1])) <ageLimit && (Double.valueOf(userInfo[1])) >= 10){
                         vo2Max_5 = (power5MPT_5 - 16.37)/39.5;} ////means young adults b/w 10 & 44 years old
                       else{
                            Toast.makeText(SecondActivity.this, "Ogiltig Alder",Toast.LENGTH_LONG).show();
                        }

               //Skickar all data till 3dje aktivitet för analys
                Intent intent2 = new Intent(SecondActivity.this,TheMeasurementActivity.class);
                Bundle user_Info2 = new Bundle(), power_Values =new Bundle();

                user_Info2.putStringArray(USER_INFO2, userInfo);
                power_Values.putDoubleArray(POWER_VALUES, new double[]{power5MPT_3, power5MPT_4, power5MPT_5}); //TODO initialize the array before

                intent2.putExtras(user_Info2);  //intent2.putExtra(USER_NAME2, sName);  == för enskilda variabler
                intent2.putExtras(power_Values); // intent2.putExtra(POWER_VALUE, power5MPT_3)
                intent2.putExtra(VO2_MAX, vo2Max_5);
                startActivity(intent2);
            }
        });

    }

    public void calculatePower(View view){

    }
    //TODO skicka all data(name & age & weight(kan direkt överföras från main activity till third?), vandor och borg) till 3dje activity och beräkna beräkna Power, V02 och spara i databas,

     //Todo fixa Borg knapparna(behöver vi ha + - knappar??)

    //TODO when switching between activities? (ex när användaren trycker tillbaka(till main activity/second activity) hur hantera chronometern?)
    //Todo onResume();
    //Todo onPause();  Chronometern?
    //Todo onDestroy(); finns det värden som behöver sparas?


}
