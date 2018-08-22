package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.Logs;
import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.PageObject.Components.Fields.*;
import com.abmcloud.cf.test.PageObject.Components.Notification;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class AppFormSteps extends BaseSteps {

    private DecimalField decimalField;
    private StringField stringField;
    private BooleanField booleanField;
    private CatalogField catalogField;
    private DateField dateField;
    private Notification notification;
    private String nameOfActiveCatalogField;

    private boolean fieldHasChanged;

    public AppFormSteps(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
        logs = new Logs(AppFormSteps.class.getName());
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

    @Step("Сохранить заявку")
    public AppListSteps saveApplication() {
        try {
            objectManager.getAppEditPage().saveButton.click();
            fieldHasChanged = false;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        getNotification().saveTextAndNumberInNotificationOfSavedApplication(objectManager.getTestInfo());
        return getAppListSteps();
    }

    @Step("Кликнуть кнопку \"сохранить заявку\"")
    public AppFormSteps saveButtonClick() {
        try {
            objectManager.getAppEditPage().saveButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        getNotification().saveTextAndNumberInNotificationOfSavedApplication(objectManager.getTestInfo());
        return this;
    }

    @Step("Редактировать числовое поле на значение:")
    public AppFormSteps editDecimalField(String nameOfField, String text) {
        WebElement field = getDecimalField().getField(nameOfField);
        try {
            field.clear();
            field.sendKeys(text);
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Редактировать числовое поле в ТЧ на значение:")
    public AppFormSteps editTCHDecimalField(String nameOfField, String text) {
        WebElement field = getDecimalField().getTCHField(nameOfField);
        try {
            field.clear();
            field.sendKeys(text);
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Редактировать числовое поле в строке ТЧ на значение:")
    public AppFormSteps editTCHDecimalField(String nameOfField, int rowNum, String text) {
        WebElement field = getDecimalField().getTCHField(nameOfField, rowNum);
        try {
            field.clear();
            field.sendKeys(text);
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Редактировать строчное поле на значение:")
    public AppFormSteps editStringField(String nameOfField, String text) {
        WebElement field = getStringField().getField(nameOfField);
        try {
            field.clear();
            field.sendKeys(text);
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Редактировать строчное поле в ТЧ на значение:")
    public AppFormSteps editTCHStringField(String nameOfField, String text) {
        WebElement field = getStringField().getTCHField(nameOfField);
        try {
            field.clear();
            field.sendKeys(text);
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Изменить булеан поле")
    public AppFormSteps booleanButtonClick(String nameOfField) {
        try {
            objectManager.getAppEditPage().editPopupTitle.click();     //для активации формы (если кликнуть по кнопке с неактивной формой, то в результате кликом активируется форма, а кнопка не кликнется)
            booleanField = new BooleanField(driver);
            WebElement button = booleanField.getField(nameOfField);
            button.click();
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Кликнуть по кнопке in_out:")
    public AppFormSteps inOutButtonClick() {
        try {
            objectManager.getAppEditPage().editPopupTitle.click();     //для активации формы (если кликнуть по кнопке с неактивной формой, то в результате кликом активируется форма, а кнопка не кликнется)
            objectManager.getAppEditPage().inOutSwitch.click();
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Кликнуть по кнопке:")
    public AppFormSteps buttonClick( WebElement button) {
        getWait().waitForElementClickable(10, button);
        try {
            button.click();
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Кликнуть на поле каталог:")
    public AppFormSteps catalogFieldClick(String nameOfField) {
        nameOfActiveCatalogField = nameOfField;
        WebElement element = getCatalogField().getField(nameOfField);
        try {
            element.click();
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Кликнуть на элемент каталога:")
    public AppFormSteps catalogElementClick(String nameOfItem) {
        WebElement element = getCatalogField().getItem(nameOfItem);
        try {
            element.click();
            WebElement activeCatalogField = getCatalogField().getField(nameOfActiveCatalogField);
            getWait().waitForAttributeContains(activeCatalogField, "ng-reflect-selected", nameOfItem);
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Кликнуть на папку каталога:")
    public AppFormSteps catalogFolderClick(String nameOfFolder) {
        WebElement element = getCatalogField().getItem(nameOfFolder);
        try {
            element.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Кликнуть на поле с датой")
    public AppFormSteps clickOnDateField(String nameOfField) {
        WebElement dateField = getDateField().getField(nameOfField);
        getWait().waitForElementClickable(10, dateField);
        try {
            dateField.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Очистить поле каталог")
    public AppFormSteps clearCatalogValue(String nameOfField) {
        WebElement catalogField = getCatalogField().getField(nameOfField);
        try {
            catalogField.click();
            getWait().waitForElementClickable(4, objectManager.getAppEditPage().clearCatalogValueButton);
            objectManager.getAppEditPage().clearCatalogValueButton.click();
            objectManager.getAppEditPage().closeCatalogPopupLocator.click();
            fieldHasChanged = true;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Утвердить заявку из формы заявки")
    public AppListSteps approveButtonClick() {
        try {
            objectManager.getAppEditPage().approveAppButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return getAppListSteps();
    }

    @Step("Отменить заявку из формы заявки")
    public AppListSteps cancelButtonClick() {
        try {
            objectManager.getAppEditPage().cancelAppButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return getAppListSteps();
    }

    @Step("Закрыть форму заявки")
    public AppListSteps backButtonClick() {
        try {
            objectManager.getAppEditPage().closeAppFormButton.click();
            if (fieldHasChanged == true) {
                fieldHasChanged = false;
                objectManager.getAppEditPage().yesGoOut.click();
            }
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return getAppListSteps();
    }

    @Step("Открыть инфо блок")
    public AppFormSteps showInformationBlockClick() {
        getWait().waitForElementClickable(2, objectManager.getAppEditPage().showInformationBlock);
        try {
            objectManager.getAppEditPage().showInformationBlock.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Открыть историю изменений")
    public AppFormSteps changesHistoryClick() {
        try {
            if (objectManager.getTestInfo().activeUser.getLocalizeLanguage() == BaseTest.EN)
                objectManager.getAppEditPage().changesHistory.click();
            else driver.$(By.xpath("//*[contains(text(), 'История изменений')]")).click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Открыть вкладку:")
    public AppFormSteps openTab(String nameOfTab) {
        try {
            driver.$(By.xpath("//*[@class='no-border pointer option_btn']/*[contains(text(), '" + nameOfTab + "')]")).click();
            driver.assertThat(ExpectedConditions.attributeContains(                      //verificationThat проверка, что вкладка открыта
                    driver.$(By.xpath("//*[contains(text(), '" + nameOfTab + "')]//parent::button")),
                    "class", "no-border pointer option_btn btn-active"));
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Добавить новую строку")
    public AppFormSteps addNewLineClick() {
        try {
            objectManager.getAppEditPage().addNewLine.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Открыть вкладку \"Файлы по заявке\"")
    public AppFormSteps openFilesTab() {
        try {
            objectManager.getAppEditPage().filesTab.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Прикрепить файл:")
    public AppFormSteps addFile(String wayToFile) {
        try {
            objectManager.getAppEditPage().addFileInput.sendKeys(new File(wayToFile).getAbsolutePath());
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }
}
