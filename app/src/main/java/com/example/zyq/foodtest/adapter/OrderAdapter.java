package com.example.zyq.foodtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zyq.foodtest.R;
import com.example.zyq.foodtest.model.Food;

import java.util.List;

/**
 * Created by ZYQ on 2015/5/12 0012.
 */
public class OrderAdapter extends ArrayAdapter<Food> {

    private int resourceId;

    public OrderAdapter(Context context, int textViewResourceId, List<Food> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override//当子项滚入屏幕时
    public View getView(int position, View convertView, ViewGroup parent) {
        Food food = (Food) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.orderFoodName = (TextView) view.findViewById(R.id.order_food_name);
            viewHolder.orderFoodNumber = (TextView) view.findViewById(R.id.order_food_number);
            viewHolder.orderFoodPrice = (TextView) view.findViewById(R.id.order_food_price);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        int foodNumber = Integer.valueOf(food.getFoodNumber());
        float foodPrice = Float.parseFloat(food.getFoodPrice());
        foodPrice = foodNumber * foodPrice;
        viewHolder.orderFoodName.setText(food.getFoodName());
        viewHolder.orderFoodNumber.setText(food.getFoodNumber());
        viewHolder.orderFoodPrice.setText(String.valueOf(foodPrice));
        return view;
    }

    private class ViewHolder {
        TextView orderFoodName;
        TextView orderFoodNumber;
        TextView orderFoodPrice;
    }
}
