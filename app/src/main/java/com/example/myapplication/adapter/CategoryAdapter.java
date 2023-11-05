package com.example.myapplication.adapter;

import static com.example.myapplication.R.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.example.myapplication.Bean.CategoryBean;
import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryBean> {
    private Context context;
    private ArrayList<CategoryBean> spinnerItems = null;

    public CategoryAdapter(@NonNull Context context, ArrayList<CategoryBean> spinnerItems) {
        super(context, layout.categories_list, spinnerItems);
        this.context = context;
        this.spinnerItems = spinnerItems;
    }

    @Override
    public int getCount() {
        return spinnerItems.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout.spinner_category, parent, false);
        }

        ImageView imageView = convertView.findViewById(id.spinner_image);
        TextView titleTextView = convertView.findViewById(id.spinner_title);

        CategoryBean item = spinnerItems.get(position);

        imageView.setImageResource(item.get_icon());
        titleTextView.setText(item.get_title());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    public static ArrayList<CategoryBean> getAllCategories(Context context) {
        ArrayList<CategoryBean> categories = new ArrayList<>();
        String[] titles = context.getResources().getStringArray(R.array.ctype);
        String[] imageNames = context.getResources().getStringArray(R.array.category_icon);

        for(int i = 0; i < titles.length; i++) {
            int imageId = context.getResources().getIdentifier(imageNames[i], "drawable", context.getPackageName());
            categories.add(new CategoryBean(titles[i], imageId));
        }
        return categories;
    }
}
