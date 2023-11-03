package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.net.Uri;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.app.Activity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class AddGoodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);

        //实现返回功能
        Toolbar toolbar=this.findViewById(R.id.sign_up_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddGoodsActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        EditText name = findViewById(R.id.upload_goods_name);
        EditText price = findViewById(R.id.upload_goods_price);
        EditText detail = findViewById(R.id.upload_goods_detail);
        Spinner category = findViewById(R.id.upload_goods_name_category_spinner);
        Button upload = findViewById(R.id.upload_button);
        ImageView image = findViewById(R.id.upload_image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(AddGoodsActivity.this,"upload_image被点击了", Toast.LENGTH_SHORT).show();
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
            
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameT = name.getText().toString();
                String priceT = price.getText().toString();
                String detailT = detail.getText().toString();


            if(nameT.isEmpty()){
                Toast.makeText(AddGoodsActivity.this, "请填写物品名", Toast.LENGTH_SHORT).show();
            }
                else if(priceT.isEmpty()){
                Toast.makeText(AddGoodsActivity.this, "请填写价格", Toast.LENGTH_SHORT).show();
            }
                else if(detailT.isEmpty()){
                Toast.makeText(AddGoodsActivity.this, "请填写详情", Toast.LENGTH_SHORT).show();
            }

            }
        });
    }
}