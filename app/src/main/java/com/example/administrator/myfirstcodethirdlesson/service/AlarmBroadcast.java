package com.example.administrator.myfirstcodethirdlesson.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/1/11.
 */

public class AlarmBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context,LongRunService.class);
        context.startService(intent1);
    }
}
