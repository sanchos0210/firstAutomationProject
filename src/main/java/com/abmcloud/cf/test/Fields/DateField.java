package com.abmcloud.cf.test.Fields;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DateField extends BaseField {

    public WebElement getField(String nameOfField) {
        return $(By.xpath("//*[contains(text(), '"+nameOfField+"')]//following::span"));
    }

    public String getValue(String nameOfField) {
        return getField(nameOfField).getText();
    }

    public WebElement getTCHField(String nameOfField) {
        return $(By.xpath("//*[contains(text(), '"+nameOfField+"')]//following-sibling::span//span"));
    }
}
