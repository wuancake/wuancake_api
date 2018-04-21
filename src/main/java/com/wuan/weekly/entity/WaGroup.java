package com.wuan.weekly.entity;

import java.util.Date;

public class WaGroup {
    private Integer id;
    private String group_name;
    private Integer deleteFlg;
    private Date create_time;
    private Date modify_time;

    @Override
    public String toString() {
        return "WaGroup{" +
                "id=" + id +
                ", group_name='" + group_name + '\'' +
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

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
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
