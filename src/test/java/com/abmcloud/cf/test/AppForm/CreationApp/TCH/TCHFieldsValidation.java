package com.abmcloud.cf.test.AppForm.CreationApp.TCH;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;
import static com.abmcloud.cf.test.Driver.Constants.TODAY;

@Epic("Валидация полей в табличной части форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class TCHFieldsValidation extends BaseTest {

    Json dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new Json("app_form_db.json");
    }

    @Test(priority = 10)
    public void requiredDecimalTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 20)
    public void negativeValueInDecimalTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .editTCHDecimalField(dbInfo.getString("decimal_field_1"), "-150")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 30)
    public void zeroValueInDecimalTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .editTCHDecimalField(dbInfo.getString("decimal_field_1"), "0")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 40)
    public void literalsValueInDecimalTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .editTCHDecimalField(dbInfo.getString("decimal_field_1"), "5t5t")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 50)
    public void disabledDecimalTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .asserts().assertTrue(objectManager.getDecimalField().isDisabledTCH(dbInfo.getString("decimal_field_2")));
    }

    @Test(priority = 60)
    public void autoInsertDecimalAndStringTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .asserts()
                .compare("1", objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_2")))
                .compare("text1", objectManager.getStringField().getValueTCH(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 70)
    public void autoClearDecAndStrTCHFieldAfterAutoInsert() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_3"))
                .asserts()
                .compare("", objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_2")))
                .compare("", objectManager.getStringField().getValueTCH(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 80)
    public void autoInsertDecAndStrTCHFieldFromBody() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .newAppButtonClick()
                .asserts()
                .compare("1000", objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_2")))
                .compare("value 1", objectManager.getStringField().getValueTCH(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 90)
    public void autoClearDecAndStrTCHFieldAfterAutoInsertFromBody() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .newAppButtonClick()
                .clearCatalogValue(dbInfo.getString("catalog_field_2"))
                .asserts()
                .compare("", objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_2")))
                .compare("", objectManager.getStringField().getValueTCH(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 100)
    public void defaultValueInDecimalTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .newAppButtonClick()
                .asserts()
                .compare("35", objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_1")));
    }

    @Test(priority = 110)
    public void requiredStringTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 120)
    public void disabledStringTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .asserts().assertTrue(objectManager.getStringField().isDisabledTCH(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 130)
    public void defaultValueInStringTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .newAppButtonClick()
                .asserts()
                .compare("default text", objectManager.getStringField().getValueTCH(dbInfo.getString("string_field_1")));
    }

    @Test(priority = 140)
    public void checkingBooleanTCHFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .asserts()
                .compare("false" , objectManager.getBooleanField().getValueTCH(dbInfo.getString("boolean_field")))
                .getAppFormStep()
                .booleanButtonClick(dbInfo.getString("boolean_field"))
                .asserts()
                .compare("true" , objectManager.getBooleanField().getValueTCH(dbInfo.getString("boolean_field")));
    }

    @Test(priority = 150)
    public void autoInsertBooleanTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_5"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .asserts().compare("true", objectManager.getBooleanField().getValueTCH(dbInfo.getString("boolean_field")))
                .getAppFormStep()
                .catalogFieldClick(dbInfo.getString("catalog_field_5"))
                .catalogElementClick(dbInfo.getString("value_2"))
                .asserts().compare("false", objectManager.getBooleanField().getValueTCH(dbInfo.getString("boolean_field")))
                .getAppFormStep().clearCatalogValue(dbInfo.getString("catalog_field_5"))
                .asserts().compare("false", objectManager.getBooleanField().getValueTCH(dbInfo.getString("boolean_field")));
    }

    @Test(priority = 160)
    public void requiredCatalogTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .editTCHStringField("String field 1*", "text1")
                .editTCHDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 170)
    public void chosenValueOfCatalogTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .asserts().compare("Project 1", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_3")));
    }

    @Test(priority = 180)
    public void clearChosenValueOfCatalogTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_3"))
                .asserts().compare("", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_3")));
    }

    @Test(priority = 190)
    public void autoLimitCatalogFieldTCHByFieldFromBody() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .asserts().isElementPresent(objectManager.getCatalogField().getItem(dbInfo.getString("project_1")), true)
                .assertFalse(objectManager.getCatalogField().isItemPresent(dbInfo.getString("project_2")));
    }

    @Test(priority = 200)
    public void autoClearForAutoLimitCatalogFieldTCHByFieldFromBody() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_1"))
                .asserts()
                .compare("", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_3")))
                .compare("", objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_2")))
                .compare("", objectManager.getStringField().getValueTCH(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 210)
    public void chooseTodayDateInDateField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .newAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1", "text1")
                .editTCHDecimalField("Decimal field 1", "150")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare(objectManager.getDateField().getTCHValue("Date field 1"), objectManager.getDateUtil().getDate(TODAY, "dd.MM.yyyy"));
    }
}