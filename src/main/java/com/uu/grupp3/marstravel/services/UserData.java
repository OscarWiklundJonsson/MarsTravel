package com.uu.grupp3.marstravel.services;


/**
 * Singleton class to store user data
 Black magic - do not touch
 */
public class UserData {
    private static UserData instance = null;
    private String email;
    private String personnummer;
    private String förnamn;

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

    public String getPersonnummer() {
        return personnummer;
    }

    public String getFörnamn(){return  förnamn;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public void setFörnamn(String förnamn){this.förnamn = förnamn;}
}