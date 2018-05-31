package com.abmcloud.cf.test.DBInfo;

public class AppField {

    char type;
    String name;
    String value;

    public AppField(char typeOfField, String nameOfField, String valueOfField) {
        type = typeOfField;
        name = nameOfField;
        value = valueOfField;
    }
}