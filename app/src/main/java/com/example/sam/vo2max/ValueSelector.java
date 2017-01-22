package com.example.sam.vo2max;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.sam.customviews.R;

/**
 * View to allow the selection of a numeric value by pressing plus/minus buttons.  Pressing and holding
 * a button will update the value repeatedly.
 * <p>
 * This view can be configured with a minimum and maximum value.  There is also a label that will
 * display below the current value.
 * </p>
 *
 */
public class ValueSelector extends RelativeLayout {

    private int minValue = Integer.MIN_VALUE;
    private int maxValue = Integer.MAX_VALUE;

    View rootView;
    TextView valueTextView;
    View minusButton;
    View plusButton;


    public ValueSelector(Context context) {
        super(context);
        init(context);
    }

    public ValueSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ValueSelector(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    public int getMinValue() {
        return minValue;
    }


    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }


    public int getMaxValue() {
        return maxValue;
    }


    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getValue() {
        return Integer.valueOf(valueTextView.getText().toString());
    }


    public void setValue(int newValue) {
        int value = newValue;
        if(newValue < minValue) {
            value = minValue;
        } else if (newValue > maxValue) {
            value = maxValue;
        }

        valueTextView.setText(String.valueOf(value));
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.value_selector, this);
        valueTextView = (TextView) rootView.findViewById(R.id.valueTextView);

        minusButton = rootView.findViewById(R.id.minusButton);
        plusButton = rootView.findViewById(R.id.plusButton);

        minusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementValue();
            }
        });


        plusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
            }
        });

    }

    private void incrementValue() {
        int currentVal = Integer.valueOf(valueTextView.getText().toString());
        if(currentVal < maxValue) {
            valueTextView.setText(String.valueOf(currentVal + 1));
        }
    }

    private void decrementValue() {
        int currentVal = Integer.valueOf(valueTextView.getText().toString());
        if(currentVal > minValue) {
            valueTextView.setText(String.valueOf(currentVal - 1));
        }
    }

// TODO method som nollställer räknaren vid tryckning av restart knappen

}
