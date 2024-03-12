package com.uu.grupp3.marstravel.services;


/**
 * Singleton class to store user data
 Black magic - do not touch
 */
public class UserData {
    private static UserData instance = null;
    private String email;

    private UserData() {}

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}