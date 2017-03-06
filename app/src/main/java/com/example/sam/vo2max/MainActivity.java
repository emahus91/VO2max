package com.example.sam.vo2max;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    public final static String USER_KEY = "com.example.sam.vo2max.USER_KEY";
    public final static String CHECKBOX_KEY = "com.example.sam.vo2max.CHECKBOX_KEY";

    private CheckBox cbPretest1, cbOtherTest;
    private Button  btnNewMeasurement;
    private EditText etName, etAge, etWeight;
    private String sName,sAge,sWeight;
    private Boolean boxChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showActionBar();
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

        btnNewMeasurement = (Button) findViewById(R.id.NewMeasurementButtonID);
        etName = (EditText) findViewById(R.id.NameEditTextID);
        etAge = (EditText) findViewById(R.id.AgeEditTextID);
        etWeight = (EditText) findViewById(R.id.WeightEditTextID);
        cbPretest1=(CheckBox) findViewById(R.id.PretestCheckBoxID);
        cbOtherTest=(CheckBox) findViewById(R.id.OtherTestsCheckBoxID);

        btnNewMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sName = etName.getText().toString();
                sAge = etAge.getText().toString();
                sWeight = etWeight.getText().toString();

                //TODO Tricks för att påminna/tvinga användaren att välja en av alternativen(viktigast pretest1) då om man bara har en checkbox kan användaren välja att ignorera kryssa i.
                if(cbPretest1.isChecked()&& cbOtherTest.isChecked()){
                    Toast.makeText(MainActivity.this, "Please tick only one checkbox!!",Toast.LENGTH_LONG).show();
                    return;}

                if (cbPretest1.isChecked()|| cbOtherTest.isChecked()){}
                    else{
                    cbPretest1.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    cbOtherTest.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Checkbox not ticked!",Toast.LENGTH_LONG).show();
                    return;}

                if (sName.length() == 0 || sName.equals("")){
                    //etName.setBackgroundColor(Color.RED);
                    etName.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Please Write your Name!",Toast.LENGTH_LONG).show();
                    return;}

                //kontrollera om den inmatade Åldern stämmer
                if (sAge.length() == 0 || sAge.equals("")) {
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Please Write your Age!", Toast.LENGTH_LONG).show();
                    return;
                }else if((Double.valueOf(sAge)) >= 100 || (Double.valueOf(sAge)) <18){
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this,"Wrong Age range!" ,Toast.LENGTH_LONG).show();
                    return;}

                if (sWeight.length() == 0){
                    etWeight.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Please Write your Weight!",Toast.LENGTH_LONG).show();
                    return;
                }else if((Double.valueOf(sWeight)) >= 150 || (Double.valueOf(sWeight)) <10){
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Wrong Weight range!",Toast.LENGTH_LONG).show();
                    return;}

                copyUserInformation(); // Copy All userinformation to Second activity
            }
        });
    }

    public void copyUserInformation(){

        Intent intent1 = new Intent(MainActivity.this,SecondActivity.class);
        Bundle user_info = new Bundle();

        user_info.putStringArray(USER_KEY, new String[]{sName, sAge,sWeight}); //String[0]= sName
        intent1.putExtra(CHECKBOX_KEY,boxChecked);
        intent1.putExtras(user_info);

        startActivity(intent1);
    }


    public void viewContact(View view)
    {
        Intent intent = new Intent(this, DataListActivity.class);
        startActivity(intent);
    }


    public void search_contact(View view)
    {
        Intent intent3 = new Intent(MainActivity.this, SearchContactActivity.class);
        startActivity(intent3);
    }
   public void showActionBar(){

       ActionBar actionBar = getSupportActionBar();
       actionBar.setLogo(R.mipmap.ic_launcher);
       actionBar.setDisplayUseLogoEnabled(true);
       actionBar.setDisplayShowHomeEnabled(true);
   }

}