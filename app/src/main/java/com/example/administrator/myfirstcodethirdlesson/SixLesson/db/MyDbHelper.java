package com.example.administrator.myfirstcodethirdlesson.SixLesson.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/12/31.
 */

public class MyDbHelper extends SQLiteOpenHelper{
    private Context context;
    String creteDb = "create table books(id integer primary key autoincrement,author text,price real,page integer,name text)";
    String creategory = "create table category(id integer primary key autoincrement,category_name text,category_code integer)";
    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creteDb);
        db.execSQL(creategory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("drop table if exists books");
         db.execSQL("drop table if exists catgory");
         onCreate(db);
    }
}
