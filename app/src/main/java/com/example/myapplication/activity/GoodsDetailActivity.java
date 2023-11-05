package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Bean.GoodsBean;


public class GoodsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        // 获取商品详细信息的视图元素
        TextView goodsNameTextView = findViewById(R.id.goods_detail_goods_name);
        TextView goodsAddressTextView = findViewById(R.id.goods_detail_goods_address);
        TextView goodsContactTextView = findViewById(R.id.goods_detail_goods_contact);
        TextView goodsDescribeTextView = findViewById(R.id.goods_detail_goods_describe);
        ImageView goodsImageView = findViewById(R.id.goods_detail_goods_picture);

        Intent intent = getIntent();
        if (intent != null) {
//            GoodsBean selectedGoods = (GoodsBean) intent.getSerializableExtra("selectedGoods");
//            // 根据商品数据更新物品详情页的视图
//
//            if (selectedGoods != null) {
//                // 设置商品详细信息的文本视图和图像视图内容
//                goodsNameTextView.setText("物品名称：" + selectedGoods.getG_name());
//                goodsAddressTextView.setText("所在校区：" + selectedGoods.getS_address());
//                goodsContactTextView.setText("联系方式：" + selectedGoods.getS_contact());
//                goodsDescribeTextView.setText("物品描述：" + selectedGoods.getG_describe());
//                // 设置图片，如果有的话
//                // goodsImageView.setImageBitmap(...);
//            }
        }
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.goods_detail_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 返回上一个界面
            }
        });

    }
}