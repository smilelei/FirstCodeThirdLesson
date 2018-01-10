package com.example.administrator.myfirstcodethirdlesson.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2018/1/10.
 */

public class MyHandleService extends IntentService{

    public MyHandleService() {
        super("smile");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("@lei",Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("@lei",Thread.currentThread().getName()+"destory");

    }
}
