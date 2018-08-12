package com.abmcloud.cf.test.API;

import com.abmcloud.cf.test.pages.*;
import com.abmcloud.cf.test.steps.*;

public class ObjectManager {

    private Driver driver;

    //All objects which ObjectManager creates
    private Wait wait;
    //steps
    private LoginSteps loginSteps;
    private AppListSteps appListSteps;
    private AppFormSteps appFormSteps;
    private CalendarSteps calendarSteps;
    private CalendarListSteps calendarListSteps;
    private Asserts asserts;
    //pages
    private LoginPage loginPage;
    private AppListPage appListPage;
    private AppEditPage appEditPage;
    private CalendarPage calendarPage;
    private ConfirmElement confirmElement;

    public ObjectManager(Driver driver) {
        this.driver = driver;
    }

    public Wait getWait() {
        if(wait == null) wait = new Wait(driver);
        return wait;
    }

    public LoginSteps getLoginSteps() {
        if(loginSteps == null) loginSteps = new LoginSteps(driver, this);
        return loginSteps;
    }

    public AppListSteps getAppListSteps() {
        if(appListSteps == null) appListSteps = new AppListSteps(driver, this);
        return appListSteps;
    }

    public AppFormSteps getAppFormSteps() {
        if(appFormSteps == null) appFormSteps= new AppFormSteps(driver, this);
        return appFormSteps;
    }

    public CalendarSteps getCalendarSteps() {
        if(calendarSteps == null) calendarSteps = new CalendarSteps(driver, this);
        return calendarSteps;
    }

    public CalendarListSteps getCalendarListSteps() {
        if(calendarListSteps == null) calendarListSteps = new CalendarListSteps(driver, this);
        return calendarListSteps;
    }
    public Asserts getAsserts() {
        if(asserts == null) asserts = new Asserts(driver, this);
        return asserts;
    }

    public LoginPage getLoginPage() {
        if(loginPage == null) loginPage = new LoginPage(driver);
        return loginPage;
    }

    public AppListPage getAppListPage() {
        if(appListPage == null) appListPage = new AppListPage(driver);
        return appListPage;
    }

    public AppEditPage getAppEditPage() {
        if(appEditPage == null) appEditPage = new AppEditPage(driver);
        return appEditPage;
    }

    public CalendarPage getCalendarPage() {
        if(calendarPage == null) calendarPage = new CalendarPage(driver);
        return calendarPage;
    }

    public ConfirmElement getConfirmElement() {
        if(confirmElement == null) confirmElement = new ConfirmElement(driver);
        return confirmElement;
    }
}
