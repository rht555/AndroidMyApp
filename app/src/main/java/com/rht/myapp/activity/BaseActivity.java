package com.rht.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }
    public void showToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();//提示
    }
    public void navigateTo(Class cls){
        Intent in = new Intent(context,cls);//跳转
        startActivity(in);
    }
}
