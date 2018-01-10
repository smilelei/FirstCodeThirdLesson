package com.example.administrator.myfirstcodethirdlesson.forcedown;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;


/**
 * Created by Administrator on 2017/12/26.
 */

public class ForcedownBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(final Context context, final Intent intent) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("infor").setMessage("your account has logined in other place,you are Forced down ");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseActivity.finishAll();
                Intent intent1 = new Intent(context,LoginActivity.class);
                context.startActivity(intent1);

            }
        }).create().show();

    }
}
