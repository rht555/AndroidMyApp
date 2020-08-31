package com.rht.myapp.api;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
    public static String requestUrl;
    public static HashMap<String,Object> mParams;
    public static  Api api = new Api();
    public static OkHttpClient client;
    public Api(){

    }
    public static Api config(String url, HashMap<String,Object> params){
         client = new OkHttpClient.Builder()
                .build();
        requestUrl = ApiConfig.BASE_URL + url;
        mParams = params;
        return api;
    }
    public void postRequest(final TtitCallback ttitCallback){
        JSONObject jsonObject = new JSONObject(mParams);
        String jsonStr = jsonObject.toString();
        //请求体
        RequestBody requestBodyJson =
                RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                        , jsonStr);
        //第三步创建Rquest
        Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader("contentType", "application/json;charset=UTF-8")
                .post(requestBodyJson)
                .build();
        //第四步创建call回调对象
        final Call call = client.newCall(request);
        //第五步发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
                ttitCallback.onFailure(e);//回调
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                ttitCallback.onSuccess(result);
            }
        });
    }
}
