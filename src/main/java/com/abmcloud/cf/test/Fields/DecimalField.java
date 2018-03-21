package com.abmcloud.cf.test.Fields;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DecimalField extends BaseField {

    public WebElement getField(String nameOfField) {
         return $(By.xpath("//label[contains(text(), '"+nameOfField+"')]/following-sibling::*/input"));
    }

    public String getValue(String nameOfField) {
        WebElement headerOfDecimalField = getField(nameOfField).findElement(By.xpath(".//parent::div//parent::div//parent::decimal-field"));
        String decimalValue = headerOfDecimalField.getAttribute("ng-reflect-value");
        return decimalValue;
    }

    public boolean isDisabled(String nameOfField) {
        WebElement field = getField(nameOfField);
        String valueOfAttribute = "true";
        boolean isDisabled = valueOfAttribute.equals(field.getAttribute("readonly"));
        return isDisabled;
    }

    public boolean isDisabledTCH(String nameOfField) {
        WebElement field = getTCHField(nameOfField);
        String valueOfAttribute = "true";
        boolean isDisabled = valueOfAttribute.equals(field.getAttribute("readonly"));
        return isDisabled;
    }

    public WebElement getTCHField(String nameOfField) {
        return $(By.xpath("//*[contains(text(), '"+nameOfField+"')]//parent::td//input"));
    }

    public String getValueTCH(String nameOfField) {
        WebElement headerOfDecimalField = getTCHField(nameOfField).findElement(By.xpath(".//parent::div//parent::div//parent::decimal-field"));
        String decimalValue = headerOfDecimalField.getAttribute("ng-reflect-value");
        if(decimalValue == null) decimalValue = "";
        return decimalValue;
    }
}