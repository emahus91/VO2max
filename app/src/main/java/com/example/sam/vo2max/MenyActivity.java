package com.example.sam.vo2max;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meny_activity);

        showActionBar();

        Button btn = (Button)findViewById(R.id.StartNewMeasurementID);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MenyActivity.this, UserProfileActivity.class);
                        startActivity(intent);
                    }
                });
    }

    public void viewContact(View view){
        Intent intent1 = new Intent(MenyActivity.this, DataListActivity.class);
        startActivity(intent1);
    }

    public void search_contact(View view){
        Intent intent2 = new Intent(MenyActivity.this, SearchContactActivity.class);
        startActivity(intent2);
    }

    public void showActionBar(){

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }
}
