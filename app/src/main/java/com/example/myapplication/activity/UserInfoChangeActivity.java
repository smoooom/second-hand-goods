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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GoodsAdapter;

import java.util.ArrayList;
public class UserInfoChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_change);


        Intent intent = getIntent();
        String s_id = intent.getStringExtra("s_id");
        String role = intent.getStringExtra("role");

        //实现返回功能
        Toolbar toolbar=this.findViewById(R.id.user_info_change_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserInfoChangeActivity.this, UserPageActivity.class);
                intent.putExtra("s_id", s_id);
                intent.putExtra("role", role);
                startActivity(intent);
            }
        });

        EditText contact = findViewById(R.id.info_change_user_contact);
        RadioButton mid = findViewById(R.id.info_change_user_mid);
        mid.setChecked(true);
        RadioButton east = findViewById(R.id.info_change_user_east);
        RadioButton west = findViewById(R.id.info_change_user_west);
        RadioButton high = findViewById(R.id.info_change_user_high);
        Button change = findViewById(R.id.info_change_button);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String conT = contact.getText().toString();
                String address = "中校区";
                if(east.isChecked()){
                    address = "东校区";
                }
                if(west.isChecked()){
                    address = "西校区";
                }
                if(high.isChecked()){
                    address = "高新区";
                }

                if(conT.isEmpty()){
                    Toast.makeText(UserInfoChangeActivity.this, "请填写新的联系方式", Toast.LENGTH_SHORT).show();
                }
                else{// 表已经填完了，修改数据库
                    String[] selectionArgs = { s_id };
                    UserDao.changeInfo(selectionArgs,conT,address);
                    Toast.makeText(UserInfoChangeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(UserInfoChangeActivity.this, UserPageActivity.class);
                    intent.putExtra("s_id", s_id);
                    intent.putExtra("role", role);
                    startActivity(intent);
                }
            }
        });
    }
}