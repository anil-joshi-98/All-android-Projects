package com.example.tutorial_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button bt;
    TextView tv;
    private String str="";
    private RadioGroup rg;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bt=findViewById(R.id.nextbtn);
        tv=findViewById(R.id.textView);
        rg=findViewById(R.id.radioGrp);
    }
    public void newActivity(View v){
        AlertDialog.Builder alb=new AlertDialog.Builder(MainActivity.this);
        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.password_page,null);
        alb.setView(view);
        alb.setMessage("please fill the information");
        alb.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            EditText et=view.findViewById(R.id.password_toggle);
            String pin=et.getText().toString().trim();
            if(pin.equals("2684")){
                Intent intent = new Intent(getApplicationContext(),NewActivity.class);
                startActivity(intent);int i = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(i);
                str = rb.getText().toString();
                str += " pressed";
                tv.setText(str);
                Log.d("anil", str);
                Toast.makeText(getApplicationContext(), "radio " + str + " pressed", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"Please enter correct password",Toast.LENGTH_SHORT).show();
            }
            }
        }).setNegativeButton("no",null);
        AlertDialog alert=alb.create();
        alert.show();
    }
}