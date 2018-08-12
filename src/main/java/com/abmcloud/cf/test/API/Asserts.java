package com.abmcloud.cf.test.API;

import com.abmcloud.cf.test.steps.AppFormSteps;
import com.abmcloud.cf.test.steps.AppListSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Asserts {

    private Driver driver;
    private ObjectManager objectManager;
    private Wait wait;
    private Helpers helpers;

    public Asserts(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
    }

    private Wait getWait() {
        if(wait == null) {
            wait = new Wait(driver);
        }
        return wait;
    }

    private Helpers getHelpers() {
        if(helpers == null) {
            helpers = new Helpers(driver);
        }
        return helpers;
    }

    public Asserts assertTrue(boolean condition) {
        Assert.assertTrue(condition);
        return this;
    }

    @Step("Проверка")
    public Asserts assertFalse(boolean condition) {
        Assert.assertFalse(condition);
        return this;
    }

    public Asserts checkHistoryOf(String nameOfField, String fromValue, String toValue) {
        String fromValue2;
        String toValue2;
        WebElement assertsField = driver.$(By.xpath("//*[contains(text(), '"+ nameOfField +"')]//ancestor::legend"));
        getWait().waitForElementClickable(2, assertsField);
        assertsField.click();
        getWait().waitForElementClickable(4, assertsField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[1]")));
        fromValue2 = assertsField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[1]")).getText();
        fromValue2 = fromValue2.substring(1,fromValue2.length()-1);
        assertTrue(getHelpers().compare(fromValue2, fromValue));
        toValue2 = assertsField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[2]")).getText();
        toValue2 = toValue2.substring(1, toValue2.length()-1);
        assertTrue(getHelpers().compare(toValue2, toValue));
        return this;
    }

    public Asserts assertTextInElement(WebElement element, String text) {
        driver.assertThat(ExpectedConditions.textToBePresentInElement(element, text));  //verificationThat
        return this;
    }

    public Asserts assertTextIn(WebElement row, By locator, String text) {
            driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            (new WebDriverWait(driver.getWebDriver(), 6)).until(
                    ExpectedConditions.textToBePresentInElement(row.findElement(locator), text));
        driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        return this;
    }

    public Asserts assertVisibilityButtonInRow(WebElement row, By buttonLocator) {
        getWait().waitForElementClickable(2, row.findElement(buttonLocator));
        return this;
    }

    public AppFormSteps getAppFormStep() {
        return objectManager.getAppFormSteps();
    }

    public AppListSteps getAppListStep() {
        return objectManager.getAppListSteps();
    }
}