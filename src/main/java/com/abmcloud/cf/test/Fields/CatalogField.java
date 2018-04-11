package com.abmcloud.cf.test.Fields;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CatalogField extends BaseField {

    public WebElement getField(String nameOfField) {
        WebElement catalogField = $(By.xpath("//catalog-field[@*='"+nameOfField+"']"));
        return catalogField;
    }

    public String getValue(String nameOfField) {
        WebElement selectedValue = getField(nameOfField).findElement(By.cssSelector("a"));
        return selectedValue.getText();
    }

    public WebElement getItem(String nameOfItem) {
        return $(By.xpath("//span[contains(text(), '"+nameOfItem+"')]//parent::*//parent::*"));
    }
}
