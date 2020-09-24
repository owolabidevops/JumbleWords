package com.example.computer.jumbleword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {
    public void beginner(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void intermediate(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void expert(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
}
