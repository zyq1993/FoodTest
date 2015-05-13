package com.example.zyq.foodtest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyq.foodtest.R;
import com.example.zyq.foodtest.adapter.OrderAdapter;
import com.example.zyq.foodtest.model.Food;
import com.example.zyq.foodtest.util.HttpClient;
import com.example.zyq.foodtest.util.Post;

import java.util.List;

/**
 * Created by ZYQ on 2015/5/12 0012.
 */
public class OrderActivity extends Activity {

    private List<Food> orderList;
    private OrderAdapter adapter;
    private String userId;
    private String restaurantId;
    private ListView orderListView;
    private Button goBack;
    private Button confirmOrder;
    private TextView totalPrice;
    public float orderTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        goBack = (Button) findViewById(R.id.go_back2);
        confirmOrder = (Button) findViewById(R.id.confirm_order);
        orderListView = (ListView) findViewById(R.id.order_list);
        totalPrice = (TextView) findViewById(R.id.total_price);

        Log.d("response", "bundle4");
        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getString("userId");
        restaurantId = bundle.getString("restaurantId");
        orderList = (List<Food>) this.getIntent().getSerializableExtra("foodOrder");
        Log.d("response", "restaurantId: " + restaurantId + " userId: " + userId);

        adapter = new OrderAdapter(OrderActivity.this, R.layout.order_item, orderList);
        orderListView.setAdapter(adapter);
        float orderTotalPrice = 0;
        for (Food food : orderList) {
//            Log.d("response","fff: " + food.getFoodName()+food.getFoodNumber());
            int foodNumber = Integer.valueOf(food.getFoodNumber());
            float foodPrice = Float.parseFloat(food.getFoodPrice());
            orderTotalPrice += foodNumber * foodPrice;
        }
        Log.d("response", "orderTotalPrice: " + orderTotalPrice);
        totalPrice.setText("总计：￥" + orderTotalPrice);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] flag = {1};
                for (Food food : orderList) {
                    String foodId = food.getFoodId();
                    String amount = food.getFoodNumber();
                    new Post() {
                        @Override
                        protected synchronized void onPostExecute(String response) {
                            if (response.equals("lack")) {
                                Toast.makeText(OrderActivity.this, "菜已卖完", Toast.LENGTH_SHORT).show();
                                flag[0] = 0;
                                return;
                            } else if (response.equals("not-exist")) {
                                Toast.makeText(OrderActivity.this, "不存在此菜", Toast.LENGTH_SHORT).show();
                                flag[0] = 0;
                                return;
                            } else {
                                Log.d("response", "order_id: " + response);
                            }
                        }
                    }.execute("/order/", "user", userId, "restaurant", restaurantId, "food", foodId, "amount", amount);
                }

            }
        });
    }
}
