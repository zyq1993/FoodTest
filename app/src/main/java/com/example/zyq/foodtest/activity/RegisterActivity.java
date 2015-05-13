package com.example.zyq.foodtest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zyq.foodtest.util.HttpCallbackListener;
import com.example.zyq.foodtest.R;
import com.example.zyq.foodtest.util.HttpUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYQ on 2015/4/2 0002.
 */

//注册类
public class RegisterActivity extends Activity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private EditText addressEdit;
    private EditText confirmpsw;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        confirmpsw = (EditText) findViewById(R.id.confirm_psw);
        addressEdit = (EditText) findViewById(R.id.address);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = accountEdit.getText().toString().trim();
                final String password1 = passwordEdit.getText().toString().trim();
                final String password2 = confirmpsw.getText().toString().trim();
                final String address = addressEdit.getText().toString().trim();
                if (account == "" || password1 == "" || password2 == "" || address == "") {
                    Toast.makeText(RegisterActivity.this, "输入内容不能为空，请重新输入", Toast.LENGTH_SHORT).show();
                    //return;//?必要么
                }
                //把数据递交给服务器
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", account));
                params.add(new BasicNameValuePair("password1", password1));
                params.add(new BasicNameValuePair("password2", password2));
                params.add(new BasicNameValuePair("address", address));
                String url = "/user/";
                HttpUtil.doPost(url, params, new HttpCallbackListener() {
                    @Override
                    public void onFinish(final String response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.equals("ok")) {//注册成功后，再返回登录界面
                                    Toast.makeText(RegisterActivity.this, "注册成功，马上返回登录界面", Toast.LENGTH_SHORT).show();
                                    RegisterActivity.actionStart(RegisterActivity.this, account, password1);
                                } else if (response.equals("duplicated")) {
                                    Toast.makeText(RegisterActivity.this, "用户名已注册，请重新输入", Toast.LENGTH_SHORT).show();
                                } else if (response.equals("mismatch")) {
                                    Toast.makeText(RegisterActivity.this, "两次密码不一样，请重新输入", Toast.LENGTH_SHORT).show();
                                } else if (response.equals("unknown")) {
                                    Toast.makeText(RegisterActivity.this, "未知错误，请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(RegisterActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private static void actionStart(Context context, String account, String password) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("account", account);
        intent.putExtra("password", password);
        context.startActivity(intent);
    }

}
