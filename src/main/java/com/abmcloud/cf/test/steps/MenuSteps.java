package com.abmcloud.cf.test.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuSteps extends HederSteps {

    private static final char APP_LIST = 'A';
    private static final char CALENDAR = 'B';

    private void openMenuTab(WebElement menuButton, char typeOfPage) {
        menuButton.click();
        switch(typeOfPage) {
            case 'A': {     //for application list
                getWait().loginWait();
                break;
            }
            case 'B': {     //for calendar
                getWait().calendarPreloadWait();
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
    public CalendarSteps openCalendar(String nameOfMenuButton) {
        openMenuTab(driver.$(By.xpath("//*[contains(text(), '"+nameOfMenuButton+"')]")), CALENDAR);
        return getCalendarSteps();
    }
}
