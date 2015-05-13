package com.example.zyq.foodtest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zyq.foodtest.util.Get;
import com.example.zyq.foodtest.util.HttpCallbackListener;
import com.example.zyq.foodtest.R;
import com.example.zyq.foodtest.adapter.RestaurantAdapter;
import com.example.zyq.foodtest.model.Restaurant;
import com.example.zyq.foodtest.util.HttpClient;
import com.example.zyq.foodtest.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

//餐馆ListView的Activity

public class MainActivity extends Activity {

    private List<Restaurant> restaurantList = new ArrayList<Restaurant>();//Collections.synchronizedList()
//    public String[] idArray = new String[100];
    private RestaurantAdapter adapter;
    private ListView shopsListView;
    private String userId;
    private Button goIndividualCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        init();
//        initRestaurant();
        adapter = new RestaurantAdapter(MainActivity.this, R.layout.restaurant_item, restaurantList);
        shopsListView = (ListView) findViewById(R.id.shops_list);
        shopsListView.setAdapter(adapter);
        shopsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = restaurantList.get(position);
                String restaurantId = restaurant.getRestaurantId();
                Log.d("response","Main-restaurantId: " + restaurantId + " userId: " + userId);
                //然后进入具体餐馆内部的菜单
                MainActivity.actionStart(MainActivity.this, userId, restaurantId);
            }
        });
        goIndividualCenter = (Button) findViewById(R.id.go_individual_center);
        goIndividualCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, IndividualActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void initRestaurant() {
//        Log.d("response","init");
        restaurantList.clear();
        String urlId = "/restaurant/";
        HttpUtil.doGet(urlId, new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {
                Log.d("response", "urlid");
                String[] idArray = response.split("\\n");
                int i = 0;//
                for (String id : idArray) {
                    final Restaurant restaurant = new Restaurant();
                    restaurant.setRestaurantId(id);
                    final String urlName = "/restaurant/" + id + "/name/";
                    final int idInt = Integer.valueOf(id);//shan chu
                    HttpUtil.doGet(urlName, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            restaurant.setRestaurantName(response);
//                          Log.d("response","name: " + response);
                            Log.d("response", " && " + restaurant.getRestaurantName());
                        }

                        @Override
                        public void onError(Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "url2未知错误", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    final String urlImage = "/restaurant/" + id + "/image/";

                    final String urlAddress = "/restaurant/" + id + "/address/";
                    HttpUtil.doGet(urlAddress, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            restaurant.setRestaurantAddress(response);
//                            Log.d("response","Address: " + response);
                            Log.d("response", "Address && " + restaurant.getRestaurantAddress());
                        }

                        @Override
                        public void onError(Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "url4未知错误", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    restaurantList.add(restaurant);
                    Log.d("response", "id: " + restaurantList.get(i).getRestaurantId());
                    Log.d("response", "name: " + restaurantList.get(i).getRestaurantName());
                    Log.d("response", "Address: " + restaurantList.get(i).getRestaurantAddress());
                    i++;
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "url1未知错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void init() {
        Log.d("response","init");
        restaurantList.clear();
        String urlId = "/restaurant/";
/*        String urlName;
        String urlAddress;
        String shop_name;
        String shop_address;*/

/*        String shop_id = new HttpClient().get(urlId);
        String[] idArray = shop_id.split("\\n");
        for (String id : idArray) {
            Restaurant restaurant = new Restaurant();
            urlName = "/restaurant/" + id + "/name/";
            shop_name = new HttpClient().get(urlName);
            urlAddress = "/restaurant/" + id + "/address/";
            shop_address = new HttpClient().get(urlAddress);
            restaurant.setRestaurantId(id);
            restaurant.setRestaurantName(shop_name);
            restaurant.setRestaurantAddress(shop_address);
            restaurantList.add(restaurant);
        }*/
        new Get() {
            @Override
            protected synchronized void onPostExecute(String shop_id) {
                String[] idArray = shop_id.split("\\n");
                for (String id : idArray) {
                    final Restaurant restaurant = new Restaurant();
                    restaurant.setRestaurantId(id);
                    String urlName = "/restaurant/" + id + "/name/";
                    new Get() {
                        @Override
                        protected synchronized void onPostExecute(String shop_name) {
                            restaurant.setRestaurantName(shop_name);
                        }
                    }.execute(urlName);
                    String urlAddress = "/restaurant/" + id + "/address/";
                    new Get() {
                        @Override
                        protected synchronized void onPostExecute(String shop_address) {
                            restaurant.setRestaurantAddress(shop_address);
                        }
                    }.execute(urlAddress);
                    restaurantList.add(restaurant);
                }
            }
        }.execute(urlId);
    }

    private static void actionStart(Context context, String userId, String restaurantId) {
        Intent intent = new Intent(context, FoodActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("restaurantId", restaurantId);
        context.startActivity(intent);
    }
}
