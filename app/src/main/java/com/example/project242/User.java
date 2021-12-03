package com.example.project242;


import android.widget.EditText;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int userID;
    private String name;
    private String username;
    private String password;
    private Date DOB;
    private String gender;
    private String phoneNo;
    private String email;

    public User(){

    }

    public User(int userID, String name, String username, String password, Date DOB, String gender, String phoneNo, String email){
        this.userID=userID;
        this.name=name;
        this.username=username;
        this.password=password;
        this.DOB=DOB;
        this.gender=gender;
        this.phoneNo=phoneNo;
        this.email=email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
