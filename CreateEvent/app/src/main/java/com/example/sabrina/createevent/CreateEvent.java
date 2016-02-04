package com.example.sabrina.createevent;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

/**
 * Created by sabrina on 1/30/2016.
 */
public class CreateEvent extends Activity {

    String eventName, eventDate, evetTime, eventLocation, eventDescription;
    EditText ET_eventName, Et_eventDate, ET_evetTime, ET_eventLocation, ET_eventDescription;
    static final int DIALOG_TIME = 0;
    static final int DIALOG_DATE = 1;
    int hour, min;
    int yearx, monthx, dayx;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
        ET_eventName = (EditText) findViewById(R.id.editTextEventName);
        Et_eventDate = (EditText) findViewById(R.id.editTextDate);
        ET_eventLocation = (EditText) findViewById(R.id.editTextLocation);
        ET_eventDescription = (EditText) findViewById(R.id.editTextDescription);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Calendar cal = Calendar.getInstance();
        yearx=cal.get(Calendar.YEAR);
        monthx=cal.get(Calendar.MONTH);
        dayx=cal.get(Calendar.DATE);
        hour=cal.get(Calendar.HOUR_OF_DAY);
        min=cal.get(Calendar.MINUTE);
        showDatePickerDialog();
        showTimePickerDialog();
    }

    public void createEventMethod(View view) {
        eventName = ET_eventName.getText().toString();
        eventDate = yearx+"-"+monthx+"-"+dayx+" "+hour+":"+min+":"+"00";
        //eventDate = "2016-01-14 00:00:00";
        String category = "Music";
        eventLocation = ET_eventLocation.getText().toString();
        eventDescription = ET_eventDescription.getText().toString();

        String method = "CreateEvent";
        DbEventInsertion databaseTc = new DbEventInsertion(this);
        databaseTc.execute(method, eventName, category, eventLocation, eventDescription, eventDate);
        finish();

        Intent page=new Intent(this, MainActivity.class);
        startActivity(page);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            return new TimePickerDialog(this, kTimePickerListener, hour, min, false);
        }
        if(id==DIALOG_DATE){
            return new DatePickerDialog(this, kDatePickerListener, yearx, monthx, dayx);
        }

        return null;
    }

    protected DatePickerDialog.OnDateSetListener kDatePickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    yearx=year;
                    monthx=monthOfYear+1;
                    dayx=dayOfMonth;
                    String date = yearx+":"+monthx+":"+dayx;
                    if(monthx<10)
                        date=yearx+":0"+monthx+":"+dayx;
                    if(dayx<10)
                        date=yearx+":"+monthx+":0"+dayx;
                    if(monthx<10&&dayx<10)
                        date=yearx+":0"+monthx+":0"+dayx;
                    Et_eventDate.setText(date);
                }
            };

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour = hourOfDay;
                    min = minute;
                    String time = hour+":"+min;
                    if(min<10)
                        time = hour+":0"+min;
                    if(hour<10)
                        time = "0"+hour+":"+min;
                    if(min<10&&hour<10)
                        time = "0"+hour+":0"+min;

                    ET_evetTime.setText(time);
                }
            };

    public void showTimePickerDialog() {
        ET_evetTime = (EditText) findViewById(R.id.editTextTime);
        ET_evetTime.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_TIME);
                    }
                }
        );
    }

    public void showDatePickerDialog(){
        Et_eventDate = (EditText)findViewById(R.id.editTextDate);
        Et_eventDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_DATE);
                    }
                }
        );
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreateEvent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sabrina.createevent/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreateEvent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sabrina.createevent/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
