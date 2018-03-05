package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.architecture.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class EditAppSteps extends BaseSteps {

    private void saveTextAndNumberOfNotification() {
        BaseTest.textOfNotification = appListPage.applSavedNotification.getText();
        switch(BaseTest.activeUser.getLocalizeLanguage()) {
            case EN: {     //for english language
                BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(11, 18);
                break;
            }
            case RU: {     //for russian language
                BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(9, 16);
                break;
            }
        }
        appListPage.applSavedNotification.click();
    }

    public AppListSteps saveApplication() {
        appEditPage.saveButton.click();
        saveTextAndNumberOfNotification();
        return new AppListSteps();
    }

    public EditAppSteps saveButtonClick() {
        appEditPage.saveButton.click();
        saveTextAndNumberOfNotification();
        return this;
    }

    public EditAppSteps edit(WebElement field, String text) {
        field.clear();
        field.sendKeys(text);
        return this;
    }

    public EditAppSteps booleanButtonClick(WebElement button) {
        button.click();
        return this;
    }

    public EditAppSteps catalogElementClick(WebElement element) {
        waitForElementClicable(10, element);
        element.click();
        return this;
    }

    public EditAppSteps type(String value, WebElement field) {
        field.sendKeys(value);
        return this;
    }

    public String getStringValue(WebElement element) {     //For Deposit Business
        String decimalValue;
        WebElement decimalField = element.findElement(By.xpath(".//parent::description-field"));
        decimalValue = decimalField.getAttribute("ng-reflect-value");
        return decimalValue;
    }

    public String getDecimalValue(WebElement element) {     //For Deposit Business
        String decimalValue;
        WebElement decimalField = element.findElement(By.xpath(".//parent::div//parent::div//parent::decimal-field"));
        decimalValue = decimalField.getAttribute("ng-reflect-value");
        return decimalValue;
    }

    public AppListSteps approveButtonClick() {
        appEditPage.approveAppButton.click();
        return new AppListSteps();
    }

    public AppListSteps cancelButtonClick() {
        appEditPage.cancelAppButton.click();
        return new AppListSteps();
    }

    public AppListSteps backButtonClick() {
        appEditPage.backToAppList.click();
        return new AppListSteps();
    }

    public EditAppSteps showInformationBlockClick() {
        waitForElementClicable(2, appEditPage.showInformationBlock);
        appEditPage.showInformationBlock.click();
        return this;
    }

    public EditAppSteps changesHistoryClick() {
        appEditPage.changesHistory.click();
        return this;
    }
    //-----------------------------------Asserts for edit application steps---------------------------------------------
    public EditAppSteps assertValueOfBooleanField(WebElement booleanButton, String expectedValue) {
        WebElement headerOfBooleanField = booleanButton.findElement(By.xpath("//parent::*[@class='select']//parent::bool-select-button"));
        WebElement activeButtonInBoolField = headerOfBooleanField.findElement(By.xpath(".//*[@class='d-inline-block pointer select_btn select-active']/*"));
        boolean result = expectedValue.equals(activeButtonInBoolField.getText());
        assertTrue(result);
        return this;
    }

    public EditAppSteps assertThat(ExpectedCondition<Boolean> condition) {
        verificationThat(condition);
        return this;
    }

    public EditAppSteps checkHistoryOf(String nameOfField, String fromValue, String toValue) {
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

    public EditAppSteps assertThatFieldIsDisabled(WebElement field) {           //for string and decimal fields
        String valueOfAttribute = "true";
        boolean isDisabled = valueOfAttribute.equals(field.getAttribute("readonly"));
        assertTrue(isDisabled);
        return this;
    }

    public EditAppSteps assertThatDateFieldIsDisabled(WebElement dateField) {
        dateField.click();
        assertFalse(isElementPresent(appEditPage.futureDate));
        return this;
    }

    public EditAppSteps assertTrue(boolean condition) {
        verificationOf(condition, 'A');
        return this;
    }

    public EditAppSteps assertFalse(boolean condition) {
        verificationOf(condition, 'B');
        return this;
    }
}
