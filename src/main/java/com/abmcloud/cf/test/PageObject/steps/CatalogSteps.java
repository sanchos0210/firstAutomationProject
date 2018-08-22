package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
import io.qameta.allure.Step;

public class CatalogSteps extends MenuSteps {

    public CatalogSteps(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
    }

    @Step("Найти элемент каталога с именем:")
    public CatalogSteps selectItemByName(String nameOfItem) {
        return this;
    }
}
