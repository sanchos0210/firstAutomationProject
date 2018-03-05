package com.abmcloud.cf.test.architecture;

import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public abstract class API {

    public abstract WebDriver getWebDriver();

    //Common objects
    public static LoginPage loginPage;
    public static AppListPage appListPage;
    public static AppEditPage appEditPage;

    //-----------------------------------------------CONSTANT VALUES----------------------------------------------------
    //URL constants
    public static final String TEST_PROLINE = "https://test5.cf.abmcloud.com/";
    public static final String TEST_LOTOK = "https://test12.cf.abmcloud.com";

    //--------------------------------------Data information about test users-------------------------------------------
    //General test user
    public static final String USER = "Alexandr Verezhevych";
    public static final String EMAIL = "indonesia.cashflow@gmail.com";
    public static final String PASSWORD = "123456";
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

    //-----------------------------------Constant names of application status-------------------------------------------
    public static final String NEW = "New";
    public static final String IN_PROGRESS = "In progress";
    public static final String APPROVED = "Approved";

    //--------------------------------Constant names for actions in status----------------------------------------------
    public static final char SEND_FOR_APPROVAL = 'A';
    public static final char APPROVE = 'C';
    public static final char CANCEL = 'E';
    //--------------------------------Constant attributes of fields in edit popup---------------------------------------
    public static final String IS_DISABLE = "readonly";
    public static final String CURRENT_VALUE = "ng-reflect-value";

    //--------------------------------------------Asserts---------------------------------------------------------------
    public void verificationThat(ExpectedCondition<Boolean> condition) {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        (new WebDriverWait(getWebDriver(), 4)).until(condition);
        getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void checkThat(WebElement row, By locator, String text) {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        (new WebDriverWait(getWebDriver(), 6)).until(ExpectedConditions.textToBePresentInElement(row.findElement(locator), text));
        getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void verificationOf(boolean condition, char typeOfVerification) {
        switch(typeOfVerification) {
            case 'A': {
                Assert.assertTrue(condition);
                break;
            }
            case 'B': {
                Assert.assertFalse(condition);
                break;
            }
        }
    }
    //-----------------------------------------Asserts methods----------------------------------------------------------
    public boolean isElementPresent(WebElement element) {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            element.click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
    }

    public boolean compare(String referenseValue, String verificationValue) {
        boolean s = referenseValue.equals(verificationValue);
        return s;
    }
    //--------------------------------------------Waiters---------------------------------------------------------------


    public void loginWait() {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        //(new WebDriverWait(getWebDriver(), 20)).until(ExpectedConditions.attributeContains
               // (By.cssSelector("tbody tr:nth-of-type(1) td:nth-of-type(4) filter-item i"), "title", "Filter by " + CATEGORY));
        try {
            (new WebDriverWait(getWebDriver(), 20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("tbody td:nth-of-type(3)")));
        }catch(TimeoutException e) {
            TimeoutException a = new TimeoutException("Тест упал. Логин занял больше 20 секунд.");
            //System.out.print("Тест упал. Логин занял больше 20 секунд.");
            throw a;
        }
        getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void waitForClicable(By locator) {
        (new WebDriverWait(getWebDriver(), 4)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementClicable(int seconds, WebElement element) {
        (new WebDriverWait(getWebDriver(), seconds)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void preloadWait() {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        (new WebDriverWait(getWebDriver(), 6)).until(ExpectedConditions.attributeContains
                (By.cssSelector("div#preloader"), "style", "display: block; background: none rgba(0, 0, 0, 0.7);"));
        (new WebDriverWait(getWebDriver(), 6)).until(ExpectedConditions.attributeContains
                (By.cssSelector("div#preloader"), "style", "display: none; background: none;"));
        getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }
    //---------------------------------------Find locators methods------------------------------------------------------
    public WebElement getDecimalField(String nameOfDecimalField) {
        WebElement decimalField = getWebDriver().findElement(By.cssSelector("decimal-field[ng-reflect-title='"+nameOfDecimalField+"'] input"));
        return decimalField;
    }
}