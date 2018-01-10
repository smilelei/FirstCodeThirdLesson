package com.example.administrator.myfirstcodethirdlesson.forcedown;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class BaseActivity extends Activity{
    static List<Activity> activities = new ArrayList<Activity>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        activities.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }
    public static void finishAll(){
        if(activities!=null){
        for(Activity activity : activities){
            activity.finish();
        }
        }
    }
}
