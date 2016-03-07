package com.tonikamitv.loginregister;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;

/**
 * Created by Lenovo on 09-02-2016.
 */
public class Edit_Profile extends Activity implements View.OnClickListener{

        UserLocalStore userLocalStore;
        TextView langwant, langskill, interests;
        TextView back;
        TextView save;
        ImageButton iEdit;
        private ImageView imageView;

        private DbImageInsert dbimageinsert;

final CharSequence myList[] = { "English", "German", "Hindi", "Turkish",  "Russian", "Urdu", "Arabic", "Franch", "Chineses", "Spanish"};
        final CharSequence myActivityList[] = { "sports", "Cinema", "Food", "Music",  "Culture", "Games", "Education", "Time Out", "Excursion", "Help", "Language Practice", "Not Specifieed"};

        User user;
        ArrayList<Integer> selList=new ArrayList();
        boolean bl[] = new boolean[myList.length];
        boolean ml[] = new boolean[myActivityList.length];
        String knowlanguage ="";
        String wantlanguage ="";
        String interestActivity = "";

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        langskill = (TextView) findViewById(R.id.langskill);
        langwant = (TextView) findViewById(R.id.langwant);
        interests = (TextView) findViewById(R.id.interests);
        back = (TextView) findViewById(R.id.back);
        save = (TextView) findViewById(R.id.save);
        imageView = (ImageView) findViewById(R.id.imgPic);

        dbimageinsert = new DbImageInsert();


        back.setOnClickListener(this);
        save.setOnClickListener(this);
        langskill.setOnClickListener(this);
        langwant.setOnClickListener(this);
        interests.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);




final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Language skills");
        ad.setMultiChoiceItems(myList, bl, new DialogInterface.OnMultiChoiceClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
                        // TODO Auto-generated method stub

                        if (arg2) {
                                // If user select a item then add it in selected items
                                selList.add(arg1);
                        } else if (selList.contains(arg1)) {
                                // if the item is already selected then remove it
                                selList.remove(Integer.valueOf(arg1));
                        }
                }
        });
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        knowlanguage = "";
                        for (int i = 0; i < selList.size(); i++) {

                                knowlanguage = knowlanguage + myList[selList.get(i)] + "  ";
                        }
                        Toast.makeText(getApplicationContext(),
                                "Total" + knowlanguage, Toast.LENGTH_LONG)
                                .show();

                        user = userLocalStore.getLoggedInUser();
                        User users = new User(user.username, user.password, knowlanguage, user.wantlanguage, user.interests);
                        //users = new User(user.name, user.age, user.username, user.password, knowlanguage, user.wantlanguage, user.interests);
                        updateUserknowlang(users);




                }
        });
        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(),
                                "You Have Cancel the Dialog box", Toast.LENGTH_LONG)
                                .show();
                }
        });
        langskill.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View arg0) {
        // TODO Auto-generated method stub
        knowlanguage = "";
        ad.show();
        }
        });



        final AlertDialog.Builder wl = new AlertDialog.Builder(this);
        wl.setTitle("Language you want to learn");
        wl.setMultiChoiceItems(myList, bl, new DialogInterface.OnMultiChoiceClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
                        // TODO Auto-generated method stub

                        if (arg2) {
                                // If user select a item then add it in selected items
                                selList.add(arg1);
                        } else if (selList.contains(arg1)) {
                                // if the item is already selected then remove it
                                selList.remove(Integer.valueOf(arg1));
                        }
                }
        });
        wl.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        wantlanguage = "";
                        for (int i = 0; i < selList.size(); i++) {

                                wantlanguage = wantlanguage + myList[selList.get(i)] + "  ";
                        }
                        Toast.makeText(getApplicationContext(),
                                "Total" + wantlanguage, Toast.LENGTH_LONG)
                                .show();

                        user = userLocalStore.getLoggedInUser();
                        User users = new User(user.username, user.password, user.knowlanguage, wantlanguage, user.interests);
                        //users = new User(user.name, user.age, user.username, user.password, knowlanguage, user.wantlanguage, user.interests);
                        updateUserwantlang(users);



                }
        });
        wl.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(),
                                "You Have Cancel the Dialog box", Toast.LENGTH_LONG)
                                .show();
                }
        });
        langwant.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        wantlanguage = "";
                        wl.show();
                }
        });



        final AlertDialog.Builder ia = new AlertDialog.Builder(this);
        ia.setTitle("Interest activity");
        ia.setMultiChoiceItems(myActivityList, ml, new DialogInterface.OnMultiChoiceClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
                        // TODO Auto-generated method stub

                        if (arg2) {
                                // If user select a item then add it in selected items
                                selList.add(arg1);
                        } else if (selList.contains(arg1)) {
                                // if the item is already selected then remove it
                                selList.remove(Integer.valueOf(arg1));
                        }
                }
        });
        ia.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        interestActivity = "";
                        for (int i = 0; i < selList.size(); i++) {

                                interestActivity = interestActivity + myActivityList[selList.get(i)] + "  ";
                        }
                        Toast.makeText(getApplicationContext(),
                                "Total" + interestActivity, Toast.LENGTH_LONG)
                                .show();
                        interests.setText(interestActivity);
                        user = userLocalStore.getLoggedInUser();
                        User users = new User(user.username, user.password, user.knowlanguage, user.wantlanguage, interestActivity);
                        //users = new User(user.name, user.age, user.username, user.password, knowlanguage, user.wantlanguage, user.interests);
                        updateUserinterests(users);


                }
        });
        ia.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(),
                                "You Have Cancel the Dialog box", Toast.LENGTH_LONG)
                                .show();
                }
        });
        interests.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        interestActivity = "";
                        ia.show();
                }
        });



}




