package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.DataBase.DBUtil;
import com.example.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

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

        db.close();

        BarChart bar = findViewById(R.id.BarChart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, mid_count));
        barEntries.add(new BarEntry(2, east_count));
        barEntries.add(new BarEntry(3, west_count));
        barEntries.add(new BarEntry(4, high_count));

        BarDataSet barDataSet = new BarDataSet(barEntries, "各校区用户数");
        barDataSet.setValueTextColor(Color.GRAY);
        barDataSet.setValueTextSize(12);
        barDataSet.setColor(Color.RED);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.3f);

        bar.setData(barData);

        // 获取 X 轴对象
        XAxis xAxis = bar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置 X 轴位置
        // 设置标签数量
        xAxis.setLabelCount(5);
        // 创建自定义标签文本格式化器
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                // 在这里根据 value 返回相应的标签文本
                switch ((int) value) {
                    case 1:
                        return "中校区";
                    case 2:
                        return "东校区";
                    case 3:
                        return "西校区";
                    case 4:
                        return "高新区";
                    default:
                        return "";
                }
            }
        });
        xAxis.setGranularity(1f); // 设置标签间隔
        xAxis.setAxisMinimum(0f);
        xAxis.setTextColor(Color.parseColor("#1755FB")); // 设置标签颜色
        xAxis.setTextSize(12f); // 设置标签大小
        xAxis.setAxisLineWidth(5f); // 设置 X 轴线宽
        xAxis.setAxisLineColor(Color.parseColor("#6200EE")); // 设置 X 轴线颜色

        bar.getAxisRight().setEnabled(false);
        bar.getAxisLeft().setAxisLineWidth(4f);
        bar.getAxisLeft().setAxisLineColor(Color.parseColor("#6200EE"));
        bar.getDescription().setEnabled(false);
        bar.setScaleEnabled(false);

        // 打开数据库连接
        dbUtil = new DBUtil(Statistics.this);
        db = dbUtil.getWritableDatabase();//获取数据库连接
        DBUtil.db=db;

        cursor = db.query("goods", null, "g_type='教材/图书/音像制品'", null, null, null, null);
        int type1 = cursor.getCount();
        cursor.close();

        cursor = db.query("goods", null, "g_type='手机/电脑/数码产品'", null, null, null, null);
        int type2 = cursor.getCount();
        cursor.close();

        cursor = db.query("goods", null, "g_type='衣服/鞋子/箱包'", null, null, null, null);
        int type3 = cursor.getCount();
        cursor.close();

        cursor = db.query("goods", null, "g_type='日常用品/消耗品'", null, null, null, null);
        int type4 = cursor.getCount();
        cursor.close();

        cursor = db.query("goods", null, "g_type='演出展览门票'", null, null, null, null);
        int type5 = cursor.getCount();
        cursor.close();

        cursor = db.query("goods", null, "g_type='其他'", null, null, null, null);
        int type6 = cursor.getCount();
        cursor.close();

        db.close();

        RadarChart radarChart = findViewById(R.id.radarChart);

        // 对雷达图进行一些设置
        radarChart.getDescription().setEnabled(false); // 可以禁用描述
        radarChart.setWebLineWidth(1f); // 设置雷达图的线宽
        radarChart.setWebColor(Color.LTGRAY); // 设置雷达图的线颜色
        radarChart.setWebLineWidthInner(1f); // 设置雷达图内部线的宽度
        radarChart.setWebColorInner(Color.LTGRAY); // 设置雷达图内部线的颜色

        List<RadarEntry> radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(type1, 0));
        radarEntries.add(new RadarEntry(type2, 1));
        radarEntries.add(new RadarEntry(type3, 2));
        radarEntries.add(new RadarEntry(type4, 3));
        radarEntries.add(new RadarEntry(type5, 4));
        radarEntries.add(new RadarEntry(type6, 5));

        String[] labels = new String[]{"教材/图书/音像制品", "手机/电脑/数码产品", "衣服/鞋子/箱包", "日常用品/消耗品", "演出展览门票", "其他"};
        xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels)); // 将标签数组设置给 X 轴
        xAxis.setTextSize(12f); // 设置标签文本大小
        xAxis.setTextColor(Color.BLACK); // 设置标签文本颜色
        xAxis.setLabelCount(labels.length); // 设置标签的数量
        xAxis.setDrawLabels(true); // 允许绘制标签

        RadarDataSet radarDataSet = new RadarDataSet(radarEntries, "各类商品数量");
        radarDataSet.setColor(Color.RED); // 设置数据集的颜色

        RadarData radarData = new RadarData(radarDataSet);
        radarChart.setData(radarData);


    }
}