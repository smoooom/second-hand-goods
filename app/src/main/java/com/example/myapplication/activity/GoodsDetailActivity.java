package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.Bean.GoodsBean;


public class GoodsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        Intent intent = getIntent();
        if (intent != null) {
            GoodsBean selectedGoods = (GoodsBean) intent.getSerializableExtra("selectedGoods");
            // 根据商品数据更新物品详情页的视图
        }
    }
}