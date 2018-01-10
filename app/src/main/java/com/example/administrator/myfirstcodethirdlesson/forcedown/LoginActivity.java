package com.example.administrator.myfirstcodethirdlesson.forcedown;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myfirstcodethirdlesson.R;


/**
 * Created by Administrator on 2017/12/26.
 */

public class LoginActivity extends Activity{
    private EditText etaccount;
    private EditText etpassword;
    private Button btnlogin;

    private String account;
    private String password;
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        //requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //setContentView(R.layout.loginlayout);
//        setContentView(R.layout.loginlayout);
//        init();
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loginlayout);
        init();
    }

    private void init(){
        etaccount = (EditText)findViewById(R.id.etaccount);
        etpassword = (EditText)findViewById(R.id.etpas);
        btnlogin = (Button)findViewById(R.id.btnlog);



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = etaccount.getText().toString();
                password = etpassword.getText().toString();
                if (account==null||password==null){
                    Toast.makeText(LoginActivity.this,"account or passward is null",Toast.LENGTH_LONG).show();
                }else{
                    if(account.equals("admin")&&password.equals("sss")){
                        Intent intent = new Intent(LoginActivity.this,ForceActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"account or passward is error",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}
