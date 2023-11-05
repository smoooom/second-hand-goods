package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.Bean.GoodsBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UserBean> {
    private ArrayList<UserBean> list = null;

    public UserAdapter(@NonNull Context context, ArrayList<UserBean> list) {
        super(context, R.layout.user_list, list);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.user_list,parent,false);
        }

        TextView id = convertView.findViewById(R.id.user_id);
        TextView password = convertView.findViewById(R.id.user_password);
        TextView contact = convertView.findViewById(R.id.user_contact);
        TextView address = convertView.findViewById(R.id.user_address);

        UserBean userBean = list.get(position);
        id.setText("用户账号：" + userBean.getS_id());
        password.setText("用户密码：" + userBean.getS_password());
        contact.setText("联系方式：" + userBean.getS_contact());

        String raw_address = userBean.getS_address();
        switch (raw_address) {
            case "east":
                address.setText("所在校区：东区");
                break;
            case "west":
                address.setText("所在校区：西区");
                break;
            case "mid":
                address.setText("所在校区：中区");
                break;
            case "high":
                address.setText("所在校区：高新区");
                break;
        }

        return convertView;
    }
}
