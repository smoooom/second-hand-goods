package com.example.myapplication.Dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DataBase.DBUtil;

public class UserDao {
    public static SQLiteDatabase db = DBUtil.db;

    // 注册新用户
    public static int addUser(String ...data){
        ContentValues values = new ContentValues();
        values.put("s_id",data[0]);
        values.put("s_password",data[1]);
        values.put("s_contact",data[2]);
        values.put("s_address",data[3]);
        try {
            db.execSQL("INSERT INTO user VALUES(?,?,?,?)",data);
            return 1;
        }
        catch (Exception e){
            return -1;
        }
    }
}
