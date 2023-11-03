package com.example.myapplication.activity;

import static java.lang.Math.max;
import static java.lang.Math.min;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GoodsAdapter;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    public int currentPage = 0;

    public ArrayList<GoodsBean> selectedItems = new ArrayList<>();

    public ArrayList<GoodsBean> originalItems = new ArrayList<>();

    public GoodsAdapter adapter;

    public ListView goodsList;

    private void updateSelectedItems() {
        int begin = 5 * currentPage;
        int end = Math.min(currentPage + 5, originalItems.size());
        selectedItems.clear();
        selectedItems.addAll(originalItems.subList(begin, end));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        goodsList = findViewById(R.id.user_list_view);
        originalItems = UserDao.getAllGoods(); // 初始化 originalItems

        Button btnPreviousPage = findViewById(R.id.prevPageButton);
        Button btnNextPage = findViewById(R.id.nextPageButton);

        currentPage = 0; // 初始化 currentPage
        updateSelectedItems();

        adapter = new GoodsAdapter(this, selectedItems); // 初始化 adapter
        goodsList.setAdapter(adapter);

        btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage > 0) {
                    currentPage--;
                    updateSelectedItems(); // 更新数据
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(UserActivity.this, "已是第一页", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((currentPage + 1) * 5 < originalItems.size()){
                    currentPage++;
                    updateSelectedItems(); // 更新数据
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(UserActivity.this, "已是最后一页", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}