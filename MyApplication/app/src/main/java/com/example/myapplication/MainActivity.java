package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.butttonid);
        et=findViewById(R.id.textGet);
    }
    public void nextBtn(View view){
        String str=" "+et.getText().toString();
        Intent intent=new Intent(this,MainActivity2.class);
        intent.putExtra("hey",str);
        startActivity(intent);
    }
}