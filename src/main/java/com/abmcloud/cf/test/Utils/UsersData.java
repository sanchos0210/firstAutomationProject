package com.abmcloud.cf.test.Utils;

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

    public static String pass = "Adelina-4";

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
