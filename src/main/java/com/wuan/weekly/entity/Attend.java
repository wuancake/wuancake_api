package com.wuan.weekly.entity;

/**
 * @author Nobody
 * Date: 2018/3/24
 * Time: 9:33
 */
public class Attend {
    private Integer user_id;
    private Integer group_id = 0;
    private Integer status;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
