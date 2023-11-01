package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.activity.SignUp;
import com.example.myapplication.activity.UserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 连接数据库
        DBUtil dbUtil = new DBUtil(MainActivity.this);
        SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
        DBUtil.db=db;

        Intent intent=new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);

        // 读取表格信息
        EditText id = findViewById(R.id.main_user_id);
        EditText password = findViewById(R.id.main_user_password);
        RadioButton user = findViewById(R.id.main_user);
        RadioButton admin = findViewById(R.id.main_admin);
        user.setChecked(true);
        Button sign_up = findViewById(R.id.main_signup);
        Button login = findViewById(R.id.main_login);

        // 从主界面跳转注册界面
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        // 从主界面登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idT = id.getText().toString();
                String pwdT = password.getText().toString();

                if(idT.isEmpty()){
                    Toast.makeText(MainActivity.this, "请填写学工号", Toast.LENGTH_SHORT).show();
                }
                else if(pwdT.isEmpty()){
                    Toast.makeText(MainActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
                }

                else{
                    if(user.isChecked()){
                        int flag = UserDao.LoginUser(idT,pwdT,"user");
                        if(flag == -1){
                            Toast.makeText(MainActivity.this, "登录失败，学工号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                        else if(flag == 1){
                            Toast.makeText(MainActivity.this, "用户登录成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if(admin.isChecked()){
                        int flag = UserDao.LoginUser(idT,pwdT,"admin");
                        if(flag == -1){
                            Toast.makeText(MainActivity.this, "登录失败，学工号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                        else if(flag == 1){
                            Toast.makeText(MainActivity.this, "管理员登录成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

    }
}