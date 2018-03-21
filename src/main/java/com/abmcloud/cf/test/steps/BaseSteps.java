package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.Asserts;
import com.abmcloud.cf.test.API.API;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseSteps extends API {

   public static WebDriver driver;

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    public AppListSteps openAppList(WebElement menuButton) {
        menuButton.click();
        loginWait();
        return new AppListSteps();
    }

    public Asserts asserts() {
        return new Asserts();
    }
}
