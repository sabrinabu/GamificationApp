package com.tonikamitv.loginregister;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Events extends ActionBarActivity  {

    ListView listView;
    ArrayAdapter<String> adapter;


    /*private static final String LIST1_TAB_TAG = "List1";
    private static final String LIST2_TAB_TAG = "List2";

    // The two views in our tabbed example
    private ListView listView;

    private TabHost tabHost;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        listView = (ListView) findViewById(R.id.eventListView);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent page=new Intent(Events.this, DetailedEventPage.class);

               /* page.putExtra("location", (Serializable) eventInfos.get(position).getLocation());
                page.putExtra("name", (Serializable) eventInfos.get(position).getName());
                page.putExtra("time", (Serializable) eventInfos.get(position).getDateTime());
                page.putExtra("creator", (Serializable) eventInfos.get(position).getCreatorName());
                page.putExtra("participant", (Serializable) eventInfos.get(position).getParticipant());
                startActivity(page);*/
            }
        });
        listView.invalidateViews();




        // create some dummy strings to add to the list
       /* List<String> list1Strings = new ArrayList<String>();
        list1Strings.add("Item 1");
        list1Strings.add("Item 2");
        list1Strings.add("Item 3");
        list1Strings.add("Item 4");
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.activity_list_item, list1Strings));


        // add views to tab host
        tabHost.addTab(tabHost.newTabSpec(LIST1_TAB_TAG).setIndicator(LIST1_TAB_TAG).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String arg0) {
                return listView;
            }
        }));*/




    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_events, menu);
        return true;
    }*/

}
