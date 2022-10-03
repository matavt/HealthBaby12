package com.matavt.healthbaby12;

import java.util.Date;

public class User {
    private static User instance = null;

    private String userName, childName;
    private Date childDoB;

    private User() {
        userName ="";
        childName ="";
        childDoB = new Date();
    }

    public static User getInstance(){
        if (instance == null){
            instance = new User();
        }
        return instance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setChildDoB(Date childDoB) {
        this.childDoB = childDoB;
    }

    public String getUserName() {
        return userName;
    }

    public String getChildName() {
        return childName;
    }

    public Date getChildDoB() {
        return childDoB;
    }
}
