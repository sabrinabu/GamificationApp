package com.tonikamitv.loginregister.pushnotification;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by sabrina on 2/24/2016.
 */
public class DbPushSave extends AsyncTask<String, Void, String> {

    Context ctx;
    String currentUserName;

    public DbPushSave(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String insert_url = "http://nishadesai.16mb.com/insertpush.php";

        String username = params[0];
        currentUserName=username;
        String event = params[1];
        String message = params[2];
        String time = params[3];

        try {
        URL url = new URL(insert_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        OutputStream OS = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
        String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
        URLEncoder.encode("event", "UTF-8") + "=" + URLEncoder.encode(event, "UTF-8") + "&" +
        URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8")+ "&" +
        URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8");

        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.close();
        OS.close();
        InputStream IS = httpURLConnection.getInputStream();
        IS.close();
        //httpURLConnection.connect();
        httpURLConnection.disconnect();

        //return "Create Event Success...";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Data insertion success";
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

        //Retrieve call here
        DbPushRetrive dbPushRetrive = new DbPushRetrive(ctx);
        dbPushRetrive.execute(currentUserName);
    }

}

