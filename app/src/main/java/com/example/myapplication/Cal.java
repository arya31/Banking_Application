package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Cal extends AppCompatActivity {
    Dbcon db;
    String uname1;
    String uname2;
    Float uamt1;
    Float uamt2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal);
        db = new Dbcon(this);
        Intent it=getIntent();
        String item1 = it.getStringExtra("user1");
        String item2 = it.getStringExtra("user2");
        Integer uid1 = Integer.parseInt(item1.split(" ")[0]);
        uname1=item1.split(" ")[1];
        uamt1 = Float.parseFloat(item1.split(" ")[2]);
        Integer uid2 = Integer.parseInt(item2.split(" ")[0]);
        uname2=item2.split(" ")[1];
        uamt2 = Float.parseFloat(item2.split(" ")[2]);
        Button snd=findViewById(R.id.tb);
        EditText entamt=findViewById(R.id.ed1);
        TextView name1 = findViewById(R.id.u1);
        TextView name2 = findViewById(R.id.u2);
        TextView amount1 = findViewById(R.id.ua1);
        TextView amount2 = findViewById(R.id.ua2);
        name1.setText(uname1);
        name2.setText(uname2);
        amount1.setText(uamt1.toString());
        amount2.setText(uamt2.toString());
        snd.setOnClickListener(v->{
            Log.d("Clicks", "You clicked Register");
            try {
                Float amt = Float.parseFloat(entamt.getText().toString());
                if (amt <= uamt1) {
                    uamt1 = uamt1 - amt;
                    uamt2 = uamt2 + amt;
                    db.update(uamt1, uid1);
                    db.update(uamt2, uid2);
                    db.insert_transactions(uname1, uname2, amt);
                    Toast.makeText(getApplicationContext(), "Transaction Successful", Toast.LENGTH_LONG).show();
                    gsecondactivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Amount Exceeded. Send again!", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), "Invalid Characters", Toast.LENGTH_LONG).show();
            }

        });
        Button cancel=findViewById(R.id.tb2);
        cancel.setOnClickListener(v->{
            Log.d("Clicks", "You clicked Register");
            gsecondactivity();
        });
    }
    void gsecondactivity(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Press Cancel to leave", Toast.LENGTH_LONG).show();

    }

}