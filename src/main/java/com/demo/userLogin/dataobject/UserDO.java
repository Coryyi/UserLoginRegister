package com.demo.userLogin.dataobject;

import com.demo.userLogin.model.User;

import java.time.LocalDateTime;

public class UserDO {

    private long id;

    private String userName;

    private String pwd;

    private String avatar;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public User toModel(UserDO userDO){
        // 将 UserDO 对象转化为 User 对象
        User user = new User();
        user.setId(userDO.getId());
        user.setUserName(userDO.getUserName());
        user.setAvatar(getAvatar());
        user.setGmtCreated(getGmtCreated());
        user.setGmtModified(getGmtModified());
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }
}
