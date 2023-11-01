package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GoodsAdapter;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // 货物列表展示
        ListView goodsList = this.findViewById(R.id.user_list_view); // 创建一个视图
        ArrayList<GoodsBean> originalItems = new ArrayList<>(); // 存储数据
        originalItems = UserDao.getAllGoods();

        if(originalItems == null || originalItems.size() == 0){
            goodsList.setAdapter(null);
        }
        else{
            GoodsAdapter adapter = new GoodsAdapter(this, originalItems);
            goodsList.setAdapter(adapter);
        }

    }
}