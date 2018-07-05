package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.Asserts;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.API.Wait;
import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.CalendarPage;
import com.abmcloud.cf.test.pages.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaseSteps {

   public Driver driver;
   public Wait wait;
   public LoginPage loginPage;
   public AppListPage appListPage;
   public AppEditPage appEditPage;
   public CalendarPage calendarPage;

    private static final char APP_LIST = 'A';
    private static final char CALENDAR = 'B';

   public Wait getWait() {
       if(wait == null) {
           wait = new Wait(driver);
       }
       return wait;
   }

    protected LoginSteps getLoginSteps() {
        return new LoginSteps(driver);
    }

    protected AppListSteps getAppListSteps() {
        return new AppListSteps(driver);
    }

    protected AppFormSteps getAppFormSteps() {
        return new AppFormSteps(driver);
    }

    protected CalendarSteps getCalendarSteps() {
        return new CalendarSteps(driver);
    }

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

    public Asserts asserts() {
        return new Asserts(driver);
    }
}
