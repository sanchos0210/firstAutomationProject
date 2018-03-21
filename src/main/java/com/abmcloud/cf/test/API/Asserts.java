package com.abmcloud.cf.test.API;

import com.abmcloud.cf.test.steps.AppFormSteps;
import com.abmcloud.cf.test.steps.AppListSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Asserts extends API {

    private WebDriver driver;

    public Asserts() {
        this.driver = BaseTest.driver;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    public Asserts assertTrue(boolean condition) {
        Assert.assertTrue(condition);
        return this;
    }

    public Asserts assertFalse(boolean condition) {
        Assert.assertFalse(condition);
        return this;
    }

    public Asserts checkHistoryOf(String nameOfField, String fromValue, String toValue) {
        String fromValue2;
        String toValue2;
        WebElement assertsField = getWebDriver().findElement(By.xpath("//*[contains(text(), '"+ nameOfField +"')]//parent::legend"));
        waitForElementClicable(2, assertsField);
        assertsField.click();
        waitForElementClicable(4, assertsField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[1]")));
        fromValue2 = assertsField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[1]")).getText();
        fromValue2 = fromValue2.substring(1,fromValue2.length()-1);
        assertTrue(compare(fromValue2, fromValue));
        toValue2 = assertsField.findElement(By.xpath(".//following-sibling::div//*[@class = 'backspace2X']/b[2]")).getText();
        toValue2 = toValue2.substring(1, toValue2.length()-1);
        assertTrue(compare(toValue2, toValue));
        return this;
    }

    public Asserts assertTextInElement(WebElement element, String text) {
        verificationThat(ExpectedConditions.textToBePresentInElement(element, text));
        return this;
    }

    public AppFormSteps getAppFormStep() {
        return new AppFormSteps();
    }

    public AppListSteps getAppListStep() {
        return new AppListSteps();
    }
}
