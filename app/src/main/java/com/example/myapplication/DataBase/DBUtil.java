package com.example.myapplication.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil extends SQLiteOpenHelper {

    private static final String DB_NAME="DataBase_Second_hand.db";//命名数据库

    public static SQLiteDatabase db=null;//通过db进行数据库操作

    private static final int VERSION=1;//版本 //每一次对数据库进行操作该数据都会加1

    public DBUtil(Context context){
        super(context,DB_NAME,VERSION,null);

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

        db.execSQL("PRAGMA foreign_keys = true");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
