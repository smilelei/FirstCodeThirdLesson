package com.example.administrator.myfirstcodethirdlesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class MyArrayAdapter extends ArrayAdapter<Friut> {
    private int resourceId;
    public MyArrayAdapter(Context context, int textViewId, List<Friut> list){
        super(context,textViewId,list);
        this.resourceId = textViewId;
    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Friut friut = getItem(position);
        View view;
        viewHolder holder;
        if(convertView==null){
            holder = new viewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            holder.imageView = (ImageView)view.findViewById(R.id.imag);
            holder.textView = (TextView)view.findViewById(R.id.tx);
            view.setTag(holder);
        }else
        {
            view = convertView;
            holder = (viewHolder) view.getTag();
        }
        holder.textView.setText(friut.getName());
        holder.imageView.setImageResource(friut.getImageId());
        return  view;
    }
    class viewHolder{
        public TextView textView;
        public ImageView imageView;
    }
}
