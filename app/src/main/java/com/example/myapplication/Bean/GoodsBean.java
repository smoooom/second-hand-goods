package com.example.myapplication.Bean;

public class GoodsBean {
    private String g_id;
    private String s_id;
    private String g_price;
    private String g_name;
    private String g_type;
    private String g_describe;
    private String g_picture;

    @Override
    public String toString() {
        return "GoodsBean{" +
                "g_id='" + g_id + '\'' +
                ", s_id='" + s_id + '\'' +
                ", g_price='" + g_price + '\'' +
                ", g_name='" + g_name + '\'' +
                ", g_type='" + g_type + '\'' +
                ", g_describe='" + g_describe + '\'' +
                ", g_picture='" + g_picture + '\'' +
                '}';
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getG_price() {
        return g_price;
    }

    public void setG_price(String g_price) {
        this.g_price = g_price;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_type() {
        return g_type;
    }

    public void setG_type(String g_type) {
        this.g_type = g_type;
    }

    public String getG_describe() {
        return g_describe;
    }

    public void setG_describe(String g_describe) {
        this.g_describe = g_describe;
    }

    public String getG_picture() {
        return g_picture;
    }

    public void setG_picture(String g_picture) {
        this.g_picture = g_picture;
    }

    public GoodsBean() {
    }

    public GoodsBean(String g_id, String s_id, String g_price, String g_name, String g_type, String g_describe, String g_picture) {
        this.g_id = g_id;
        this.s_id = s_id;
        this.g_price = g_price;
        this.g_name = g_name;
        this.g_type = g_type;
        this.g_describe = g_describe;
        this.g_picture = g_picture;
    }
}
