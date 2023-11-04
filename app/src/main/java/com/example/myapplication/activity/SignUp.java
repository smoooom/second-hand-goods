package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

//注册用户的界面
public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //实现返回功能
        Toolbar toolbar=this.findViewById(R.id.sign_up_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });

        EditText id = findViewById(R.id.sign_up_user_id);
        EditText password = findViewById(R.id.sign_up_user_password);
        EditText contact = findViewById(R.id.sign_up_user_contact);
        RadioButton mid = findViewById(R.id.sign_up_user_mid);
        mid.setChecked(true);
        RadioButton east = findViewById(R.id.sign_up_user_east);
        RadioButton west = findViewById(R.id.sign_up_user_west);
        RadioButton high = findViewById(R.id.sign_up_user_high);
        Button sign_up = findViewById(R.id.sign_up_button);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idT = id.getText().toString();
                String pwdT = password.getText().toString();
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

                if(idT.isEmpty()){
                    Toast.makeText(SignUp.this, "请填写学工号", Toast.LENGTH_SHORT).show();
                }
                else if(pwdT.isEmpty()){
                    Toast.makeText(SignUp.this, "请填写密码", Toast.LENGTH_SHORT).show();
                }
                else if(conT.isEmpty()){
                    Toast.makeText(SignUp.this, "请填写联系方式", Toast.LENGTH_SHORT).show();
                }
                else{// 表已经填完了，将新用户加入数据库
                    int flag = UserDao.addUser(idT,pwdT,conT,address);
                    if(flag == 1){
                        Toast.makeText(SignUp.this, "已注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SignUp.this, UserActivity.class);
                        intent.putExtra("s_id", idT);
                        startActivity(intent);
                    }
                    if(flag == -1){
                        Toast.makeText(SignUp.this, "注册失败，账号已存在", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}