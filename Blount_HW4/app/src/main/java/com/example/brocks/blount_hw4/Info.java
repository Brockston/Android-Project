package com.example.brocks.blount_hw4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;



public class Info extends AppCompatActivity {
    WebView browser;
    TextView infotxt;
    TableLayout tablerow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        this.setTitle("Information");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

       public boolean onOptionsItemSelected(MenuItem item) {
        LinearLayout main_view = (LinearLayout) findViewById(R.id.activity_info);
        browser = (WebView) findViewById(R.id.webkit);
        infotxt = (TextView) findViewById(R.id.infotxt);
        tablerow1 = (TableLayout) findViewById(R.id.tablerow1);




        switch (item.getItemId()) {
            case R.id.menu_family:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                browser.loadUrl("https://www.bch.org/Our-Services/Surgery/Before-Your-Surgery.aspx");
                tablerow1.setVisibility(tablerow1.GONE);
                return true;
            case R.id.menu_prefer:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent intent = new Intent(this, Preferences.class);
                startActivity(intent);
                return true;

            case R.id.menu_recovery:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                browser.loadUrl("https://www.bch.org/Our-Services/Surgery/After-Your-Surgery.aspx");
                tablerow1.setVisibility(tablerow1.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*public void onCreateContextMenu(ContextMenu menu){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        ArrayAdapter<String> itemsArrayList = new ArrayAdapter<String>(getBaseContext(),   android.R.layout.simple_list_item_1);
        String[] itemNames = getResources().getStringArray(R.array.Font);


        for (int i = 0; i < 16; i++) {
            if (prefs.getBoolean("itemKey[i]", true)) {
                itemsArrayList.add(itemNames[i]);
            }
        }

        if (itemNames.equals("Default")) {
            infotxt.setTypeface(Typeface.DEFAULT);

        }
        else if (itemNames.equals("Bold")) {
            infotxt.setTypeface(Typeface.SERIF);
        }

    } */


}
