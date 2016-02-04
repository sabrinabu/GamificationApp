package com.example.sabrina.createevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabrina on 2/2/2016.
 */
public class ShowEvent extends Activity {
    String[] arrayList = {"hello1", "hell02", "hello3", "hello4", "hello5", "hello6", "hello7"};
    List<String> listSource=new ArrayList<String>();
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_event);

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
            }
        });
        listView.invalidateViews();
    }

    public void callCreateEventMethod(View view) {
        Intent page = new Intent(this, CreateEvent.class);
        startActivity(page);
    }

    public void showEventList(View view){}
}
