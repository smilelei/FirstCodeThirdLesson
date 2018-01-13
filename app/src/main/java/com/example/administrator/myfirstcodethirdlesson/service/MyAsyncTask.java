package com.example.administrator.myfirstcodethirdlesson.service;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/1/12.
 */

public class MyAsyncTask extends AsyncTask<Void,Integer,Integer>{
    private TextView textView;
    public MyAsyncTask(TextView textView){
        this.textView = textView;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        int i = 0;
        while (i<100){
            publishProgress(i);
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        textView.setText(values[0]+"---------");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        textView.setText(integer+"all in");
        super.onPostExecute(integer);
    }
}
