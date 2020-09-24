package com.example.computer.jumbleword;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String[] WORDS_DATABASE = new String[]{ "crafty", "creative",
            "complex",  "cuddly",
            "daring",  "patient",
            "feisty",  "refined",
            "sassy",  "romantic",
            "radiant",  "candy",
            "quirky",  "precious",
            "perfect",  "petite",
            "poetic",  "daring",
            "crafty",  "naughty",
            "curvy",  "clever",
            "cheeky",  "classy",
            "caring",  "bright"
    };
    String original = selectRandomWord();
    String shuffled = getShuffledWord(original);
    StringBuilder builder;
    int numberOfGuesses=0;
    Timer t=new Timer();

      int sec=50;
      String  s;
      int a=0;




    public String selectRandomWord() {
        int rPos = new Random().nextInt(WORDS_DATABASE.length);
        return WORDS_DATABASE[rPos];
    }

    public String getShuffledWord(String original) {

        String shuffledWord = original; // start with original
        int wordSize = original.length();
        int shuffleCount = 10; // let us randomly shuffle letters 10 times
        for (int i = 0; i < shuffleCount; i++) {
            //swap letters in two indexes
            int position1 = new Random().nextInt(wordSize);
            int position2 = new Random().nextInt(wordSize);

            shuffledWord = swapCharacters(shuffledWord, position1, position2);
        }
                return shuffledWord;

    }



    private String swapCharacters(String shuffledWord, int position1, int position2) {
        char[] charArray = shuffledWord.toCharArray();
        char temp = charArray[position1];
        charArray[position1] = charArray[position2];
        charArray[position2] = temp;
        return new String(charArray);

    }
    public String getUserGuess() {
        EditText tv1 = (EditText) findViewById(R.id.editText);
        String userguess=tv1.getText().toString(); 
        return userguess;

    }
    public void onclick(View v){
        EditText tv1 = (EditText) findViewById(R.id.editText);
        numberOfGuesses++;
        String userguess = getUserGuess();
        if (original.equalsIgnoreCase(userguess)) {
            tv1.setBackgroundColor(Color.parseColor("#008000"));
            t.cancel();
            message();
            congratmessage();
            TextView tv4 = (TextView) findViewById(R.id.textView3);
            tv4.setText(String.valueOf(score()));



        } else {

            Toast.makeText(MainActivity.this, "Sorry, Wrong answer", Toast.LENGTH_LONG).show();
           // tv1.setTextColor(Color.parseColor("#ff0000"));
            tv1.setText("");


        }

    }
    public void Hint(View v){
       // h++;
        char[] charArray = original.toCharArray();
        EditText tv1 = (EditText) findViewById(R.id.editText);
      // z StringBuilder b = new StringBuilder();
       //for(int i=0;i<original.length();i++){
        //if(h==0)
         tv1.setHint(String.valueOf(charArray[0]));
       // if(h==1){
            //tv1.setHint(String.valueOf(charArray[0]+charArray[h]));
      // }

    }
    public void congratmessage() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Congratulations! You Found The Word In " + numberOfGuesses + " Guess(es)");
        builder.setMessage("             "+" score: "+score());
        // add the buttons
        AlertDialog.Builder ok = builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               dialog.dismiss();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void message() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Would you like to continue?");

        // add the buttons
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText tv1 = (EditText) findViewById(R.id.editText);
                tv1.setText(" ");
                recreate();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GOTO();
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void timemessage() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do You Want To Play Again?");

        // add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restart();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GOTO();
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void gameovermsg() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("GAME OVER");
        // add the buttons
        AlertDialog.Builder ok = builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void restart(){
        Intent intent=getIntent();
        finish();
        startActivity(intent);
    }
    public void shuffle(View v){
       // Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.)
        String shuffled = getShuffledWord(original);
        builder = new StringBuilder();
        TextView tv = (TextView) findViewById(R.id.textView);
        for(int i=0;i<shuffled.length();i++){
            builder.append(shuffled.charAt(i)+"  ");

        }

        tv.setText(builder.toString());
    }
    public void sg(){
        String original = selectRandomWord();
        String shuffled = getShuffledWord(original);
        builder = new StringBuilder();
        TextView tv = (TextView) findViewById(R.id.textView);
        for(int i=0;i<shuffled.length();i++){
            builder.append(shuffled.charAt(i)+"  ");


        }

        tv.setText(builder.toString());
        }


        TextView y;

            @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                    builder = new StringBuilder();
                    TextView tv = (TextView) findViewById(R.id.textView);
                    TextView tv4 = (TextView) findViewById(R.id.textView3);
                            tv4.setText(String.valueOf(a));
                   for (int i = 0; i < shuffled.length(); i++) {
                        builder.append(shuffled.charAt(i) + "  ");
                      /*  tv.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)tv.getLayoutParams();
                        params.setMargins(2,0,2,0);

                        tv.setLayoutParams(params);*/
                    }

                    tv.setText(builder.toString());


                    t.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tv3 = (TextView) findViewById(R.id.textView5);
                                s = Integer.toString(sec);
                                tv3.setText(" "+s);
                                sec --;
                                stop();

                            }


                        });


                    }
                }, 0, 1000);


            }


    public void stop(){
        if (sec==-1) {
            t.cancel();
            t.purge();
            timemessage();
            gameovermsg();

            }}

        public int score() {
            if (sec <= 50 && sec >= 40){  a=55; }
            if (sec < 40 && sec >= 30){  a=40; }
            if (sec < 30 && sec >= 20){  a=35; }
            if (sec <20 && sec >= 10){  a=25; }
            if (sec <10 && sec >= 0){  a=15; }
             return a;

        }

            public void GOTO() {
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);

    }



}









