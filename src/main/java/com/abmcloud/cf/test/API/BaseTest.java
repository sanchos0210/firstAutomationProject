package com.abmcloud.cf.test.API;

import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.LoginPage;
import com.abmcloud.cf.test.steps.LoginSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest extends API {

    public static WebDriver driver;
    public LoginSteps steps;

    public static UsersData activeUser;
    public static WebElement selectedApp;
    public static String numberOfSelectedApp;
    public static String statusOfSelectedApp;
    public static String numberOfCreatedApp;
    public static String textOfNotification;

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    public String getTodaysDate() {
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String s = df.format(today);
        return s;
    }

    public String getTomorrowDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = c.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    public String getYesterdayDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date tomorrow = c.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    @BeforeMethod
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        appListPage = new AppListPage(driver);
        appEditPage = new AppEditPage(driver);
        steps = new LoginSteps(driver);
    }

    @AfterMethod
    public void tearDown() {
        // Close all browser windows and safely end the session
        driver.quit();
    }
}