package com.example.kwongyo.httpclientproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kwongyo on 2016-07-15.
 */
public class Activity2 extends AppCompatActivity {
    @Bind(R.id.inputId)
    EditText inputId;
    @Bind(R.id.isLogin)
    TextView isLogin;
    HttpRequest httpRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        ButterKnife.bind(this);
        isLogin.setText(CustomPreference.getInstance(this).getValue("isLogin", "false"));
    }
    @OnClick(R.id.loginBtn)
    public void loginBtn(View v) {
        final String id = inputId.getText().toString();
        final Map<String,String> data = new HashMap<String,String>();
        data.put("id", id);
        new Thread(new Runnable(){
            public void run(){
                httpRequest = HttpRequest.post("http://192.168.31.119:8089/HttpRequestServer/LoginExample").form(data);
                if(!httpRequest.ok() )
                    return;
                final String body = httpRequest.body();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isLogin.setText(body);
                        CustomPreference.getInstance(Activity2.this).put("isLogin", body);

                    }
                });
            }
        }).start();
    }
}
