package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.API.ObjectManager;
import com.abmcloud.cf.test.Fields.DateField;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CalendarListSteps extends BaseSteps {

    public CalendarListSteps(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
    }

    @Step("Открыть реестр на дату:")
    public CalendarListSteps changeDateInRegistryOn(String date) {
        objectManager.getCalendarPage().dateInRegistry.click();
        DateField dateField = new DateField(driver);
        dateField.getDateInDatePicker(driver.$(By.xpath("//*[@class='daterangepicker dropdown-menu ltr single opensright show-calendar'][last()-1]")), date).click();
        return this;
    }

    @Step("Выбрать платежку с номером:")
    public CalendarListSteps checkAppWithNumber(String numberOfApp) {
        AppListSteps appListSteps = getAppListSteps();
        appListSteps.selectAppByNumberInTable(numberOfApp, driver.$$(By.cssSelector("table.appl_table.bg_on_hover tbody tr")));
        appListSteps.clickOn(objectManager.getAppListPage().checkBox, BaseTest.selectedApp);
        return this;
    }

    @Step("Оплатить выбранные платежки")
    public CalendarListSteps payButtonClick() {
        objectManager.getCalendarPage().payButton.click();
        getNotification().notificationClick();
        getWait().waitForElementClickable(3, objectManager.getCalendarPage().headerOfRegistry);
        return this;
    }

    @Step("Утвердить выбранные платежки")
    public CalendarListSteps approveButtonClick() {
        objectManager.getCalendarPage().approveButton.click();
        getNotification().notificationClick();
        getWait().waitForElementClickable(3, objectManager.getCalendarPage().headerOfRegistry);
        return this;
    }

    @Step("Закрыть реестр")
    public CalendarSteps closeRegistry() {
        objectManager.getCalendarPage().closeRegistry.click();
        getWait().calendarPreloadWait();
        return getCalendarSteps();
    }

    @Step("Изменить дату оплаты в реестре на:")
    public CalendarListSteps changePaymentDate(String date) {
        DateField dateField = new DateField(driver);
        dateField.getField("Сменить дату оплаты").click();
        dateField.getDateInDatePicker(driver.$(By.xpath("//*[@class='daterangepicker dropdown-menu ltr single opensright show-calendar'][last()]")), date).click();
        objectManager.getCalendarPage().changePaymentDateApproveButton.click();
        getWait().waitForElementClickable(3, objectManager.getCalendarPage().headerOfRegistry);
        getNotification().notificationClick();
        return this;
    }
}
