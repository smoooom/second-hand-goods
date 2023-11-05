package com.example.myapplication.Bean;

import java.io.Serializable;
public class CategoryBean implements Serializable{
    private String category_title;
    private int category_icon_id;


    public String get_title() {
        return category_title;
    }

    public void set_title(String category_title) {
        this.category_title = category_title;
    }

    public int get_icon() {
        return category_icon_id;
    }

    public void set_icon(int category_icon_id) {
        this.category_icon_id = category_icon_id;
    }

    public CategoryBean() {
    }

    public CategoryBean(String category_title, int category_icon_id) {
        this.category_title = category_title;
        this.category_icon_id = category_icon_id;

    }
}
