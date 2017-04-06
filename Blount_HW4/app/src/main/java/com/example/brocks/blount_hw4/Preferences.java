package com.example.brocks.blount_hw4;

/**
 * Created by Brocks on 3/28/2017.
 */

import android.app.NotificationManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import android.support.annotation.LayoutRes;
//import android.support.annotation.Nullable;


public class Preferences extends PreferenceActivity {
    private AppCompatDelegate mDelegate;

    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;

    private Preference mLoginPreference;

    Preference myPref = (Preference) findPreference("notifications_new_message_notify");


    String string = "";
    String[] list;
    ListView listView;
    ArrayAdapter<String> ada;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Preferences");
        addPreferencesFromResource(R.xml.prefer);


        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        mLoginPreference = getPreferenceManager().findPreference(
                getString(R.string.notifications_new_message_notify));

        Preference myPref = findPreference("notifications_new_message_notify");
        final Preference Mylist = (Preference) findPreference("Fonts");
        //Preference fonts = findPreference("Fonts");
        //registerForContextMenu(fonts);

        listView = (ListView) findViewById(R.id.list);

        list = getResources().getStringArray(R.array.FontStyle);
        ada = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);
        getListView().setAdapter(ada);
        registerForContextMenu(getListView());



        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                //open browser or intent here

                notification.setSmallIcon(R.drawable.logo);
                notification.setTicker("This is the Ticker");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("Pre-Op Notification");
                notification.setContentText("You will be notified for your upcoming surgery");

                /*Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent); */

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID, notification.build());
                return false;
            }
        });


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       // AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        string = list[(int) adapterContextMenuInfo.id];

        switch (item.getItemId()) {
            case R.id.bold:
                if (item.equals("bold"))
                    item.getItemId();
                else
                    item.setChecked(true);
                //ada.remove(adapterContextMenuInfo.position);
                Info.infotxt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Info.txt1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Info.txt2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Info.txt3.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Info.txt4.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Info.txt5.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                MainActivity.date.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                MainActivity.selection.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                MainActivity.text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                MainActivity.notify.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                MainActivity.dateAndTimeLabel.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                return true;
            case R.id.default1:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                //ada.remove(adapterContextMenuInfo.position);
                Info.infotxt.setTypeface(Typeface.DEFAULT);
                Info.txt1.setTypeface(Typeface.DEFAULT);
                Info.txt2.setTypeface(Typeface.DEFAULT);
                Info.txt3.setTypeface(Typeface.DEFAULT);
                Info.txt4.setTypeface(Typeface.DEFAULT);
                Info.txt5.setTypeface(Typeface.DEFAULT);
                MainActivity.date.setTypeface(Typeface.DEFAULT);
                MainActivity.selection.setTypeface(Typeface.DEFAULT);
                MainActivity.text.setTypeface(Typeface.DEFAULT);
                MainActivity.notify.setTypeface(Typeface.DEFAULT);
                MainActivity.dateAndTimeLabel.setTypeface(Typeface.DEFAULT);
                return true;
            case R.id.monospace:
                if (item.equals("monospace"))
                    item.getItemId();
                else
                    item.setChecked(true);
                //ada.remove(adapterContextMenuInfo.position);
                Info.infotxt.setTypeface(Typeface.MONOSPACE);
                Info.txt1.setTypeface(Typeface.MONOSPACE);
                Info.txt2.setTypeface(Typeface.MONOSPACE);
                Info.txt3.setTypeface(Typeface.MONOSPACE);
                Info.txt4.setTypeface(Typeface.MONOSPACE);
                Info.txt5.setTypeface(Typeface.MONOSPACE);
                MainActivity.date.setTypeface(Typeface.MONOSPACE);
                MainActivity.selection.setTypeface(Typeface.MONOSPACE);
                MainActivity.text.setTypeface(Typeface.MONOSPACE);
                MainActivity.notify.setTypeface(Typeface.MONOSPACE);
                MainActivity.dateAndTimeLabel.setTypeface(Typeface.MONOSPACE);
                return true;
            case R.id.SanSerif:
                if (item.equals("SanSerif"))
                    item.getItemId();
                else
                    item.setChecked(true);
                //ada.remove(adapterContextMenuInfo.position);
                Info.infotxt.setTypeface(Typeface.SANS_SERIF);
                Info.txt1.setTypeface(Typeface.SANS_SERIF);
                Info.txt2.setTypeface(Typeface.SANS_SERIF);
                Info.txt3.setTypeface(Typeface.SANS_SERIF);
                Info.txt4.setTypeface(Typeface.SANS_SERIF);
                Info.txt5.setTypeface(Typeface.SANS_SERIF);
                MainActivity.date.setTypeface(Typeface.SANS_SERIF);
                MainActivity.selection.setTypeface(Typeface.SANS_SERIF);
                MainActivity.text.setTypeface(Typeface.SANS_SERIF);
                MainActivity.notify.setTypeface(Typeface.SANS_SERIF);
                MainActivity.dateAndTimeLabel.setTypeface(Typeface.SANS_SERIF);
                return true;
            case R.id.Serif:
                if (item.equals("Serif"))
                    item.getItemId();
                else
                    item.setChecked(true);
                //ada.remove(adapterContextMenuInfo.position);
                Info.infotxt.setTypeface(Typeface.SERIF);
                Info.txt1.setTypeface(Typeface.SERIF);
                Info.txt2.setTypeface(Typeface.SERIF);
                Info.txt3.setTypeface(Typeface.SERIF);
                Info.txt4.setTypeface(Typeface.SERIF);
                Info.txt5.setTypeface(Typeface.SERIF);
                MainActivity.date.setTypeface(Typeface.SERIF);
                MainActivity.selection.setTypeface(Typeface.SERIF);
                MainActivity.text.setTypeface(Typeface.SERIF);
                MainActivity.notify.setTypeface(Typeface.SERIF);
                MainActivity.dateAndTimeLabel.setTypeface(Typeface.SERIF);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }
    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }
    @Override
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }
    @Override
    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().setContentView(view, params);
    }
    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().addContentView(view, params);
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }
    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getDelegate().setTitle(title);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getDelegate().onConfigurationChanged(newConfig);
    }
    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }
    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }
    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }

};



