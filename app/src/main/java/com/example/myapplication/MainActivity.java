package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.activity.SignUp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 连接数据库
        DBUtil dbUtil = new DBUtil(MainActivity.this);
        SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
        DBUtil.db=db;

        // 从主界面跳转注册界面
        /**
        Intent intent=new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
        **/

        EditText id = findViewById(R.id.main_user_id);
        EditText password = findViewById(R.id.main_user_password);
        RadioButton user = findViewById(R.id.main_user);
        user.setChecked(true);
        Button sign_up = findViewById(R.id.main_signup);
        Button login = findViewById(R.id.main_login);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
}