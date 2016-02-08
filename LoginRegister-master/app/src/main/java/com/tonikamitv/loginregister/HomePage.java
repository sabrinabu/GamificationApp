package com.tonikamitv.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 23-01-2016.
 */
public class HomePage extends ActionBarActivity {

    String[] arrayList = {"hello1", "hell02", "hello3", "hello4", "hello5", "hello6", "hello7"};
    List<String> listSource=new ArrayList<String>();
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        List<EventInfo> eventInfos = DbEventRetrieve.eventInfos;
        for (int i = 0; i < eventInfos.size(); i++) {
            listSource.add(eventInfos.get(i).getName()+ " at " + eventInfos.get(i).getLocation() + " on " + eventInfos.get(i).getDateTime());
        }

        listView = (ListView) findViewById(R.id.eventListView);
        adapter = new ArrayAdapter<String>(this, R.layout.single_row_listview, R.id.single_row_textView, listSource);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_LONG).show();

                Intent page=new Intent(HomePage.this, DetailedEventPage.class);
                startActivity(page);
            }
        });
        listView.invalidateViews();
    }

    public void callCreateEventMethod(View view) {
        Intent page = new Intent(this, CreateEvent.class);
        startActivity(page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_page, menu);
        return true;
    }


    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.user_profile:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                startActivity(new Intent(this, My_Profile.class));
                return true;

            case R.id.logout:
                Toast.makeText(HomePage.this, "Save is Selected", Toast.LENGTH_SHORT).show();
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }

}





