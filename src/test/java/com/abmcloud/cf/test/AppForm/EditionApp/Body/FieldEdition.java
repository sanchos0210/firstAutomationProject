package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;
import static com.abmcloud.cf.test.Driver.Constants.TOMORROW;

@Epic("Проверка изменения полей в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class FieldEdition extends BaseTest {

    Json dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new Json("app_form_db.json");
    }

    @Test(priority = 1)
    public void editionDecimalField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .editDecimalField(dbInfo.getString("decimal_field_3"), "500")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare("500", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_3")));
    }

    @Test(priority = 2)
    public void autoInsertFieldsInEditPopup() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick("Contractor 1")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick("Contractor 5")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts()
                .compare("5000", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_2")))
                .compare("value 5", objectManager.getStringField().getValue(dbInfo.getString("string_field_2")))
                .compare("false", objectManager.getBooleanField().getValue(dbInfo.getString("boolean_field_3")))
                .compare("02.10.2018", objectManager.getDateField().getValue(dbInfo.getString("date_field_1")));
    }

    @Test(priority = 10)
    public void editionStringField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .editStringField(dbInfo.getString("string_field_3"), "new text")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare("new text", objectManager.getStringField().getValue(dbInfo.getString("string_field_3")));
    }

    @Test(priority = 20)
    public void editionBooleanField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .booleanButtonClick(dbInfo.getString("boolean_field_3"))
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare("true", objectManager.getBooleanField().getValue(dbInfo.getString("boolean_field_3")));
    }

    @Test(priority = 30)
    public void editionCatalogField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogFolderClick(dbInfo.getString("folder_1"))
                .catalogElementClick(dbInfo.getString("value_2"))
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare("Value 2", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_3")));
    }

    @Test(priority = 40)
    public void editionDateField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .clickOnDateField(dbInfo.getString("date_field_2"))
                .buttonClick(objectManager.getDatePicker().tomorrowDate)
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare(objectManager.getDateUtil().getDate(TOMORROW, "dd.MM.yyyy"), objectManager.getDateField().getValue(dbInfo.getString("date_field_2")));
    }
}