package com.example.sam.vo2max;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String USER_KEY = "com.example.sam.vo2max.USER_KEY";
//TODO fixa datatbas överskrift


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewMeasurement = (Button) findViewById(R.id.StartNewMeasurementID);
        btnNewMeasurement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText etName = (EditText) findViewById(R.id.NameEditTextID);
                EditText etAge = (EditText) findViewById(R.id.AgeEditTextID);
                EditText etWeight = (EditText) findViewById(R.id.WeightEditTextID);

                String sName = etName.getText().toString(); //
                String sAge = etAge.getText().toString(); // TODO Must put in field(toast), for choosing the right Power formula
                String sWeight = etWeight.getText().toString(); //TODO Must put in field(toast), to avoid crash when calculating(SecondActivity)


                if (sName.length() == 0 || sName.equals("")){
                    //etName.setBackgroundColor(Color.RED);
                   etName.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Please Write your Name!",Toast.LENGTH_LONG).show();
                    return;}

                //kontrollera om den inmatade Åldern stämmer
                if (sAge.length() == 0 || sAge.equals("")) {
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Please Write your Age", Toast.LENGTH_LONG).show();
                    return;
                }else if((Double.valueOf(sAge)) >= 100 || (Double.valueOf(sAge)) <18){
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this,"Wrong Age range" ,Toast.LENGTH_LONG).show();
                    return;
                }

                if (sWeight.length() == 0){
                    etWeight.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Please Write your Weight!!",Toast.LENGTH_LONG).show();
                    return;
                }else if((Double.valueOf(sWeight)) >= 150 || (Double.valueOf(sWeight)) <10){
                    etAge.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(MainActivity.this, "Wrong Weight range",Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent1 = new Intent(MainActivity.this,SecondActivity.class);
                Bundle user_Info= new Bundle();

                user_Info.putStringArray(USER_KEY, new String[]{sName, sAge,sWeight}); //String[0]= sName
                intent1.putExtras(user_Info);

                startActivity(intent1);
            }
        });
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


}