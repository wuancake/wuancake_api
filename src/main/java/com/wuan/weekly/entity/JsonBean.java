package com.wuan.weekly.entity;

import java.util.List;

/**
 * @author Nobody
 * Date: 2018/3/24
 * Time: 10:30
 */
public class JsonBean {

    //响应内容
    private String infoText;
    //响应码
    private String infoCode;
    //用户id
    private Integer userId;
    //分组id
    private Integer groupId;
    //用户名
    private String userName;
    //分组名
    private String groupName;
    //当前周数（微信小程序登录后用）
    private Integer currWeek;
    //周报状态（微信小程序登录后用）
    private Integer status;

    public Integer getCurrWeek() {
        return currWeek;
    }

    public void setCurrWeek(Integer currWeek) {
        this.currWeek = currWeek;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    private List<WaGroup> groups;

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<WaGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<WaGroup> groups) {
        this.groups = groups;
    }


}
