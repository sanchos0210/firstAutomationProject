package com.abmcloud.cf.test.PageObject.Components.Fields;

import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CatalogField extends BaseField {

    public CatalogField(Driver driver) {
        super(driver);
    }

    public WebElement getField(String nameOfField) {
        WebElement catalogField = driver.fluentWait(By.xpath("//catalog-field[@*='" + nameOfField + "']"));
        return catalogField;
    }

    public String getValue(String nameOfField) {
        WebElement selectedValue = getField(nameOfField).findElement(By.cssSelector("a"));
        return selectedValue.getText();
    }

    public WebElement getItem(String nameOfItem) {
        return driver.$(By.xpath("//directoryelement//span[contains(text(), '" + nameOfItem + "')]//parent::*//parent::*"));
    }
}