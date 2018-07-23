package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.API.Logs;
import com.abmcloud.cf.test.Fields.*;
import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AppFormSteps extends BaseSteps {

    private DecimalField decimalField;
    private StringField stringField;
    private BooleanField booleanField;
    private CatalogField catalogField;
    private DateField dateField;

    private boolean fieldHasChanged;

    public AppFormSteps(Driver driver) {
        this.driver = driver;
        appEditPage = new AppEditPage(driver);
        logs = new Logs(AppFormSteps.class.getName());
    }

    private AppListPage getAppListPage() {
        if(appListPage == null) {
            appListPage = new AppListPage(driver);
        }
        return appListPage;
    }

    private DecimalField getDecimalField() {
        if(decimalField == null) {
            decimalField = new DecimalField(driver);
            return  decimalField;
        }
        else return decimalField;
    }

    private StringField getStringField() {
        if(stringField == null) {
            stringField = new StringField(driver);
            return  stringField;
        }
        else return stringField;
    }

    private CatalogField getCatalogField() {
        if(catalogField == null) {
            catalogField = new CatalogField(driver);
            return  catalogField;
        }
        else return catalogField;
    }

    private DateField getDateField() {
        if(dateField == null) {
            dateField = new DateField(driver);
            return  dateField;
        }
        else return dateField;
    }


    private void saveTextAndNumberOfNotification() {
        BaseTest.textOfNotification = getAppListPage().applSavedNotification.getText();
        switch(BaseTest.activeUser.getLocalizeLanguage()) {
            case BaseTest.EN: {     //for english language
                BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(11, 18);
                break;
            }
            case BaseTest.RU: {     //for russian language
                BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(9, 16);
                break;
            }
        }
        getAppListPage().applSavedNotification.click();
    }

    @Step("Сохранить заявку")
    public AppListSteps saveApplication() {
        appEditPage.saveButton.click();
        saveTextAndNumberOfNotification();
        return getAppListSteps();
    }

    @Step("Кликнуть кнопку \"сохранить заявку\"")
    public AppFormSteps saveButtonClick() {
        appEditPage.saveButton.click();
        saveTextAndNumberOfNotification();
        return this;
    }

    @Step("Редактировать числовое поле на значение:")
    public AppFormSteps editDecimalField(String nameOfField, String text) {
        WebElement field = getDecimalField().getField(nameOfField);
        field.clear();
        field.sendKeys(text);
        fieldHasChanged = true;
        return this;
    }

    @Step("Редактировать числовое поле в ТЧ на значение:")
    public AppFormSteps editTCHDecimalField(String nameOfField, String text) {
        WebElement field = getDecimalField().getTCHField(nameOfField);
        field.clear();
        field.sendKeys(text);
        fieldHasChanged = true;
        return this;
    }

    @Step("Редактировать числовое поле в строке ТЧ на значение:")
    public AppFormSteps editTCHDecimalField(String nameOfField, int rowNum, String text) {
        WebElement field = getDecimalField().getTCHField(nameOfField, rowNum);
        field.clear();
        field.sendKeys(text);
        fieldHasChanged = true;
        return this;
    }

    @Step("Редактировать строчное поле на значение:")
    public AppFormSteps editStringField(String nameOfField, String text) {
        WebElement field = getStringField().getField(nameOfField);
        field.clear();
        field.sendKeys(text);
        fieldHasChanged = true;
        return this;
    }

    @Step("Редактировать строчное поле в ТЧ на значение:")
    public AppFormSteps editTCHStringField(String nameOfField, String text) {
        WebElement field = getStringField().getTCHField(nameOfField);
        field.clear();
        field.sendKeys(text);
        fieldHasChanged = true;
        return this;
    }

    @Step("Изменить булеан поле")
    public AppFormSteps booleanButtonClick(String nameOfField) {
        appEditPage.editPopupTitle.click();     //для активации формы (если кликнуть по кнопке с неактивной формой, то в результате кликом активируется форма, а кнопка не кликнется)
        booleanField = new BooleanField(driver);
        WebElement button = booleanField.getField(nameOfField);
        button.click();
        fieldHasChanged = true;
        return this;
    }

    @Step("Кликнуть по кнопке in_out:")
    public AppFormSteps inOutButtonClick() {
        appEditPage.editPopupTitle.click();     //для активации формы (если кликнуть по кнопке с неактивной формой, то в результате кликом активируется форма, а кнопка не кликнется)
        appEditPage.inOutSwitch.click();
        fieldHasChanged = true;
        return this;
    }

    @Step("Кликнуть по кнопке:")
    public AppFormSteps buttonClick( WebElement button) {
        getWait().waitForElementClickable(10, button);
        button.click();
        fieldHasChanged = true;
        return this;
    }

    @Step("Кликнуть на поле каталог:")
    public AppFormSteps catalogFieldClick(String nameOfField) {
        WebElement element = getCatalogField().getField(nameOfField);
        getWait().waitForElementClickable(10, element);
        element.click();
        fieldHasChanged = true;
        return this;
    }

    @Step("Кликнуть на элемент каталога:")
    public AppFormSteps catalogElementClick(String nameOfItem) {
        WebElement element = getCatalogField().getItem(nameOfItem);
        getWait().waitForElementClickable(10, element);
        element.click();
        fieldHasChanged = true;
        return this;
    }

    @Step("Кликнуть на поле с датой")
    public AppFormSteps clickOnDateField(String nameOfField) {
        WebElement dateField = getDateField().getField(nameOfField);
        getWait().waitForElementClickable(10, dateField);
        dateField.click();
        return this;
    }

    @Step("Очистить поле каталог")
    public AppFormSteps clearCatalogValue(String nameOfField) {
        WebElement catalogField = getCatalogField().getField(nameOfField);
        catalogField.click();
        getWait().waitForElementClickable(4, appEditPage.clearCatalogValueButton);
        appEditPage.clearCatalogValueButton.click();
        appEditPage.closeCatalogPopupLocator.click();
        fieldHasChanged = true;
        return this;
    }

    @Step("Утвердить заявку из формы заявки")
    public AppListSteps approveButtonClick() {
        appEditPage.approveAppButton.click();
        return getAppListSteps();
    }

    @Step("Отменить заявку из формы заявки")
    public AppListSteps cancelButtonClick() {
        appEditPage.cancelAppButton.click();
        return getAppListSteps();
    }

    @Step("Закрыть форму заявки")
    public AppListSteps backButtonClick() {
        appEditPage.closeAppFormButton.click();
        if(fieldHasChanged == true) {
            fieldHasChanged = false;
            appEditPage.yesGoOut.click();
        }
        return getAppListSteps();
    }

    @Step("Открыть инфо блок")
    public AppFormSteps showInformationBlockClick() {
        getWait().waitForElementClickable(2, appEditPage.showInformationBlock);
        appEditPage.showInformationBlock.click();
        return this;
    }

    @Step("Открыть историю изменений")
    public AppFormSteps changesHistoryClick() {
        if(BaseTest.activeUser.getLocalizeLanguage() == BaseTest.EN)
        appEditPage.changesHistory.click();
        else driver.$(By.xpath("//*[contains(text(), 'История изменений')]")).click();
        return this;
    }

    @Step("Открыть вкладку:")
    public AppFormSteps openTab(String nameOfTab) {
        driver.$(By.xpath("//*[@class='no-border pointer option_btn']/*[contains(text(), '"+nameOfTab+"')]")).click();
        driver.assertThat(ExpectedConditions.attributeContains(                      //verificationThat проверка, что вкладка открыта
                driver.$(By.xpath("//*[contains(text(), '"+nameOfTab+"')]//parent::button")),
                "class", "no-border pointer option_btn btn-active"));
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
