package com.example.zyq.foodtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ZYQ on 2015/4/2 0002.
 */

//登录类

public class LoginActivity extends Activity {

    private EditText accountEdit;
    private EditText passordEdit;
    private Button login;
    private Button wantRegister;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        accountEdit = (EditText) findViewById(R.id.account);
        passordEdit = (EditText) findViewById(R.id.password);
        //注册之后，在登录界面，自动填入注册好的账号密码
        Intent intent = getIntent();
        accountEdit.setText(intent.getStringExtra("account"));
        passordEdit.setText(intent.getStringExtra("password"));

        login = (Button) findViewById(R.id.login);
        wantRegister = (Button) findViewById(R.id.want_register);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {//若是记住密码，则自动填写账号密码
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passordEdit.getText().toString();
                //核对账号密码是否匹配，这里存在本地数据库还是服务器呢？现有处理是本地
                if (account.equals("admin") && password.equals("123456")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.commit();
                    //登录成功来到点菜界面
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //转入注册界面
        wantRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });

    }
}
