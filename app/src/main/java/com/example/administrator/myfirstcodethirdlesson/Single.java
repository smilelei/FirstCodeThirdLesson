package com.example.administrator.myfirstcodethirdlesson;

/**
 * Created by Administrator on 2017/12/24.
 */

public class Single {
    private Single(){}
    private static Single single = null;
    public static synchronized Single getSingle(){
        if(single==null){
            single = new Single();
        }
        return single;
    }
}
