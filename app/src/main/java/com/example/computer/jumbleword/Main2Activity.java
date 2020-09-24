package com.example.computer.jumbleword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    public void start(View v) {
        Intent intent = new Intent(this,Main3Activity.class);
         startActivity(intent);

    }
    public void score(View v) {
        Intent intent = new Intent(this,Main4Activity.class);
        startActivity(intent);

    }
    public void help(View v) {
        Intent intent = new Intent(this,Main5Activity.class);
        startActivity(intent);

    }
    public void exit(View v) {
        System.exit(0);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
    }}