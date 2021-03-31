package com.example.pickerfortime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.BreakIterator;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int hour, minute;
    Button timeButton;
    Button dateButton;
    EditText dateText;
    EditText timeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeButton = findViewById(R.id.time_button);
        dateButton = findViewById(R.id.date_button);
        dateText = findViewById(R.id.date_text);
        timeText = findViewById(R.id.time_text);
    }

    public void showTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int selectMinute) {
                hour = hourOfDay;
                minute = selectMinute;
                timeText.setText(String.format(Locale.getDefault(), "%02d:%2d", hour, minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.show();
    }

    public void showDatePicker(View view) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateText.setText(MessageFormat.format("{0}/{1}/{2}", dayOfMonth, month, year));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, mYear, mMonth, mDay);
        datePickerDialog.show();

    }


}