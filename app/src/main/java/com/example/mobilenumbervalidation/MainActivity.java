package com.example.mobilenumbervalidation;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText etMobileNumber;
    private Button btnSubmit;
    private String mobile = "1234567890";
    private int cursorPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMobileNumber = findViewById(R.id.et_mobile);
        btnSubmit = findViewById(R.id.btn_submit);

        etMobileNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        String formattedNumber = PhoneNumberUtils.formatNumber(mobile, "US");
        etMobileNumber.setText(formattedNumber);

        enableDisableButton(formattedNumber);
        etMobileNumber.addTextChangedListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int cursor, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int cursor, int i1, int i2) {
        cursorPosition = cursor;
        enableDisableButton(String.valueOf(charSequence));
    }

    private void enableDisableButton(String mobile) {
        String mobileNumber = mobile.replaceAll("[^0-9]", "");
        if (mobileNumber.length() == 10) {
            btnSubmit.setEnabled(true);
            btnSubmit.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            btnSubmit.setEnabled(false);
            btnSubmit.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBtnDisabled));
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() > 0) {
            if (cursorPosition != editable.length()) {
                if (cursorPosition == 0) return;
                if (!editable.toString().contains("-")) {
                    editable.replace(cursorPosition, cursorPosition, "-");
                } else if (!editable.toString().contains(" ")) {
                    editable.replace(cursorPosition, cursorPosition, " ");
                } else if (!editable.toString().contains("(")) {
                    editable.replace(cursorPosition, cursorPosition, "(");
                } else if (!editable.toString().contains(")")) {
                    editable.replace(cursorPosition, cursorPosition, ")");
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                log("Submit Button");
                break;
        }
    }

    private void log(String message) {
        Log.d(TAG, "" + message);
    }
}
