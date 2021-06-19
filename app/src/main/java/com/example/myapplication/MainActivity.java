package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Dbcon db = new Dbcon(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor c = db.view(-1);
        if (c.getCount() == 0)
            createdb();
        Button b1 = findViewById(R.id.b1);
        Button b2= findViewById(R.id.b2);
        b1.setOnClickListener(v -> {

            try {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        b2.setOnClickListener(view -> {

            try {
                Intent i = new Intent(MainActivity.this, Transaction.class);
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    void createdb() {

        db.insert_user("Umang", (float) (10000));
        db.insert_user("Anil", (float) (2000));
        db.insert_user("Amit", (float) (500.67));
        db.insert_user("Shreya", (float) (20000.78));
        db.insert_user("Subhash", (float) (30000));
        db.insert_user("Karan", (float) (200));
        db.insert_user("Taneesha", (float) (8903));
        db.insert_user("Roshni", (float) (4500));
        db.insert_user("Sahil", (float) (2345));
        db.insert_user("Kapil", (float) (60000));
    }
    @Override
    public void onBackPressed() {}


}