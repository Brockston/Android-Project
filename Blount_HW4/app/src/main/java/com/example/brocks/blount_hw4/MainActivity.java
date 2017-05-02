package com.example.brocks.blount_hw4;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    String[] items = {"Moses Cone Hospital", "Wesley Long Hospital", "Women's Hospital", "Cone Health", "Kindred Hospital",
            "Duke University Hospital", "UNC Health Care" };
    String[] a = {"Phone Call", "Text", "Email"};
    String[] dr = {"Dr. Abbott", "Dr. Barber", "Dr. Davis", "Dr. Johnson","Dr. Gregory", "Dr. Brown", "Dr. Cheek"};
    static ListView myList;
    ListView doc;
    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    static TextView dateAndTimeLabel, notify;
    static Calendar dateAndTime = Calendar.getInstance();


    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    private static final int uniqueID2 = 456;

    WebView browser;
    TextView infotxt;
    static TextView text, selection, date;
    TableLayout tablerow1;


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
        this.setTitle("Main");

        notification = new NotificationCompat.Builder(this);
        Context context = getApplicationContext();
        notification.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        text = (TextView) findViewById(R.id.text);
        selection = (TextView) findViewById(R.id.selection);
        date = (TextView) findViewById(R.id.date);
        notify = (TextView) findViewById(R.id.notify);



        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }
            }
        };

        //Intent intent = new Intent(this, Login.class);
        //startActivity(intent);

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
                    notify.setTextColor(Color.WHITE);
                    notification.setSmallIcon(R.drawable.logo);
                    notification.setTicker("This is the Ticker");
                    notification.setWhen(System.currentTimeMillis());
                    notification.setContentTitle("Pre-Op Notification");
                    notification.setContentText("You Selected Phone Calls");

                /*Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent); */

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID, notification.build());
                   // return false;
                } else if (i == 1) {
                    notify.setText("You Selected Text Messages");
                    notify.setTextColor(Color.WHITE);
                    notify.setText("You Selected Phone Calls");
                    notify.setTextColor(Color.WHITE);
                    notification.setSmallIcon(R.drawable.logo);
                    notification.setTicker("This is the Ticker");
                    notification.setWhen(System.currentTimeMillis());
                    notification.setContentTitle("Pre-Op Notification");
                    notification.setContentText("You Selected Text Messages");

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(view.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID, notification.build());
                    notification.setOngoing(true);
                } else if (i == 2) {
                    notify.setText("You Selected Emails");
                    notify.setTextColor(Color.WHITE);

                    notification.setSmallIcon(R.drawable.logo);
                    notification.setTicker("This is the Ticker");
                    notification.setWhen(System.currentTimeMillis());
                    notification.setContentTitle("Pre-Op Notification");
                    notification.setContentText("You Selected Emails");

                Intent intent5 = new Intent(view.getContext(), MainActivity.class);
                    intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
               // PendingIntent pendingIntent = PendingIntent.getActivity(view.getContext(), 0, intent5, 0);
                //notification.setContentIntent(pendingIntent);
                  //  intent5.notify(0, notification);
                    notification.setOngoing(true);

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID2, notification.build());
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        LinearLayout main_view = (LinearLayout) findViewById(R.id.activity_info);
        browser = (WebView) findViewById(R.id.webkit);
        infotxt = (TextView) findViewById(R.id.infotxt);
        tablerow1 = (TableLayout) findViewById(R.id.tablerow1);




        switch (item.getItemId()) {
            case R.id.meds:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent intent2 = new Intent(this, AddMed.class);
                startActivity(intent2);
                return true;
            case R.id.signout:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                auth.signOut();
                Intent intent3 = new Intent(this, Login.class);
                startActivity(intent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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







    @Override
    protected void onRestart() {
        super.onRestart();

    }
    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
