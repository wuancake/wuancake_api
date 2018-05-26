package com.wuan.weekly.entity;

import java.util.Date;

public class WaGroup {
    private Integer id;
    private String groupName;
    private Integer deleteFlg;
    private Date createTime;
    private Date modifyTime;

    @Override
    public String toString() {
        return "WaGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", deleteFlg=" + deleteFlg +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
