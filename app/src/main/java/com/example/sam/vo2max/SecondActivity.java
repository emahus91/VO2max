package com.example.sam.vo2max;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//import com.example.sam.vo2max;


//TODO import activity 1 class (kolla på demo appen från github om hur man anropar alla klaser till SecondActivity
//TODO import Chronometer class

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // TODO behöver all metoder skrivas inom onCreate metoden?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_main);

        //final ValueSelector valueSelector = (ValueSelector) findViewById(R.id.valueSelector);
        //valueSelector.setMinValue(0);
        //valueSelector.setMaxValue(100);


        Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() { // TODO ersätts av onchronometerticklistener efter test
            @Override
            public void onClick(View view) {
          //      int value = valueSelector.getValue();
                // TODO Kopiera valueselector värdet till editTextVander3, editTextVander4 och editTextVander3 när chronometern räknat 3, 4 och 5 min
                //valueBar.setValue(value);

                //code to use Object Animation instead of the built-in ValueBar animation
                //if you use this, be sure the call valueBar.setAnimated(false);
                /*
                ObjectAnimator anim = ObjectAnimator.ofInt(valueBar, "value", valueBar.getValue(), value);
                anim.setDuration(1000);
                anim.start();
                */
            }
        });

        // TODO method som nollställer räknaren vid tryckning av restart knappen
        Button buttonRestart = (Button) findViewById(R.id.buttonRestart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set value av text vie till noll (currentvalue till 0)
                                                    /*
         private void decrementValue() {
            int currentVal = Integer.valueOf(valueTextView.getText().toString());
            if(currentVal > minValue) {
                valueTextView.setText(String.valueOf(currentVal - 1));
            }
        }*/
            }
        });

    }

    //Todo onResume();
    //Todo onPause();
    //Todo onDestroy();
}
