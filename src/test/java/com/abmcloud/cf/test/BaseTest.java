package com.abmcloud.cf.test;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
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
    public static final String APP_FORM_COMPANY_URL = "https://test5.cft.abmcloud.com";
    public static final String APP_LIST_COMPANY_URL = "https://test6.cft.abmcloud.com";
    public static final String SUPER_APP_COMPANY_URL = "https://test7.cft.abmcloud.com";
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
    public static final String USER2 = "User2 Deputy";
    public static final String EMAIL2 = "user2.indonesia@gmail.com";
    public static final String PASSWORD2 = "123456";
    //Third user
    public static final String USER3 = "User3 Test3";
    public static final String EMAIL3 = "user3.indonesia@i.ua";
    public static final String PASSWORD3 = "123456";

    //------------------------------------------------------------------------------------------------------------------

    @BeforeMethod
    public void initializeDriver(Method method) {
        objectManager = new ObjectManager(method.getName());
        driver = objectManager.getDriver();
        testInfo = objectManager.getTestInfo();
        steps = objectManager.getLoginSteps();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}