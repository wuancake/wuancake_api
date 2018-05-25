package com.wuan.weekly.entity;

import java.util.Date;

public class User {
    private Integer id;
    private String user_name;
    private String email;
    private String wuan_name;
    private String password;
    private String QQ;
    private Integer auth = 0;
    private Integer deleteFlg = 0;
    private Date create_time;
    private Date modify_time;


    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", wuan_name='" + wuan_name + '\'' +
                ", password='" + password + '\'' +
                ", QQ='" + QQ + '\'' +
                ", auth=" + auth +
                ", deleteFlg=" + deleteFlg +
                ", create_time=" + create_time +
                ", modify_time=" + modify_time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWuan_name() {
        return wuan_name;
    }

    public void setWuan_name(String wuan_name) {
        this.wuan_name = wuan_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public Integer getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Integer deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }
}
