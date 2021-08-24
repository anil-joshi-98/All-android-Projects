package com.example.sqllit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2,bt3,bt4,bt5;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.first);
        e2=findViewById(R.id.second);
        bt1=findViewById(R.id.saveBtn);
        bt2=findViewById(R.id.retBtn);
        bt3=findViewById(R.id. deleteBtn);
        bt4=findViewById(R.id.showAll);
        bt5=findViewById(R.id.deleteAll);
        addData();
        show();
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new DatabaseHelper(MainActivity.this).deleteAll()>0){
                    Toast.makeText(MainActivity.this,"done",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Error ",Toast.LENGTH_SHORT).show();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=new DatabaseHelper(MainActivity.this).showAll();
                StringBuffer st=new StringBuffer();
                if(cursor.getCount()==0){
                    showData("ShowData","No data found");
                    return;
                }
                while(cursor.moveToNext()){
                    st.append("ID\t:\t"+cursor.getString(0)+"\n");
                    st.append("Name\t:\t"+cursor.getString(1)+"\n");
                    st.append("Message\t:\t"+cursor.getString(2)+"\n\n");
                }
                showData("All data",st.toString());
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=e1.getText().toString();
                if(id.equals(String.valueOf(""))){
                    e1.setError("please enter id");
                }
                Integer var=new DatabaseHelper(MainActivity.this).delete(id);
                if(var>=0){
                    Toast.makeText(MainActivity.this,"data deleted successfully",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"data deleted error",Toast.LENGTH_SHORT).show();
            }
        });
        e2.setText("");
        e1.setText("");
    }

    public void addData(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean data=new DatabaseHelper(MainActivity.this).insertData(e1.getText().toString(),e2.getText().toString());
                if(data){
                    Toast.makeText(MainActivity.this, "success" , Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "failed" , Toast.LENGTH_SHORT).show();
                }
                e2.setText("");
                e1.setText("");
            }
        });
    }
    public void show(){
            bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=e1.getText().toString();
                    if(id.equals(String.valueOf(""))){
                        e1.setError("please enter correct id");
                        return;
                    }
                    Cursor cursor=new DatabaseHelper(MainActivity.this).getData(id);
                    String values=null;
                    if(cursor.moveToNext()){
                        values="id:"+cursor.getString(0)+"\n"+"name:"+cursor.getString(1)+"\n"+"email:"+cursor.getString(2);

                    }
                    showData("data",values);
                }
            });
            e2.setText("");
        e1.setText("");
        }
    public void showData(String title,String msg){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.create();
        alert.setCancelable(true);
        alert.setTitle(title);
        if(msg!=null)
            alert.setMessage(msg);
        else
            alert.setMessage("no data found");
        alert.show();
        }
}