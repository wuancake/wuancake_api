package com.wuan.weekly.entity;


/**
 * @author Ericheel
 * 安卓所需 保证时效性 Entity
 */
public class EffectiveEntity {
    private User user;  //用户信息表-密码除外

    private String groupName; //用户所在分组

    private String infoCode;

    private String infoText;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public EffectiveEntity(User user, String groupName, String infoCode, String infoText) {
        if (user != null) {
            user.setPassword("");
        }
        this.user = user;
        this.groupName = groupName;
        this.infoCode = infoCode;
        this.infoText = infoText;
    }
}
