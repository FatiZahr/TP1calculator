package com.example.tp1calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String input = "";
    private String operator = "";
    private double num1 = 0;
    private double num2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Find and set click listeners for number buttons
        setNumberButtonListeners();

        // Find and set click listeners for operator buttons
        setOperatorButtonListeners();

        // Handle equal button click
        Button equalButton = findViewById(R.id.button36);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.button17, R.id.button18, R.id.button19, R.id.button21,
                R.id.button22, R.id.button23, R.id.button25, R.id.button26,
                R.id.button27, R.id.button29, R.id.button30, R.id.button31,
                R.id.button33, R.id.button34, R.id.button35
        };

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    input += clickedButton.getText().toString();
                    resultTextView.setText(input);
                }
            });
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = {
                R.id.button20, R.id.button24, R.id.button28, R.id.button32
        };

        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    if (!input.isEmpty()) {
                        num1 = Double.parseDouble(input);
                        operator = clickedButton.getText().toString();
                        input = "";
                        resultTextView.setText(input);
                    }
                }
            });
        }
    }

    private void calculateResult() {
        if (!input.isEmpty()) {
            num2 = Double.parseDouble(input);
            double result = 0;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "×":
                    result = num1 * num2;
                    break;
                case "÷":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }
            resultTextView.setText(String.valueOf(result));
            input = String.valueOf(result);
        }
    }
}