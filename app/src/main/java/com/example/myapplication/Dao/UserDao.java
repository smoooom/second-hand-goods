package com.example.myapplication.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.DataBase.DBUtil;

import java.util.ArrayList;

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

    public static int LoginUser(String id, String password, String role){
        String values[] = {id, password};
        Cursor result = null;
        if(role.equals("user")){
            result = db.rawQuery("select * from user where s_id=? and s_password=?", values);
        }
        else if(role.equals("admin")){
            result = db.rawQuery("select * from admin where s_id=? and s_password=?", values);
        }

        if(result != null && result.moveToFirst()){
            // 查询结果非空且有至少一行数据，表示登录成功
            result.close();
            return 1;
        }
        else {
            // 查询结果为空，表示登录失败
            result.close();
            return -1;
        }
    }

    public static ArrayList<GoodsBean> getAllGoods(){
        ArrayList<GoodsBean> list = new ArrayList<>();

        String query = "SELECT goods.g_id, goods.s_id, goods.g_price, goods.g_name, goods.g_type, goods.g_describe, goods.g_picture, user.s_contact, user.s_address " +
                "FROM goods " +
                "INNER JOIN user ON goods.s_id = user.s_id";

        Cursor result = db.rawQuery(query, null);

        while(result.moveToNext()){
            Integer g_id = result.getInt(0);
            String s_id = result.getString(1);
            String g_price = result.getString(2);
            String g_name = result.getString(3);
            String g_type = result.getString(4);
            String g_describe = result.getString(5);
            byte[] g_picture = result.getBlob(6);
            String s_contact = result.getString(7);
            String s_address = result.getString(8);

            GoodsBean goodsBean = new GoodsBean(g_id, s_id, g_price,g_name, g_type, g_describe, g_picture, s_contact, s_address);
            list.add(goodsBean);
        }
        return list;
    }

}
