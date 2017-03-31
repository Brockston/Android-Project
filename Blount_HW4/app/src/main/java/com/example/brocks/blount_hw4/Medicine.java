package com.example.brocks.blount_hw4;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Medicine extends ListActivity {
    String string = "";
    String[] list;
    ListView listView;
    ArrayAdapter<String> ada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = (ListView) findViewById(R.id.list);
        setContentView(R.layout.activity_meds);
        list = getResources().getStringArray(R.array.Meds);
        ada = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);
        getListView().setAdapter(ada);
        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        string = list[(int) adapterContextMenuInfo.id];

        switch (item.getItemId()) {
            case R.id.edit:
                //ada.remove(adapterContextMenuInfo.position);
                Toast.makeText(Medicine.this,
                        "Edit this " + string,
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}