package com.rht.myapp.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rht.myapp.R;
import com.rht.myapp.api.Api;
import com.rht.myapp.api.ApiConfig;
import com.rht.myapp.api.TtitCallback;
import com.rht.myapp.utils.StringUtil;

import java.util.HashMap;

public class loginActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPwd;
    private Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();//调用输入框的文本，并将前后空格去掉
                String pwd = etPwd.getText().toString().trim();
                login(account, pwd);
            }

            private void login(String account, String pwd) {
                if (StringUtil.isEmpty(account)) {
                    showToast("请输入账号");
                    return;
                }
                if (StringUtil.isEmpty(pwd)) {
                    showToast("请输入密码");
                    return;
                }
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("mobile", account);
                params.put("password", pwd);
                Api.config(ApiConfig.LOGIN, params).postRequest(new TtitCallback() {
                    @Override
                    public void onSuccess(final String res) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                showToast(res);//ui线程不能在子线程里启动只可以放在主线程中
//                            }
//                        });
                        showToastSync(res);
                    }

                    @Override
                    public void onFailure(Exception ex) {

                    }
                });
            }
        });
    }

//    private void login(String account, String pwd) {
//        if (StringUtil.isEmpty(account)){
//            showToast("请输入账号");
//            return;
//        }
//        if (StringUtil.isEmpty(pwd)){
//            showToast("请输入密码");
//            return;
//        }
    //第一步创建OKHttpClient
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//        Map m = new HashMap();
//        m.put("mobile", account);
//        m.put("password", pwd);
//        JSONObject jsonObject = new JSONObject(m);
//        String jsonStr = jsonObject.toString();
//        //请求体
//        RequestBody requestBodyJson =
//                RequestBody.create(MediaType.parse("application/json;charset=utf-8")
//                        , jsonStr);
//        //第三步创建Rquest
//        Request request = new Request.Builder()
//                .url(AppConfig.BASE_URL + "/app/login")
//                .addHeader("contentType", "application/json;charset=UTF-8")
//                .post(requestBodyJson)
//                .build();
//        //第四步创建call回调对象
//        final Call call = client.newCall(request);
//        //第五步发起请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("onFailure", e.getMessage());
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String result = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showToast(result);//ui线程不能在子线程里启动
//                    }
//                });
//            }
//        });
//    }

}
