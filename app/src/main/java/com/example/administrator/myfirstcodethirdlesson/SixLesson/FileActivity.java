package com.example.administrator.myfirstcodethirdlesson.SixLesson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myfirstcodethirdlesson.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2017/12/27.
 */

public class FileActivity extends Activity{
    private FileOutputStream outputStream = null;
    private BufferedWriter bufferedWriter = null;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filelayout);
        editText = (EditText)findViewById(R.id.edit);
        button = (Button)findViewById(R.id.btn1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save("sss");
            }
        });

        SharedPreferences.Editor editor = getSharedPreferences("le",MODE_PRIVATE).edit();
        editor.putInt("first",1);
        editor.commit();
        SharedPreferences sharedPreferences = getSharedPreferences("le",MODE_PRIVATE);
        int i = sharedPreferences.getInt("first",0);

    }



    private String fronFile(){
        String put = "";
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;
       try {
           inputStream = openFileInput("lei");
           reader = new BufferedReader(new InputStreamReader(inputStream));
           while((put=reader.readLine())!=null){
               buffer.append(put);
           }
       }catch (IOException e){
           e.printStackTrace();
       }finally {
           if(reader!=null){
               try {               reader.close();
               }catch (IOException e){
                   e.printStackTrace();
               }

           }
       }
      return buffer.toString();
    }
    private void save(String out){
        try {
            outputStream = openFileOutput("lei.txt",Context.MODE_APPEND);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("leicddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
