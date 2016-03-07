package com.tonikamitv.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * Created by Lenovo on 25-02-2016.
 */
public class LangLearningList extends Activity implements View.OnClickListener {


    ImageView culture, cinema, education, food, sports, games, languagepractice, help, timeout, notspecified, music, excursion  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lang_learning_list);
        cinema = (ImageView) findViewById(R.id.icinema);
        culture = (ImageView) findViewById(R.id.iculture);
        education = (ImageView) findViewById(R.id.ieducation);
        food = (ImageView) findViewById(R.id.ifood);
        sports = (ImageView) findViewById(R.id.isports);
        games = (ImageView) findViewById(R.id.igames);
        languagepractice = (ImageView) findViewById(R.id.ilanguagepractice);
        help = (ImageView) findViewById(R.id.ihelp);
        timeout = (ImageView) findViewById(R.id.itimeout);
        notspecified = (ImageView) findViewById(R.id.inotspecified);
        music = (ImageView) findViewById(R.id.imusic);
        excursion = (ImageView) findViewById(R.id.iexcursion);
       // music.setOnClickListener(this);
       culture.setOnClickListener(this);
        cinema.setOnClickListener(this);
        education.setOnClickListener(this);
        food.setOnClickListener(this);
        sports.setOnClickListener(this);
        games.setOnClickListener(this);
        languagepractice.setOnClickListener(this);
        help.setOnClickListener(this);
        timeout.setOnClickListener(this);
        notspecified.setOnClickListener(this);
        music.setOnClickListener(this);
        excursion.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icinema:
                Intent cinema = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(cinema);
                break;
            case R.id.iculture:
                Intent musicb = new Intent(LangLearningList.this, LangLearningCulture.class);
                startActivity(musicb);
                break;
            case R.id.ieducation:
                Intent education = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(education);
                break;
            case R.id.ifood:
                Intent food = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(food);
                break;
            case R.id.isports:
                Intent sports = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(sports);
                break;
            case R.id.igames:
                Intent games = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(games);
                break;
            case R.id.ilanguagepractice:
                Intent ll = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(ll);
                break;
            case R.id.ihelp:
                Intent help = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(help);
                break;
            case R.id.itimeout:
                Intent timeout = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(timeout);
                break;
            case R.id.inotspecified:
                Intent nots = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(nots);
                break;
            case R.id.imusic:
                Intent music = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(music);
                break;
            case R.id.iexcursion:
                Intent excursion = new Intent(LangLearningList.this, LangLearning.class);
                startActivity(excursion);
                break;


        }
    }


}