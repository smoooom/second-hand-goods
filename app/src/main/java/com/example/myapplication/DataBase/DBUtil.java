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

    private static final int VERSION = 20;//版本 //每一次对数据库进行操作该数据都会加1

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

        db.execSQL("INSERT INTO user VALUES('PB20151749','202318','QQ:838606117','west')");
        db.execSQL("INSERT INTO user VALUES('PB20151796','202318','QQ:111111111','east')");

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

        ContentValues contentValues = new ContentValues();
        contentValues.put("g_id", "1");
        contentValues.put("s_id", "PB20151749");
        contentValues.put("g_price", "120￥");
        contentValues.put("g_name", "永久牌自行车");
        contentValues.put("g_type", "其他");
        contentValues.put("g_describe", "99新，基本没用过，因为我根本不会骑自行车");
        db.insert("goods", null, contentValues);

        contentValues.put("g_id", "2");
        contentValues.put("s_id", "PB20151749");
        contentValues.put("g_price", "20￥");
        contentValues.put("g_name", "《软件开发：从入门到入土》");
        contentValues.put("g_type", "图书");
        contentValues.put("g_describe", "罗耀阳真的打不来代码");
        db.insert("goods", null, contentValues);

        contentValues.put("g_id", "3");
        contentValues.put("s_id", "PB20151796");
        contentValues.put("g_price", "20￥");
        contentValues.put("g_name", "《软件开：从入门到入土》");
        contentValues.put("g_type", "图书");
        contentValues.put("g_describe", "真的打不来代码");
        db.insert("goods", null, contentValues);

        contentValues.put("g_id", "4");
        contentValues.put("s_id", "PB20151796");
        contentValues.put("g_price", "20￥");
        contentValues.put("g_name", "《软件发：从入门到入土》");
        contentValues.put("g_type", "图书");
        contentValues.put("g_describe", "真的代码");
        db.insert("goods", null, contentValues);

        db.execSQL("PRAGMA foreign_keys = true");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
