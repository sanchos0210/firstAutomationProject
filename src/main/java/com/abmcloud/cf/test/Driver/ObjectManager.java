package com.abmcloud.cf.test.Driver;

import com.abmcloud.cf.test.PageObject.Components.DatePicker;
import com.abmcloud.cf.test.PageObject.Components.Fields.*;
import com.abmcloud.cf.test.PageObject.Components.StepsPopup;
import com.abmcloud.cf.test.PageObject.pages.*;
import com.abmcloud.cf.test.PageObject.steps.*;
import com.abmcloud.cf.test.Utils.DateUtil;
import com.abmcloud.cf.test.Utils.TestInfo;

public class ObjectManager {

    private Driver driver;
    private TestInfo testInfo;

    //All objects which ObjectManager creates
    private Wait wait;
    //steps
    private LoginSteps loginSteps;
    private AppListSteps appListSteps;
    private AppFormSteps appFormSteps;
    private CalendarTableSteps calendarTableSteps;
    private CalendarListSteps calendarListSteps;
    private CatalogSteps catalogSteps;
    private Asserts asserts;
    private StepsPopup stepsPopup;
    //pages
    private LoginPage loginPage;
    private AppListPage appListPage;
    private AppEditPage appEditPage;
    private CalendarPage calendarPage;
    private ConfirmElement confirmElement;
    //components
    private DatePicker datePicker;
    //Fields in application form
    BaseField baseField;

    public ObjectManager() {
        driver = new Driver();
        testInfo = new TestInfo();
    }

    public Driver getDriver() {
        return driver;
    }

    public TestInfo getTestInfo() {
        return testInfo;
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

    public CalendarTableSteps getCalendarTableSteps() {
        if(calendarTableSteps == null) calendarTableSteps = new CalendarTableSteps(driver, this);
        return calendarTableSteps;
    }

    public CalendarListSteps getCalendarListSteps() {
        if(calendarListSteps == null) calendarListSteps = new CalendarListSteps(driver, this);
        return calendarListSteps;
    }

    public CatalogSteps getCatalogSteps() {
        if(catalogSteps == null) catalogSteps = new CatalogSteps(driver, this);
        return catalogSteps;
    }

    public Asserts getAsserts() {
        if(asserts == null) asserts = new Asserts(driver, this);
        return asserts;
    }

    public StepsPopup getStepsPopup() {
        if(stepsPopup == null) stepsPopup = new StepsPopup(driver);
        return stepsPopup;
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

    public DatePicker getDatePicker() {
        if(datePicker == null) datePicker = new DatePicker(driver);
        return datePicker;
    }

    public DecimalField getDecimalField() {
        baseField = new DecimalField(driver);
        return (DecimalField) baseField;
    }

    public BooleanField getBooleanField() {
        baseField = new BooleanField(driver);
        return (BooleanField) baseField;
    }

    public DateField getDateField() {
        baseField = new DateField(driver);
        return (DateField) baseField;
    }

    public CatalogField getCatalogField() {
        baseField = new CatalogField(driver);
        return (CatalogField) baseField;
    }

    public StringField getStringField() {
        baseField = new StringField(driver);
        return (StringField) baseField;
    }

    public DateUtil getDateUtil() {
        return new DateUtil();
    }
}
