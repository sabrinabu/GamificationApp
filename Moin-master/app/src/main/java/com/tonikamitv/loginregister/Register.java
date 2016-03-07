package com.tonikamitv.loginregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


public class Register extends Activity implements View.OnClickListener{


    public static final String UPLOAD_URL = "http://nishadesai.16mb.com/image.php";
    public static final String UPLOAD_KEY = "image";
    EditText etName, etAge, etUsername, etPassword;
    RadioGroup rg;
    Button bRegister;
    private String selectedImagePath;
    Button bTakePic;
    //TextView tvHasCamera, tvHasCameraApp;
    ImageView imgTakePic;
    byte[] byteArray;
    String encodedImage;
    Bitmap originBitmap;
    Bitmap bitMap;
    static int SELECT_FILE = 2;
    int pos;
    String cast;
    public static final String ARG_FROM_MAIN = "arg";
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);

        bTakePic = (Button) findViewById(R.id.bTakePic);
        imgTakePic = (ImageView) findViewById(R.id.imgPic);

        bRegister.setOnClickListener(this);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                pos = rg.indexOfChild(findViewById(checkedId));


                switch (pos) {
                    case 0:
                        Toast.makeText(getBaseContext(), "You have Clicked RadioButton 1",
                                Toast.LENGTH_SHORT).show();
                        cast = "female";
                        break;
                    case 1:
                        Toast.makeText(getBaseContext(), "You have Clicked RadioButton 2",
                                Toast.LENGTH_SHORT).show();
                        cast = "male";
                        break;


                    default:
                        //The default selection is RadioButton 1
                        Toast.makeText(getBaseContext(), "You have Clicked RadioButton 1",
                                Toast.LENGTH_SHORT).show();
                        cast = "Other";
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                uploadImage();
                Intent nextIntent = new Intent(Register.this, MainScreen.class);
               nextIntent.putExtra("arg", getText());
                nextIntent.putExtra("arg1", userName());
                nextIntent.putExtra("arg2", password());
                nextIntent.putExtra("arg3", age());
                nextIntent.putExtra("a1", cast());
                nextIntent.putExtra("b1", image());
                startActivity(nextIntent);
                break;


        }
    }




    public void loadPic(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_FILE && resultCode == RESULT_OK && data != null) {
            originBitmap = null;
            selectedImage = data.getData();

            try {
                originBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage );
                imgTakePic.setImageBitmap(originBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }



    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }



    public String getText(){
        String name = etName.getText().toString();
        return name;
    }
    public String userName(){
        String username = etUsername.getText().toString();
        return username;
    }
    public String password(){
        String password = etPassword.getText().toString();
        return password;
    }
    public int age(){
        int age = Integer.parseInt(etAge.getText().toString());
        return age;
    }
    public String cast(){
        return cast;
    }
    public String image(){
        String encodedImag = "nisha";
        return encodedImag;
    }

    /*private void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(Register.this, ProfilePic.class);
                startActivity(loginIntent);
            }
        });
    }*/

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            DbImageInsert rh = new DbImageInsert();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);
                HashMap<String,String> data = new HashMap<>();
                String username = etUsername.getText().toString();
                data.put(UPLOAD_KEY, uploadImage);
                data.put("username", username);


                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(originBitmap);
    }




}
