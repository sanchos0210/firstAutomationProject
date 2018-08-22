package com.abmcloud.cf.test.PageObject.Components.Fields;

import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.WebElement;

public abstract class BaseField {

    Driver driver;

    public BaseField(Driver driver) {
        this.driver = driver;
    }

//    @Override
//    public WebDriver getWebDriver() {
//        return driver.getWebDriver();
//    }

    public abstract WebElement getField(String nameOfField);

    public abstract String getValue(String nameOfField);
}
