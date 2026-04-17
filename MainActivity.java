package com.myvivanx.myvivanxcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView tvMain, tvHistory;
    double firstNumber = 0;
    String currentOperator = "";
    boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMain = (TextView) findViewById(R.id.tvMain);
        tvHistory = (TextView) findViewById(R.id.tvHistory);
    }

    public void onNumberClick(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        v.startAnimation(anim);
        
        Button b = (Button) v;
        String val = b.getText().toString();

        if (isNewOp) {
            tvMain.setText(val);
            isNewOp = false;
        } else {
            tvMain.append(val);
        }

        // Live history update: Jab 2nd number type ho raha ho
        if (!currentOperator.isEmpty()) {
            tvHistory.setText(firstNumber + " " + currentOperator + " " + tvMain.getText().toString());
        }
    }

    public void onOperatorClick(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        v.startAnimation(anim);
        
        try {
            firstNumber = Double.parseDouble(tvMain.getText().toString());
            currentOperator = ((Button) v).getText().toString();
            tvHistory.setText(firstNumber + " " + currentOperator);
            isNewOp = true;
        } catch (Exception e) {}
    }

    public void onEqualClick(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        v.startAnimation(anim);
        
        try {
            double secondNumber = Double.parseDouble(tvMain.getText().toString());
            double result = 0;

            if (currentOperator.equals("+")) result = firstNumber + secondNumber;
            else if (currentOperator.equals("-")) result = firstNumber - secondNumber;
            else if (currentOperator.equals("*")) result = firstNumber * secondNumber;
            else if (currentOperator.equals("÷")) result = firstNumber / secondNumber;

            tvHistory.setText(firstNumber + " " + currentOperator + " " + secondNumber + " =");
            tvMain.setText(String.valueOf(result));
            currentOperator = "";
            isNewOp = true;
        } catch (Exception e) {}
    }

    public void onClearClick(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        v.startAnimation(anim);
        tvMain.setText("0");
        tvHistory.setText("");
        firstNumber = 0;
        currentOperator = "";
        isNewOp = true;
    }
}
