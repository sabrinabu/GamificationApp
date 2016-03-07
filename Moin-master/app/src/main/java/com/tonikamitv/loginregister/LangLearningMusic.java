package com.tonikamitv.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Lenovo on 15-02-2016.
 */
public class LangLearningMusic extends Activity implements View.OnClickListener {

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
        totalQueNum.setText("/9");

        questionAns(id);

    }

    public void questionAns(int id){

        switch (id) {
            case 1:
                questionE = "The Bremen Samba Carneval has a different motto each year.";
                questionG = "Der Bremer Samba Karneval hat jedes Jahr ______ Motto.";
                opn1 = "unterschiedliche";
                opn2 = "gleiches";
                opn3 = "gar kein";
                ans = "unterschiedliche";
                break;

            case 2:
                questionE = "The Schlachte Zauber takes place at the Weserpromenade at christmastime each year.";
                questionG = "Der Schlachte Zauber ______ jedes Jahr zur Weihnachtszeit an der Weserpromenade ______";
                opn1 = "ablaufen";
                opn2 = "passieren";
                opn3 = "stattfinden";
                ans = "stattfinden";
                break;

            case 3:
                questionE = "The Bremen Weihnachtsmarkt is one oft he biggest christmas markets in Germany.";
                questionG = "Der Bremer Weihnachtsmarkt gehört zu den ______ Weihnachtsmärkten in Deutschland.";
                opn1 = "kleinsten";
                opn2 = "unbedeutendsten";
                opn3 = "salziges";
                ans = "größten";
                break;

            case 4:
                questionE = "One week before good friday the osterwiese starts.";
                questionG = "Eine ______ vor Karfreitag beginnt in Bremen die Osterwiese.";
                opn1 = "Tag";
                opn2 = "Woche";
                opn3 = "Jahr";
                ans = "Woche";
                break;

            case 5:
                questionE = "During the long night of museeums, museums are open in to the night.";
                questionG = "Während der langen Nacht der Museen sind die Museen bis in die Nacht ______";
                opn1 = "verkauft";
                opn2 = "geschlossen";
                opn3 = "geöffnet";
                ans = "geöffnet";
                break;
            case 6:
                questionE = "La Strada is an international festival of street arts in Bremen.";
                questionG = "La Strada ist ein internationales ______ der Straßenkünste in Bremen.";
                opn1 = "Festival";
                opn2 = "Ball";
                opn3 = "Versammlung";
                ans = "Festival";
                break;
            case 7:
                questionE = "Breminale is an open air cultural festival which takes place every summer for five days.";
                questionG = "Die Breminale ist ein fünftägiges open Air Kulturfestival, welches ______ im Sommer stattfindet.";
                opn1 = "vierteljährlich";
                opn2 = "nie";
                opn3 = "jährlich";
                ans = "jährlich";
                break;
            case 8:
                questionE = "Ischa Freimaak in the traditional bremen language and means “it’s Freimarkt“.";
                questionG = "Ischa Freimaak ist bremisch und ______ für „Es ist ja Freimarkt“.";
                opn1 = "steht";
                opn2 = "sucht";
                opn3 = "findet";
                ans = "steht";
                break;
            case 9:
                questionE = "The Bremer Freimarkt takes place in Bremen and is one of the oldest folk festivals in Germany.";
                questionG = "Der Bremer Freimarkt findet in Bremen statt und ist eines der ______ Volksfeste Deutschlands.";
                opn1 = "längsten";
                opn2 = "ältesten";
                opn3 = "kürzesten";
                ans = "ältesten";
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
                if(id == 9){
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