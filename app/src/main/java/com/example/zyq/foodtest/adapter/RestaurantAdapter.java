package com.example.zyq.foodtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zyq.foodtest.R;
import com.example.zyq.foodtest.model.Restaurant;

import java.util.List;

/**
 * Created by ZYQ on 2015/4/11 0011.
 */

//餐馆ListView的Adapter

public class RestaurantAdapter extends ArrayAdapter {

    private int resourceId;

    @Override//当子项滚入屏幕时
    public View getView(int position, View convertView, ViewGroup parent) {
        Restaurant restaurant = (Restaurant) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
//            viewHolder.restaurantImage = (ImageView) view.findViewById(R.id.restaurant_image);
            viewHolder.restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
//        viewHolder.restaurantImage.setImageResource(Integer.valueOf(restaurant.getRestaurantImage()).intValue());//类型转换
        viewHolder.restaurantName.setText(restaurant.getRestaurantName());
        return view;
    }

    public RestaurantAdapter(Context context, int textViewResourceId, List<Restaurant> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    private class ViewHolder {
//        ImageView restaurantImage;
        TextView restaurantName;
    }
}
