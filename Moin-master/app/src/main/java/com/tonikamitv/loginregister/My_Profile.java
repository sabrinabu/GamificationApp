package com.tonikamitv.loginregister;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Lenovo on 04-02-2016.
 */
public class My_Profile extends Activity implements View.OnClickListener{

    UserLocalStore userLocalStore;
    TextView langwant, langskill, interests;
    TextView back;
    ImageButton iEdit;
    ImageButton missionBtn;
    Login ln;

    private ImageView imageView;

    private DbImageInsert dbimageinsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

        langskill = (TextView) findViewById(R.id.langskill);
        langwant = (TextView) findViewById(R.id.langwant);
        interests = (TextView) findViewById(R.id.interests);
        back = (TextView) findViewById(R.id.back);
        imageView = (ImageView) findViewById(R.id.imgPic);
        dbimageinsert = new DbImageInsert();

        back.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                Intent loginIntent = new Intent(this, HomePage.class);
                startActivity(loginIntent);
                break;
            case R.id.iEdit:
                Intent Intent = new Intent(this, Edit_Profile.class);
                startActivity(Intent);
                break;
            case R.id.missionBtn:
                Intent missionsIntent = new Intent(this, Missions.class);
                startActivity(missionsIntent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true) {
            displayUserDetails();
            getImage();
        }
    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        User users = userLocalStore.getLoggedInUser();
        langskill.setText(users.knowlanguage);
        langwant.setText(users.wantlanguage);
        interests.setText(users.interests);
    }

    private void getImage() {
        User users = userLocalStore.getLoggedInUser();
        String id = users.username.toString().trim();
        class GetImage extends AsyncTask<String,Void,Bitmap> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(My_Profile.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(Bitmap b) {
                super.onPostExecute(b);
                loading.dismiss();
                imageView.setImageBitmap(b);
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                String id = params[0];
                String add = "http://nishadesai.16mb.com/imageuser.php?id="+id;
                URL url = null;
                Bitmap image = null;
                try {
                    url = new URL(add);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return image;
            }
        }

        GetImage gi = new GetImage();
        gi.execute(id);
    }

}
