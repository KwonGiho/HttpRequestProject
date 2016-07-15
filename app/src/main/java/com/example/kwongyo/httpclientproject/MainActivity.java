package com.example.kwongyo.httpclientproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.textView)
    TextView textView;

    HttpRequest httpRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.getBtn)
    public void getClick(View v) {
        new Thread(new Runnable(){
            public void run(){
                String url = "http://192.168.31.119:8089/HttpRequestServer/example";
                httpRequest = HttpRequest.get(url);
                final String body = httpRequest.body();
                boolean isSuccess = httpRequest.ok();
                if(!isSuccess)
                    return;
                final int code = httpRequest.code();


                runOnUiThread(new Runnable(){
                    public void run(){
                        textView.setText("getresultCode - "+code+"\n body - "+body);
                    }
                });
            }
        }).start();
    }
    @OnClick(R.id.postBtn)
    public void postClick(View v) {
        new Thread(new Runnable(){
            public void run(){
                String url = "http://192.168.31.119:8089/HttpRequestServer/example";

                httpRequest = HttpRequest.post(url);
                boolean isSuccess = httpRequest.ok();
                if(!isSuccess)
                    return;
                final int code = httpRequest.code();
                final String body = httpRequest.body();

                runOnUiThread(new Runnable(){
                    public void run(){
                        textView.setText("post resultCode - "+code+"\n body - "+body);
                    }
                });
            }
        }).start();
    }
    @OnClick(R.id.nextActivity)
    public void nextActivityClick(View v) {
        startActivity(new Intent(getApplicationContext(),Activity2.class));
    }
}
