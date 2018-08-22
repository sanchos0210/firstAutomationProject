package com.abmcloud.cf.test.PageObject.Components.Fields;

import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DecimalField extends BaseField {

    public DecimalField(Driver driver) {
        super(driver);
    }

    public WebElement getField(String nameOfField) {
             return driver.$(By.xpath("//label[contains(text(), '"+nameOfField+"')]/following-sibling::*/input"));
    }

    public String getValue(String nameOfField) {
        return getValue(getField(nameOfField));
    }

    private String getValue(WebElement field) {
        WebElement headerOfDecimalField = field.findElement(By.xpath(".//parent::div//parent::div//parent::decimal-field"));
        String decimalValue = headerOfDecimalField.getAttribute("ng-reflect-value");
        if(decimalValue == null) decimalValue = "";
        return decimalValue;
    }

    public String getPlaceHolder(String nameOfField) {
        return getField(nameOfField).getAttribute("placeholder");
    }

    public String getTCHValue(String nameOfField) {
        return getValue(getTCHField(nameOfField));
    }

    public String getTCHValue(String nameOfField, int rowNumber) {
        return getValue(getTCHField(nameOfField, rowNumber));
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
        return getTCHField(nameOfField, 1);
    }

    public WebElement getTCHField(String nameOfField, int rowNumber) {
        List<WebElement> decimalTCHFields = driver.$$(By.xpath("//*[contains(text(), '"+nameOfField+"')]//parent::td//*[@class='relative d-flex']/input"));
        return decimalTCHFields.get(rowNumber-1);
    }
}