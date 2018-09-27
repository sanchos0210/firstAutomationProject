package com.abmcloud.cf.test.Driver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Wait {

    Driver driver;
    Logs logs;

    public Wait(Driver driver, Logs logs) {
        this.driver = driver;
        this.logs = logs;
    }

    public void loginWait() {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            waitForClickable(20, By.cssSelector("tbody td:nth-of-type(3)"));
        }catch(TimeoutException e) {
            logs.warning("Неуспешный логин или в списке нету заявок");
        }
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }



    public void waitForClickable(By locator) {
        waitForClickable(4, locator);
    }

    public void waitForClickable(int seconds, By locator) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            (new WebDriverWait(driver.getWebDriver(), seconds)).until(ExpectedConditions.elementToBeClickable(locator));
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void waitForElementClickable(int seconds, WebElement element) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            (new WebDriverWait(driver.getWebDriver(), seconds)).until(ExpectedConditions.elementToBeClickable(element));
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void waitForAttributeContains(WebElement element, String attribute, String value) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
        (new WebDriverWait(driver.getWebDriver(), 5)).until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void textToBePresentInElement(WebElement element, String text) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            (new WebDriverWait(driver.getWebDriver(), 5)).until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void waitForElementVisibility(WebElement element) {
        waitForElementVisibility(5, element);
    }

        public void waitForElementVisibility(int timeInSec, WebElement element) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
        (new WebDriverWait(driver.getWebDriver(), timeInSec)).until(ExpectedConditions.visibilityOf(element));
    } catch(StaleElementReferenceException e) {
        logs.warning(e.getMessage());
    }
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    /**
     * Ожидание загрузки всей страницы
     *
     * @param time
     */
    public void waitFullPageLoading(Long time) {
        new WebDriverWait(driver.getWebDriver(), time, 200).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete");
            }
        });
    }

    public void preloadWait() {
        //driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            (new WebDriverWait(driver.getWebDriver(), 6)).until(ExpectedConditions.attributeContains
                    (By.cssSelector("div#preloader"), "style",
                            "display: block; background: none rgba(0, 0, 0, 0.7);"));
            (new WebDriverWait(driver.getWebDriver(), 6)).until(ExpectedConditions.attributeContains
                    (By.cssSelector("div#preloader"), "style",
                            "display: none; background: none;"));
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        //driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void calendarPreloadWait() {
        try {
            driver.assertThat(ExpectedConditions.attributeContains(By.cssSelector("spec-loader #calendarLoader"), "style", "display: block;"));
            driver.assertThat(ExpectedConditions.attributeContains(By.cssSelector("spec-loader #calendarLoader"), "style", "display: none;"));
            //verificationThat(ExpectedConditions.attributeContains(By.cssSelector("spec-loader #calendarLoader"), "style", "display: none;"));
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        waitForClickable(5, By.cssSelector(".cell-gr1 calendar-table-cell"));
    }

    public void catalogPreloadWait() {
        try {
            (new WebDriverWait(driver.getWebDriver(), 4)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("directorycontainer directoryelement")));
        } catch(TimeoutException e) {
            waitForClickable(2, By.cssSelector(".empty_container .fa.fa-square-o.empty_icon"));
        }
        }
}
