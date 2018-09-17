package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.PageObject.Components.Notification;
import com.abmcloud.cf.test.PageObject.pages.CatalogFormPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class CatalogFormSteps extends BaseSteps {

    Notification notification;
    CatalogFormPage catalogFormPage;

    public CatalogFormSteps(ObjectManager objectManager) {
        this.driver = objectManager.getDriver();
        this.objectManager = objectManager;
        logs = objectManager.getLogs();
        notification = objectManager.getNotification();
        catalogFormPage = objectManager.getCatalogFormPage();
    }

    @Step("Редактировать числовое поле на значение:")
    public CatalogFormSteps editDecimalField(String nameOfField, String text) {
        objectManager.getDecimalField().editDecimalField(nameOfField, text);
        return this;
    }

    @Step("Редактировать числовое поле в ТЧ на значение:")
    public CatalogFormSteps editTCHDecimalField(String nameOfField, String text) {
        objectManager.getDecimalField().editTCHDecimalField(nameOfField, text);
        return this;
    }

    @Step("Редактировать числовое поле в строке ТЧ на значение:")
    public CatalogFormSteps editTCHDecimalField(String nameOfField, int rowNum, String text) {
        objectManager.getDecimalField().editTCHDecimalField(nameOfField, rowNum, text);
        return this;
    }

    @Step("Редактировать строчное поле на значение:")
    public CatalogFormSteps editStringField(String nameOfField, String text) {
        objectManager.getStringField().editStringField(nameOfField, text);
        return this;
    }

    @Step("Редактировать строчное поле в ТЧ на значение:")
    public CatalogFormSteps editTCHStringField(String nameOfField, String text) {
        objectManager.getStringField().editTCHStringField(nameOfField, text);
        return this;
    }

    @Step("Изменить булеан поле")
    public CatalogFormSteps booleanButtonClick(String nameOfField) {
        catalogFormPage.popupTitle.click();     //для активации формы (если кликнуть по кнопке с неактивной формой, то в результате кликом активируется форма, а кнопка не кликнется)
        objectManager.getBooleanField().booleanButtonClick(nameOfField);
        return this;
    }


    @Step("Кликнуть по кнопке:")
    public CatalogFormSteps buttonClick( WebElement button) {
        getWait().waitForElementClickable(10, button);
        try {
            button.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Кликнуть на поле каталог:")
    public CatalogFormSteps catalogFieldClick(String nameOfField) {
        objectManager.getCatalogField().catalogFieldClick(nameOfField);
        return this;
    }

    @Step("Кликнуть на элемент каталога:")
    public CatalogFormSteps catalogElementClick(String nameOfItem) {
        objectManager.getCatalogField().catalogElementClick(nameOfItem);
        return this;
    }

    @Step("Кликнуть на папку каталога:")
    public CatalogFormSteps catalogFolderClick(String nameOfFolder) {
        objectManager.getCatalogField().catalogFolderClick(nameOfFolder);
        return this;
    }

    @Step("Кликнуть на поле с датой")
    public CatalogFormSteps clickOnDateField(String nameOfField) {
        objectManager.getDateField().clickOnDateField(nameOfField);
        return this;
    }

    @Step("Очистить поле каталог")
    public CatalogFormSteps clearCatalogValue(String nameOfField) {
        objectManager.getCatalogField().clearCatalogValue(nameOfField);
        return this;
    }

    @Step("Сохранить заявку")
    public CatalogListSteps saveCatalogElement() {
        try {
            catalogFormPage.saveButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        notification.saveTextOfNotification();
        return objectManager.getCatalogListSteps();
    }

    @Step("Выбрать папку: {nameOfFolder}")
    public CatalogFormSteps choseFolder(String nameOfFolder) {
        objectManager.getCatalogField().chooseFolder(nameOfFolder);
        return this;
    }

    @Step("Кликнуть кнопку \"сохранить заявку\"")
    public CatalogFormSteps saveButtonClick() {
        try {
            catalogFormPage.saveButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        notification.saveTextOfNotification();
        notification.notificationClick();
        return this;
    }
}
