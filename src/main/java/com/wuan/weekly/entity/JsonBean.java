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
    private Integer user_id;
    private Integer group_id;

    private List<WaGroup> groups;

    public List<WaGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<WaGroup> groups) {
        this.groups = groups;
    }

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


}
