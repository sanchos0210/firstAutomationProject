package com.abmcloud.cf.test.Driver;

import com.abmcloud.cf.test.PageObject.steps.AppFormSteps;
import com.abmcloud.cf.test.PageObject.steps.AppListSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Asserts {

    private Driver driver;
    private ObjectManager objectManager;
    private Wait wait;
    private Logs logs;

    public Asserts(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
        this.logs = new Logs(Asserts.class.getName());
    }

    private Wait getWait() {
        if(wait == null) {
            wait = new Wait(driver);
        }
        return wait;
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
        WebElement verificationField = driver.$(By.xpath("//*[contains(text(), '"+ nameOfField +"')]//ancestor::legend"));
        getWait().waitForElementClickable(2, verificationField);
        verificationField.click();
        getWait().waitForElementClickable(4, verificationField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[1]")));
        fromValue2 = verificationField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[1]")).getText();
        fromValue2 = fromValue2.substring(1,fromValue2.length()-1);
        compare(fromValue2, fromValue);
        toValue2 = verificationField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[2]")).getText();
        toValue2 = toValue2.substring(1, toValue2.length()-1);
        compare(toValue2, toValue);
        return this;
    }

    public Asserts assertTextInElement(WebElement element, String text) {
        driver.assertThat(ExpectedConditions.textToBePresentInElement(element, text));  //verificationThat
        return this;
    }

    public Asserts assertTextInElementsList(List<WebElement> list, String text) {
        for(WebElement element : list) {
            if(text.equals(element.getText())) {
                break;
            }
            NoSuchElementException ex = new NoSuchElementException("Assertion failed! Element has not been found.");
            logs.errorMsg(ex);
        }
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

    public Asserts compare(String referenseValue, String verificationValue) {
        Assert.assertEquals(verificationValue, referenseValue);
        return  this;
    }

    public Asserts compare(String[][] referenseArray, String[][] verificationArray) {
        for(int i = 0; i < verificationArray.length; i++) {
            for(int j = 0; j < 2; j++) {
                String referenceValue = referenseArray[i][j];
                String verificationValue = verificationArray[i][j];
                if(referenceValue == null & verificationValue == null) break;
                Assert.assertEquals(verificationValue, referenceValue);
            }
        }
        return this;
    }

    public Asserts compare(String[] referenseArray, String[] verificationArray) {
        for(int i = 0; i < verificationArray.length; i++) {
            String referenceValue = referenseArray[i];
            String verificationValue = verificationArray[i];
            if(referenceValue == null & verificationValue == null) break;
            Assert.assertEquals(verificationArray, referenseArray);
        }
        return this;
    }

    public Asserts compare(List<Object> referenceList, List<Object> verificationList) {
        Assert.assertEquals(verificationList, referenceList);
        return this;
    }

    private boolean isElementPresent(WebElement element) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            getWait().waitForElementClickable(2, element);
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
    }

    public Asserts isElementPresent(WebElement element, boolean typeOfAssertion) {
        if(typeOfAssertion == true) {
            Assert.assertTrue(isElementPresent(element));
        }
        else {
            Assert.assertFalse(isElementPresent(element));
        }
        return this;
    }

    private boolean isButtonPresentInRow(WebElement row, By buttonLocator) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            getWait().waitForElementClickable(2, row.findElement(buttonLocator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
    }

    public Asserts isButtonPresentInRow(WebElement row, By buttonLocator, boolean typeOfAssertion) {
        if(typeOfAssertion == true) {
            Assert.assertTrue(isButtonPresentInRow(row, buttonLocator));
        }
        else {
            Assert.assertFalse(isButtonPresentInRow(row, buttonLocator));
        }
        return this;
    }

    private boolean isButtonDisable(WebElement button) {
        try {
            driver.assertThat(ExpectedConditions.attributeContains(button, "disabled", "true"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public Asserts isButtonDisable(WebElement button, boolean typeOfAssertion) {
        if(typeOfAssertion == true) {
            Assert.assertTrue(isButtonDisable(button));
        }
        else {
            Assert.assertFalse(isButtonDisable(button));
        }
        return this;
    }
}