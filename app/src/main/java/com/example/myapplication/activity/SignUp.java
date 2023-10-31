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
                if(idT.isEmpty()){
                    Toast.makeText(SignUp.this, "请填写学工号", Toast.LENGTH_SHORT).show();
                }

                String pwdT = password.getText().toString();
                if(pwdT.isEmpty()){
                    Toast.makeText(SignUp.this, "请填写密码", Toast.LENGTH_SHORT).show();
                }

                String conT = contact.getText().toString();
                if(conT.isEmpty()){
                    Toast.makeText(SignUp.this, "请填写联系方式", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}