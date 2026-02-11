package com.lottiefiles.dataBase;

public class User {
    public int userID;
    public String name;
    public String password;

    public User(int userID, String name, String password) {
        this.userID = userID;
        this.name = name;
        this.password = password;
    }
}
