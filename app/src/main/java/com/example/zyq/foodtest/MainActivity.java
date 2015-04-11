package com.example.zyq.foodtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//餐馆ListView的Activity

public class MainActivity extends Activity {

    private List<Restaurant> restaurantList = new ArrayList<Restaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRestaurant();
        RestaurantAdapter adapter = new RestaurantAdapter(MainActivity.this, R.layout.restaurant_item, restaurantList);
        ListView shopsListView = (ListView) findViewById(R.id.shops_list);
        shopsListView.setAdapter(adapter);
        shopsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = restaurantList.get(position);
                //然后进入具体餐馆内部的菜单
            }
        });
    }

    private void initRestaurant() {//把数据库中的数据放入restaurantList数组中

    }

}
