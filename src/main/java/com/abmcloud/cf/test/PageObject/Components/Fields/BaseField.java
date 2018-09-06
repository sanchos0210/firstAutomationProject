package com.abmcloud.cf.test.PageObject.Components.Fields;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.Logs;
import com.abmcloud.cf.test.Driver.ObjectManager;
import org.openqa.selenium.WebElement;

public abstract class BaseField {

    Driver driver;
    ObjectManager objectManager;
    Logs logs;

    public BaseField(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
        this.logs = new Logs(BaseField.class.getName());
    }

//    @Override
//    public WebDriver getWebDriver() {
//        return driver.getWebDriver();
//    }

    public abstract WebElement getField(String nameOfField);

    public abstract String getValue(String nameOfField);
}
