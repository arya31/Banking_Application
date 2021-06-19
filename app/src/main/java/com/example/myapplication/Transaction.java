package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Transaction extends AppCompatActivity {
    Dbcon db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction);
        db=new Dbcon(this);
        ListView lv=findViewById(R.id.lv2);
        Cursor c=db.viewt();
        ArrayList<String> s= new ArrayList<>();

        while(c.moveToNext())
        {
            String t;
            t=String.valueOf(c.getInt(0));
            t=t+ ". "+String.valueOf(c.getString(1));
            t=t+ " to "+String.valueOf(c.getString(2));

            t=t+ " : "+String.valueOf(c.getFloat(3));
            s.add(t);
        }
        ArrayAdapter<String> ad=new ArrayAdapter<>(this,R.layout.textbox, s);
        lv.setAdapter(ad);

    }
}
