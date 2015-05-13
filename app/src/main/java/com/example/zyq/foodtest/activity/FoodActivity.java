package com.example.zyq.foodtest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zyq.foodtest.R;
import com.example.zyq.foodtest.adapter.FoodAdapter;
import com.example.zyq.foodtest.model.Food;
import com.example.zyq.foodtest.util.Get;
import com.example.zyq.foodtest.util.HttpClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYQ on 2015/5/11 0011.
 */
public class FoodActivity extends Activity {
    private List<Food> foodList = new ArrayList<Food>();
    private List<Food> orderList = new ArrayList<Food>();
    private String userId;
    private String restaurantId;
    private FoodAdapter adapter;
    private ListView foodListView;
    private Button goBack;
    private Button confirmFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_listview);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        restaurantId = intent.getStringExtra("restaurantId");
        Log.d("response", "food:restaurantId: " + restaurantId + " userId: " + userId);

        initFood();
        adapter = new FoodAdapter(FoodActivity.this, R.layout.foods_item, foodList);
        foodListView = (ListView) findViewById(R.id.foods_list);
        foodListView.setAdapter(adapter);
        goBack = (Button) findViewById(R.id.go_back1);
        confirmFoods = (Button) findViewById(R.id.confirm_foods);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirmFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderList.clear();
                for (Food food : foodList) {
                    if (! food.getFoodNumber().equals("0")) {
                        Food orderFood = food;
                        Log.d("response","food_cnt: " + orderFood.getFoodNumber());//
                        orderList.add(orderFood);
                    }
                }
                Log.d("response","dingdan");
                for (Food f : orderList) {
                    Log.d("response","order food id: " + f.getFoodId());
                }
                FoodActivity.actionStart(FoodActivity.this, userId, restaurantId, orderList);
            }
        });
    }

    private void initFood() {
        Log.d("response", "initFood: ");
        foodList.clear();
        String urlFoodId= "/restaurant/" + restaurantId + "/foods/";

        new Get() {
            @Override
            protected synchronized void onPostExecute(String food_id) {
                String[] foodIdArray = food_id.split("\\n");
                String urlName;
                String urlPrice;
                String food_name;
                String food_price;
                for (String foodId : foodIdArray) {
                    final Food food = new Food();
                    food.setFoodId(foodId);
                    urlName = "/food/" + foodId + "/name/";
                    new Get() {
                        @Override
                        protected synchronized void onPostExecute(String food_name) {
                            food.setFoodName(food_name);
                        }
                    }.execute(urlName);
                    urlPrice = "/food/" + foodId + "/price/";
                    new Get() {
                        @Override
                        protected synchronized void onPostExecute(String food_price) {
                            food.setFoodPrice(food_price);
                        }
                    }.execute(urlPrice);
                    foodList.add(food);
                }
                Log.d("response","over2");
            }
        }.execute(urlFoodId);

//        String food_id = new HttpClient().get(urlFoodId);
//        Log.d("response", "food_id: " + food_id);

        /*String[] foodIdArray = food_id.split("\\n");
        String urlName;
        String urlPrice;
        String food_name;
        String food_price;
        for (String foodId : foodIdArray) {
//            Log.d("response", "for: ");

            urlName = "/food/" + foodId + "/name/";
            food_name = new HttpClient().get(urlName);
            urlPrice = "/food/" + foodId + "/price/";
            food_price = new HttpClient().get(urlPrice);
            Food food = new Food();
            food.setFoodId(foodId);
            food.setFoodName(food_name);
            food.setFoodPrice(food_price);
            foodList.add(food);
        }
        Log.d("response","over2");*/
//        adapter.notifyDataSetChanged();//

        /*for (Food f : foodList) {
            Log.d("response","id: " + f.getFoodId());
            Log.d("response","name: " + f.getFoodName());
            Log.d("response","price: " + f.getFoodPrice());
        }
        Log.d("response","over3");*/
    }

    private static void actionStart(Context context, String user_id, String restaurant_id, List<Food> ordersList) {
        Intent intent = new Intent(context, OrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("foodOrder", (Serializable) ordersList);
        bundle.putString("userId", user_id);
        bundle.putString("restaurantId", restaurant_id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
