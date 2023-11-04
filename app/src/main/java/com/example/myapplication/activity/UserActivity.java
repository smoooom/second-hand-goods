package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GoodsAdapter;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        String s_id = intent.getStringExtra("s_id");

        RadioButton upload = findViewById(R.id.user_upload);

        // 货物列表展示
        ListView goodsList = this.findViewById(R.id.user_list_view); // 创建一个视图
        ArrayList<GoodsBean> originalItems = new ArrayList<>(); // 存储数据
        originalItems = UserDao.getAllGoods();

        goodsList.invalidate();
        if(originalItems == null || originalItems.size() == 0){
            goodsList.setAdapter(null);
        }
        else{
            GoodsAdapter adapter = new GoodsAdapter(this, originalItems);
            goodsList.setAdapter(adapter);
        }

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserActivity.this, AddGoodsActivity.class);
                intent.putExtra("s_id", s_id);
                startActivity(intent);
            }
        });
        }
}