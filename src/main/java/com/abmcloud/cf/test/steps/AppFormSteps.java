package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Step("Сохранить заявку")
    public AppListSteps saveApplication() {
        appEditPage.saveButton.click();
        saveTextAndNumberOfNotification();
        return new AppListSteps();
    }

    @Step("Кликнуть кнопку \"сохранить заявку\"")
    public AppFormSteps saveButtonClick() {
        appEditPage.saveButton.click();
        saveTextAndNumberOfNotification();
        return this;
    }

    @Step("Редактировать поле на значение:")
    public AppFormSteps edit(WebElement field, String text) {
        field.clear();
        field.sendKeys(text);
        return this;
    }

    @Step("Изменить булеан поле")
    public AppFormSteps booleanButtonClick(WebElement button) {
        appEditPage.editPopupTitle.click();     //для активации формы (если кликнуть по кнопке с неактивной формой, то в результате кликом активируется форма, а кнопка не кликнется)
        button.click();
        return this;
    }

    @Step("Кликнуть на поле каталог")
    public AppFormSteps catalogElementClick(WebElement element) {
        waitForElementClickable(10, element);
        element.click();
        return this;
    }

    @Step("Кликнуть на поле с датой")
    public AppFormSteps clickOnDateField(WebElement element) {
        waitForElementClickable(10, element);
        element.click();
        return this;
    }

    @Step("Очистить поле каталог")
    public AppFormSteps clearCatalogValue(WebElement catalogField) {
        catalogField.click();
        waitForElementClickable(4, appEditPage.clearCatalogValueButton);
        appEditPage.clearCatalogValueButton.click();
        appEditPage.closeCatalogPopupLocator.click();
        return this;
    }

    @Step("Ввести в поле:")
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

    @Step("Утвердить заявку из формы заявки")
    public AppListSteps approveButtonClick() {
        appEditPage.approveAppButton.click();
        return new AppListSteps();
    }

    @Step("Отменить заявку из формы заявки")
    public AppListSteps cancelButtonClick() {
        appEditPage.cancelAppButton.click();
        return new AppListSteps();
    }

    @Step("Закрыть форму заявки")
    public AppListSteps backButtonClick() {
        appEditPage.closeAppFormButton.click();
        appEditPage.yesGoOut.click();
        return new AppListSteps();
    }

    @Step("Открыть инфо блок")
    public AppFormSteps showInformationBlockClick() {
        waitForElementClickable(2, appEditPage.showInformationBlock);
        appEditPage.showInformationBlock.click();
        return this;
    }



    @Step("Открыть историю изменений")
    public AppFormSteps changesHistoryClick() {
        if(BaseTest.activeUser.getLocalizeLanguage() == EN)
        appEditPage.changesHistory.click();
        else $(By.xpath("//*[contains(text(), 'История изменений')]")).click();
        return this;
    }

    @Step("Открыть вкладку:")
    public AppFormSteps openTab(String nameOfTab) {
        $(By.xpath("//*[@class='no-border pointer option_btn']/*[contains(text(), '"+nameOfTab+"')]")).click();
        verificationThat(ExpectedConditions.attributeContains(                      //проверка, что вкладка открыта
                $(By.xpath("//*[contains(text(), '"+nameOfTab+"')]//parent::button")),
                "class", "no-border pointer option_btn btn-active"));
        return this;
    }
    //-----------------------------------BaseAsserts for edit application steps---------------------------------------------
    public AppFormSteps assertThat(ExpectedCondition<Boolean> condition) {
        verificationThat(condition);
        return this;
    }

    @Step("Добавить новую строку")
    public AppFormSteps addNewLineClick() {
        appEditPage.addNewLine.click();
        return this;
    }

    @Step("Открыть вкладку \"Файлы по заявке\"")
    public AppFormSteps openFilesTab() {
        appEditPage.filesTab.click();
        return this;
    }

    @Step("Прикрепить файл:")
    public AppFormSteps addFile(String wayToFile) {
        appEditPage.addFileInput.sendKeys(wayToFile);
        return this;
    }
}
