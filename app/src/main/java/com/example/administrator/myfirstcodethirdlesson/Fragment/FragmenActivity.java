package com.example.administrator.myfirstcodethirdlesson.Fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.administrator.myfirstcodethirdlesson.R;

/**
 * Created by Administrator on 2017/12/21.
 */

public class FragmenActivity extends Activity{
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_act);
        button = (Button)findViewById(R.id.right_fragment_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewFragment newFragment = new NewFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.right_layout,newFragment);
                transaction.commit();
            }
        });
    }
}
