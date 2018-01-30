package com.example.administrator.myfirstcodethirdlesson.RX;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myfirstcodethirdlesson.R;
import com.example.administrator.myfirstcodethirdlesson.SixLesson.db.MyDbHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/1/17.
 */

public class RXActivity extends Activity{
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        textView = findViewById(R.id.tx);
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for(int i = 1;;i++){
                    emitter.onNext(i);
                }

            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
               emitter.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });


        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                Log.e("TAG","a");
//                e.onNext("a");
//                Log.e("TAG","b");
//                e.onNext("b");
//                Log.e("TAG","c");
//                e.onNext("c");
//                Log.e("TAG","d");
//                e.onNext("d");
//                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable<Integer> observable3 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Log.e("TAG",1+"");
//                e.onNext(1);
//                Log.e("TAG",2+"");
//                e.onNext(2);
//                Log.e("TAG",3+"");
//                e.onNext(3);
//                Log.e("TAG",4+"");
//                e.onNext(4);
//                e.onComplete();
            }
        }).subscribeOn(Schedulers.computation());



//        Observable.create(new ObservableOnSubscribe<Integer>() {
//        @Override
//        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//
//            e.onNext(1);
//            Log.e("TAG","next"+1);
//            e.onNext(2);
//            Log.e("TAG","next"+2);
//
//            e.onNext(3);
//            Log.e("TAG","next"+3);
//            e.onComplete();
//            Log.e("TAG","next complete");
//            e.onNext(4);
//            Log.e("TAG","next"+4);
//
//
//
//        }
//    }).subscribe(new Observer<Integer>() {
//        private  Disposable mDisposable;
//        int i;
//        @Override
//        public void onSubscribe(Disposable d) {
//            Log.e("TAG","SUBSCRIBE");
//            mDisposable = d;
//        }
//
//        @Override
//        public void onNext(Integer value) {
//            Log.e("TAG",value+"");
//            i++;
//            if(i==2){
//                Log.e("TAG","dispose");
//                mDisposable.dispose();
//                Log.e("TAG",mDisposable.isDisposed()+"");
//            }
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            Log.e("TAG","ERROR");
//        }
//
//        @Override
//        public void onComplete() {
//            Log.e("TAG","COMPLE");
//        }
//    });
//     Observable.create(new ObservableOnSubscribe<String>() {
//         @Override
//         public void subscribe(ObservableEmitter<String> e) {
//             try{
//             URL url = new URL("https://www.baidu.com");
//             HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//             urlConnection.setRequestMethod("GET");
//             urlConnection.setReadTimeout(4000);
//             urlConnection.setConnectTimeout(4000);
//             InputStream inputStream = urlConnection.getInputStream();
//             StringBuilder builder = new StringBuilder();
//             String line = "";
//             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//             while((line=bufferedReader.readLine())!=null){
//                 builder.append(line);
//             }
//             e.onNext(builder.toString());}
//             catch (IOException ee){
//                 ee.printStackTrace();
//             }
//             e.onNext("ee");
//             e.onNext("33");
//             e.onNext("32");
//
//         }
//     }).subscribeOn(Schedulers.io()).flatMap(new Function<String, ObservableSource<Integer>>() {
//         @Override
//         public ObservableSource<Integer> apply(String s) throws Exception {
//             final List<Integer> list = new ArrayList<Integer>();
//             for(int i =0;i<3;i++){
//
//                 list.add(i);}
//             return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
//         }
//     }).observeOn(AndroidSchedulers.mainThread()).
//             subscribe(new Consumer<Integer>() {
//         @Override
//         public void accept(Integer cursor) throws Exception {
//                textView.setText(cursor+"");
//         }
//     });
    }

}
