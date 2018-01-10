package com.example.administrator.myfirstcodethirdlesson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/26.
 */

public class Boot2Receiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"dddddddddd",Toast.LENGTH_LONG).show();
        abortBroadcast();
    }
}
