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

import com.google.firebase.auth.FirebaseAuth;


public class Info extends AppCompatActivity {
    WebView browser;
    static TextView infotxt;
    static TableLayout tablerow1;
    static TextView txt1;
    static TextView txt2;
    static TextView txt3;
    static TextView txt4;
    static TextView txt5;


    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        this.setTitle("Information");
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

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
           txt1 = (TextView) findViewById(R.id.txt1);
           txt2 = (TextView) findViewById(R.id.txt2);
           txt3 = (TextView) findViewById(R.id.txt3);
           txt4 = (TextView) findViewById(R.id.txt4);
           txt5 = (TextView) findViewById(R.id.txt5);




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
            case R.id.signout:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                auth.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void signOut() {
        auth.signOut();
        Intent intent3 = new Intent(this, Login.class);
        startActivity(intent3);

    }

}
