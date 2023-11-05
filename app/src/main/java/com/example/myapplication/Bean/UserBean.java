package com.example.myapplication.Bean;

public class UserBean {
    private String s_id;
    private String s_password;
    private String s_contact;
    private String s_address;

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_contact() {
        return s_contact;
    }

    public void setS_contact(String s_contact) {
        this.s_contact = s_contact;
    }

    public UserBean(String sId, String sPassword, String sContact, String s_address) {
        this.s_id = sId;
        this.s_password = sPassword;
        this.s_contact = sContact;
        this.s_address = s_address;
    }
}
