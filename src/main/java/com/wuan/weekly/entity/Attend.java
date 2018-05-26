package com.wuan.weekly.entity;

/**
 * @author Nobody
 * Date: 2018/3/24
 * Time: 9:33
 */
public class Attend {
    private Integer userId;
    private Integer groupId = 0;
    private Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
