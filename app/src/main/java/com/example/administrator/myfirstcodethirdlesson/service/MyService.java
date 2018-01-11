package com.example.administrator.myfirstcodethirdlesson.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.myfirstcodethirdlesson.R;

/**
 * Created by Administrator on 2018/1/5.
 */

public class MyService extends Service{
    private MyBinder binder = new MyBinder();
    class MyBinder extends Binder{
        public void start(){
            Log.e("@lei","binsat");
        }
        public void destory(){
            Log.e("@lei","destory");
        }

    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder.start();

        Intent intent = new Intent(this,MyServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        Notification.Builder builder = new Notification.Builder(this);
        Notification notification = builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setWhen(System.currentTimeMillis()).setContentTitle("Notitle").setContentText("is coming").build();
        startForeground(1,notification);

        Log.e("@lei","mcreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("@lei","mcommmand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("@lei","mdestory");
        binder.destory();
    }
}
