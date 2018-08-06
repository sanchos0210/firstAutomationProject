package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldsValidation extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test(priority = 1)
    public void requiredDecimalFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare(textOfNotification,
                        "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 2)
    public void nonRequiredDecimalFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .asserts().assertTrue(helpers.compare(textOfNotification,
                "Document # "+numberOfCreatedApp+" was successfully saved."));
    }

    @Test(priority = 3)
    public void negativeValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"-150")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare(textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 4)
    public void zeroVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"0")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare(textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 5)
    public void lettersVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"), "5t5t")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare(textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 6)
    public void disableDecimalFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.isDecimalFieldDisabled(dbInfo.getString("decimal_field_1")));
    }

    @Test(priority = 7)
    public void defaultValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(
                helpers.compare("35", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_1"))));
    }

    @Test(priority = 8)
    public void autoInsert1Verification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(helpers.compare("1000", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare(helpers.getValueOfStringField(dbInfo.getString("string_field_2")), "value 1"));
    }

    @Test(priority = 9)
    public void autoClearStrAndDecFieldsAfterAutoInsert1() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .clearCatalogValue(dbInfo.getString("catalog_field_2"))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(helpers.compare("0", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("", helpers.getValueOfStringField(dbInfo.getString("string_field_2"))));
    }

    @Test(priority = 10)
    public void requiredStringFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare(textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 11)
    public void disableStringFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.isStringFieldDisabled(dbInfo.getString("string_field_1")));
    }

    @Test(priority = 12)
    public void defaultStringValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(
                helpers.compare("Default value", helpers.getValueOfStringField(dbInfo.getString("string_field_1"))));
    }

    @Test(priority = 13)
    public void disableBooleanFieldWithDefaultValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts()
                .assertTrue(helpers.isBooleanFieldDisable(dbInfo.getString("boolean_field_1")))
                .assertTrue(helpers.compare("true" , helpers.getValueOfBooleanField(dbInfo.getString("boolean_field_1"))))
                .assertTrue(helpers.compare("false" , helpers.getValueOfBooleanField(dbInfo.getString("boolean_field_2"))));
    }

    @Test(priority = 14)
    public void checkingBooleanFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .booleanButtonClick(dbInfo.getString("boolean_field_3"))
                .booleanButtonClick(dbInfo.getString("boolean_field_4"))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(helpers.compare("true" , helpers.getValueOfBooleanField(dbInfo.getString("boolean_field_3"))))
                .assertTrue(helpers.compare("false" , helpers.getValueOfBooleanField(dbInfo.getString("boolean_field_4"))));
    }

//    @Test(priority = 15)                                       //раскоментировать когда пофиксят CF-984
//    public void autoInsertBooleanButton() {
//        steps
//                .open(APP_FORM_DEMO_DB)
//                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
//                .createAppButtonClick()
//                .catalogFieldClick(catalogField.getField(dbInfo.getString("catalog_field_3")))
//                .catalogFieldClick(catalogField.getItem(dbInfo.getString("value_1")))
//                .edit(decimalField.getField(dbInfo.getString("decimal_field_3")), "150")
//                .edit(stringField.getField(dbInfo.getString("string_field_3")), "text3")
//                .catalogFieldClick(catalogField.getField(dbInfo.getString("catalog_field_2")))
//                .catalogFieldClick(catalogField.getItem(dbInfo.getString("contractor_1")))
//                .saveApplication()
//                .selectAppByNumber(numberOfCreatedApp)
//                .clickOnNumberOf(selectedApp)
//                .asserts().assertTrue(compare((booleanField.getValue(dbInfo.getString("boolean_field_3"))), "false"))
//                .getAppFormStep()
//                .clearCatalogValue(catalogField.getField(dbInfo.getString("catalog_field_2")))
//                .saveApplication()
//                .selectAppByNumber(numberOfCreatedApp)
//                .clickOnNumberOf(selectedApp)
//                .asserts().assertTrue(compare((booleanField.getValue(dbInfo.getString("boolean_field_3"))), "true"));
//    }

    @Test(priority = 16)
    public void requiredCatalogFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare(textOfNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 17)
    public void chooseValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .asserts().assertTrue(helpers.compare("Value 1", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_3"))));
    }

    @Test(priority = 18)
    public void clearCatalogValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_3"))
                .asserts().assertTrue(helpers.compare("", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_3"))));
    }

    @Test(priority = 4)
    public void disabledCatalogFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .asserts().assertFalse(helpers.isElementPresent(appEditPage.headerOfCatalogPopup));
    }

    @Test(priority = 19)
    public void defaultCatalogValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.compare("Prepare Payments 1", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_1"))));
    }

    @Test(priority = 20)
    public void autoInsertTwoFields() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(helpers.compare("1000", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("value 1", helpers.getValueOfStringField(dbInfo.getString("string_field_2"))));
    }

    @Test(priority = 21)
    public void autoLimitVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .asserts()
                .assertTrue(helpers.isElementPresent(helpers.getCatalogItem(dbInfo.getString("contractor_1"))))
                .getAppFormStep()
                .buttonClick(appEditPage.closeCatalogPopupLocator)
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogFolderClick(dbInfo.getString("folder_1"))
                .catalogElementClick(dbInfo.getString("value_2"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .asserts().assertFalse(helpers.isElementPresent(helpers.getCatalogItem(dbInfo.getString("contractor_1"))));
    }

    @Test(priority = 22)
    public void autoClearForAutoLimitVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_3"))
                .asserts()
                .assertTrue(helpers.compare("", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_2"))))
                .assertTrue(helpers.compare("", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("", helpers.getValueOfStringField(dbInfo.getString("string_field_2"))));
    }

    @Test(priority = 23)
    public void autoInsertChain() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts()
                .assertTrue(helpers.compare("Organization 1", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_5"))))
                .assertTrue(helpers.compare("10", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_5"))))
                .assertTrue(helpers.compare("text1", helpers.getValueOfStringField(dbInfo.getString("string_field_5"))))
                .assertTrue(helpers.compare("23.11.2017", helpers.getValueOfDateField(dbInfo.getString("date_field_4"))));
    }

    @Test(priority = 24)
    public void choseFutureDate() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .clickOnDateField(dbInfo.getString("date_field_2"))
                .buttonClick(appEditPage.tomorrowDate)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare(helpers.getTomorrowFullDate(), helpers.getValueOfDateField(dbInfo.getString("date_field_2"))));
    }

    @Test(priority = 25)
    public void choseYesterdayDate() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .clickOnDateField(dbInfo.getString("date_field_2"))
                .buttonClick(appEditPage.yesterdayDate)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare(helpers.getYesterdayFullDate(), helpers.getValueOfDateField(dbInfo.getString("date_field_2"))));
    }

    @Test(priority = 26)
    public void autoInsertDateField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .asserts().assertTrue(helpers.compare("24.10.2016", helpers.getValueOfDateField(dbInfo.getString("date_field_1"))));
    }

    @Test(priority = 27)
    public void autoClearAfterAutoInsertDateField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_2"))
                .asserts().assertTrue(helpers.compare("", helpers.getValueOfDateField(dbInfo.getString("date_field_1"))));
    }

    @Test(priority = 28)
    public void disableDateFieldValidation() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .clickOnDateField(dbInfo.getString("date_field_1"))
                .asserts().assertFalse(helpers.isElementPresent(appEditPage.headerOfDatepicker));
    }
}