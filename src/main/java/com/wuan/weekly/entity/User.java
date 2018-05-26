package com.wuan.weekly.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class User {
    private Integer id;
    private String userName;
    private String email;
    private String wuanName;
    private String password;
    @JsonProperty(value = "QQ")
    private String QQ;
    private Integer auth = 0;
    private Integer deleteFlg = 0;
    private Date createTime;
    private Date modifyTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", wuanName='" + wuanName + '\'' +
                ", password='" + password + '\'' +
                ", QQ='" + QQ + '\'' +
                ", auth=" + auth +
                ", deleteFlg=" + deleteFlg +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWuanName() {
        return wuanName;
    }

    public void setWuanName(String wuanName) {
        this.wuanName = wuanName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
