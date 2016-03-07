package com.tonikamitv.loginregister;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.tonikamitv.loginregister.pushnotification.DbPushSave;


import java.util.Calendar;

/**
 * Created by sabrina on 1/30/2016.
 */
public class CreateEvent extends Activity  {

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
    private UserLocalStore userLocalStore;
    String category;

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
        userLocalStore = new UserLocalStore(this);



        final ImageButton btn1 = (ImageButton) findViewById(R.id.category1);
        final ImageButton btn2 = (ImageButton) findViewById(R.id.category2);
        final ImageButton btn3 = (ImageButton) findViewById(R.id.category3);
        final ImageButton btn4 = (ImageButton) findViewById(R.id.category4);
        final ImageButton btn5 = (ImageButton) findViewById(R.id.category5);
        final ImageButton btn6 = (ImageButton) findViewById(R.id.category6);
        final ImageButton btn7 = (ImageButton) findViewById(R.id.category7);
        final ImageButton btn8 = (ImageButton) findViewById(R.id.category8);
        final ImageButton btn9 = (ImageButton) findViewById(R.id.category9);
        final ImageButton btn10 = (ImageButton) findViewById(R.id.category10);
        final ImageButton btn11 = (ImageButton) findViewById(R.id.category11);
        final ImageButton btn12 = (ImageButton) findViewById(R.id.category12);

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (v.equals(btn1)) {
                    btn1.setSelected(true);
                    category = "Cinema";
                }
                else
                    btn1.setSelected(false);
                if (v.equals(btn2)) {
                    btn2.setSelected(true);
                    category = "Culture";
                }
                else
                    btn2.setSelected(false);
                if (v.equals(btn3)) {
                    btn3.setSelected(true);
                    category = "Education";
                }
                else
                    btn3.setSelected(false);
                if (v.equals(btn4)) {
                    btn4.setSelected(true);
                    category = "Excursion";
                }
                else
                    btn4.setSelected(false);
                if (v.equals(btn5)) {
                    btn5.setSelected(true);
                    category = "Food";
                }
                else
                    btn5.setSelected(false);
                if (v.equals(btn6)) {
                    btn6.setSelected(true);
                    category = "Games";
                }
                else
                    btn6.setSelected(false);
                if (v.equals(btn7)) {
                    btn7.setSelected(true);
                    category = "Help";
                }
                else
                    btn7.setSelected(false);
                if (v.equals(btn8)) {
                    btn8.setSelected(true);
                    category = "LanguageLearning";
                }
                else
                    btn8.setSelected(false);
                if (v.equals(btn9)) {
                    btn9.setSelected(true);
                    category = "Music";
                }
                else
                    btn9.setSelected(false);
                if (v.equals(btn10)) {
                    btn10.setSelected(true);
                }
                else
                    btn10.setSelected(false);
                if (v.equals(btn11)) {
                    btn11.setSelected(true);
                    category = "Sports";
                }
                else
                    btn11.setSelected(false);
                if (v.equals(btn12)) {
                    btn12.setSelected(true);
                    category = "Time Out";
                }
                else
                    btn12.setSelected(false);
            }
        };

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btn10.setOnClickListener(listener);
        btn11.setOnClickListener(listener);
        btn12.setOnClickListener(listener);


    }

    public void createEventMethod(View view) throws InterruptedException {
        eventName = ET_eventName.getText().toString();
        eventDate = yearx+"-"+monthx+"-"+dayx+" "+hour+":"+min+":"+"00";
        //String category = "Music";
        eventLocation = ET_eventLocation.getText().toString();
        eventDescription = ET_eventDescription.getText().toString();
        User loggedInuser = userLocalStore.getLoggedInUser();

        String method = "CreateEvent";
        DbEventInsertion databaseTc = new DbEventInsertion(this);
        databaseTc.execute(method, eventName, category, eventLocation, eventDescription, eventDate, loggedInuser.username);
        finish();

        DbPreRetrieve preRetrieve = new DbPreRetrieve(this);
        preRetrieve.RetrieveDataEarlier();

        Thread.sleep(1000);

        DbPushSave database = new DbPushSave(this);
        database.execute(loggedInuser.username, eventName, eventDescription, eventDate);

        Intent page=new Intent(this, HomePage.class);
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

   /* public void onClick(View v) {
        if(v == ButtonName) {
            ButtonName.setImageResource(R.drawable.ImageName);
        }


    }*/



}
