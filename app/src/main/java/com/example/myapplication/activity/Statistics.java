package com.example.myapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {
    private LineChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        chart = findViewById(R.id.lineChart);

        Button GoodsButton = findViewById(R.id.admin_goods);
        Button UserButton = findViewById(R.id.admin_user);
        Button StatisticsButton = findViewById(R.id.admin_statistics);

        GoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Statistics.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Statistics.this, Admin_UserManage.class);
                startActivity(intent);
            }
        });

        StatisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Statistics.this, Statistics.class);
                startActivity(intent);
            }
        });

        // 打开数据库连接
        DBUtil dbUtil = new DBUtil(Statistics.this);
        SQLiteDatabase db = dbUtil.getWritableDatabase();//获取数据库连接
        DBUtil.db=db;

        Cursor cursor = db.query("user", null, "s_address='east'", null, null, null, null);
        int east_count = cursor.getCount();
        cursor.close();

        cursor = db.query("user", null, "s_address='mid'", null, null, null, null);
        int mid_count = cursor.getCount();
        cursor.close();

        cursor = db.query("user", null, "s_address='west'", null, null, null, null);
        int west_count = cursor.getCount();
        cursor.close();

        cursor = db.query("user", null, "s_address='high'", null, null, null, null);
        int high_count = cursor.getCount();
        cursor.close();

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, mid_count));
        barEntries.add(new BarEntry(1, east_count));
        barEntries.add(new BarEntry(2, west_count));
        barEntries.add(new BarEntry(3, high_count));

        BarDataSet barDataSet = new BarDataSet(barEntries, "各校区用户数");
        barDataSet.setValueTextColor(Color.GRAY);
        barDataSet.setValueTextSize(12);
        barDataSet.setColor(Color.RED);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.3f);




    }
}