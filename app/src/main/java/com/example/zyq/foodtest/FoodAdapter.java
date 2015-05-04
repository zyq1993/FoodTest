package com.example.zyq.foodtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZYQ on 2015/4/15 0015.
 */
public class FoodAdapter extends ArrayAdapter<Food>{

    private int resourceId;

    public FoodAdapter(Context context, int textViewResourceId, List<Food> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Food food = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.foodName = (TextView) view.findViewById(R.id.food_name);
            viewHolder.foodImage = (ImageView) view.findViewById(R.id.food_image);
            viewHolder.foodPrice = (TextView) view.findViewById(R.id.food_price);
            viewHolder.foodCount = (TextView) view.findViewById(R.id.food_count);
            viewHolder.countMinus = (Button) view.findViewById(R.id.count_minus);
            viewHolder.countPlus = (Button) view.findViewById(R.id.count_plus);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.foodImage.setImageResource(food.getFoodImage());
        viewHolder.foodName.setText(food.getFoodName());
        viewHolder.foodPrice.setText(String.valueOf(food.getFoodPrice()));
        final int foodcnt = 0;
        viewHolder.countPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodCnt = (String) viewHolder.foodCount.getText();
                int cnt = Integer.getInteger(foodCnt);
                cnt = cnt + 1;
                viewHolder.foodCount.setText(cnt);
            }
        });
        viewHolder.countMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodCnt = (String) viewHolder.foodCount.getText();
                int cnt = Integer.getInteger(foodCnt);
                if (cnt >= 1) {
                    cnt = cnt - 1;
                }
                viewHolder.foodCount.setText(cnt);
            }
        });
        return view;
    }

    private class ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
        TextView foodCount;
        Button countMinus;
        Button countPlus;
    }
}
