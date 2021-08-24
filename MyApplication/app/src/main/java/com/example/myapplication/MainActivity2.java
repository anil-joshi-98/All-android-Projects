package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Button bt;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt=findViewById(R.id.butttonid);
        Intent ob=getIntent();
        tv=findViewById(R.id.textView);
        String str=ob.getStringExtra("hey");
        tv.setText(str);
    }
    public void backBtn(View view){
        finish();
    }
}