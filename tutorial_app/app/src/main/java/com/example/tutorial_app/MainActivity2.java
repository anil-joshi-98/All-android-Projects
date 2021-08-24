package com.example.tutorial_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    RatingBar rb;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv=findViewById(R.id.textView2);
        rb=findViewById(R.id.ratingBar);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tv.setText("rating="+rating);
                if(rating>=4){
                    Toast.makeText(MainActivity2.this, "Thanks for your rating", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity2.this,"we will do our best to provide you best service",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}