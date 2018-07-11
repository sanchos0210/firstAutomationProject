package com.abmcloud.cf.test.API;

import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.CalendarPage;
import com.abmcloud.cf.test.pages.LoginPage;
import com.abmcloud.cf.test.steps.LoginSteps;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static ThreadLocal<Driver> tlDriver = new ThreadLocal<>();
    private Driver driver;
    protected LoginSteps steps;
    protected Helpers helpers;

    protected LoginPage loginPage;
    protected AppListPage appListPage;
    protected AppEditPage appEditPage;
    protected CalendarPage calendarPage;

    public static UsersData activeUser;
    public static WebElement selectedApp;
    public static String numberOfSelectedApp;
    public static String statusOfSelectedApp;
    public static String numberOfCreatedApp;
    public static String textOfNotification;

    //-----------------------------------------------CONSTANT VALUES----------------------------------------------------l
    //URL constants
    public static final String APP_FORM_DEMO_DB = "https://dev6.cf.abmcloud.com";     //"https://test12.cf.abmcloud.com";       //https://demo12.cf.abmcloud.com";
    public static final String APP_LIST_DEMO_DB = "https://dev5.cf.abmcloud.com";
    public static final String CALENDAR_DEMO_DB = "";//https://demo12.cf.abmcloud.com";
    //--------------------------------------Data information about test users-------------------------------------------
    //General test user
    public static final String USER = "Alexandr Verezhevych";
    public static final String EMAIL = "indonesia.cashflow@gmail.com";
    public static final String PASSWORD = "123456";
    //First test user
    public static final String USER1 = "User1 Test1";
    public static final String EMAIL1 = "user1.indonesia@gmail.com";
    public static final String PASSWORD1 = "123456";
    //Second user
    public static final String USER2 = "User4 Test4";
    public static final String EMAIL2 = "user4.indonesia@ukr.net";
    public static final String PASSWORD2 = "test4";
    //Third user
    public static final String USER3 = "Jack Nicholson";
    public static final String EMAIL3 = "tester.cashflow@gmail.com";
    public static final String PASSWORD3 = "test3";
    //Localize information
    public static final char EN = 'A';
    public static final char RU = 'B';
    //--------------------------------Constant names for actions in status----------------------------------------------
    public static final char SEND_FOR_APPROVAL = 'A';
    public static final char APPROVE = 'C';
    public static final char CANCEL = 'E';
    //------------------------------------------------------------------------------------------------------------------

    @BeforeMethod
    public void initializeDriver() {
        driver = tlDriver.get();
        driver = new Driver();
        tlDriver.set(driver);
        loginPage = new LoginPage(driver);
        appListPage = new AppListPage(driver);
        appEditPage = new AppEditPage(driver);
        calendarPage = new CalendarPage(driver);
        helpers = new Helpers(driver);
        steps = new LoginSteps(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}