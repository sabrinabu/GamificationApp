package com.tonikamitv.loginregister.pushnotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by sabrina on 2/24/2016.
 */
public class DbPushRetrive extends AsyncTask<String,Void,String> {

    static List<MyNotification> myNotifications = new ArrayList<MyNotification>();
    Context ctx;
    DbPushRetrive(Context ctx)
    {
        this.ctx = ctx;
    }
    String currentUserName;

    @Override
    protected String doInBackground(String... params) {
        //collect current user later
        String username=params[0];
        currentUserName = username;
        String retrieve_url = "http://nishadesai.16mb.com/pushRetrieve.php";
        String result="";
        InputStream inputStream = null;
        try {
            URL url = new URL(retrieve_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data = URLEncoder.encode("username", "UTF-8")+"="+ URLEncoder.encode(username, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();

            bufferedWriter.close();
            outputStream.close();
            inputStream = httpURLConnection.getInputStream();
        }catch (Exception e){
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        //convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            inputStream.close();
            result = stringBuilder.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        //parse json data
        try {
            JSONArray jArray = new JSONArray(result);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);
                myNotifications.add(new MyNotification(json.getString("username"), json.getString("event"), json.getString("message"), json.getString("time")));
            }
        } catch (Exception e) {
            Log.e("log_tag", "Error Parsing Data " + e.toString());
        }

        return "Data retrieve success";
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

        //Notification call here 2016-03-28 01:00:00
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date eventTime = new java.util.Date();
        int durationInMinute = 1;
        try {
            eventTime = sdf.parse(myNotifications.get(0).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar calStartTime = Calendar.getInstance();
        calStartTime.setTime(eventTime);
        calStartTime.set(Calendar.MINUTE, calStartTime.get(Calendar.MINUTE) + durationInMinute);

        java.util.Date startTime =calStartTime.getTime();
        long time = startTime.getTime();

        Intent alertIntent = new Intent(ctx, AlertReceiver.class);
        alertIntent.putExtra("username", myNotifications.get(0).getUserName());
        alertIntent.putExtra("title", "Knock Knock!");
        alertIntent.putExtra("eventName", myNotifications.get(0).getEvent());
        alertIntent.putExtra("message", myNotifications.get(0).getMessage());

        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(ctx, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        //NotificationManager myNotificationManager = new NotificationManager(ctx);
        //myNotificationManager.NotificationTimer(startTime, myNotifications.get(0).getEvent(), currentUserName);


        myNotifications.clear();
    }

}
