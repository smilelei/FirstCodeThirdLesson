package com.example.administrator.myfirstcodethirdlesson.content;

import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myfirstcodethirdlesson.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class ConnectActivity extends Activity{
    private ListView listView;
    ArrayAdapter<String> adapter;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connectlayout);
        listView = (ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
//        Toast.makeText(this,list.get(0),Toast.LENGTH_LONG).show();
        listView.setAdapter(adapter);
        queryConnect();


    }
    private void queryConnect(){
        Cursor cursor = null;
        try {
                cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
                while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.e("@lei",name+"name");

                    Log.e("@lei",number+"number");
                list.add(name+number);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(cursor!=null)
            {
                cursor.close();
            }
        }

    }
}
