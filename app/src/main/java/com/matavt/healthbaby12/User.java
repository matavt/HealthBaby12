//Singleton to store our user information for use throughout the app.

package com.matavt.healthbaby12;

import java.util.GregorianCalendar;

public class User {
    private static User instance = null;

    private String userName, childName;
    private GregorianCalendar childDoB;

    private User() {
        userName ="";
        childName ="";
        childDoB = new GregorianCalendar();
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

    public void setChildDoB(GregorianCalendar childDoB) {
        this.childDoB = childDoB;
    }

    public String getUserName() {
        return userName;
    }

    public String getChildName() {
        return childName;
    }

    public GregorianCalendar getChildDoB() {
        return childDoB;
    }
}
