package com.example.sabrina.createevent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by sabrina on 1/30/2016.
 */
public class CreateEvent extends Activity {

    String eventName, eventDate, evetTime, eventLocation, eventDescription;
    EditText ET_eventName, Et_eventDate, ET_evetTime, ET_eventLocation, ET_eventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
        ET_eventName = (EditText)findViewById(R.id.editTextEventName);
        Et_eventDate = (EditText)findViewById(R.id.editTextDate);
        ET_eventLocation = (EditText)findViewById(R.id.editTextLocation);
        ET_eventDescription = (EditText)findViewById(R.id.editTextDescription);
    }

    public void createEventMethod(View view){
        eventName = ET_eventName.getText().toString();
        eventDate = Et_eventDate.getText().toString();
        eventDate = "2016-01-14 00:00:00";
        String category = "Music";
        eventLocation = ET_eventLocation.getText().toString();
        eventDescription = ET_eventDescription.getText().toString();

        String method = "CreateEvent";
        DbEventInsertion databaseTc = new DbEventInsertion(this);
        databaseTc.execute(method, eventName, category, eventLocation, eventDescription, eventDate);
        finish();
    }
}
