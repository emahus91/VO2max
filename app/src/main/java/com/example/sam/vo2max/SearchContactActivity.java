package com.example.sam.vo2max;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContactActivity extends AppCompatActivity {

    TextView tvUserId,tvUserID,tvLaps3,tvLaps4,tvLaps5,tvPower3,tvPower4,tvPower5,
             tvVo2maxl3,tvVo2maxl4,tvVo2maxl5,tvVo2maxml3,tvVo2maxml4,tvVo2maxml5;
    EditText etSearchID;
    Button btnSearch, btnShowUser, btnDeleteUser;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String searchName;
    private MediaPlayer buttonClickMenu,backButton,errorSound;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchcontact_activity_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        etSearchID = (EditText)findViewById(R.id.SearchNameEditTextID);
        btnSearch= (Button) findViewById(R.id.SearchButtonID);
        btnShowUser= (Button) findViewById(R.id.ShowUserButtonID);
        btnDeleteUser= (Button) findViewById(R.id.DeleteButtonID);
        tvUserId = (TextView)findViewById(R.id.UserIdTextViewID);
        tvUserId.setVisibility(View.GONE);
        btnShowUser.setVisibility(View.GONE);
        btnDeleteUser.setVisibility(View.GONE); //Hide button

    }

    public void searchContact(View view)
    {
        searchName = etSearchID.getText().toString();
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = userDbHelper.getContact(searchName, sqLiteDatabase);

        stopPlaying(errorSound);
        errorSound = MediaPlayer.create(SearchContactActivity.this, R.raw.error_sound);

        if (searchName.length() == 0 || searchName.equals("")){
            errorSound.start();
            etSearchID.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(SearchContactActivity.this, "Please Write User ID!",Toast.LENGTH_LONG).show();
            return;}

        if(cursor.moveToFirst())
        {
            String userID = cursor.getString(0);

            tvUserId.setText("User ID:  ´" + userID+ "´  found!");
            tvUserId.setVisibility(View.VISIBLE); // Reveal button
            btnShowUser.setVisibility((View.VISIBLE));
            btnDeleteUser.setVisibility(View.VISIBLE);
        }
        stopPlaying(buttonClickMenu);
        buttonClickMenu = MediaPlayer.create(this, R.raw.button_click_menu2);
        buttonClickMenu.start();
    }

    public void showContact (View view){

        setContentView(R.layout.presentation_activityt_layout);
        tvUserID = (TextView) findViewById(R.id.UserTextViewID);
        tvLaps3 =  (TextView) findViewById(R.id.Laps3TextViewID);
        tvLaps4 =  (TextView) findViewById(R.id.Laps4TextViewID);
        tvLaps5 =  (TextView) findViewById(R.id.Laps5TextViewID);
        tvPower3 = (TextView) findViewById(R.id.Power3TextViewID);
        tvPower4 = (TextView) findViewById(R.id.Power4TextViewID);
        tvPower5 = (TextView) findViewById(R.id.Power5TextViewID);
        tvVo2maxl3 = (TextView) findViewById(R.id.Vo2maxl3TextViewID);
        tvVo2maxl4 = (TextView) findViewById(R.id.Vo2maxl4TextViewID);
        tvVo2maxl5 = (TextView) findViewById(R.id.Vo2maxl5TextViewID);
        tvVo2maxml3 = (TextView) findViewById(R.id.Vo2maxml3TextViewID);
        tvVo2maxml4 = (TextView) findViewById(R.id.Vo2maxml4TextViewID);
        tvVo2maxml5 = (TextView) findViewById(R.id.Vo2maxml5TextViewID);

        searchName = etSearchID.getText().toString();
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        Cursor cursor = userDbHelper.getContact(searchName, sqLiteDatabase);

        if(cursor.moveToFirst())
        {
            String userID = cursor.getString(0);
            String power3 = cursor.getString(1);
            String power4 = cursor.getString(2);
            String power5 = cursor.getString(3);
            String vo2max_liter_3 = cursor.getString(4);
            String vo2max_liter_4 = cursor.getString(5);
            String vo2max_liter_5 = cursor.getString(6);
            String vo2max_mliter_3 = cursor.getString(7);
            String vo2max_mliter_4 = cursor.getString(8);
            String vo2max_mliter_5 = cursor.getString(9);
            String antal_vandor_3 = cursor.getString(10);
            String antal_vandor_4 = cursor.getString(11);
            String antal_vandor_5 = cursor.getString(12);

            tvUserID.setText(" ID:  "+ userID );
            tvLaps3.setText(" #Laps (3 min):  " + antal_vandor_3);
            tvLaps4.setText(" #Laps (4 min):  " + antal_vandor_4);
            tvLaps5.setText(" #Laps (5 min):  " + antal_vandor_5);

            tvPower3.setText(" Power (3MPT):  "+ power3 + " [W]");
            tvPower4.setText(" Power (4MPT):  "+ power4 + " [W]");
            tvPower5.setText(" Power (5MPT):  "+ power5 + " [W]");


            tvVo2maxl3.setText(" V02max (3 min): " + vo2max_liter_3 + " [l/min]");
            tvVo2maxl4.setText(" V02max (4 min): " + vo2max_liter_4+ " [l/min]");
            tvVo2maxl5.setText(" V02max (5 min): " + vo2max_liter_5 + " [l/min]");

            tvVo2maxml3.setText(" V02max (3 min): " + vo2max_mliter_3 +" [ml/kg/min]");
            tvVo2maxml4.setText(" V02max (4 min): " + vo2max_mliter_4 +" [ml/kg/min]");
            tvVo2maxml5.setText(" V02max (5 min): " + vo2max_mliter_5 +" [ml/kg/min]");
        }
        stopPlaying(buttonClickMenu);
        buttonClickMenu = MediaPlayer.create(this, R.raw.button_click_menu2);
        buttonClickMenu.start();
    }

    public void deleteContact (View view)
    {
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        userDbHelper.deleteInformation(searchName,sqLiteDatabase);
        tvUserId.setText("User ID Succefuly Deleted!");
        btnShowUser.setVisibility((View.GONE));
        btnDeleteUser.setVisibility((View.GONE));
        stopPlaying(buttonClickMenu);
        buttonClickMenu = MediaPlayer.create(this, R.raw.button_click_menu2);
        buttonClickMenu.start();
        //Toast.makeText(getBaseContext(),"Contact deleted",Toast.LENGTH_LONG).show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(myIntent, 0);
        //stopPlaying(backButton);
        //backButton = MediaPlayer.create(CalculationActivity.this, R.raw.back_button);
        //backButton.start();
        return true;

    }
    private void stopPlaying(MediaPlayer mp) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
