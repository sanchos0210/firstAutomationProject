package com.abmcloud.cf.test.Driver;

import com.abmcloud.cf.test.PageObject.steps.LoginSteps;
import com.abmcloud.cf.test.Utils.TestInfo;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    private Driver driver;
    protected LoginSteps steps;
    public ObjectManager objectManager;
    protected TestInfo testInfo;
    //-----------------------------------------------CONSTANT VALUES----------------------------------------------------
    //URL constants
    public static final String APP_FORM_TEST_DB = "https://test5.cft.abmcloud.com";    //"https://dev6.cft.abmcloud.com";            //"https://test12.cf.abmcloud.com";
    public static final String APP_LIST_TEST_DB = "https://test6.cft.abmcloud.com";    //"https://dev5.cft.abmcloud.com";
    public static final String CALENDAR_TEST_DB = "https://test7.cft.abmcloud.com";                                  //https://demo12.cf.abmcloud.com";
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
    //For date methods in helper class
    public static final char THIS_MONTH = 'A';
    public static final char LAST_MONTH = 'B';
    public static final char THIS_YEAR = 'A';
    public static final char LAST_YEAR = 'B';

    //--------------------------------Constant names for actions in status----------------------------------------------
    public static final char SEND_FOR_APPROVAL = 'A';
    public static final char APPROVE = 'C';
    public static final char CANCEL = 'E';
    //------------------------------------------------------------------------------------------------------------------

    @BeforeMethod
    public void initializeDriver(Method method) {
        Logs.setFileName(method.getName());
        objectManager = new ObjectManager();
        driver = objectManager.getDriver();
        testInfo = objectManager.getTestInfo();
        steps = objectManager.getLoginSteps();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}