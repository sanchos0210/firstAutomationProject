package com.abmcloud.cf.test.Fields;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class BooleanField extends BaseField {

    private WebElement getHeaderOfBooleanField(String nameOfField) {
        WebElement headerOfBooleanField = $(By.xpath("//*[contains(text(), '"+nameOfField+"')]//parent::bool-field"));
        return headerOfBooleanField;
    }

    private WebElement getHeaderOfBooleanFieldTCH(String nameOfField) {
        WebElement headerOfBooleanField = $(By.xpath("//*[contains(text(), '"+nameOfField+"')]//following-sibling::*/bool-field"));
        return headerOfBooleanField;
    }

    public WebElement getField(String nameOfField) {
        WebElement booleanField = $(By.xpath("//*[contains(text(), '"+nameOfField+"')]//following-sibling::*//*[@class='pointer select_btn']"));
        return booleanField;
    }

    public String getValue(String nameOfField) {
        return getHeaderOfBooleanField(nameOfField).getAttribute("ng-reflect-value");
    }

    public String getValueTCH(String nameOfField) {
        return getHeaderOfBooleanFieldTCH(nameOfField).getAttribute("ng-reflect-value");
    }

    public boolean isDisable(String nameOfField) {
        try {
            getField(nameOfField).click();
            return false;
        }catch(TimeoutException e) {
            return true;
        }
    }

    public String getInOutValue() {
        String inOutValue;
        if($((("bool-field[ng-reflect-key='in_out']"))).getAttribute("ng-reflect-value").equals("false")) {
            inOutValue = "Outflow";
        }
        else inOutValue = "Inflow";
        return inOutValue;
    }

    public boolean isInOutDisable() {
        try {
            $("bool-field[ng-reflect-key='in_out'] .pointer.select_btn").click();
            return false;
        }catch(TimeoutException e) {
            return true;
        }
    }
}