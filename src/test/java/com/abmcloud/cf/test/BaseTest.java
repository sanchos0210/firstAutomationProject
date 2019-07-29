package com.abmcloud.cf.test;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.PageObject.steps.LoginSteps;
import com.abmcloud.cf.test.Utils.Json;
import com.abmcloud.cf.test.Utils.TestInfo;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

public class BaseTest {

    private Driver driver;
    protected LoginSteps steps;
    public ObjectManager objectManager;
    protected TestInfo testInfo;
    //-----------------------------------------------CONSTANT VALUES----------------------------------------------------
    //URL constants
    protected static String APP_FORM_COMPANY_URL;
    protected static String APP_LIST_COMPANY_URL;
    protected static String SUPER_APP_COMPANY_URL;
    //--------------------------------------Data information about test users-------------------------------------------
    //General test user
    public static final String USER = "Alexandr Verezhevych";
    public static final String EMAIL = "fgbgbbvrwb@gmail.com";
    public static final String PASSWORD = "123456";
    //First test user
    public static final String USER1 = "User1 Test1";
    public static final String EMAIL1 = "rtvjkwehrbv@gmail.com";
    public static final String PASSWORD1 = "123456";
    //Second user
    public static final String USER2 = "User2 Deputy";
    public static final String EMAIL2 = "ljkhbdsfvfe@gmail.com";
    public static final String PASSWORD2 = "123456";
    //Third user
    public static final String USER3 = "User3 Test3";
    public static final String EMAIL3 = "sdfklnve@i.ua";
    public static final String PASSWORD3 = "123456";

    //------------------------------------------------------------------------------------------------------------------

    @BeforeTest
    public void getCompaniesUrl() {
        Json json = new Json("test_companies_url.json");
        APP_FORM_COMPANY_URL = json.getString("APP_FORM_COMPANY_URL");
        APP_LIST_COMPANY_URL = json.getString("APP_LIST_COMPANY_URL");
        SUPER_APP_COMPANY_URL = json.getString("SUPER_APP_COMPANY_URL");
    }

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