@Override
public void onClick(View v) {
        switch(v.getId()){

                case R.id.back:
                         Intent loginIntent = new Intent(this, My_Profile.class);
                         startActivity(loginIntent);
                break;
                  case R.id.iEdit:
                          Intent Intent = new Intent(this, Edit_Profile.class);
                          startActivity(Intent);
                          break;
                case R.id.save:

                        User users = userLocalStore.getLoggedInUser();
                        User user = new User(users.username, users.password);
                        authenticatee(user);
                       // Intent Intentsave = new Intent(this, My_Profile.class);
                        //startActivity(Intentsave);
                        break;


        }
        }


        public void authenticatee(User user) {
                ServerRequests serverRequest = new ServerRequests(this);
                serverRequest.fetchUserDataAsyncTask(user, new GetUserCallback() {
                        @Override
                        public void done(User returnedUser) {
                                if (returnedUser == null) {
                                        showErrorMessage();
                                } else {
                                        userLocalStore.storeUserData(returnedUser);
                                }
                        }
                });
        }




        private void showErrorMessage() {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Edit_Profile.this);
                dialogBuilder.setMessage("Incorrect user details");
                dialogBuilder.setPositiveButton("Ok", null);
                dialogBuilder.show();
        }





private void updateUserknowlang(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.UpdateUserDataInBackground(user, new GetUserCallback() {
                @Override
                public void done(User returnedUser) {

                        Intent loginIntent = new Intent(Edit_Profile.this, Edit_Profile.class);
                        startActivity(loginIntent);
                }
        });
        }


        private void updateUserwantlang(User user) {
                ServerRequests serverRequest = new ServerRequests(this);
                serverRequest.UpdateUserwlDataInBackground(user, new GetUserCallback() {
                        @Override
                        public void done(User returnedUser) {

                                Intent loginIntent = new Intent(Edit_Profile.this, Edit_Profile.class);
                                startActivity(loginIntent);
                        }
                });
        }


        private void updateUserinterests(User user) {
                ServerRequests serverRequest = new ServerRequests(this);
                serverRequest.UpdateUseriaDataInBackground(user, new GetUserCallback() {
                        @Override
                        public void done(User returnedUser) {

                                Intent loginIntent = new Intent(Edit_Profile.this, Edit_Profile.class);
                                startActivity(loginIntent);
                        }
                });
        }


        private void getImage() {
                User users = userLocalStore.getLoggedInUser();
                String id = users.username.toString().trim();
                class GetImage extends AsyncTask<String,Void,Bitmap> {
                        ProgressDialog loading;

                        @Override
                        protected void onPreExecute() {
                                super.onPreExecute();
                                loading = ProgressDialog.show(Edit_Profile.this, "Uploading...", null,true,true);
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

        @Override
        protected void onStart() {
                super.onStart();
                        getImage();

        }


}

