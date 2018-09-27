package com.abmcloud.cf.test.PageObject.Components.Fields;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.Logs;
import com.abmcloud.cf.test.Driver.ObjectManager;

public abstract class BaseField {

    protected Driver driver;
    protected ObjectManager objectManager;
    protected Logs logs;
    protected int timeOut = 10000;

    public BaseField(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
        this.logs = objectManager.getLogs();
    }

//    @Override
//    public WebDriver getWebDriver() {
//        return driver.getWebDriver();
//    }

//    public abstract WebElement getField(String nameOfField);
//
//    public abstract String getValue(String nameOfField);
}
