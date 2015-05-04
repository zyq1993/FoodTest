package com.example.zyq.foodtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYQ on 2015/4/15 0015.
 */
public class FoodsListActivity extends Activity {

    private List<Food> foodList = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_listview);
        initFood();
        ListView listView = (ListView) findViewById(R.id.foods_list);
        FoodAdapter adapter = new FoodAdapter(this, R.layout.food_item, foodList);
        listView.setAdapter(adapter);

    }

    private void initFood() {//把数据库中的数据放入foodList数组中
//数据库操作
    }
}
