package com.example.computer.jumbleword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent = getIntent();
        String score = intent.getStringExtra("key");
        TextView tv = (TextView) findViewById(R.id.textView11);
        tv.setText(String.valueOf(0));
    }
}
