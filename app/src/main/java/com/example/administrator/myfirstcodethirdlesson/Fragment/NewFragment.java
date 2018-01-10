package com.example.administrator.myfirstcodethirdlesson.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myfirstcodethirdlesson.R;

/**
 * Created by Administrator on 2017/12/22.
 */

public class NewFragment extends Fragment{
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_fragment,container,false);
        return view;
    }
}
