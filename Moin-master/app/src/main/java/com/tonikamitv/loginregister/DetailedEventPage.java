package com.tonikamitv.loginregister;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.lucasr.twowayview.TwoWayView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Created by Tyler
 */
public class DetailedEventPage extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    User user;
    UserLocalStore userLocalStore;
    ImageButton btnJoin;
    String nm;
    TextView creator;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private ImageButton iLangLearning;
    ArrayList<Bitmap> bitmapArray;
    public Bitmap images[];
    String id;
    boolean flag = false;
    String participant, category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_event_view);

        userLocalStore = new UserLocalStore(this);
        Intent intent = getIntent();

        //Info from the last activity
        TextView eventName = (TextView) findViewById(R.id.eventName);
        eventName.setText((CharSequence) intent.getSerializableExtra("name"));
        TextView locationName = (TextView) findViewById(R.id.locationOfEvent);
        locationName.setText((CharSequence) intent.getSerializableExtra("location"));
        TextView timeEvent = (TextView) findViewById(R.id.timeOfEvent);
        timeEvent.setText((CharSequence) intent.getSerializableExtra("time"));
        creator = (TextView) findViewById(R.id.creatorName);
        iLangLearning = (ImageButton) findViewById(R.id.iLangLearning);
        creator.setText((CharSequence) intent.getSerializableExtra("creator"));
        TextView description = (TextView) findViewById(R.id.textdescription);
        description.setText((CharSequence) intent.getSerializableExtra("description"));


        participant = ((String) intent.getSerializableExtra("participant"));
        category = ((String) intent.getSerializableExtra("category"));

        final TextView clicktext = (TextView) findViewById(R.id.clicktext);

        final String _eventName = (String) intent.getSerializableExtra("name");
        final String _eventCreator = (String) intent.getSerializableExtra("creator");


        getImage();

        View.OnClickListener p = new View.OnClickListener(){
            @Override
        public void onClick(View text){

                    clicktext.setText(" ");
                     setimage();}} ;
        clicktext.setOnClickListener(p);

      /*  View.OnClickListener n = new View.OnClickListener(){
            @Override
            public void onClick(View text){

                switch (category) {
                    case "cinema":
                        // Single menu item is selected do something
                        // Ex: launching new activity/screen or show alert message
                        startActivity(new Intent(DetailedEventPage.this, LangLearning.class));
                        break;

                };}} ;
        iLangLearning.setOnClickListener(n); */


        //Fragment to the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        final ImageButton btnJoin = (ImageButton) findViewById(R.id.btnJoin);
        final ImageButton btnNotice = (ImageButton) findViewById(R.id.btnNoticed);
        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)  {
                if (v.equals(btnJoin)) {btnJoin.setSelected(true);
                    user = userLocalStore.getLoggedInUser();
                    String method = "updateParticipants";
                    DBEventUpdate databaseTC = new DBEventUpdate(this);
                    databaseTC.execute(method, user.username, _eventCreator, _eventName);
                } else {
                    btnJoin.setSelected(false);
                }
                if (v.equals(btnNotice)) {

                }
            }
        };

        btnJoin.setOnClickListener(listener);
        btnNotice.setOnClickListener(listener);
    }


    public void callLangLearningMethod(View view) {
        switch (category){
            case "Culture":
                Intent culture = new Intent(this, LangLearningCulture.class);
                startActivity(culture);
                break;
            case "Cinema":
                Intent cinema = new Intent(this, LangLearning.class);
                startActivity(cinema);
                break;
            case "Education":
                Intent education = new Intent(this, LangLearning.class);
                startActivity(education);
                break;
            case "Excursion":
                Intent excursion = new Intent(this, LangLearning.class);
                startActivity(excursion);
                break;
            case "Food":
                Intent food = new Intent(this, LangLearning.class);
                startActivity(food);
                break;
            case "Games":
                Intent games = new Intent(this, LangLearning.class);
                startActivity(games);
                break;
            case "Help":
                Intent help = new Intent(this, LangLearning.class);
                startActivity(help);
                break;
            case "LanguageLearning":
                Intent langlearning = new Intent(this, LangLearning.class);
                startActivity(langlearning);
                break;
            case "Music":
                Intent music = new Intent(this, LangLearning.class);
                startActivity(music);
                break;
            case "Sports":
                Intent sports = new Intent(this, LangLearning.class);
                startActivity(sports);
                break;
            case "Time Out":
                Intent timeout = new Intent(this, LangLearning.class);
                startActivity(timeout);
                break;
        }
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();
        String adressFromIntent;
        adressFromIntent = (String) intent.getSerializableExtra("location"); //There are probably better ways to do this, I made the cast cause we have no more time u_u

        // Send the address string here to retrieve the coordinates, example:
        // LatLng newAdress = getLocationFromAddress("Hermannstr 32, Bremen");

        LatLng newAdress = getLocationFromAddress(adressFromIntent);
        mMap.addMarker(new MarkerOptions().position(newAdress).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newAdress, 10));

    }

    //Transforms string address into LatLng for Google
    public LatLng getLocationFromAddress(String strAddress) {

        //Geocoder coder = new Geocoder(context);
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return p1;
    }



    private void getImage() {

        bitmapArray = new ArrayList<Bitmap>();

        String[] separated = participant.split(",");
            for (int i = 0; i <= separated.length - 1; i++) {
                id = separated[i].trim(); // this will contain "Fruit"


                //String id = name;
                class GetImage extends AsyncTask<String, Void, Bitmap> {
                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(DetailedEventPage.this, "Uploading...", null, true, true);
                    }

                    @Override
                    protected void onPostExecute(Bitmap b) {
                        super.onPostExecute(b);
                        loading.dismiss();
                        bitmapArray.add(b); // Add a bitmap
                        flag = true;
                        // bitmapArray.get(0);
                    }

                    @Override
                    protected Bitmap doInBackground(String... params) {
                        String id = params[0];
                        String add = "http://nishadesai.16mb.com/imageuser.php?id=" + id;
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
            //while (!flag){try { Thread.sleep(100); }catch (InterruptedException e) { e.printStackTrace(); }


    }









    public void setimage(){
        images = new Bitmap[bitmapArray.size()];
        for(int i= 0; i <= bitmapArray.size()-1; i++) {
            images[i] = bitmapArray.get(i);
        }
        CustomAdapter adapter = new CustomAdapter(images);
        //listView.setAdapter(adapter);

        TwoWayView lvTest = (TwoWayView) findViewById(R.id.lvItems);
        lvTest.setAdapter(adapter);

    }

    class CustomAdapter extends BaseAdapter
    {
        Bitmap images[];
        LayoutInflater inflater;
        public CustomAdapter(Bitmap[] Images)
        {
            images=Images;
            inflater= LayoutInflater.from(DetailedEventPage.this);

        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return images.length;
        }
        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;
            if(convertView==null)
            {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.listview_image, null);
                holder.iv= (ImageView) convertView.findViewById(R.id.imageimage);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.iv.setImageBitmap(images[position]);
            return convertView;
        }

    }
    class ViewHolder
    {
        ImageView iv;
    }






}
