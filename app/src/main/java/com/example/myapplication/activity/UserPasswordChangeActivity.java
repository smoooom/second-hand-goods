package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class UserPasswordChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_password_change);

        Intent intent = getIntent();
        String s_id = intent.getStringExtra("s_id");

        //实现返回功能
        Toolbar toolbar=this.findViewById(R.id.user_password_change_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserPasswordChangeActivity.this, UserPageActivity.class);
                intent.putExtra("s_id", s_id);
                startActivity(intent);
            }
        });


        EditText old_password = findViewById(R.id.password_change_old);
        EditText new_password = findViewById(R.id.password_change_new);
        EditText new_password_again = findViewById(R.id.password_change_new_again);
        Button change = findViewById(R.id.password_change_button);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old_passwordT = old_password.getText().toString();
                String new_passwordT = new_password.getText().toString();
                String new_password_againT = new_password_again.getText().toString();
                if(old_passwordT.isEmpty()){
                    Toast.makeText(UserPasswordChangeActivity.this, "请填写原密码", Toast.LENGTH_SHORT).show();
                }
                else if(new_passwordT.isEmpty()){
                    Toast.makeText(UserPasswordChangeActivity.this, "请填写新密码", Toast.LENGTH_SHORT).show();
                }
                else if(new_password_againT.isEmpty()){
                    Toast.makeText(UserPasswordChangeActivity.this, "请再次填写新密码", Toast.LENGTH_SHORT).show();
                }
                else if(!new_passwordT.equals(new_password_againT)){
                    Toast.makeText(UserPasswordChangeActivity.this, "两次填写新密码不同，请重新填写", Toast.LENGTH_SHORT).show();
                }
                else{// 表已经填完了，修改数据库
                    String[] selectionArgs = { s_id };
                    if (UserDao.judgePassword(selectionArgs, old_passwordT) == 0)
                    {
                        Toast.makeText(UserPasswordChangeActivity.this, "原密码错误，请重新填写", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        UserDao.changePassword(selectionArgs,new_passwordT);
                        Toast.makeText(UserPasswordChangeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(UserPasswordChangeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });
    }
}