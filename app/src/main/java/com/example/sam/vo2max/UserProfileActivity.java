package com.example.sam.vo2max;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {

    public final static String USER_KEY = "com.example.sam.vo2max.USER_KEY";
    public final static String CHECKBOX_KEY = "com.example.sam.vo2max.CHECKBOX_KEY";

    private CheckBox cbPretest1, cbOtherTest;
    private Button btnNewTest;
    private EditText etName, etAge, etWeight;
    private String sName,sAge,sWeight;
    private Boolean boxChecked;
    private MediaPlayer buttonClickMenu,errorSound,backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addListnerOnCheckBox(); //onCheckListnerCheckBox
        addListnerOnMeasurmentButton(); //onClickListnerMeasurmentButton

    }

    public void addListnerOnCheckBox(){//onCheckListnerRadioButton
        cbPretest1=(CheckBox) findViewById(R.id.PretestCheckBoxID);

        cbPretest1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //is box checked?
                if (((CheckBox) v).isChecked()) {//(((CheckBox) v).isChecked())
                    boxChecked= true;
                }
                else
                    boxChecked= false;
            }
        });
    }

    public void addListnerOnMeasurmentButton(){

        btnNewTest = (Button) findViewById(R.id.StartTestButtonID);
        etName = (EditText) findViewById(R.id.NameEditTextID);
        etAge = (EditText) findViewById(R.id.AgeEditTextID);
        etWeight = (EditText) findViewById(R.id.WeightEditTextID);
        cbPretest1=(CheckBox) findViewById(R.id.PretestCheckBoxID);
        cbOtherTest=(CheckBox) findViewById(R.id.OtherTestsCheckBoxID);

        btnNewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sName = etName.getText().toString();
                sAge = etAge.getText().toString();
                sWeight = etWeight.getText().toString();
                stopPlaying(errorSound);
                errorSound = MediaPlayer.create(UserProfileActivity.this, R.raw.error_sound);

                //TODO Tricks för att påminna/tvinga användaren att välja en av alternativen(viktigast pretest1) då om man bara har en checkbox kan användaren välja att ignorera kryssa i.
                if(cbPretest1.isChecked()&& cbOtherTest.isChecked()){
                    errorSound.start();
                    Toast.makeText(UserProfileActivity.this, "Please tick only one checkbox!!",Toast.LENGTH_LONG).show();
                    return;}

                if (cbPretest1.isChecked()|| cbOtherTest.isChecked()){}
                    else{
                    errorSound.start();
                    cbPretest1.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    cbOtherTest.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(UserProfileActivity.this, "Checkbox not ticked!",Toast.LENGTH_LONG).show();
                    return;}

                if (sName.length() == 0 || sName.equals("")){
                    //etName.setBackgroundColor(Color.RED);
                    errorSound.start();
                    etName.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(UserProfileActivity.this, "Please write your ID!",Toast.LENGTH_LONG).show();
                    return;}

                //kontrollera om den inmatade Åldern stämmer
                if (sAge.length() == 0 || sAge.equals("")) {
                    errorSound.start();
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(UserProfileActivity.this, "Please Write your Age!", Toast.LENGTH_LONG).show();
                    return;
                }else if((Double.valueOf(sAge)) >= 100 || (Double.valueOf(sAge)) <18){
                    errorSound.start();
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(UserProfileActivity.this,"Wrong Age range!" ,Toast.LENGTH_LONG).show();
                    return;}

                if (sWeight.length() == 0){
                    errorSound.start();
                    etWeight.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(UserProfileActivity.this, "Please Write your Weight!",Toast.LENGTH_LONG).show();
                    return;
                }else if((Double.valueOf(sWeight)) >= 150 || (Double.valueOf(sWeight)) <10){
                    errorSound.start();
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(UserProfileActivity.this, "Wrong Weight range!",Toast.LENGTH_LONG).show();
                    return;}

                copyUserInformation(); // Copy All userinformation to Second activity

                buttonClickMenu= MediaPlayer.create(UserProfileActivity.this, R.raw.button_click_menu2);
                buttonClickMenu.start();
            }
        });
    }

    public void copyUserInformation(){

        Intent intent1 = new Intent(UserProfileActivity.this,CalculationActivity.class);
        Bundle user_info = new Bundle();

        user_info.putStringArray(USER_KEY, new String[]{sName, sAge,sWeight}); //String[0]= sName
        intent1.putExtra(CHECKBOX_KEY,boxChecked);
        intent1.putExtras(user_info);

        startActivity(intent1); 
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(myIntent, 0);
        stopPlaying(backButton);
        backButton = MediaPlayer.create(UserProfileActivity.this, R.raw.back_button);
        backButton.start();
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