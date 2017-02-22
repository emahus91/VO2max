package com.example.sam.vo2max;

        import android.content.Intent;
        import android.graphics.PorterDuff;
        import android.os.Bundle;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Chronometer;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import java.text.DecimalFormat;

public class SecondActivity extends AppCompatActivity {

    public final static String POWER_KEY ="com.example.sam.vo2max.POWER_KEY ";
    public final static String USER_KEY2 ="com.example.sam.vo2max.USER_KEY2";
    public final static String VO2MAX_LITER_KEY ="com.example.sam.vo2max.VO2MAX_LITER_KEY";
    public final static String VO2MAX_MLITER_KEY ="com.example.sam.vo2max.VO2MAX_MLITER_KEY";
    public final static String ANTAL_VANDOR_KEY ="com.example.sam.vo2max.ANTAL_VANDOR_KEY";

    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    //Declaration and initialization of the variables used in the activity
    private long timeWhenStopped = 0;
    private String[] userInfo= new String [3];
    private double[] powerValues= new double[3], vo2max_liter_Values= new double[3], vo2max_mliter_Values= new double[3], antalVandor=new double[3]; // Array wich holds three elements valued 0.

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
                if ("00:05".equals(chronometer.getText())) {
                    int value = valueSelector.getValue();
                    etVandor3.setText(String.valueOf(value));
                }

                if ("00:10".equals(chronometer.getText())) {
                    int value = valueSelector.getValue();
                    etVandor4.setText(String.valueOf(value));
                }

