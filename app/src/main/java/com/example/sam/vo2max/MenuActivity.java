package com.example.sam.vo2max;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    private MediaPlayer buttonClickMenu,buttonInstruction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity_layout);

        showActionBar();

    }

    public void newMeasurment(View view) {
        stopPlaying(buttonClickMenu);
        buttonClickMenu = MediaPlayer.create(this, R.raw.button_click_menu2);
        buttonClickMenu.start();
        Intent intent = new Intent(MenuActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void showDatabase(View view) {
        stopPlaying(buttonClickMenu);
        buttonClickMenu = MediaPlayer.create(this, R.raw.button_click_menu2);
        buttonClickMenu.start();
        Intent intent1 = new Intent(MenuActivity.this, DataListActivity.class);
        startActivity(intent1);
    }

    public void searchContact(View view) {
        stopPlaying(buttonClickMenu);
        buttonClickMenu = MediaPlayer.create(this, R.raw.button_click_menu2);
        buttonClickMenu.start();
        Intent intent2 = new Intent(MenuActivity.this, SearchContactActivity.class);
        startActivity(intent2);
    }

    public void showInstruction(View view) {
        //stopPlaying(buttonInstruction);
        //buttonInstruction = MediaPlayer.create(this, R.raw.button_click_menu2);
        //buttonInstruction.start();
    }

    public void showActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void stopPlaying(MediaPlayer mp) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
