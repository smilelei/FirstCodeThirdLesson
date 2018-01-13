package com.example.administrator.myfirstcodethirdlesson.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2018/1/11.
 */

public class LongRunService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("@leiLong","start");
            }
        }).start();
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Long currtime = SystemClock.elapsedRealtime();
        Intent intent1 = new Intent(this,AlarmBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent1,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,currtime+60*60,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
