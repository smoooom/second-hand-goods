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

    private static final int VERSION = 24;//版本 //每一次对数据库进行操作该数据都会加1

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
        contentValues.put("g_type", "教材/图书/音像制品");
        contentValues.put("g_describe", "罗耀阳真的打不来代码");
        db.insert("goods", null, contentValues);

        contentValues.put("g_id", "3");
        contentValues.put("s_id", "PB20151796");
        contentValues.put("g_price", "1000￥");
        contentValues.put("g_name", "华为mate30");
        contentValues.put("g_type", "手机/电脑/数码产品");
        contentValues.put("g_describe", "手机配置低，一玩崩铁就死机，我要换小米手机");
        db.insert("goods", null, contentValues);

        contentValues.put("g_id", "4");
        contentValues.put("s_id", "PB20151796");
        contentValues.put("g_price", "19999￥");
        contentValues.put("g_name", "爱马仕钱包");
        contentValues.put("g_type", "衣服/鞋子/箱包");
        contentValues.put("g_describe", "舔狗送的，全新没用过");
        db.insert("goods", null, contentValues);

        contentValues.put("g_id", "5");
        contentValues.put("s_id", "PB20151796");
        contentValues.put("g_price", "9999￥");
        contentValues.put("g_name", "小米14pro钛合金版");
        contentValues.put("g_type", "手机/电脑/数码产品");
        contentValues.put("g_describe", "全新小米14pro钛合金版，仅售9999￥，非诚勿扰");
        db.insert("goods", null, contentValues);

        contentValues.put("g_id", "6");
        contentValues.put("s_id", "PB20151749");
        contentValues.put("g_price", "5999￥");
        contentValues.put("g_name", "小米14pro钛合金版");
        contentValues.put("g_type", "手机/电脑/数码产品");
        contentValues.put("g_describe", "上面的心也太黑了吧，我这里只要5999");
        db.insert("goods", null, contentValues);

        db.execSQL("PRAGMA foreign_keys = true");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
