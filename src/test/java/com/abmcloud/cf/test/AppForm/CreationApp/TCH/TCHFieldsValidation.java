package com.abmcloud.cf.test.AppForm.CreationApp.TCH;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TCHFieldsValidation extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test(priority = 10)
    public void requiredDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 20)
    public void negativeValueInDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .editTCHDecimalField(dbInfo.getString("decimal_field_1"), "-150")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 30)
    public void zeroValueInDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .editTCHDecimalField(dbInfo.getString("decimal_field_1"), "0")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 40)
    public void literalsValueInDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .editTCHDecimalField(dbInfo.getString("decimal_field_1"), "5t5t")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 50)
    public void disabledDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.isDecimalFieldDisabledTCH(dbInfo.getString("decimal_field_2")));
    }

    @Test(priority = 60)
    public void autoInsertDecimalAndStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .asserts()
                .assertTrue(helpers.compare("1", helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("text1", helpers.getValueOfStringFieldTCH(dbInfo.getString("string_field_2"))));
    }

    @Test(priority = 70)
    public void autoClearDecAndStrTCHFieldAfterAutoInsert() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_3"))
                .asserts()
                .assertTrue(helpers.compare("", helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("", helpers.getValueOfStringFieldTCH(dbInfo.getString("string_field_2"))));
    }

    @Test(priority = 80)
    public void autoInsertDecAndStrTCHFieldFromBody() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .createAppButtonClick()
                .asserts()
                .assertTrue(helpers.compare("1000", helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("value 1", helpers.getValueOfStringFieldTCH(dbInfo.getString("string_field_2"))));
    }

    @Test(priority = 90)
    public void autoClearDecAndStrTCHFieldAfterAutoInsertFromBody() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .createAppButtonClick()
                .clearCatalogValue(dbInfo.getString("catalog_field_2"))
                .asserts()
                .assertTrue(helpers.compare("", helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("", helpers.getValueOfStringFieldTCH(dbInfo.getString("string_field_2"))));
    }

    @Test(priority = 100)
    public void defaultValueInDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .createAppButtonClick()
                .asserts()
                .assertTrue(helpers.compare("35", helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_1"))));
    }

    @Test(priority = 110)
    public void requiredStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1*", "text1")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 120)
    public void disabledStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.isStringFieldDisabledTCH(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 130)
    public void defaultValueInStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .createAppButtonClick()
                .asserts()
                .assertTrue(helpers.compare("default text", helpers.getValueOfStringFieldTCH(dbInfo.getString("string_field_1"))));
    }

    @Test(priority = 140)
    public void checkingBooleanTCHFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .asserts()
                .assertTrue(helpers.compare("false" , helpers.getValueOfBooleanFieldTCH(dbInfo.getString("boolean_field"))))
                .getAppFormStep()
                .booleanButtonClick(dbInfo.getString("boolean_field"))
                .asserts()
                .assertTrue(helpers.compare("true" , helpers.getValueOfBooleanFieldTCH(dbInfo.getString("boolean_field"))));
    }

    @Test(priority = 150)
    public void autoInsertBooleanTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_5"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .asserts().assertTrue(helpers.compare("true", helpers.getValueOfBooleanFieldTCH(dbInfo.getString("boolean_field"))))
                .getAppFormStep()
                .catalogFieldClick(dbInfo.getString("catalog_field_5"))
                .catalogElementClick(dbInfo.getString("value_2"))
                .asserts().assertTrue(helpers.compare("false", helpers.getValueOfBooleanFieldTCH(dbInfo.getString("boolean_field"))))
                .getAppFormStep().clearCatalogValue(dbInfo.getString("catalog_field_5"))
                .asserts().assertTrue(helpers.compare("false", helpers.getValueOfBooleanFieldTCH(dbInfo.getString("boolean_field"))));
    }

    @Test(priority = 160)
    public void requiredCatalogTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .editTCHStringField("String field 1*", "text1")
                .editTCHDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 170)
    public void chosenValueOfCatalogTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .asserts().assertTrue(helpers.compare("Project 1", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_3"))));
    }

    @Test(priority = 180)
    public void clearChosenValueOfCatalogTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_3"))
                .asserts().assertTrue(helpers.compare("", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_3"))));
    }

    @Test(priority = 190)
    public void autoLimitCatalogFieldTCHByFieldFromBody() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .asserts().assertTrue(helpers.isElementPresent(helpers.getCatalogItem(dbInfo.getString("project_1"))))
                .assertFalse(helpers.isElementPresent(helpers.getCatalogItem(dbInfo.getString("project_2"))));
    }

    @Test(priority = 200)
    public void autoClearForAutoLimitCatalogFieldTCHByFieldFromBody() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("project_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_1"))
                .asserts()
                .assertTrue(helpers.compare("", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_3"))))
                .assertTrue(helpers.compare("", helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("", helpers.getValueOfStringFieldTCH(dbInfo.getString("string_field_2"))));
    }

    @Test(priority = 210)
    public void chooseTodayDateInDateField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .catalogFieldClick("Catalog field 2*")
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editTCHStringField("String field 1", "text1")
                .editTCHDecimalField("Decimal field 1", "150")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare(helpers.getValueOfDateFieldTCH("Date field 1"), helpers.getTodayFullDate()));
    }
}