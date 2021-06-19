package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Nextpage extends AppCompatActivity {
    Dbcon db;
    String item2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nextpage);
        db = new Dbcon(this);
        ListView lv = findViewById(R.id.lv2);
        Intent it=getIntent();
        String item1 = it.getStringExtra("user1");
        Integer prev = Integer.parseInt(item1.split(" ")[0]);
        Cursor c = db.view(prev);
        ArrayList<String> s = new ArrayList<>();

        while (c.moveToNext()) {
            String t;
            t = String.valueOf(c.getInt(0));
            t = t + " " + String.valueOf(c.getString(1));
            t = t + " " + String.valueOf(c.getFloat(2));
            s.add(t);
        }
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, R.layout.textbox, s);
        lv.setAdapter(ad);
        lv.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            item2 = (String) lv.getItemAtPosition(position);
            cal(item1);
        });
    }

    void  cal(String item1){
        Intent i = new Intent(this, Cal.class);
        i.putExtra("user1", item1);
        i.putExtra("user2", item2);
        startActivity(i);
    }
}