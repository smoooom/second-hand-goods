package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DBUtil extends SQLiteOpenHelper {

    private static final String DB_NAME="DataBase_Second_hand.db";//命名数据库

    public static SQLiteDatabase db = null;//通过db进行数据库操作

    private static final int VERSION = 2;//版本 //每一次对数据库进行操作该数据都会加1

    public DBUtil(Context context){
        super(context,DB_NAME,null,VERSION,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = false");

        // 创建一个用户表
        db.execSQL("drop table if exists user");
        db.execSQL("create table user(s_id varchar(20) primary key," +
                "s_password varchar(20)," +
                "s_contact varchar(20)," +
                "s_address varchar(20))");

        // 创建一个管理员表
        db.execSQL("drop table if exists admin");
        db.execSQL("create table admin(s_id varchar(20) primary key," +
                "s_password varchar(20)," +
                "s_contact varchar(20)," +
                "s_address varchar(20))");

        db.execSQL("INSERT INTO admin VALUES('root','123456','QQ:838606117','high')");

        // 创建商品表
        db.execSQL("drop table if exists goods");
        db.execSQL("create table goods(g_id varchar(20) primary key," +
                "s_id varchar(20)," +
                "g_price varchar(20)," +
                "g_name varchar(20)," +
                "g_type varchar(20)," +
                "g_describe varchar(200)," +
                "g_picture blob)");

        String imagePath = "drawable/bike.png";
        byte[] imageBytes = Tool.loadImageBytes(imagePath);

        ContentValues contentValues = new ContentValues();
        contentValues.put("g_id", "1");
        contentValues.put("s_id", "root");
        contentValues.put("g_price", "300￥");
        contentValues.put("g_name", "永久牌自行车");
        contentValues.put("g_type", "其他");
        contentValues.put("g_describe", "99新，基本没用过，因为我根本不会骑自行车");
        contentValues.put("g_picture", imageBytes);

        db.insert("goods", null, contentValues);

        db.execSQL("PRAGMA foreign_keys = true");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
