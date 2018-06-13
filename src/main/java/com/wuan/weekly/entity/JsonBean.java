package com.wuan.weekly.entity;

import java.util.List;

/**
 * @author Nobody
 * Date: 2018/3/24
 * Time: 10:30
 */
public class JsonBean {

    private String infoText;
    private String infoCode;
    private Integer userId;
    private Integer groupId;
    private String userName;
    private String groupName;

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
