package com.abmcloud.cf.test.Fields;

import com.abmcloud.cf.test.API.API;
import com.abmcloud.cf.test.API.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseField extends API {

    @Override
    public WebDriver getWebDriver() {
        return BaseTest.driver;
    }

    public abstract WebElement getField(String nameOfField);

    public abstract String getValue(String nameOfField);
}
