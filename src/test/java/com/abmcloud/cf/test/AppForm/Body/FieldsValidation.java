package com.abmcloud.cf.test.AppForm.Body;

import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.Fields.*;
import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldsValidation extends BaseTest {

    AppFormDBInfo appFormDB;
    AppFormSteps appFormSteps;
    DecimalField decimalField;
    StringField stringField;
    CatalogField catalogField;
    BooleanField booleanField;
    DateField dateField;

    @BeforeMethod
    public void objectCreation() {
        appFormDB = new AppFormDBInfo();
        appFormSteps = new AppFormSteps();
        decimalField = new DecimalField();
        stringField = new StringField();
        catalogField = new CatalogField();
        booleanField = new BooleanField();
        dateField = new DateField();
    }

    @Test(priority = 1)
    public void requiredDecimalFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(stringField.getField(appFormDB.stringField3),"text")
                .saveButtonClick()
                .asserts().assertTrue(compare(textOfNotification,
                        "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 2)
    public void nonRequiredDecimalFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(appFormDB)
                .asserts().assertTrue(compare(textOfNotification,
                "Document # "+numberOfCreatedApp+" was successfully saved."));
    }

    @Test(priority = 3)
    public void negativeValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(decimalField.getField(appFormDB.decimalField3),"-150")
                .edit(stringField.getField(appFormDB.stringField3),"text")
                .saveButtonClick()
                .asserts().assertTrue(compare(textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 4)
    public void zeroVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(decimalField.getField(appFormDB.decimalField3),"-150")
                .edit(stringField.getField(appFormDB.stringField3),"text")
                .saveButtonClick()
                .asserts().assertTrue(compare(textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 5)
    public void lettersVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(decimalField.getField(appFormDB.decimalField3), "5t5t")
                .edit(stringField.getField(appFormDB.stringField3),"text")
                .saveButtonClick()
                .asserts().assertTrue(compare(textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 6)
    public void disableDecimalFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(decimalField.isDisabled(appFormDB.decimalField1));
    }

    @Test(priority = 7)
    public void defaultValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(
                        compare("35", decimalField.getValue(appFormDB.decimalField1)));
    }

    @Test(priority = 8)
    public void autoInsert1Verification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .catalogElementClick(appFormDB.contractor1)
                .edit(decimalField.getField(appFormDB.decimalField3),"150")
                .edit(stringField.getField(appFormDB.stringField3),"text")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(compare("1000", decimalField.getValue(appFormDB.decimalField2)))
                .assertTrue(compare(stringField.getValue(appFormDB.stringField2), "value 1"));
    }

    @Test(priority = 9)
    public void autoClearStrAndDecFieldsAfterAutoInsert1() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .catalogElementClick(appFormDB.contractor1)
                .edit(decimalField.getField(appFormDB.decimalField3),"150")
                .edit(stringField.getField(appFormDB.stringField3),"text")
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField2))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(compare("0", decimalField.getValue(appFormDB.decimalField2)))
                .assertTrue(compare("", stringField.getValue(appFormDB.stringField2)));
    }

    @Test(priority = 10)
    public void requiredStringFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(decimalField.getField(appFormDB.decimalField3),"150")
                .saveButtonClick()
                .asserts().assertTrue(compare(textOfNotification,
                "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 11)
    public void disableStringFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(stringField.isDisabled(appFormDB.stringField1));
    }

    @Test(priority = 12)
    public void defaultStringValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(
                compare("Default value", stringField.getValue(appFormDB.stringField1)));
    }

    @Test(priority = 13)
    public void disableBooleanFieldWithDefaultValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts()
                .assertTrue(booleanField.isDisable(appFormDB.booleanField1))
                .assertTrue(compare("true" ,booleanField.getValue(appFormDB.booleanField1)))
                .assertTrue(compare("false" ,booleanField.getValue(appFormDB.booleanField2)));
    }

    @Test(priority = 14)
    public void checkingBooleanFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(decimalField.getField(appFormDB.decimalField3),"150")
                .edit(stringField.getField(appFormDB.stringField3), "text3")
                .booleanButtonClick(booleanField.getField(appFormDB.booleanField3))
                .booleanButtonClick(booleanField.getField(appFormDB.booleanField4))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(compare("true" ,booleanField.getValue(appFormDB.booleanField3)))
                .assertTrue(compare("false" ,booleanField.getValue(appFormDB.booleanField4)));
    }

    /*@Test(priority = 15)                                       раскоментировать когда пофиксят CF-984
    public void autoInsertBooleanButton() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(appFormDB.catalogField3)
                .catalogElementClick(appFormDB.value1)
                .edit(appFormDB.decimalField3, "150")
                .edit(appFormDB.stringField3, "text3")
                .catalogElementClick(appFormDB.catalogField2)
                .catalogElementClick(appFormDB.contractor1)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertValueOfBooleanField(appFormDB.booleanField3, "true");
                .clearCatalogValue(appFormDB.catalogField2)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertValueOfBooleanField(appFormDB.booleanField3, "false");
    }*/

    @Test(priority = 16)
    public void requiredCatalogFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .edit(decimalField.getField(appFormDB.decimalField3),"150")
                .edit(stringField.getField(appFormDB.stringField3), "text3")
                .saveButtonClick()
                .asserts().assertTrue(compare(textOfNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 17)
    public void chooseValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .asserts().assertTrue(compare("Value 1", catalogField.getValue(appFormDB.catalogField3)));
    }

    @Test(priority = 18)
    public void clearCatalogValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField3))
                .asserts().assertTrue(compare("", catalogField.getValue(appFormDB.catalogField3)));
    }

    @Test(priority = 4)
    public void disabledCatalogFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .asserts().assertFalse(isElementPresent(appEditPage.headerOfCatalogPopup));
    }

    @Test(priority = 19)
    public void defaultCatalogValueVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts().assertTrue(compare("Prepare Payments 1", catalogField.getValue(appFormDB.catalogField1)));
    }

    @Test(priority = 20)
    public void autoInsertTwoFields() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(decimalField.getField(appFormDB.decimalField3),"150")
                .edit(stringField.getField(appFormDB.stringField3), "text3")
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .catalogElementClick(appFormDB.contractor1)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(compare("1000", decimalField.getValue(appFormDB.decimalField2)))
                .assertTrue(compare("value 1", stringField.getValue(appFormDB.stringField2)));
    }

    @Test(priority = 21)
    public void autoLimitVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .asserts()
                .assertTrue(isElementPresent(appFormDB.contractor1))
                .getAppFormStep()
                .catalogElementClick(appEditPage.closeCatalogPopupLocator)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.folder1)
                .catalogElementClick(appFormDB.value2)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .asserts().assertFalse(isElementPresent(appFormDB.contractor1));
    }

    @Test(priority = 22)
    public void autoClearForAutoLimitVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .catalogElementClick(appFormDB.contractor1)
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField3))
                .asserts()
                .assertTrue(compare("", catalogField.getValue(appFormDB.catalogField2)))
                .assertTrue(compare("", decimalField.getValue(appFormDB.decimalField2)))
                .assertTrue(compare("", stringField.getValue(appFormDB.stringField2)));
    }

    @Test(priority = 23)
    public void autoInsertChain() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .asserts()
                .assertTrue(compare("Organization 1", catalogField.getValue(appFormDB.catalogField5)))
                .assertTrue(compare("10", decimalField.getValue(appFormDB.decimalField5)))
                .assertTrue(compare("text1", stringField.getValue(appFormDB.stringField5)))
                .assertTrue(compare("23.11.2017", dateField.getValue(appFormDB.dateField4)));
    }

    @Test(priority = 24)
    public void choseFutureDate() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(decimalField.getField(appFormDB.decimalField3),"150")
                .edit(stringField.getField(appFormDB.stringField3), "text3")
                .catalogElementClick(dateField.getField(appFormDB.dateField2))
                .catalogElementClick(appEditPage.tomorrowDate)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(compare(getTomorrowFullDate(), dateField.getValue(appFormDB.dateField2)));
    }

    @Test(priority = 25)
    public void choseYesterdayDate() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .edit(decimalField.getField(appFormDB.decimalField3),"150")
                .edit(stringField.getField(appFormDB.stringField3), "text3")
                .catalogElementClick(dateField.getField(appFormDB.dateField2))
                .catalogElementClick(appEditPage.yesterdayDate)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(compare(getYesterdayFullDate(), dateField.getValue(appFormDB.dateField2)));
    }

    @Test(priority = 26)
    public void autoInsertDateField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .catalogElementClick(appFormDB.contractor1)
                .asserts().assertTrue(compare("24.10.2016", dateField.getValue(appFormDB.dateField1)));
    }

    @Test(priority = 27)
    public void autoClearAfterAutoInsertDateField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .catalogElementClick(appFormDB.contractor1)
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField2))
                .asserts().assertTrue(compare("", dateField.getValue(appFormDB.dateField1)));
    }

    @Test(priority = 28)
    public void disableDateFieldValidation() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(dateField.getField(appFormDB.dateField1))
                .asserts().assertFalse(isElementPresent(appEditPage.headerOfCatalogPopup));
    }
}