package com.example.loginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageView tv;
    EditText text,ps;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.clickHere);
        bt = findViewById(R.id.loginBtn);
        text = findViewById(R.id.text1);
        ps = findViewById(R.id.pswrd);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewActivity.class));
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = new DatabaseHelper(MainActivity.this).checkUser(text.getText().toString(), ps.getText().toString());
                if (var) {
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Overactivity.class));
                    finish();
                } else
                    Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}