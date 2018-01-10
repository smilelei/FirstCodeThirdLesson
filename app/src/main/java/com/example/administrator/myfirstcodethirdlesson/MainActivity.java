package com.example.administrator.myfirstcodethirdlesson;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity{
    private Button btn1;
//    private ProgressBar progressBar;
//    private EditText editText;
//    private ImageView imageView;
//    String tex;
//    int pro = 0;
    private LocalBroadcastManager localBroadcastManager;
    BootReceiver bootReceiver;
    private ListView listView;
    private NetReceiver netReceiver;
    private List<Friut> list = new ArrayList<>();
    String[] strings = {
            "dd","sd","da","adada","dd","sd","da","adada"
    };
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.different_layout);

        listView = (ListView)findViewById(R.id.list);
        initList();
        MyArrayAdapter arrayAdapter = new MyArrayAdapter(MainActivity.this,R.layout.item_layout,list);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,strings);
        listView.setAdapter(arrayAdapter);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);


//        Intent intent = new Intent("com.dddd");
//        sendOrderedBroadcast(intent,null);
//        netReceiver = new NetReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(netReceiver,intentFilter);
//        setContentView(R.layout.layout_main);
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.dddd");

                localBroadcastManager.sendBroadcast(intent);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.dddd");
        bootReceiver = new BootReceiver();
        localBroadcastManager.registerReceiver(bootReceiver,intentFilter);
//        progressBar = (ProgressBar)findViewById(R.id.progress);
//        editText = (EditText)findViewById(R.id.edit);
//        imageView = (ImageView)findViewById(R.id.imag);
//        btn1.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(netReceiver);
        localBroadcastManager.unregisterReceiver(bootReceiver);
    }

    public void initList(){
        Friut a = new Friut("aa",R.mipmap.ic_launcher);
        list.add(a);
          Friut b = new Friut("aabb",R.mipmap.ic_launcher);
          list.add(b);
          Friut c = new Friut("aacc",R.mipmap.ic_launcher);
          list.add(c);
          Friut ad = new Friut("aadd",R.mipmap.ic_launcher);
          list.add(ad);
          Friut ae = new Friut("aaeee",R.mipmap.ic_launcher);
          list.add(ae);
      }
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.btn1:
//                tex = editText.getText().toString();
//                Toast.makeText(MainActivity.this,tex,Toast.LENGTH_SHORT).show();
             //   imageView.setImageResource(R.drawable.weclome);
//                if(progressBar.getVisibility()==View.GONE){
//                     progressBar.setVisibility(View.VISIBLE);
//                }else{
//                    progressBar.setVisibility(View.GONE);
//                }
//                progressBar.setProgress(pro);
//                pro+=10;
//                if(pro>=100){
//                    progressBar.setVisibility(View.GONE);
//                }
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("this is Dialog");
//                builder.setMessage("please choose one");
//                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
//                builder.setView(R.layout.proalert_layout);
//                }
//                builder.setNegativeButton("ok",new Dialog.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                     Toast.makeText(MainActivity.this,"yes",Toast.LENGTH_SHORT).show();
//                }
//            });
//                builder.setPositiveButton("no", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,"no",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//                builder.create().show();
//                break;
//        }
//    }
    public class NetReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
//        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if(networkInfo!=null&&networkInfo.isAvailable()){
//            Toast.makeText(context,"goood",Toast.LENGTH_SHORT).show();
//        }else{
//        Toast.makeText(context,"bad",Toast.LENGTH_SHORT).show();}
//    }
        Toast.makeText(context,"bad",Toast.LENGTH_SHORT).show();}
}
}
