package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Bean.GoodsBean;

import java.util.Objects;


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
        TextView goodsPriceTextView = findViewById(R.id.goods_detail_goods_price);
        TextView goodsTypeTextView = findViewById(R.id.goods_detail_goods_type);

        ImageView goodsImageView = findViewById(R.id.goods_detail_goods_picture);

        Intent intent = getIntent();
        if (intent != null) {
            // 打开数据库连接
            DBUtil dbUtil = new DBUtil(GoodsDetailActivity.this);
            SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
            DBUtil.db=db;

            // 从 Intent 中获取传递的 g_id
            Integer selectedGoodsId = intent.getIntExtra("selectedGoods", 0);
            String role = intent.getStringExtra("role");
            String S_id = intent.getStringExtra("s_id");
            Button deleteButton = findViewById(R.id.deleteButton);
            Button changeButton = findViewById(R.id.changeButton);

            if (Objects.equals(role, "admin") || Objects.equals(role, "owner") ) {
                // 如果是管理员，显示删除按钮
                deleteButton.setVisibility(View.VISIBLE);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 构建删除的 SQL 语句
                        String deleteQuery = "DELETE FROM goods WHERE g_id = " + selectedGoodsId;

                        // 打开数据库连接
                        DBUtil dbUtil = new DBUtil(GoodsDetailActivity.this);
                        SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
                        DBUtil.db=db;

                        // 执行 SQL 语句
                        db.execSQL(deleteQuery);

                        // 关闭数据库连接
                        db.close();

                        Toast.makeText(GoodsDetailActivity.this, "商品已删除", Toast.LENGTH_SHORT).show();
                        if (Objects.equals(role, "admin")){
                            Intent AdminIntent = new Intent(GoodsDetailActivity.this, AdminActivity.class);
                            AdminIntent.putExtra("s_id", S_id);
                            startActivity(AdminIntent);
                        }

                        if (Objects.equals(role, "owner")){
                            Intent UserIntent = new Intent(GoodsDetailActivity.this, UserGoodsListActivity.class);
                            UserIntent.putExtra("s_id", S_id);
                            UserIntent.putExtra("role", "user");
                            startActivity(UserIntent);
                        }

                    }

                });

                // 如果是管理员，显示修改按钮
                changeButton.setVisibility(View.VISIBLE);
                changeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GoodsDetailActivity.this, UserGoodsChangeActivity.class);
                        intent.putExtra("selectedGoods", selectedGoodsId);
                        startActivity(intent);
                    }
                });
            }
            else {
                // 如果不是管理员，隐藏删除按钮
                deleteButton.setVisibility(View.GONE);
                changeButton.setVisibility(View.GONE);
            }

            // 查询数据库，使用 selectedGoodsId 作为条件
            Cursor result = db.rawQuery("select * from goods INNER JOIN user ON goods.s_id = user.s_id where g_id=?", new String[] {selectedGoodsId.toString()});
            if (result.moveToFirst()) {
                // 从查询结果中获取商品数据
                String s_id = result.getString(1);
                String g_price = result.getString(2);
                String g_name = result.getString(3);
                String g_type = result.getString(4);
                String g_describe = result.getString(5);
                byte[] g_picture = result.getBlob(6);
                String s_contact = result.getString(9);
                String s_address = result.getString(10);

                // 设置商品详细信息的文本视图内容
                goodsNameTextView.setText("物品名称：" + g_name);
                switch (s_address) {
                    case "east":
                        goodsAddressTextView.setText("所在校区：东区");
                        break;
                    case "west":
                        goodsAddressTextView.setText("所在校区：西区");
                        break;
                    case "mid":
                        goodsAddressTextView.setText("所在校区：中区");
                        break;
                    case "high":
                        goodsAddressTextView.setText("所在校区：高新区");
                        break;
                }
                goodsContactTextView.setText("联系方式：" + s_contact);
                goodsDescribeTextView.setText("物品描述：" + g_describe);
                goodsPriceTextView.setText("物品价格：" + g_price);
                goodsTypeTextView.setText("物品类型：" + g_type);
                if (g_picture != null){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(g_picture, 0, g_picture.length); // 将 BLOB 数据转换为 Bitmap
                    goodsImageView.setImageBitmap(bitmap); // 显示图像
                }
            }

            result.close(); // 关闭游标
            db.close(); // 关闭数据库连接
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