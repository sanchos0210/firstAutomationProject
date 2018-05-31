package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.API;
import com.abmcloud.cf.test.API.Asserts;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseSteps extends API {

   public static WebDriver driver;

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    private void openMenuTab(WebElement menuButton, char typeOfPage) {
        menuButton.click();
        switch(typeOfPage) {
            case 'A': {     //for application list
                loginWait();
                break;
            }
            case 'B': {     //for calendar
                calendarPreloadWait();
                break;
            }
        }
    }

    @Step("Открыть список заявок:")
    public AppListSteps openAppList(WebElement menuButton) {
        openMenuTab(menuButton, APP_LIST);
        return new AppListSteps();
    }

    @Step("Открыть календарь:")
    public CalendarSteps openCalendar(WebElement menuButton) {
        openMenuTab(menuButton, CALENDAR);
        return new CalendarSteps();
    }

    public Asserts asserts() {
        return new Asserts();
    }
}
