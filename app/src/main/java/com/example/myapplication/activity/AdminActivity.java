package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GoodsAdapter;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    public int currentPage = 0;

    public ArrayList<GoodsBean> selectedItems = new ArrayList<>();

    public ArrayList<GoodsBean> originalItems = new ArrayList<>();

    public GoodsAdapter adapter;

    public ListView goodsList;

    private void updateSelectedItems() {
        originalItems = UserDao.getAllGoods();
        int begin = 5 * currentPage;
        int end = Math.min(begin + 5, originalItems.size());
        selectedItems.clear();
        selectedItems.addAll(originalItems.subList(begin, end));
    }

    private void filterData(String query) {
        ArrayList<GoodsBean> filteredItems = new ArrayList<>();
        for (GoodsBean item : originalItems) {
            if (item.getG_name().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        adapter.clear();
        adapter.addAll(filteredItems);
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button GoodsButton = findViewById(R.id.admin_goods);
        Button UserButton = findViewById(R.id.admin_user);
        Button StatisticsButton = findViewById(R.id.admin_statistics);

        GoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this, Admin_UserManage.class);
                startActivity(intent);
            }
        });

        StatisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this, Statistics.class);
                startActivity(intent);
            }
        });

        goodsList = findViewById(R.id.admin_list_view);
        originalItems = UserDao.getAllGoods(); // 初始化 originalItems

        // 实现翻页功能
        Button btnPreviousPage = findViewById(R.id.prevPageButton);
        Button btnNextPage = findViewById(R.id.nextPageButton);

        currentPage = 0; // 初始化 currentPage
        updateSelectedItems(); // 初始化第一页的内容

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
                    Toast.makeText(AdminActivity.this, "已是第一页", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AdminActivity.this, "已是最后一页", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 实现搜索功能
        EditText searchEditText = findViewById(R.id.admin_search);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 用户输入文本时触发
                String query = charSequence.toString();
                filterData(query);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        goodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取用户点击的商品
                Integer selectedGoodsId = selectedItems.get(position).getG_id();
                // 创建意图用于启动物品详情页的Activity
                Intent intent = new Intent(AdminActivity.this, GoodsDetailActivity.class);
                // 传递商品数据给详情页
                intent.putExtra("selectedGoods", selectedGoodsId);
                // 传递用户身份数据给详情页
                intent.putExtra("role","admin");
                startActivity(intent);
            }
        });

    }
}