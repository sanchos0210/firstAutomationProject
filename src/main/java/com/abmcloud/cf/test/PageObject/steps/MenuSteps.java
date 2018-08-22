package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuSteps extends HederSteps {

    private static final char APP_LIST = 'A';
    private static final char CALENDAR = 'B';
    private static final char CATALOGS = 'C';

    private void openMenuTab(WebElement menuButton, char typeOfPage) {
        menuButton.click();
        switch(typeOfPage) {
            case APP_LIST: {
                getWait().loginWait();
                break;
            }
            case CALENDAR: {
                getWait().calendarPreloadWait();
                break;
            }
            case CATALOGS: {
                getWait().catalogPreloadWait();
                break;
            }
        }
    }

    @Step("Открыть список заявок:")
    public AppListSteps openAppList(String nameOfMenuButton) {
        openMenuTab(driver.$(By.xpath("//*[contains(text(), '"+nameOfMenuButton+"')]")), APP_LIST);
        return getAppListSteps();
    }

    @Step("Открыть календарь:")
    public CalendarTableSteps openCalendar(String nameOfMenuButton) {
        openMenuTab(driver.$(By.xpath("//*[contains(text(), '"+nameOfMenuButton+"')]")), CALENDAR);
        return getCalendarSteps();
    }

    @Step("Открыть каталоги:")
    public CatalogSteps openCatalogs() {
        String nameOfMenuButton = null;
        switch(objectManager.getTestInfo().activeUser.getLocalizeLanguage()) {
            case BaseTest.EN: {
                nameOfMenuButton = "Catalogs";
                break;
            }
            case BaseTest.RU: {
                nameOfMenuButton = "Каталоги";
                break;
            }
        }
        openMenuTab(driver.$(By.xpath("//*[contains(text(), '"+nameOfMenuButton+"')]")), CATALOGS);
        return objectManager.getCatalogSteps();
    }
}