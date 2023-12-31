package com.example.myapplication.activity;

import static java.lang.Math.max;
import static java.lang.Math.min;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GoodsAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class UserGoodsListActivity extends AppCompatActivity {
    public int currentPage = 0;

    public ArrayList<GoodsBean> selectedItems = new ArrayList<>();

    public ArrayList<GoodsBean> originalItems = new ArrayList<>();

    public GoodsAdapter adapter;

    public ListView goodsList;

    private void updateSelectedItems(String s_id) {
        String[] selectionArgs = { s_id };
        originalItems = UserDao.getUserGoods(selectionArgs);
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
        setContentView(R.layout.activity_user_goods_list);

        Intent intent = getIntent();
        String role = intent.getStringExtra("role");
        String s_id = intent.getStringExtra("s_id");
        String[] selectionArgs = { s_id };

        goodsList = findViewById(R.id.user_list_view);
        originalItems = UserDao.getUserGoods(selectionArgs); // 初始化 originalItems

        //实现返回功能
        Toolbar toolbar=this.findViewById(R.id.user_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(role, "owner")){
                    Intent intent=new Intent(UserGoodsListActivity.this, UserPageActivity.class);
                    intent.putExtra("s_id", s_id);
                    startActivity(intent);
                }
                else if (Objects.equals(role, "admin")){
                    Intent intent=new Intent(UserGoodsListActivity.this, Admin_UserDetail.class);
                    intent.putExtra("s_id", s_id);
                    startActivity(intent);
                }

            }
        });

        // 实现翻页功能
        Button btnPreviousPage = findViewById(R.id.prevPageButton);
        Button btnNextPage = findViewById(R.id.nextPageButton);

        currentPage = 0; // 初始化 currentPage
        updateSelectedItems(s_id); // 初始化第一页的内容

        adapter = new GoodsAdapter(this, selectedItems); // 初始化 adapter
        goodsList.setAdapter(adapter);

        btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage > 0) {
                    currentPage--;
                    updateSelectedItems(s_id); // 更新数据
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(UserGoodsListActivity.this, "已是第一页", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((currentPage + 1) * 5 < originalItems.size()){
                    currentPage++;
                    updateSelectedItems(s_id); // 更新数据
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(UserGoodsListActivity.this, "已是最后一页", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 实现搜索功能
        EditText searchEditText = findViewById(R.id.user_search);
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

        // 实现跳转到物品详情页的功能
        goodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取用户点击的商品
                Integer selectedGoodsId = selectedItems.get(position).getG_id();
                // 创建意图用于启动物品详情页的Activity
                Intent intent = new Intent(UserGoodsListActivity.this, GoodsDetailActivity.class);
                // 传递数据给详情页
                intent.putExtra("selectedGoods", selectedGoodsId);
                intent.putExtra("s_id", s_id);
                intent.putExtra("role","owner");
                startActivity(intent);
            }
        });


    }

}