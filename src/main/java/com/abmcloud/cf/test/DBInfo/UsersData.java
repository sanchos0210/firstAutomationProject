package com.abmcloud.cf.test.DBInfo;

public class UsersData {

    private String userName;
    private String userEmail;
    private String userPassword;
    private char localizeLanguage;

    public UsersData(String name, String email ,String pass, char lang) {
        userName = name;
        userEmail = email;
        userPassword = pass;
        localizeLanguage = lang;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public char getLocalizeLanguage() {
        return localizeLanguage;
    }
}