                if ("00:15".equals(chronometer.getText())) {
                    int value = valueSelector.getValue();
                    etVandor5.setText(String.valueOf(value));
                }
            }
        });

        //TODO calculatePower(view);

        // Update knappen
        btnUpdate.setOnClickListener(new View.OnClickListener() { // TODO ersätts av onchronometerticklistener efter test
            @Override
            public void onClick(View view) {

                if (etVandor3.getText().toString().length() == 0|| etVandor3.getText().toString().equals("")){
                    //etName.setBackgroundColor(Color.RED);
                    etVandor3.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(SecondActivity.this, "Ange antal vändor!",Toast.LENGTH_LONG).show();
                    return;}
                if (etVandor4.getText().toString().length() == 0 || etVandor4.getText().toString().equals("")){
                    etVandor4.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(SecondActivity.this, "Ange antal vändor!",Toast.LENGTH_LONG).show();
                    return;}
                if (etVandor5.getText().toString().length() == 0 || etVandor5.getText().toString().equals("")){
                    etVandor5.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(SecondActivity.this, "Ange antal vändor!",Toast.LENGTH_LONG).show();
                    return;}

                // skapar lokala variabler inför beräkningen
                double power5MPT_3=0, power5MPT_4=0, power5MPT_5=0,vo2Max_3=0,vo2Max_4=0,vo2Max_5=0; //[W]
                final double tid_3min =180, tid_4min =240 , tid_5min =300, Height =0.62, tA =9.82; //[s],[l/min], ,[m], [m/s²]


                //Recieving user information från Main activity
                Intent intent = getIntent();
                Bundle user_info =intent.getExtras();

                userInfo= user_info.getStringArray(MainActivity.USER_KEY); // userInfo is a string array already declared above

                //3 ekvationer för beräkning av Power(utförd arbete) vid minut 3 4 och 5.
                assert userInfo != null;
                antalVandor[0]=Double.valueOf(etVandor3.getText().toString());
                antalVandor[1]=Double.valueOf(etVandor4.getText().toString());
                antalVandor[2]=Double.valueOf(etVandor5.getText().toString());
                powerValues[0]= (((Double.valueOf(userInfo[2]) * tA) * (antalVandor[0]* Height)) / tid_3min); //userInfo[2]= User Weight
                powerValues[1]= (((Double.valueOf(userInfo[2]) * tA) * (antalVandor[1]* Height)) / tid_4min); // powerValues[0]=Power3min[W], powerValues[1]=Power4min[W], powerValues[2]=Power5min[W]
                powerValues[2]= (((Double.valueOf(userInfo[2]) * tA) * (antalVandor[2]* Height)) / tid_5min);


                //TODO if förtest 1 multiplicera med 1.03
                // while(power5MPT_5!=0) {}
                 if ((Double.valueOf(userInfo[1])) >= 18 && (Double.valueOf(userInfo[1])) <= 59) { //means Young adults b/w 18 & 59 years old
                     vo2max_liter_Values[0] = (powerValues[0] - 21.296) / 33.242;//vo2max_liter_Values[0]= Vo2max3min [l/min]
                     vo2max_liter_Values[1] = (powerValues[1] - 21.296) / 33.242;//vo2max_liter_Values[1]= Vo2max4min [l/min]
                     vo2max_liter_Values[2] = (powerValues[2] - 21.296) / 33.242;//vo2max_liter_Values[2]= Vo2max5min [l/min]

                     vo2max_mliter_Values[0]= (vo2max_liter_Values[0]*1000)/(Double.valueOf(userInfo[1])); //vo2max_mliter_Values[0]= Vo2max3min [ml/kg/min]
                     vo2max_mliter_Values[1]= (vo2max_liter_Values[1]*1000)/(Double.valueOf(userInfo[1])); //vo2max_mliter_Values[1]= Vo2max3min [ml/kg/min]
                     vo2max_mliter_Values[2]= (vo2max_liter_Values[2]*1000)/(Double.valueOf(userInfo[1])); //vo2max_mliter_Values[2]= Vo2max3min [ml/kg/min]

                    }else if ((Double.valueOf(userInfo[1])) >= 60 && (Double.valueOf(userInfo[1])) <= 100) { //means Old adults b/w 60 & 100 years old
                     vo2max_liter_Values[0]= (powerValues[0] - 11.026) / 40.816;
                     vo2max_liter_Values[1]= (powerValues[1] - 11.026) / 40.816;
                     vo2max_liter_Values[2]= (powerValues[2] - 11.026) / 40.816;

                     vo2max_mliter_Values[0]= (vo2max_liter_Values[0]*1000)/(Double.valueOf(userInfo[1])); //userInfo[1]= user Age
                     vo2max_mliter_Values[1]= (vo2max_liter_Values[1]*1000)/(Double.valueOf(userInfo[1]));
                     vo2max_mliter_Values[2]= (vo2max_liter_Values[2]*1000)/(Double.valueOf(userInfo[1]));
                         }// else if (power5MPT_5==0){vo2Max_5= 0;} //TODO denna rad funkar inte just nu, lägg in det som en while argument

                //Skickar all data till 3dje aktivitet för analys
                Intent intent2 = new Intent(SecondActivity.this,TheMeasurementActivity.class);
                Bundle user_info2 = new Bundle(), power_values =new Bundle(), vo2max_liter_values =new Bundle(), vo2max_mliter_values =new Bundle(), antal_vandor= new Bundle();

                user_info2.putStringArray(USER_KEY2, userInfo);

                //powerValues=new double[]{power5MPT_3, power5MPT_4, power5MPT_5};
                //vo2max_liter_Values=new double[]{vo2Max_3, vo2Max_4, vo2Max_5};
                power_values.putDoubleArray(POWER_KEY, powerValues);
                vo2max_liter_values.putDoubleArray(VO2MAX_LITER_KEY, vo2max_liter_Values);
                vo2max_mliter_values.putDoubleArray(VO2MAX_MLITER_KEY, vo2max_mliter_Values);
                antal_vandor.putDoubleArray(ANTAL_VANDOR_KEY,antalVandor);

                intent2.putExtras(user_info2);  //intent2.putExtra(USER_NAME2, sName);  == för enskilda variabler
                intent2.putExtras(power_values); // intent2.putExtra(POWER_3, power5MPT_3)
                intent2.putExtras(vo2max_liter_values);
                intent2.putExtras(vo2max_mliter_values);
                intent2.putExtras(antal_vandor);
                startActivity(intent2);

                addContact();
            }
        });
    }

    //TODO RENSA DATABASEN EFTER VISS ANTALL USERINFORMATION
    //TODO CHECK THAT THE USER IONFORMATION IS NOT BEING SAVED AGAIN(VIKTIGT), (if null/empty dont save)
     public void addContact()
      {
        DecimalFormat formatVal= new DecimalFormat("##.##");
        String name = userInfo[0];
        String power3= String.valueOf(formatVal.format(powerValues[0]));
        String power4= String.valueOf(formatVal.format(powerValues[1]));
        String power5= String.valueOf(formatVal.format(powerValues[2]));
        String vo2max_liter_3 = String.valueOf(formatVal.format(vo2max_liter_Values[0]));
        String vo2max_liter_4 = String.valueOf(formatVal.format(vo2max_liter_Values[1]));
        String vo2max_liter_5 = String.valueOf(formatVal.format(vo2max_liter_Values[2]));
        String vo2max_mliter_3 = String.valueOf(formatVal.format(vo2max_mliter_Values[0]));
        String vo2max_mliter_4 = String.valueOf(formatVal.format(vo2max_mliter_Values[1]));
        String vo2max_mliter_5 = String.valueOf(formatVal.format(vo2max_mliter_Values[2]));
        String antal_vandor_3 = String.valueOf(formatVal.format(antalVandor[0]));
        String antal_vandor_4 = String.valueOf(formatVal.format(antalVandor[1]));
        String antal_vandor_5 = String.valueOf(formatVal.format(antalVandor[2]));


       userDbHelper = new UserDbHelper(context);
       sqLiteDatabase = userDbHelper.getWritableDatabase();
       userDbHelper.addInformations(name, power3, power4, power5, vo2max_liter_3,
                                    vo2max_liter_4, vo2max_liter_5, vo2max_mliter_3,
                                    vo2max_mliter_4, vo2max_mliter_5, antal_vandor_3,
                                    antal_vandor_4,antal_vandor_5,sqLiteDatabase);
       Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();
       userDbHelper.close();
     }

    public void calculatePower(View view){

    }

    //TODO when switching between activities? (ex när användaren trycker tillbaka(till main activity/second activity) hur hantera chronometern?)
    //Todo onResume();  ex Chronometern? userinformation från Second/ThirdActivity
    //Todo onPause();  ex Chronometern? userinformation från Second/ThirdActivity
    //Todo onDestroy(); finns det värden som behöver sparas?

}
