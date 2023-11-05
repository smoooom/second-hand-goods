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
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GoodsAdapter;
import com.example.myapplication.adapter.UserAdapter;

import java.util.ArrayList;

public class Admin_UserManage extends AppCompatActivity {
    public int currentPage = 0;

    public ArrayList<UserBean> selectedUsers = new ArrayList<>();

    public ArrayList<UserBean> originalUsers = new ArrayList<>();

    public UserAdapter adapter;

    public ListView userList;

    private void updateSelectedItems() {
        originalUsers = UserDao.getAllUsers();
        int begin = 5 * currentPage;
        int end = Math.min(begin + 5, originalUsers.size());
        selectedUsers.clear();
        selectedUsers.addAll(originalUsers.subList(begin, end));
    }

    private void filterData(String query) {
        ArrayList<UserBean> filteredUsers = new ArrayList<>();
        for (UserBean user : originalUsers) {
            if (user.getS_id().toLowerCase().contains(query.toLowerCase())) {
                filteredUsers.add(user);
            }
        }
        adapter.clear();
        adapter.addAll(filteredUsers);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_manage);

        Button GoodsButton = findViewById(R.id.admin_goods);
        Button UserButton = findViewById(R.id.admin_user);

        GoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_UserManage.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_UserManage.this, Admin_UserManage.class);
                startActivity(intent);
            }
        });


        userList = findViewById(R.id.admin_list_view);
        originalUsers = UserDao.getAllUsers(); // 初始化 originalUsers

        // 实现翻页功能
        Button btnPreviousPage = findViewById(R.id.prevPageButton);
        Button btnNextPage = findViewById(R.id.nextPageButton);

        currentPage = 0; // 初始化 currentPage
        updateSelectedItems(); // 初始化第一页的内容

        adapter = new UserAdapter(this, selectedUsers); // 初始化 adapter
        userList.setAdapter(adapter);

        btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage > 0) {
                    currentPage--;
                    updateSelectedItems(); // 更新数据
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(Admin_UserManage.this, "已是第一页", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((currentPage + 1) * 5 < originalUsers.size()){
                    currentPage++;
                    updateSelectedItems(); // 更新数据
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(Admin_UserManage.this, "已是最后一页", Toast.LENGTH_SHORT).show();
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

//        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // 获取用户点击的商品
//                Integer selectedGoodsId = selectedUsers.get(position).getS_id();
//                // 创建意图用于启动物品详情页的Activity
//                Intent intent = new Intent(Admin_UserManage.this, GoodsDetailActivity.class);
//                // 传递商品数据给详情页
//                intent.putExtra("selectedGoods", selectedGoodsId);
//                // 传递用户身份数据给详情页
//                intent.putExtra("role","admin");
//                startActivity(intent);
//            }
//        });

    }


}