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

public class RegisterActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPwd;
    private Button btnRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {//点击事件
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();//调用输入框的文本，并将前后空格去掉
                String pwd = etPwd.getText().toString().trim();
                register(account,pwd);
            }
        });

    }
    private void register(String account, String pwd) {
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
        Api.config(ApiConfig.REGISTER, params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(res);//ui线程不能在子线程里启动
                    }
                });
            }

            @Override
            public void onFailure(Exception ex) {
                
            }
        });
    }
}
