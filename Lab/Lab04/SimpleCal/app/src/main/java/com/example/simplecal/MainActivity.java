package com.example.simplecal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "CalculatorActivity";
    private Calculator mCalculator;
    private EditText mOperandOneEditText;
    private EditText mOperandTwoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the calculator class and views

        mCalculator = new Calculator();
        mResultTextView = findViewById(R.id.operation_result_text_view);
        mOperandOneEditText = findViewById(R.id.operand_one_edit_text);
        mOperandTwoEditText = findViewById(R.id.operand_two_edit_text);

    }

    public void onAdd(View view) {
        compute(Calculator.Operator.ADD);
    }


    public void onSub(View view) {
        compute(Calculator.Operator.SUB);
    }

    public void onDiv(View view) {
        compute(Calculator.Operator.DIV);
    }

    public void onMul(View view) {
        compute(Calculator.Operator.MUL);
    }


    // get the text which was entered in the Edit Text field
    private Double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        if (!operandText.equals("")) {
            return Double.valueOf(operandText);
        } else {
            Log.e(TAG, "Empty string");
            mResultTextView.setText(R.string.blank);
            return 0.0;
        }
    }

    // get the text which was entered in Edit Text field
    private static String getOperandText(EditText operandEditText) {
        return operandEditText.getText().toString();
    }


    // adding the operation function for on click buttons
    private void compute(Calculator.Operator operator) {
        double operandOne;
        double operandTwo;
        try {
            operandOne = getOperand(mOperandOneEditText);
            operandTwo = getOperand(mOperandTwoEditText);
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            mResultTextView.setText(getString(R.string.computationError));
            return;
        }

        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(mCalculator.addition(operandOne, operandTwo));
                break;
            case SUB:
                result = String.valueOf(mCalculator.subtraction(operandOne, operandTwo));
                break;
            case DIV:
                if (operandTwo == 0.0) {
                    result = "Error: Division by 0";
                } else {
                    result = String.valueOf(mCalculator.division(operandOne, operandTwo));
                }
                break;
            case MUL:
                result = String.valueOf(mCalculator.multiply(operandOne, operandTwo));
                break;
            default:
                result = getString(R.string.computationError);
                break;
        }
        mResultTextView.setText(result);
    }
}