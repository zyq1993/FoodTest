package com.example.zyq.foodtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ZYQ on 2015/4/2 0002.
 */

//注册类
public class RegisterActivity extends Activity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private EditText confirmpsw;
    private Button register;
    private Button returnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        confirmpsw = (EditText) findViewById(R.id.confirm_psw);
        register = (Button) findViewById(R.id.register);
        returnLogin = (Button) findViewById(R.id.return_login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                if (account == "" || password == "") {
                    Toast.makeText(RegisterActivity.this, "账号或密码为空，请重新输入", Toast.LENGTH_SHORT).show();
                    //return;//?必要么
                }
                String pswTwice = confirmpsw.getText().toString().trim();
                if (password.equals(pswTwice)) {
                    Toast.makeText(RegisterActivity.this, "注册成功，返回登录界面", Toast.LENGTH_SHORT).show();
                    //注册成功后，把账号密码存入数据库或者服务器，再返回登录界面
                    //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.actionStart(RegisterActivity.this, account, password);
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码不一样，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void actionStart(Context context, String account, String password) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("account", account);
        intent.putExtra("password", password);
        //context.startActivityForResult(intent,1);//错误
        context.startActivity(intent);
    }

}
