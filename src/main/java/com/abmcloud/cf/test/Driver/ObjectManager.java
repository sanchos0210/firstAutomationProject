package com.abmcloud.cf.test.Driver;

import com.abmcloud.cf.test.PageObject.Components.Confirmation;
import com.abmcloud.cf.test.PageObject.Components.DatePicker;
import com.abmcloud.cf.test.PageObject.Components.Fields.*;
import com.abmcloud.cf.test.PageObject.Components.Notification;
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
    private CatalogListSteps catalogListSteps;
    private CatalogFormSteps catalogFormSteps;
    private Asserts asserts;
    private StepsPopup stepsPopup;
    private UserProfileSteps userProfileSteps;
    //pages
    private LoginPage loginPage;
    private AppListPage appListPage;
    private AppEditPage appEditPage;
    private CalendarPage calendarPage;
    private CatalogListPage catalogListPage;
    private CatalogFormPage catalogFormPage;
    //components
    private DatePicker datePicker;
    private Notification notification;
    private Confirmation confirmation;
    //Fields in application form
    private DecimalField decimalField;
    private StringField stringField;
    private BooleanField booleanField;
    private CatalogField catalogField;
    private DateField dateField;

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

    public CatalogListSteps getCatalogListSteps() {
        if(catalogListSteps == null) catalogListSteps = new CatalogListSteps(driver, this);
        return catalogListSteps;
    }

    public CatalogFormSteps getCatalogFormSteps() {
        if(catalogFormSteps == null) catalogFormSteps = new CatalogFormSteps(this);
        return catalogFormSteps;
    }

    public UserProfileSteps getUserProfileSteps() {
        if(userProfileSteps == null) userProfileSteps = new UserProfileSteps(this);
        return userProfileSteps;
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

    public CatalogListPage getCatalogListPage() {
        if(catalogListPage == null) catalogListPage = new CatalogListPage(driver);
        return catalogListPage;
    }

    public CatalogFormPage getCatalogFormPage() {
        if(catalogFormPage == null) catalogFormPage = new CatalogFormPage(driver);
        return catalogFormPage;
    }

    public DatePicker getDatePicker() {
        if(datePicker == null) datePicker = new DatePicker(driver);
        return datePicker;
    }

    public Notification getNotification() {
        if(notification == null) notification = new Notification(this);
        return notification;
    }

    public Confirmation getConfirmation() {
        if(confirmation == null) confirmation = new Confirmation(driver);
        return confirmation;
    }

    public DecimalField getDecimalField() {
        if(decimalField == null) decimalField = new DecimalField(driver, this);
        return decimalField;
    }

    public BooleanField getBooleanField() {
        if(booleanField == null) booleanField = new BooleanField(driver, this);
        return booleanField;
    }

    public DateField getDateField() {
        if(dateField == null) dateField = new DateField(driver, this);
        return dateField;
    }

    public CatalogField getCatalogField() {
        if(catalogField == null) catalogField = new CatalogField(driver, this);
        return catalogField;
    }

    public StringField getStringField() {
        if(stringField == null) stringField = new StringField(driver, this);
        return stringField;
    }

    public DateUtil getDateUtil() {
        return new DateUtil();
    }
}
