package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ArrayList<DataModel> ar=new ArrayList<>();
        ar.add(new DataModel(R.drawable.a,"first"));
        ar.add(new DataModel(R.drawable.b,"second"));
        ar.add(new DataModel(R.drawable.c,"third"));
        ar.add(new DataModel(R.drawable.d,"fourth"));
        ar.add(new DataModel(R.drawable.e,"fifth"));
        ar.add(new DataModel(R.drawable.f,"sixth"));
        MyAdapter adapter=new MyAdapter(ar);
        recyclerView.setAdapter(adapter);
    }
}