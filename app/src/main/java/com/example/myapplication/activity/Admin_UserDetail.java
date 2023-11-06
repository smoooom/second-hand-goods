package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class Admin_UserDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_detail);

        Intent intent = getIntent();
        String s_id = intent.getStringExtra("s_id");

        //实现返回功能
        Toolbar toolbar=this.findViewById(R.id.user_page_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_UserDetail.this, Admin_UserManage.class);
                startActivity(intent);
            }
        });

        TextView user_id = findViewById(R.id.user_id);
        TextView user_address = findViewById(R.id.user_address);
        TextView user_contact = findViewById(R.id.user_contact);
        Button user_delete = findViewById(R.id.delete_user_button);
        Toolbar user_goods_list = findViewById(R.id.user_goods_list);

        // 打开数据库连接
        DBUtil dbUtil = new DBUtil(Admin_UserDetail.this);
        SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
        DBUtil.db=db;

        //从数据库查询其他信息
        String[] projection = {
                "s_address",
                "s_contact",
        };

        String selection = "s_id = ?";
        String[] selectionArgs = { s_id };

        Cursor cursor = db.query(
                "user", // 表名
                projection, // 要查询的字段
                selection, // 查询条件
                selectionArgs, // 查询条件的参数
                null, // 不需要分组
                null, // 不需要过滤
                null // 不需要排序
        );

        if (cursor.moveToFirst()) {
            // 从数据库检索数据
            @SuppressLint("Range") String s_address = cursor.getString(cursor.getColumnIndex("s_address"));
            @SuppressLint("Range") String s_contact = cursor.getString(cursor.getColumnIndex("s_contact"));

            //将查询结果设置到界面视图
            user_id.setText("学工号："+s_id);
            user_address.setText("地址："+s_address);
            user_contact.setText("联系方式："+s_contact);
        }

        cursor.close(); // 关闭游标
        db.close(); // 关闭数据库连接

        //跳转至我的出售
        user_goods_list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_UserDetail.this, UserGoodsListActivity.class);
                intent.putExtra("s_id", s_id);
                intent.putExtra("role","admin");
                startActivity(intent);
            }
        });

        //实现删除账号功能
        user_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 构建删除的 SQL 语句
                String deleteQuery = "DELETE FROM user WHERE s_id = " + s_id;

                // 打开数据库连接
                DBUtil dbUtil = new DBUtil(Admin_UserDetail.this);
                SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
                DBUtil.db=db;

                // 执行 SQL 语句
                db.execSQL(deleteQuery);

                // 关闭数据库连接
                db.close();

                Toast.makeText(Admin_UserDetail.this, "用户已删除", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Admin_UserDetail.this, Admin_UserManage.class);
                startActivity(intent);
            }
        });


    }
}