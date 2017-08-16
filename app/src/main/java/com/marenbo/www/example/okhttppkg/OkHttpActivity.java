package com.marenbo.www.example.okhttppkg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.marenbo.www.example.R;
import com.marenbo.www.example.okhttppkg.bean.VersionResp;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    @BindView(R.id.btn_get)
    Button btnGet;

    private String baseUrl = "http://bjxs.framedia.cn:9000/kkfmobile/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

        initListener();

    }

    private void initListener() {

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                okhttpGet();

                okHttpPost();
            }
        });
    }

    private void okHttpPost() {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("username", "18868944579")
                .add("password", "w123456")
                .add("app", "kfpatrol")
                .add("app_key", "kfpatrol_app_android")
                .build();

        Request request = new Request.Builder()
                .url(baseUrl+"login")
                .post(requestBody)
                .addHeader("app_token", "80cec20702ddef6f760aad2724539b64")
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("OkHttpActivity", "onFailure");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();

                Log.d("OkHttpActivity", "onResponse = " + json);
            }
        });


    }

    private void okhttpGet() {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://news-at.zhihu.com/api/4/version/android/2.3.0")
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("OkHttpActivity", "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("OkHttpActivity", "onResponse");

//                Log.d("OkHttpActivity", response.body().string());

                Gson gson = new Gson();

                String json = response.body().string();

                VersionResp resp = gson.fromJson(json, VersionResp.class);

                Log.d("OkHttpActivity", resp.toString());

            }
        });

    }

}
