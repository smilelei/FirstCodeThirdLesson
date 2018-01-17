package com.example.administrator.myfirstcodethirdlesson.Http;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myfirstcodethirdlesson.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;


/**
 * Created by Administrator on 2018/1/13.
 */

public class WebviewActivty extends Activity {
    private WebView mWebView;
    private Button mBtnSend;
    private TextView mTextHtml;
    //private final String TAG = "https://www.baidu.com";
    private final String TAG = "http://10.0.3.15:8080/web/lei.xml ";
   Gson gson = new Gson();

    private List<AppBean> appBeans = new ArrayList<>();
    public void setText(final String text){
        if(text!=null){
            appBeans = gson.fromJson(text,new TypeToken<List<AppBean>>(){}.getType());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                    try {
                        XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
                        MyDefaultHandler defaultHandler = new MyDefaultHandler();
                        xmlReader.setContentHandler(defaultHandler);
                        xmlReader.parse(new InputSource(new StringReader(text)));
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                AppBean bean = null;
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser pullParser = factory.newPullParser();
                pullParser.setInput(new StringReader(text));
                int eventType = pullParser.getEventType();
                while(eventType!=XmlPullParser.END_DOCUMENT){
                   String node = pullParser.getName();
                   switch (eventType){
                       case XmlPullParser.START_TAG:
                           if(node.equals("app")){
                                bean = new AppBean();
                           }
                           if(node.equals("id")){
                               assert bean != null;
                               bean.setId(Integer.parseInt(pullParser.nextText()));
                           }
                           if(node.equals("name")){
                               assert bean != null;
                               bean.setApp(pullParser.nextText());
                           }
                           if(node.equals("version")){
                               assert bean != null;
                               bean.setVersion(Integer.parseInt(pullParser.nextText()));
                           }
                           break;
                       case XmlPullParser.END_TAG:
                           if (node.equals("app")) {
                               appBeans.add(bean);
                           }
                           break;
                   }
                }
             } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }    }
            }).start();

            mTextHtml.setText(text);
        }
    }
    static class MyHandler extends Handler{
        WeakReference<WebviewActivty> weakReference;
        public MyHandler(WebviewActivty activity){
                weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            WebviewActivty activity = weakReference.get();
            if (activity!=null){
                activity.setText(msg.obj.toString());
            }
        }
    }
    interface liste{
        void ok(String re);
        void error(Exception e);
        ;
    }

    class ii implements Serializable{
        private String id;
        private int ver;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getVer() {
            return ver;
        }

        public void setVer(int ver) {
            this.ver = ver;
        }
    }
    class MyDefaultHandler extends DefaultHandler{
        private String nodeName;
        private StringBuilder id;
        private StringBuilder name;
        private StringBuilder version;
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            nodeName = localName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if("app".equals(localName)){
                id.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            if ("id".equals(nodeName)){
                id.append(ch,start,length);
            }else if("name".equals(nodeName)){
                name.append(ch,start,length);
            }else if("version".equals(nodeName)){
                version.append(ch, start, length);
            }
        }
    }
    private Handler mHandler;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.web_layout);
        mWebView = findViewById(R.id.web_view);
        mBtnSend = findViewById(R.id.btn_send);
        mTextHtml = findViewById(R.id.txt_html);
        mHandler = new MyHandler(this);
        Intent intent1 = new Intent();
        ii i = new ii();
        intent1.putExtra("ii",i);

        try {
            URL url = new URL(TAG);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(TAG, new liste() {
                    @Override
                    public void ok(String re) {
                        Message message = new Message();
                        message.obj = re;
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void error(Exception e) {
                        Toast.makeText(WebviewActivty.this,"rrrr",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(TAG);
                return true;
            }
        });
        mWebView.loadUrl(TAG);
    }
    public void sendRequest(final String urlin, final liste liste){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //URL url = new URL(TAG);
                    URL url = new URL(urlin);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                    httpURLConnection.setConnectTimeout(2000);
                    httpURLConnection.setReadTimeout(2000);
                    httpURLConnection.setRequestMethod("GET");
//                    httpURLConnection.setRequestMethod("POST");
//                    DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
//                    String utf = URLEncoder.encode("tn=98012078_5_dg&ch=1","utf-8");
//                    outputStream.writeBytes(utf);

                    InputStream inputStream = httpURLConnection.getInputStream();
                    String re ="";
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    while((re=bufferedReader.readLine())!=null){
                        stringBuilder.append(re);
                    }
                    if(liste!=null){
                    liste.ok(stringBuilder.toString());}
//                    Message message = new Message();
//                    message.obj = stringBuilder;
//                    mHandler.sendMessage(message);

                } catch (Exception e){
                    if (liste!=null){
                        liste.error(e);
                    }
                }
            }
        }).start();

    }
}
