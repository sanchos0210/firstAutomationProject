package com.abmcloud.cf.test.DataInfo;

import com.abmcloud.cf.test.API.API;
import org.openqa.selenium.WebDriver;

public abstract class EditAppData extends API {

    protected WebDriver driver;

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    public abstract void createApp();

    /*public void catalogElementClick(WebElement element) {
        waitForElementClicable(10, element);
        element.click();
    }*/
}
