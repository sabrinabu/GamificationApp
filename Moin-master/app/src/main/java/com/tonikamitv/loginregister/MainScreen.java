package com.tonikamitv.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainScreen extends Activity {
    private Item itemHandler;
    private ItemSelectedAdapter adapter;
    private ArrayList<Item> itemList;
    private ListView listView;
    private Button button;
    UserLocalStore userLocalStore;
    EditText etUsername;
    Register r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_task);
        listView = (ListView) findViewById(R.id.listview);
        button = (Button) findViewById(R.id.printbtn);
        setWidget();
    }

    public void setWidget() {
        itemList = new ArrayList<Item>();
        itemList.add(new Item("German", false));
        itemList.add(new Item("English", false));
        itemList.add(new Item("French", false));
        itemList.add(new Item("Arabic", false));
        itemList.add(new Item("Spanish", false));
        itemList.add(new Item("Chinese", false));
        itemList.add(new Item("Hindi", false));
        itemList.add(new Item("Urdu", false));
        itemList.add(new Item("Turkish", false));
        itemList.add(new Item("Russian", false));

        adapter = new ItemSelectedAdapter(MainScreen.this, itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapter.setCheckBox(position);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String language = "";
                for (Item hold : adapter.getAllData()) {
                    if (hold.isCheckbox()) {
                        language += "," + hold.getName();
                        // countries +=hold.getName();
                    }
                }


                //Toast.makeText(MainScreen.this, "Names: " + language, Toast.LENGTH_SHORT).show();
               String name = getIntent().getExtras().getString("arg");
                String username = getIntent().getExtras().getString("arg1");
                String password = getIntent().getExtras().getString("arg2");
                int age = getIntent().getExtras().getInt("arg3");
                String cast = getIntent().getExtras().getString("a1");
                String image = getIntent().getExtras().getString("b1");

                //User user = new User(name, age, username, password, language);
                //registerUser(user);
                Intent nextIntent = new Intent(MainScreen.this, WantLanguage.class);
                nextIntent.putExtra("arg4", name);
                nextIntent.putExtra("arg5", username);
                nextIntent.putExtra("arg6", password);
                nextIntent.putExtra("arg7", age);
                nextIntent.putExtra("arg8", language);
                nextIntent.putExtra("a2", cast);
                nextIntent.putExtra("b2", image);
                startActivity(nextIntent);

            }
        });

    }

  /*  private void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(MainScreen.this, ProfilePic.class);
                startActivity(loginIntent);
            }
        });
    }*/
}
    /*private void insertToDatabase(String language){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... param) {
                String paramLanguage = param[0];


                String language = "";
                for (Item hold : adapter.getAllData()) {
                    if (hold.isCheckbox()) {
                        language += hold.getName()+",";
                        // countries +=hold.getName();
                    }
                }



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("language", language));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://nishadesai.16mb.com/Language.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText(" ");
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(language);
    }
}*/
















