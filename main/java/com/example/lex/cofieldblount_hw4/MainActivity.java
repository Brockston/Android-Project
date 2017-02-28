package com.example.lex.cofieldblount_hw4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    String[] items = {"Moses Cone Hospital", "Wesley Long Hospital", "Women's Hospital", "Cone Health", "Kindred Hospital",
            "Duke University Hospital", "UNC Health Care" };
    String[] a = {"Phone Call", "Text", "Email"};
    String[] dr = {"Dr. Abbott", "Dr. Barber", "Dr. Davis", "Dr. Johnson","Dr. Gregory", "Dr. Brown", "Dr. Cheek"};
    ListView myList, doc;
    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    TextView dateAndTimeLabel, notify;
    Calendar dateAndTime = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay,
                              int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        notify = (TextView) findViewById(R.id.notify);

        Button date = (Button) findViewById(R.id.dateRadio);
        Button time = (Button) findViewById(R.id.timeRadio);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        Spinner spin2 = (Spinner) findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        ArrayAdapter<String> bb = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dr);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(bb);

        myList = (ListView) findViewById(R.id.list);
        myList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, a));
        myList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    notify.setText("You Selected Phone Calls");
                } else if (i == 1) {
                    notify.setText("You Selected Text Messages");
                } else if (i == 2) {
                    notify.setText("You Selected Emails");
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this,
                        d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this,
                        t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE),
                        true).show();
            }
        });
        dateAndTimeLabel = (TextView) findViewById(R.id.dateAndTime);
        updateLabel();


    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        parent.getItemAtPosition(position);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void updateLabel() {
        dateAndTimeLabel.setText(fmtDateAndTime
                .format(dateAndTime.getTime()));
    }
}
