package com.example.myapplication.adapter;

import static com.example.myapplication.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class GoodsAdapter extends ArrayAdapter<GoodsBean> {
    private ArrayList<GoodsBean> list = null;

    public GoodsAdapter(@NonNull Context context, ArrayList<GoodsBean> list) {
        super(context, layout.goods_list, list);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.goods_list,parent,false);
        }

        TextView name = convertView.findViewById(id.goods_list_name);
        TextView price = convertView.findViewById(id.goods_list_price);
        TextView type = convertView.findViewById(id.goods_list_type);
        ImageView picture = convertView.findViewById(id.goods_list_picture);

        GoodsBean goodsBean = list.get(position);
        name.setText(goodsBean.getG_name());
        price.setText(goodsBean.getG_price());
        type.setText(goodsBean.getG_type());
        picture.setImageResource(drawable.login);

        return convertView;
    }
}
