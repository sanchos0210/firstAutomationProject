package com.abmcloud.cf.test.Fields;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StringField extends BaseField {

    public WebElement getField(String nameOfField) {
        return $(By.xpath("//label[contains(text(), '"+nameOfField+"')]/following-sibling::*/textarea"));
    }

    public String getValue(String nameOfField) {
        String stringValue;
        WebElement decimalField = getField(nameOfField).findElement(By.xpath(".//parent::*//parent::*//parent::description-field"));
        stringValue = decimalField.getAttribute("ng-reflect-value");
        return stringValue;
    }

    public boolean isDisabled(String nameOfField) {
        WebElement field = getField(nameOfField);
        String valueOfAttribute = "true";
        boolean isDisabled = valueOfAttribute.equals(field.getAttribute("readonly"));
        return isDisabled;
    }

    public WebElement getTCHField(String nameOfField) {
        return $(By.xpath("//*[contains(text(), '"+nameOfField+"')]//parent::td//textarea"));
    }

    public boolean isDisabledTCH(String nameOfField) {
        WebElement field = getTCHField(nameOfField);
        String valueOfAttribute = "true";
        boolean isDisabled = valueOfAttribute.equals(field.getAttribute("readonly"));
        return isDisabled;
    }

    public String getValueTCH(String nameOfField) {
        String stringValue;
        WebElement stringField = getTCHField(nameOfField).findElement(By.xpath(".//parent::*//parent::*//parent::description-field"));
        stringValue = stringField.getAttribute("ng-reflect-value");
        if(stringValue == null) stringValue = "";
        return stringValue;
    }
}