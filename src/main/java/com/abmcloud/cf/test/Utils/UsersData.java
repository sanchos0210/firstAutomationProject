package com.abmcloud.cf.test.Utils;

import com.abmcloud.cf.test.Driver.Constants;

public class UsersData {

    private String userName;
    private String userEmail;
    private String userPassword;
    private Constants localizeLanguage;

    public UsersData(String name, String email ,String pass, Constants lang) {
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

    public Constants getLocalizeLanguage() {
        return localizeLanguage;
    }
}
