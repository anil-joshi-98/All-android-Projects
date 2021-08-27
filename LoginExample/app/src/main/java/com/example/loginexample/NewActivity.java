package com.example.loginexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class NewActivity extends AppCompatActivity {
    ImageView lt;
    Button bt;
    EditText text,email,ps;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.layout_new);
        lt=findViewById(R.id.signIN);
        text=findViewById(R.id.first);
        email=findViewById(R.id.second);
        ps=findViewById(R.id.third);
        bt=findViewById(R.id.signUp);
        lt.setOnClickListener(v -> finish());
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = new DatabaseHelper(NewActivity.this).registerUser(text.getText().toString() , email.getText().toString() , ps.getText().toString());
                if(var){
                    Toast.makeText(NewActivity.this, "User Registered Successfully !!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(NewActivity.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setPositiveButton("YES",
        super.onBackPressed();
    }
}