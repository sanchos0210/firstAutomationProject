package com.abmcloud.cf.test.API;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Wait {

    Driver driver;

    public Wait(Driver driver) {
        this.driver = driver;
    }

    public void loginWait() {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            waitForClickable(20, By.cssSelector("tbody td:nth-of-type(3)"));
        }catch(TimeoutException e) {
            /*TimeoutException a = new TimeoutException("Тест упал. Логин занял больше 20 секунд.");
            throw a;*/
            System.out.println("Неуспешный логин или в списке нету заявок");
        }
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void waitForClickable(By locator) {
        waitForClickable(4, locator);
    }

    public void waitForClickable(int seconds, By locator) {
        (new WebDriverWait(driver.getWebDriver(), seconds)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementClickable(int seconds, WebElement element) {
        (new WebDriverWait(driver.getWebDriver(), seconds)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void preloadWait() {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        (new WebDriverWait(driver.getWebDriver(), 6)).until(ExpectedConditions.attributeContains
                (By.cssSelector("div#preloader"), "style",
                        "display: block; background: none rgba(0, 0, 0, 0.7);"));
        (new WebDriverWait(driver.getWebDriver(), 6)).until(ExpectedConditions.attributeContains
                (By.cssSelector("div#preloader"), "style",
                        "display: none; background: none;"));
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void calendarPreloadWait() {
        driver.assertThat(ExpectedConditions.attributeContains(By.cssSelector("spec-loader #calendarLoader"), "style", "display: block;"));
        driver.assertThat(ExpectedConditions.attributeContains(By.cssSelector("spec-loader #calendarLoader"), "style", "display: none;"));
        //verificationThat(ExpectedConditions.attributeContains(By.cssSelector("spec-loader #calendarLoader"), "style", "display: none;"));
        waitForClickable(5, By.cssSelector(".cell-gr1 calendar-table-cell"));
    }
}
