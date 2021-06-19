package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbcon extends SQLiteOpenHelper {
    public Dbcon(Context context){
        super(context,   "User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sql) {
        sql.execSQL("create table user_table(Uid Integer primary key autoincrement, uname Text, uamt Real)");
        sql.execSQL("create table transactions(Tid Integer primary key autoincrement, uname1 Text, uname2 Text, inc Real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int i, int i1) {
        sql.execSQL("drop table if exists user_table");
        sql.execSQL("drop table if exists transactions");
    }

    public Boolean insert_user(String uname, Float uamt){
        SQLiteDatabase sql= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("uname",uname);
        cv.put("uamt",uamt);
        if(sql.insert("user_table",null,cv)==-1)
            return false;
        return true;
    }

    public Boolean insert_transactions(String uname1,String uname2, Float inc){
        SQLiteDatabase sql= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("uname1",uname1);
        cv.put("uname2",uname2);
        cv.put("inc",inc);
        if(sql.insert("transactions",null,cv)==-1)
            return false;
        return true;
    }

    public Boolean update(Float uamt, Integer Uid){
        SQLiteDatabase sql=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("uamt",uamt);
        Cursor c;
        c=sql.rawQuery("select uamt FROM user_table WHERE uid=?",new String[]{Uid.toString()});
        if(c.getCount()>0)
        {
            if(sql.update("user_table", cv,"uid=?",new String[]{Uid.toString()})==-1)
                return false;
            return true;
        }
        return false;
    }
    public Cursor view(Integer Uid){
        SQLiteDatabase sql= this.getWritableDatabase();
        if(Uid==-1)
        {
            return sql.rawQuery("select * from user_table", null);
        }
        else {
            return sql.rawQuery("select * from user_table where uid<>?",new String[]{Uid.toString()});
        }
    }

    public Cursor viewt()
    {
        SQLiteDatabase sql= this.getWritableDatabase();
        return sql.rawQuery("select * from transactions",null);
    }

}
