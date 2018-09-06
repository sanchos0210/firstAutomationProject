package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.PageObject.Components.Fields.DateField;
import com.abmcloud.cf.test.PageObject.Components.Notification;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CalendarListSteps extends BaseSteps {

    Notification notification;

    public CalendarListSteps(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
        notification = objectManager.getNotification();
    }

    @Step("Открыть реестр на дату:")
    public CalendarListSteps changeDateInRegistryOn(String date) {
        objectManager.getCalendarPage().dateInRegistry.click();
        DateField dateField = objectManager.getDateField();
        dateField.getDateInDatePicker(driver.$(By.xpath("//*[@class='daterangepicker dropdown-menu ltr single opensright show-calendar'][last()-1]")), date).click();
        return this;
    }

    @Step("Выбрать платежку с номером:")
    public CalendarListSteps checkAppWithNumber(String numberOfApp) {
        AppListSteps appListSteps = getAppListSteps();
        appListSteps.selectAppByNumberInTable(numberOfApp, driver.$$(By.cssSelector("table.appl_table.bg_on_hover tbody tr")));
        appListSteps.clickOn(objectManager.getAppListPage().checkBox, objectManager.getTestInfo().selectedApp);
        return this;
    }

    @Step("Оплатить выбранные платежки")
    public CalendarListSteps payButtonClick() {
        objectManager.getCalendarPage().payButton.click();
        notification.notificationClick();
        getWait().waitForElementClickable(3, objectManager.getCalendarPage().headerOfRegistry);
        return this;
    }

    @Step("Утвердить выбранные платежки")
    public CalendarListSteps approveButtonClick() {
        objectManager.getCalendarPage().approveButton.click();
        notification.notificationClick();
        getWait().waitForElementClickable(3, objectManager.getCalendarPage().headerOfRegistry);
        return this;
    }

    @Step("Закрыть реестр")
    public CalendarTableSteps closeRegistry() {
        objectManager.getCalendarPage().closeRegistry.click();
        getWait().calendarPreloadWait();
        return getCalendarSteps();
    }

    @Step("Изменить дату оплаты в реестре на:")
    public CalendarListSteps changePaymentDate(String date) {
        DateField dateField = objectManager.getDateField();
        //dateField.getField("Сменить дату оплаты").click();  заменил на следующую строку
        driver.fluentWait(By.xpath("//*[contains(text(), 'Сменить дату оплаты')]//parent::div//date-field//following::div")).click();
        dateField.getDateInDatePicker(driver.$(By.xpath("//*[@class='daterangepicker dropdown-menu ltr single opensright show-calendar'][last()]")), date).click();
        objectManager.getCalendarPage().changePaymentDateApproveButton.click();
        getWait().waitForElementClickable(3, objectManager.getCalendarPage().headerOfRegistry);
        notification.notificationClick();
        return this;
    }
}
