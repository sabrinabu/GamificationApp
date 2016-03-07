package com.tonikamitv.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Lenovo on 15-02-2016.
 */
public class LangLearning extends Activity implements View.OnClickListener {

    TextView bBack;
    TextView bNext;
    TextView engQue;
    TextView germanQue;
    TextView queNum;
    TextView totalQueNum;
    Button option1;
    Button option2;
    Button option3;
    TextView GermanAns;

    private int id = 1;
    private String questionE;
    private String questionG;
    private String opn1;
    private String opn2;
    private String opn3;
    private String ans;
    private String ids;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lang_learning);
        bBack = (TextView) findViewById(R.id.tback);
        bNext = (TextView) findViewById(R.id.tnext);
        engQue = (TextView) findViewById(R.id.engQue);
        germanQue = (TextView) findViewById(R.id.germanQue);
        queNum = (TextView) findViewById(R.id.queNum);
        totalQueNum = (TextView) findViewById(R.id.totalQueNum);
        option1 = (Button) findViewById(R.id.op1);
        option2 = (Button) findViewById(R.id.op2);
        option3 = (Button) findViewById(R.id.op3);


        bBack.setOnClickListener(this);
        bNext.setOnClickListener(this);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        totalQueNum.setText("/5");

        questionAns(id);

    }

    public void questionAns(int id){

        switch (id) {
            case 1:
                questionE = "Is the cinema Cinespace located in the mall called Waterfront?";
                questionG = "Befindet sich das Kino Cinespace im ______ Waterfront?";
                opn1 = "Einkaufszentrum";
                opn2 = "Kino";
                opn3 = "Haus";
                ans = "Einkaufszentrum";
                break;

            case 2:
                questionE = "Which movies do you show today at the Schauburg?";
                questionG = "Welche Filme werden ______ in der Schauburg gezeigt?";
                opn1 = "morgen";
                opn2 = "heute";
                opn3 = "gestern";
                ans = "heute";
                break;

            case 3:
                questionE = "Do you have salty and sweet popcorn?";
                questionG = "Habt ihr ______ und süßes Popcorn?";
                opn1 = "süßes";
                opn2 = "saures";
                opn3 = "salziges";
                ans = "salziges";
                break;

            case 4:
                questionE = "How much costs a movie ticket?";
                questionG = "Wie viel ______ die Kinokarte?";
                opn1 = "kostet";
                opn2 = "wiegt";
                opn3 = "fliegt";
                ans = "kostet";
                break;

            case 5:
                questionE = "Where do you have free seats (box or parkett)?";
                questionG = "Wo sind noch ______ frei (Loge oder Parkett)?";
                opn1 = "Tische";
                opn2 = "Türen";
                opn3 = "Sitze";
                ans = "Sitze";
                break;


        }

        engQue.setText(questionE);
        germanQue.setText(questionG);
        option1.setText(opn1);
        option1.setBackgroundResource(R.drawable.unbedeutendstenbg);
        option2.setText(opn2);
        option2.setBackgroundResource(R.drawable.unbedeutendstenbg);
        option3.setText(opn3);
        option3.setBackgroundResource(R.drawable.unbedeutendstenbg);
        ids  = Integer.toString(id);
        queNum.setText(ids);

       // queNum.setText(id);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tback:
                if(id == 1){
                    startActivity(new Intent(this, HomePage.class));
                }else{
                    id--;
                    option1.setEnabled(true);
                    option2.setEnabled(true);
                    option3.setEnabled(true);
                    questionAns(id);

                }
                break;

            case R.id.tnext:
                if(id == 5){
                    startActivity(new Intent(this, HomePage.class));
                }else{
                    id++;
                    option1.setEnabled(true);
                    option2.setEnabled(true);
                    option3.setEnabled(true);
                    questionAns(id);
                }
                break;
            case R.id.op1:
                if(option1.getText().toString() == ans){
                    option1.setBackgroundResource(R.drawable.groestengreenbg);
                    String str = germanQue.getText().toString();
                    String sourceString = "<b>" + ans + "</b> ";
                    String str1 = str.replace("______", sourceString);
                    germanQue.setText(Html.fromHtml(str1));
                    option2.setEnabled(false);
                    option3.setEnabled(false);


                }else{
                    option1.setBackgroundResource(R.drawable.groesstenbg);

                    }


                break;
            case R.id.op2:
                if(option2.getText().toString() == ans){
                    option2.setBackgroundResource(R.drawable.groestengreenbg);
                    String str = germanQue.getText().toString();
                    String sourceString = "<b>" + ans + "</b> ";
                    String str1 = str.replace("______", sourceString);
                    germanQue.setText(Html.fromHtml(str1));
                    option1.setEnabled(false);
                    option3.setEnabled(false);
                }else{
                    option2.setBackgroundResource(R.drawable.groesstenbg);
                }
                break;
            case R.id.op3:
                if(option3.getText().toString() == ans){
                    option3.setBackgroundResource(R.drawable.groestengreenbg);
                    String str = germanQue.getText().toString();
                    String sourceString = "<b>" + ans + "</b> ";
                    String str1 = str.replace("______", sourceString);
                    germanQue.setText(Html.fromHtml(str1));
                    option1.setEnabled(false);
                    option2.setEnabled(false);
                }else{
                    option3.setBackgroundResource(R.drawable.groesstenbg);
                }
                break;

        }
    }

}