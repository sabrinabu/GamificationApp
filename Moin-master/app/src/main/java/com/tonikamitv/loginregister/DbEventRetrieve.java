package com.tonikamitv.loginregister;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabrina on 2/1/2016.
 */
public class DbEventRetrieve extends AsyncTask<String,Void,String> {

        static List<EventInfo> eventInfos = new ArrayList<EventInfo>();
        Context ctx;
        DbEventRetrieve(Context ctx)
        {
                this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
                eventInfos.clear();
                String retrieve_url = "http://nishadesai.16mb.com/reteive.php";
                String result = "";
                InputStream inputStream = null;
                try {
                        URL url = new URL(retrieve_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                        String data = URLEncoder.encode("u640683613_nisha", "UTF-8")+"="+ URLEncoder.encode("u640683613_nisha", "UTF-8")+"&"+
                                URLEncoder.encode("sunny6121986", "UTF-8")+"="+ URLEncoder.encode("sunny6121986", "UTF-8");
                        bufferedWriter.write(data);
                        bufferedWriter.flush();

                        bufferedWriter.close();
                        outputStream.close();
                        inputStream = httpURLConnection.getInputStream();
                } catch (Exception e) {
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
                                eventInfos.add(new EventInfo(json.getString("name"), json.getString("category"),json.getString("location"), json.getString("description"), json.getString("datetime"), json.getString("username"), json.getString("participant")));
                        }
                } catch (Exception e) {
                        Log.e("log_tag", "Error Parsing Data " + e.toString());
                }

                return "Event Retrieved";
        }

        @Override
        protected void onPostExecute(String result) {
                //Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }

        public List<EventInfo> EventInfosList() {
                return eventInfos;
        }
}
