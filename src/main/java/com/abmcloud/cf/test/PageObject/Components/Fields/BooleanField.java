package com.abmcloud.cf.test.PageObject.Components.Fields;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class BooleanField extends BaseField {

    public BooleanField(Driver driver, ObjectManager objectManager) {
        super(driver, objectManager);
    }

    private WebElement getHeaderOfBooleanField(String nameOfField) {
        WebElement headerOfBooleanField = driver.$(By.xpath("//*[contains(text(), '"+nameOfField+"')]//parent::bool-field"));
        return headerOfBooleanField;
    }

    private WebElement getHeaderOfBooleanFieldTCH(String nameOfField) {
        WebElement headerOfBooleanField = driver.$(By.xpath("//*[contains(text(), '"+nameOfField+"')]//following-sibling::*/bool-field"));
        return headerOfBooleanField;
    }

    public WebElement getField(String nameOfField) {
        WebElement booleanField = driver.$(By.xpath("//*[contains(text(), '"+nameOfField+"')]//following-sibling::*//*[@class='pointer select_btn']"));
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
        if(driver.$((("bool-field[ng-reflect-key='in_out']"))).getAttribute("ng-reflect-value").equals("false")) {
            inOutValue = "Outflow";
        }
        else inOutValue = "Inflow";
        return inOutValue;
    }

    public boolean isInOutDisable() {
        try {
            driver.$("bool-field[ng-reflect-key='in_out'] .pointer.select_btn").click();
            return false;
        }catch(TimeoutException e) {
            return true;
        }
    }

    public void booleanButtonClick(String nameOfField) {
        try {
            WebElement button = getField(nameOfField);
            button.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

}