package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Dbcon db;
    String clickeditem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);
        db = new Dbcon(this);
        ListView lv = findViewById(R.id.lv1);
        Cursor c = db.view(-1);
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
            clickeditem = (String) lv.getItemAtPosition(position);
            nextpage();
        });

    }

    void nextpage() {
        Intent i = new Intent(this, Nextpage.class);
        i.putExtra("user1", clickeditem);
        startActivity(i);
    }


}
