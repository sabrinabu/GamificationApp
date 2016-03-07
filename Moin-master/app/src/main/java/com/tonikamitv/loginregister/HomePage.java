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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 23-01-2016.
 */
public class HomePage extends ActionBarActivity implements Serializable {

    // String[] arrayList = {"hello1", "hell02", "hello3", "hello4", "hello5", "hello6", "hello7"};
    List<String> listSource=new ArrayList<String>();
    ListView listView;
    ArrayAdapter<String> adapter;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        userLocalStore = new UserLocalStore(this);

        final List<EventInfo> eventInfos = DbEventRetrieve.eventInfos;
        for (int i = 0; i < eventInfos.size(); i++) {
            listSource.add(eventInfos.get(i).getName()+"\n"+eventInfos.get(i).getDateTime() + "\n"+ eventInfos.get(i).getLocation());
        }

        listView = (ListView) findViewById(R.id.eventListView);
        adapter = new ArrayAdapter<String>(this, R.layout.single_row_listview, R.id.single_row_textView, listSource);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent page=new Intent(HomePage.this, DetailedEventPage.class);

                page.putExtra("location", (Serializable) eventInfos.get(position).getLocation());
                page.putExtra("name", (Serializable) eventInfos.get(position).getName());
                page.putExtra("time", (Serializable) eventInfos.get(position).getDateTime());
                page.putExtra("creator", (Serializable) eventInfos.get(position).getCreatorName());
                page.putExtra("participant", (Serializable) eventInfos.get(position).getParticipant());
                page.putExtra("category", (Serializable) eventInfos.get(position).getCategory());
                page.putExtra("description", (Serializable) eventInfos.get(position).getDescription());

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
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Intent loginIntent = new Intent(this, Login.class);
                startActivity(loginIntent);
                return true;
            case R.id.lang_learning:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                startActivity(new Intent(this, LangLearningList.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}





