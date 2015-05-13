package com.example.zyq.foodtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyq.foodtest.R;
import com.example.zyq.foodtest.util.Get;
import com.example.zyq.foodtest.util.HttpClient;
import com.example.zyq.foodtest.util.Post;

/**
 * Created by ZYQ on 2015/5/13 0013.
 */
public class IndividualActivity extends Activity {

    private TextView individualPhone;
    private TextView individualAddress;
    private TextView giveScore;
    private Button phoneButton;
    private Button addressButton;
    private Button admitAddress;
    private Button admitPhone;
    private EditText revisePhone;
    private EditText reviseAddress;
    private EditText password1;
    private EditText password2;
    private LinearLayout phoneFrame;
    private LinearLayout addressFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_center);
        individualPhone = (TextView) findViewById(R.id.individual_phone);
        individualAddress = (TextView) findViewById(R.id.individual_address);
        giveScore = (TextView) findViewById(R.id.give_score);
        phoneButton = (Button) findViewById(R.id.phone_button);
        addressButton = (Button) findViewById(R.id.address_button);
        admitPhone = (Button) findViewById(R.id.admit_phone);
        admitAddress = (Button) findViewById(R.id.admit_address);
        revisePhone = (EditText) findViewById(R.id.revise_phone);
        reviseAddress = (EditText) findViewById(R.id.revise_address);
        password1 = (EditText) findViewById(R.id.admit_password1);
        password2 = (EditText) findViewById(R.id.admit_password2);
        phoneFrame = (LinearLayout) findViewById(R.id.phone_frame);
        addressFrame = (LinearLayout) findViewById(R.id.address_frame);

        new Get() {
            @Override
            protected synchronized void onPostExecute(String phone) {
                individualPhone.setText(phone);
            }
        }.execute("/user/username/");
        new Get() {
            @Override
            protected synchronized void onPostExecute(String address) {
                individualAddress.setText(address);
            }
        }.execute("/user/address/");

        /*String phone = new HttpClient().get("/user/username/");
        individualPhone.setText(phone);
        String address = new HttpClient().get("/user/address/");
        individualAddress.setText(address);*/

        String newAddress = reviseAddress.getText().toString().trim();

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneFrame.getVisibility() == View.GONE) {
                    phoneFrame.setVisibility(View.VISIBLE);
                } else if (phoneFrame.getVisibility() == View.VISIBLE) {
                    phoneFrame.setVisibility(View.GONE);
                }
            }
        });
        admitPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newPhone = revisePhone.getText().toString().trim();
                String psw = password1.getText().toString().trim();
//                String response = new HttpClient().post("/user/username/", "username", newPhone, "password", psw);
                new Post() {
                    @Override
                    protected synchronized void onPostExecute(String response) {
                        if (response.equals("ok")) {
                            Toast.makeText(IndividualActivity.this, "电话号码修改成功", Toast.LENGTH_SHORT).show();
                            individualPhone.setText(newPhone);
                            phoneFrame.setVisibility(View.GONE);
                        } else if (response.equals("unaccessible")) {
                            Toast.makeText(IndividualActivity.this, "需登录后才能操作", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("mismatch")) {
                            Toast.makeText(IndividualActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("unknown")) {
                            Toast.makeText(IndividualActivity.this, "未知错误", Toast.LENGTH_SHORT).show();

                        }
                    }
                }.execute("/user/username/", "username", newPhone, "password", psw);

                phoneFrame.setVisibility(View.GONE);
            }
        });
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressFrame.getVisibility() == View.GONE) {
                    addressFrame.setVisibility(View.VISIBLE);
                } else if (addressFrame.getVisibility() == View.VISIBLE) {
                    addressFrame.setVisibility(View.GONE);
                }
            }
        });
        admitAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newAddress = reviseAddress.getText().toString().trim();
                String psw = password2.getText().toString().trim();
//                String response = new HttpClient().post("/user/address/", "address", newAddress, "password", psw);
                new Post() {
                    @Override
                    protected synchronized void onPostExecute(String response) {
                        if (response.equals("ok")) {
                            Toast.makeText(IndividualActivity.this, "用户地址修改成功", Toast.LENGTH_SHORT).show();
                            individualAddress.setText(newAddress);
                            addressFrame.setVisibility(View.GONE);
                        } else if (response.equals("unaccessible")) {
                            Toast.makeText(IndividualActivity.this, "需登录后才能操作", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("mismatch")) {
                            Toast.makeText(IndividualActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("unknown")) {
                            Toast.makeText(IndividualActivity.this, "未知错误", Toast.LENGTH_SHORT).show();

                        }
                    }
                }.execute("/user/address/", "address", newAddress, "password", psw);
            }
        });
        giveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IndividualActivity.this, "order", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
