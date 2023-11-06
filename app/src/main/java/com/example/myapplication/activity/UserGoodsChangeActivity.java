package com.example.myapplication.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UserGoodsChangeActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri; // 用于存储选定的图片的 URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_goods_change);

        Intent intent = getIntent();
        Integer selectedGoodsId = intent.getIntExtra("selectedGoods", 0);
        String s_id = intent.getStringExtra("s_id");

        //实现返回功能
        Toolbar toolbar = this.findViewById(R.id.change_goods_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserGoodsChangeActivity.this, GoodsDetailActivity.class);
                // 传递商品数据给详情页
                intent.putExtra("selectedGoods", selectedGoodsId);
                // 传递用户身份数据给详情页
                intent.putExtra("role", "owner");
                startActivity(intent);
            }
        });

        EditText name = findViewById(R.id.change_goods_name);
        EditText price = findViewById(R.id.change_goods_price);
        EditText detail = findViewById(R.id.change_goods_detail);
        Spinner category = findViewById(R.id.change_goods_name_category_spinner);
        Button change = findViewById(R.id.change_goods_button);
        ImageView image = findViewById(R.id.change_goods_image);


        // 打开数据库连接
        DBUtil dbUtil = new DBUtil(UserGoodsChangeActivity.this);
        SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
        DBUtil.db = db;
        // 查询数据库，使用 selectedGoodsId 作为条件
        Cursor result = db.rawQuery("select * from goods INNER JOIN user ON goods.s_id = user.s_id where g_id=?", new String[]{selectedGoodsId.toString()});
        if (result.moveToFirst()) {
            // 从查询结果中获取商品数据
            String g_price = result.getString(2);
            String g_name = result.getString(3);
            String g_type = result.getString(4);
            String g_describe = result.getString(5);
            byte[] g_picture = result.getBlob(6);

            //设置显示当前信息
            name.setText(g_name);
            price.setText(g_price);
            detail.setText(g_describe);
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) category.getAdapter();
            int position = adapter.getPosition(g_type);
            category.setSelection(position);
            if (g_picture != null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(g_picture, 0, g_picture.length); // 将 BLOB 数据转换为 Bitmap
                image.setImageBitmap(bitmap); // 显示图像
            }
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(UserGoodsChangeActivity.this,"请选择图片", Toast.LENGTH_SHORT).show();
                openGallery();
            }

        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameT = name.getText().toString();
                String priceT = price.getText().toString();
                String detailT = detail.getText().toString();
                String categoryT = category.getSelectedItem().toString();

                if (nameT.isEmpty()) {
                    Toast.makeText(UserGoodsChangeActivity.this, "请填写物品名", Toast.LENGTH_SHORT).show();
                } else if (priceT.isEmpty()) {
                    Toast.makeText(UserGoodsChangeActivity.this, "请填写价格", Toast.LENGTH_SHORT).show();
                } else if (detailT.isEmpty()) {
                    Toast.makeText(UserGoodsChangeActivity.this, "请填写详情", Toast.LENGTH_SHORT).show();
                } else if (imageUri == null) {
                    Toast.makeText(UserGoodsChangeActivity.this, "请上传图片", Toast.LENGTH_SHORT).show();
                } else {
                    // 将 Bitmap 转换为字节数组
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri); // 从某处获取 Bitmap
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byte_image = stream.toByteArray();
                        // 连接数据库
                        DBUtil dbUtil = new DBUtil(UserGoodsChangeActivity.this);
                        SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
                        DBUtil.db=db;

                        // 插入数据库
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("s_id", s_id);
                        contentValues.put("g_price", priceT);
                        contentValues.put("g_name", nameT);
                        contentValues.put("g_type", categoryT);
                        contentValues.put("g_describe", detailT);
                        contentValues.put("g_picture", byte_image);
                        db.insert("goods", null, contentValues);
                        db.close();

                        Toast.makeText(UserGoodsChangeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(UserGoodsChangeActivity.this, GoodsDetailActivity.class);
                        intent.putExtra("s_id", s_id);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    // 打开相册选择图片
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "选择图片"), PICK_IMAGE_REQUEST);
    }

    // 处理选择的图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            // 将选择的图片显示在 ImageView 中
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ImageView imageView = findViewById(R.id.upload_image);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}