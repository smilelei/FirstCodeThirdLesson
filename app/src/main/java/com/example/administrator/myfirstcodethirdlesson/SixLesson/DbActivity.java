package com.example.administrator.myfirstcodethirdlesson.SixLesson;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myfirstcodethirdlesson.R;
import com.example.administrator.myfirstcodethirdlesson.SixLesson.db.MyDbHelper;

/**
 * Created by Administrator on 2017/12/31.
 */

public class DbActivity extends Activity implements View.OnClickListener{
    private Button btnCreate;
    private Button btninsert;
    private Button btndelete;
    private Button btnquery;
    private Button btnreplace;
    private TextView txtresult;


    MyDbHelper  dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);
        dbHelper = new MyDbHelper(DbActivity.this,"mylei.db",null,2);

        btnCreate = (Button)findViewById(R.id.btncreate);
        btninsert = (Button)findViewById(R.id.btninsert);
        btndelete = (Button)findViewById(R.id.btndelete);
        btnquery = (Button)findViewById(R.id.btnquery);
        btnreplace = (Button)findViewById(R.id.btnreplace);

        txtresult = (TextView)findViewById(R.id.txtresult);

        btnCreate.setOnClickListener(this);
        btninsert.setOnClickListener(this);
        btndelete.setOnClickListener(this);
        btnquery.setOnClickListener(this);
        btnreplace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        StringBuffer buffer = new StringBuffer();
        switch (v.getId()){
            case R.id.btncreate:
                dbHelper.getWritableDatabase();
                break;
            case R.id.btninsert:
                ContentValues values = new ContentValues();
                values.put("author","first code");
                values.put("price",100);
                values.put("page",499);
                values.put("name","firstcode");
                database.insert("books",null,values);
                values.clear();
                break;
            case R.id.btndelete:
                database.delete("books","page > ?",new String[]{"200"});
                break;
            case R.id.btnquery:
                Cursor cursor = database.query("books",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        buffer.append( new StringBuffer( cursor.getString(cursor.getColumnIndex("page"))));
                        buffer.append( new StringBuffer( cursor.getString(cursor.getColumnIndex("author"))));
                        Log.e("@lei",cursor.getString(cursor.getColumnIndex("page")));
                        Log.e("@lei",cursor.getString(cursor.getColumnIndex("author")));


                    }while (cursor.moveToNext());
                }
                String  result = buffer.toString();
                txtresult.setText(result);
                cursor.close();
                break;
            case R.id.btnreplace:
                database.beginTransaction();
                try{
                database.delete("books",null,null);
//                if(true){
//                    throw new NullPointerException();
//                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("author","second code");
                contentValues.put("price",111);
                contentValues.put("page",5000);
                contentValues.put("name","second");
                database.insert("books",null,contentValues);
                database.setTransactionSuccessful();
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    database.endTransaction();
                }
                break;
        }
    }
}
