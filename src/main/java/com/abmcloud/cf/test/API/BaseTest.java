package com.abmcloud.cf.test.API;

import com.abmcloud.cf.test.DBInfo.AppField;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.CalendarPage;
import com.abmcloud.cf.test.pages.LoginPage;
import com.abmcloud.cf.test.steps.LoginSteps;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    public static List<AppField> fieldsToFill = null;

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    public String getTodayFullDate() {
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        return getDate(today, "dd.MM.yyyy");
    }

    public String getTodayDate() {
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        return getDate(today, "dd");
    }

    public String getTomorrowDate() {
        DateFormat df = new SimpleDateFormat("dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = c.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    public String getDate(Date date, String format) {
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat(format);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String soughtDate = df.format(date);
        return soughtDate;
    }

    public String getStepName(int stepNum) {
        WebElement step = $(By.cssSelector("step-view-approved .list-group > li:nth-of-type("+stepNum+") > ul > li div"));
        return step.getText();
    }

    public String getParallelStepsNames(int stepNum) {
        List<WebElement> parallelSteps = $$(By.cssSelector("step-view-approved .list-group > li:nth-of-type("+stepNum+") >ul > li"));
        List<String> stepsNames = new ArrayList<String>();
        for(int i = 0; i < parallelSteps.size(); i++) {
            WebElement step = parallelSteps.get(i);
            stepsNames.add(i, step.findElement(By.cssSelector("div")).getText());
        }
        String result = StringUtils.join(stepsNames, ", ");
        return result;
    }

    public String[][] getChainSteps() {
        List<WebElement> chainSteps = $$(By.cssSelector("step-view-approved .list-group > li"));
        String[][] chainStepsList = new String[chainSteps.size()][2];
        for(int i = 0; i < chainSteps.size(); i++) {
            WebElement step = chainSteps.get(i);
            List<WebElement> parallelStepsList = step.findElements(By.cssSelector("ul.parallel_steps li .list-group-item.steps_in_appl"));
            for(int j = 0; j < parallelStepsList.size(); j++) {
                WebElement nameOfStep = parallelStepsList.get(j).findElement(By.cssSelector("div"));
                if(nameOfStep.getText() != null) chainStepsList[i][j] = nameOfStep.getText();
            }
        }
        return chainStepsList;
    }

    public String getApprovers(int stepNum) {
        List<WebElement> approvers = $$(By.cssSelector("step-view-approved .list-group > li:nth-of-type("+stepNum+") ul.parallel_steps li .list-group-item.steps_in_appl ul li"));
        List<String> approversName = new ArrayList<String>();
        for(int i = 0; i < approvers.size(); i++) {
            approversName.add(i, approvers.get(i).findElement(By.cssSelector("img + span")).getText());
        }
        String result = StringUtils.join(approversName, ", ");
        return result;
    }

    public String getTomorrowFullDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = c.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    public String getYesterdayFullDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date tomorrow = c.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    @BeforeMethod
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vera\\Desktop\\chrome-driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        appListPage = new AppListPage(driver);
        appEditPage = new AppEditPage(driver);
        calendarPage = new CalendarPage(driver);
        steps = new LoginSteps(driver);
    }
/*
    @AfterMethod
    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }
*/
    @AfterMethod
    public void tearDown() {
        // Close all browser windows and safely end the session
        driver.close();
    }
}