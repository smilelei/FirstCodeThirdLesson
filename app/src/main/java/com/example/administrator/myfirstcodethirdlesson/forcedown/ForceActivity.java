package com.example.administrator.myfirstcodethirdlesson.forcedown;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myfirstcodethirdlesson.R;

/**
 * Created by Administrator on 2017/12/26.
 */

public class ForceActivity extends Activity {
    private Button btnsend;
    private ForcedownBroadcastReceiver forcedownBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senbroadcast_layout);
        btnsend = (Button)findViewById(R.id.btnsend);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.administrator.forcedown");
                sendBroadcast(intent);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.administrator.forcedown");
        forcedownBroadcastReceiver = new ForcedownBroadcastReceiver();
        registerReceiver(forcedownBroadcastReceiver,intentFilter);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(forcedownBroadcastReceiver);
    }
}
