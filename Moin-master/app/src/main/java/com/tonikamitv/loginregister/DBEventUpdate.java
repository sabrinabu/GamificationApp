package com.tonikamitv.loginregister;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

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
 * Created by susana on 20/02/16.
 */

public class DBEventUpdate extends AsyncTask<String,Void,String> {


    Context ctx;
    DBEventUpdate(Context ctx)
    {
        this.ctx = ctx;
    }

    public DBEventUpdate(View.OnClickListener onClickListener) {
    }

    @Override
    protected String doInBackground(String... params) {
        String insert_url = "http://nishadesai.16mb.com/updateParticipants.php";
        String method=params[0];
        String participant = params[1];
        String eventCreator = params[2];
        String eventName = params[3];

        try {
            URL url = new URL(insert_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(eventName, "UTF-8") + "&" +
                    URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(eventCreator, "UTF-8") + "&" +
                    URLEncoder.encode("participant", "UTF-8") + "=" + URLEncoder.encode(participant, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = httpURLConnection.getInputStream();
            IS.close();
            httpURLConnection.disconnect();

            return "Create Event Success";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        // Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }
}