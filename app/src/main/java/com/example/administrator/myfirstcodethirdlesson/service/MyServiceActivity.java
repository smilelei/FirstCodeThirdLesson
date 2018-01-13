package com.example.administrator.myfirstcodethirdlesson.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myfirstcodethirdlesson.R;

/**
 * Created by Administrator on 2018/1/5.
 */

public class MyServiceActivity extends Activity implements View.OnClickListener{
    private Button mBtnstart;
    private Button mBtnstop;
    private Button mBtnbind;
    private Button mBtnunbind;
    private Button mBtnIntentSer;
    private Button mBtnLongSer;

    private TextView textView;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MyServiceActivity.this,"c",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    textView.setText("dhasjdhajsdgajsd");
            }

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);
        mBtnstart = (Button)findViewById(R.id.btn_startservice);
        mBtnstop = (Button)findViewById(R.id.btn_stopservice);
        mBtnbind = (Button)findViewById(R.id.btn_bindservice);
        mBtnunbind = (Button)findViewById(R.id.btn_unbindservice);
        mBtnIntentSer = (Button)findViewById(R.id.btn_intentservice);
        mBtnLongSer = (Button)findViewById(R.id.btn_longservice);


        textView = (TextView)findViewById(R.id.txhandle);

        mBtnstart.setOnClickListener(this);
        mBtnstop.setOnClickListener(this);
        mBtnbind.setOnClickListener(this);
        mBtnunbind.setOnClickListener(this);
        mBtnIntentSer.setOnClickListener(this);
        mBtnLongSer.setOnClickListener(this);
        new MyAsyncTask(textView).execute();

    }

    public int ll(){
        return kk();
    }
    public int kk(){
        Toast.makeText(MyServiceActivity.this,"dd",Toast.LENGTH_LONG).show();
        return 1;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_startservice:
                Intent intent = new Intent(this,MyService.class);
                startService(intent);
                Log.e("@lei","mstart");
                ll();
                break;
            case R.id.btn_stopservice:
                Intent stopintent = new Intent(this,MyService.class);
                Log.e("@lei","mstop");
                stopService(stopintent);
                String ddd= "";
                break;
            case R.id.btn_bindservice:
                Intent bindintent = new Intent(this,MyService.class);
                bindService(bindintent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbindservice:
                unbindService(connection);
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
                break;
            case R.id.btn_intentservice:
                Intent intent1 = new Intent(this,MyHandleService.class);
                startService(intent1);
                break;
            case R.id.btn_longservice:
                Intent longIntent = new Intent(this,LongRunService.class);
                startService(longIntent);
                break;

        }
    }
}
