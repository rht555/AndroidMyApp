package com.rht.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rht.myapp.activity.BaseActivity;
import com.rht.myapp.activity.RegisterActivity;
import com.rht.myapp.activity.loginActivity;

public class MainActivity extends BaseActivity {
    private Button btnLogin;
    private Button btnRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_login);//绑定
        btnLogin.setOnClickListener(new View.OnClickListener() {//单击事件
            @Override
            public void onClick(View v) {
                navigateTo(loginActivity.class);
            }
        });
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(RegisterActivity.class);
            }
        });
    }
}
