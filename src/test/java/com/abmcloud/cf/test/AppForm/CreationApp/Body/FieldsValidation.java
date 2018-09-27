package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Ignore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;

@Epic("Валидация полей в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class FieldsValidation extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test(priority = 1)
    public void requiredDecimalFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveButtonClick()
                .asserts().compare(testInfo.textOfNotification,
                        "Oops! Looks like you have not filled out all of the required fields!");
    }

    @Test(priority = 2)
    public void nonRequiredDecimalFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .asserts().compare(testInfo.textOfNotification,
                "Document # "+testInfo.numberOfCreatedApp+" was successfully saved.");
    }

    @Test(priority = 3)
    public void negativeValueVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"-150")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveButtonClick()
                .asserts().compare(testInfo.textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!");
    }

    @Test(priority = 4)
    public void zeroVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"0")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveButtonClick()
                .asserts().compare(testInfo.textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!");
    }

    @Test(priority = 5)
    public void lettersVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"), "5t5t")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveButtonClick()
                .asserts().compare(testInfo.textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!");
    }

    @Test(priority = 6)
    public void disableDecimalFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts().assertTrue(objectManager.getDecimalField().isDisabled(dbInfo.getString("decimal_field_1")));
    }

    @Test(priority = 7)
    public void defaultValueVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts().compare("35", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_1")));
    }

    @Test(priority = 8)
    public void autoInsert1Verification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts()
                .compare("1000", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_2")))
                .compare(objectManager.getStringField().getValue(dbInfo.getString("string_field_2")), "value 1");
    }

    @Test(priority = 9)
    public void autoClearStrAndDecFieldsAfterAutoInsert1() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .clearCatalogValue(dbInfo.getString("catalog_field_2"))
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts()
                .compare("0", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_2")))
                .compare("", objectManager.getStringField().getValue(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 10)
    public void requiredStringFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .saveButtonClick()
                .asserts().compare(testInfo.textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!");
    }

    @Test(priority = 11)
    public void disableStringFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts().assertTrue(objectManager.getStringField().isDisabled(dbInfo.getString("string_field_1")));
    }

    @Test(priority = 12)
    public void defaultStringValueVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts().compare("Default value", objectManager.getStringField().getValue(dbInfo.getString("string_field_1")));
    }

    @Test(priority = 13)
    public void disableBooleanFieldWithDefaultValueVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts()
                .assertTrue(objectManager.getBooleanField().isDisable(dbInfo.getString("boolean_field_1")))
                .compare("true" , objectManager.getBooleanField().getValue(dbInfo.getString("boolean_field_1")))
                .compare("false" , objectManager.getBooleanField().getValue(dbInfo.getString("boolean_field_2")));
    }

    @Test(priority = 14)
    public void checkingBooleanFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"),"text")
                .booleanButtonClick(dbInfo.getString("boolean_field_3"))
                .booleanButtonClick(dbInfo.getString("boolean_field_4"))
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts()
                .compare("true" , objectManager.getBooleanField().getValue(dbInfo.getString("boolean_field_3")))
                .compare("false" , objectManager.getBooleanField().getValue(dbInfo.getString("boolean_field_4")));
    }

    @Ignore
    @Test(priority = 15)                                       //раскоментировать когда пофиксят CF-984
    public void autoInsertBooleanButton() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"), "150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare((objectManager.getBooleanField().getValue(dbInfo.getString("boolean_field_3"))), "false")
                .getAppFormStep()
                .clearCatalogValue(dbInfo.getString("catalog_field_2"))
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare((objectManager.getBooleanField().getValue(dbInfo.getString("boolean_field_3"))), "true");
    }

    @Test(priority = 16)
    public void requiredCatalogFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .saveButtonClick()
                .asserts().compare(testInfo.textOfNotification, "Oops! Looks like you have not filled out all of the required fields!");
    }

    @Test(priority = 17)
    public void chooseValueVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .asserts().compare("Value 1", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_3")));
    }

    @Test(priority = 18)
    public void clearCatalogValueVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_3"))
                .asserts().compare("", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_3")));
    }

    @Test(priority = 4)
    public void disabledCatalogFieldVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .asserts().isElementPresent(objectManager.getAppEditPage().headerOfCatalogPopup, false);
    }

    @Test(priority = 19)
    public void defaultCatalogValueVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts().compare("Prepare Payments 1", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_1")));
    }

    @Test(priority = 20)
    public void autoInsertTwoFields() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts()
                .compare("1000", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_2")))
                .compare("value 1", objectManager.getStringField().getValue(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 21)
    public void autoLimitVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .asserts()
                .isElementPresent(objectManager.getCatalogField().getItem(dbInfo.getString("contractor_1")), true)
                .getAppFormStep()
                .buttonClick(objectManager.getAppEditPage().closeCatalogPopupLocator)
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogFolderClick(dbInfo.getString("folder_1"))
                .catalogElementClick(dbInfo.getString("value_2"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .asserts().assertFalse(objectManager.getCatalogField().isItemPresent(dbInfo.getString("contractor_1")));

    }

    @Test(priority = 22)
    public void autoClearForAutoLimitVerification() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_3"))
                .asserts()
                .compare("", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_2")))
                .compare("", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_2")))
                .compare("", objectManager.getStringField().getValue(dbInfo.getString("string_field_2")));
    }

    @Test(priority = 23)
    public void autoInsertChain() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts()
                .compare("Organization 1", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_5")))
                .compare("10", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_5")))
                .compare("text1", objectManager.getStringField().getValue(dbInfo.getString("string_field_5")))
                .compare("23.11.2017", objectManager.getDateField().getValue(dbInfo.getString("date_field_4")));
    }

    @Test(priority = 24)
    public void choseFutureDate() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .clickOnDateField(dbInfo.getString("date_field_2"))
                .buttonClick(objectManager.getDatePicker().tomorrowDate)
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare(objectManager.getDateUtil().getTomorrowFullDate(), objectManager.getDateField().getValue(dbInfo.getString("date_field_2")));
    }

    @Test(priority = 25)
    public void choseYesterdayDate() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"),"150")
                .editStringField(dbInfo.getString("string_field_3"), "text3")
                .clickOnDateField(dbInfo.getString("date_field_2"))
                .buttonClick(objectManager.getDatePicker().yesterdayDate)
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare(objectManager.getDateUtil().getYesterdayFullDate(), objectManager.getDateField().getValue(dbInfo.getString("date_field_2")));
    }

    @Test(priority = 26)
    public void autoInsertDateField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .asserts().compare("24.10.2016", objectManager.getDateField().getValue(dbInfo.getString("date_field_1")));
    }

    @Test(priority = 27)
    public void autoClearAfterAutoInsertDateField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .clearCatalogValue(dbInfo.getString("catalog_field_2"))
                .asserts().compare("", objectManager.getDateField().getValue(dbInfo.getString("date_field_1")));
    }

    @Test(priority = 28)
    public void disableDateFieldValidation() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .clickOnDateField(dbInfo.getString("date_field_1"))
                .asserts().isElementPresent(objectManager.getDatePicker().headerOfDatepicker, false);
    }
}