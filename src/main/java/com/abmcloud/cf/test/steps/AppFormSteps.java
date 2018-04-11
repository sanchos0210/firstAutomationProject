package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class AppFormSteps extends BaseSteps {

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

    public AppFormSteps saveButtonClick() {
        appEditPage.saveButton.click();
        saveTextAndNumberOfNotification();
        return this;
    }

    public AppFormSteps edit(WebElement field, String text) {
        field.clear();
        field.sendKeys(text);
        return this;
    }

    public AppFormSteps booleanButtonClick(WebElement button) {
        appEditPage.editPopupTitle.click();     //для активации формы (если кликнуть по кнопке с неактивной формой, то в результате кликом активируется форма, а кнопка не кликнется)
        button.click();
        return this;
    }

    public AppFormSteps catalogElementClick(WebElement element) {
        waitForElementClickable(10, element);
        element.click();
        return this;
    }

    public AppFormSteps clearCatalogValue(WebElement catalogField) {
        catalogField.click();
        waitForElementClickable(4, appEditPage.clearCatalogValueButton);
        appEditPage.clearCatalogValueButton.click();
        appEditPage.closeCatalogPopupLocator.click();
        return this;
    }

    public AppFormSteps type(String value, WebElement field) {
        field.sendKeys(value);
        return this;
    }

    public String getStringValue(WebElement element) {     //For Deposit Business
        String decimalValue;
        WebElement decimalField = element.findElement(By.xpath(".//parent::*//parent::*//parent::description-field"));
        decimalValue = decimalField.getAttribute("ng-reflect-value");
        return decimalValue;
    }

    public String getDecimalValue(WebElement element) {     //For Deposit Business
        String decimalValue;
        WebElement decimalField = element.findElement(By.xpath(".//parent::div//parent::div//parent::decimal-field"));
        decimalValue = decimalField.getAttribute("ng-reflect-value");
        return decimalValue;
    }

    public String getCatalogValue(WebElement catalogField) {
        WebElement selectedValue = catalogField.findElement(By.cssSelector("a"));
        return selectedValue.getText();
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
        appEditPage.closeAppFormButton.click();
        appEditPage.yesGoOut.click();
        return new AppListSteps();
    }

    public AppFormSteps showInformationBlockClick() {
        waitForElementClickable(2, appEditPage.showInformationBlock);
        appEditPage.showInformationBlock.click();
        return this;
    }

    public AppFormSteps changesHistoryClick() {
        appEditPage.changesHistory.click();
        return this;
    }
    //-----------------------------------BaseAsserts for edit application steps---------------------------------------------
    public AppFormSteps assertThat(ExpectedCondition<Boolean> condition) {
        verificationThat(condition);
        return this;
    }
}
