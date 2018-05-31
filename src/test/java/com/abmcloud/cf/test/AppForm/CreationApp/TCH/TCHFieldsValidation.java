package com.abmcloud.cf.test.AppForm.CreationApp.TCH;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.Fields.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TCHFieldsValidation extends BaseTest {

    AppFormDBInfo appFormDB;
    CatalogField catalogField;
    DecimalField decimalField;
    StringField stringField;
    BooleanField booleanField;
    DateField dateField;

    @BeforeMethod
    public void objectCreation() {
        appFormDB = new AppFormDBInfo();
        catalogField = new CatalogField();
        decimalField = new DecimalField();
        stringField = new StringField();
        booleanField = new BooleanField();
        dateField = new DateField();
    }

    @Test(priority = 10)
    public void requiredDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField("Catalog field 2*"))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField("String field 1*"), "text1")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 20)
    public void negativeValueInDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField("Catalog field 2*"))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField("String field 1*"), "text1")
                .edit(decimalField.getTCHField(appFormDB.decimalField1), "-150")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 30)
    public void zeroValueInDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField("Catalog field 2*"))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField("String field 1*"), "text1")
                .edit(decimalField.getTCHField(appFormDB.decimalField1), "0")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 40)
    public void literalsValueInDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField("Catalog field 2*"))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField("String field 1*"), "text1")
                .edit(decimalField.getTCHField(appFormDB.decimalField1), "5t5t")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 50)
    public void disabledDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .asserts().assertTrue(decimalField.isDisabledTCH(appFormDB.decimalField2));
    }

    @Test(priority = 60)
    public void autoInsertDecimalAndStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .asserts()
                .assertTrue(compare("1", decimalField.getTCHValue(appFormDB.decimalField2)))
                .assertTrue(compare("text1", stringField.getValueTCH(appFormDB.stringField2)));
    }

    @Test(priority = 70)
    public void autoClearDecAndStrTCHFieldAfterAutoInsert() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField3))
                .asserts()
                .assertTrue(compare("", decimalField.getTCHValue(appFormDB.decimalField2)))
                .assertTrue(compare("", stringField.getValueTCH(appFormDB.stringField2)));
    }

    @Test(priority = 80)
    public void autoInsertDecAndStrTCHFieldFromBody() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments3)
                .createAppButtonClick()
                .asserts()
                .assertTrue(compare("1000", decimalField.getTCHValue(appFormDB.decimalField2)))
                .assertTrue(compare("value 1", stringField.getValueTCH(appFormDB.stringField2)));
    }

    @Test(priority = 90)
    public void autoClearDecAndStrTCHFieldAfterAutoInsertFromBody() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments3)
                .createAppButtonClick()
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField2))
                .asserts()
                .assertTrue(compare("", decimalField.getTCHValue(appFormDB.decimalField2)))
                .assertTrue(compare("", stringField.getValueTCH(appFormDB.stringField2)));
    }

    @Test(priority = 100)
    public void defaultValueInDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments3)
                .createAppButtonClick()
                .asserts()
                .assertTrue(compare("35", decimalField.getTCHValue(appFormDB.decimalField1)));
    }

    @Test(priority = 110)
    public void requiredStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField("Catalog field 2*"))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField("String field 1*"), "text1")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 120)
    public void disabledStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .asserts().assertTrue(stringField.isDisabledTCH(appFormDB.stringField2));
    }

    @Test(priority = 130)
    public void defaultValueInStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments3)
                .createAppButtonClick()
                .asserts()
                .assertTrue(compare("default text", stringField.getValueTCH(appFormDB.stringField1)));
    }

    @Test(priority = 140)
    public void checkingBooleanTCHFieldVerification() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .asserts()
                .assertTrue(compare("false" , booleanField.getValueTCH(appFormDB.booleanField)))
                .getAppFormStep().booleanButtonClick(booleanField.getField(appFormDB.booleanField))
                .asserts()
                .assertTrue(compare("true" ,booleanField.getValueTCH(appFormDB.booleanField)));
    }

    @Test(priority = 150)
    public void autoInsertBooleanTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField5))
                .catalogElementClick(appFormDB.value1)
                .asserts().assertTrue(compare("true", booleanField.getValueTCH(appFormDB.booleanField)))
                .getAppFormStep().catalogElementClick(catalogField.getField(appFormDB.catalogField5))
                .catalogElementClick(appFormDB.value2)
                .asserts().assertTrue(compare("false", booleanField.getValueTCH(appFormDB.booleanField)))
                .getAppFormStep().clearCatalogValue(catalogField.getField(appFormDB.catalogField5))
                .asserts().assertTrue(compare("false", booleanField.getValueTCH(appFormDB.booleanField)));
    }

    @Test(priority = 160)
    public void requiredCatalogTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .edit(stringField.getTCHField("String field 1*"), "text1")
                .edit(decimalField.getTCHField(appFormDB.decimalField1), "150")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 170)
    public void chosenValueOfCatalogTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .asserts().assertTrue(compare("Project1", catalogField.getValue(appFormDB.catalogField3)));
    }

    @Test(priority = 180)
    public void clearChosenValueOfCatalogTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField3))
                .asserts().assertTrue(compare("", catalogField.getValue(appFormDB.catalogField3)));
    }

    @Test(priority = 190)
    public void autoLimitCatalogFieldTCHByFieldFromBody() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .asserts().assertTrue(isElementPresent(appFormDB.project1))
                .assertFalse(isElementPresent(appFormDB.project2));
    }

    @Test(priority = 200)
    public void autoClearForAutoLimitCatalogFieldTCHByFieldFromBody() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField1))
                .asserts()
                .assertTrue(compare("", catalogField.getValue(appFormDB.catalogField3)))
                .assertTrue(compare("", decimalField.getTCHValue(appFormDB.decimalField2)))
                .assertTrue(compare("", stringField.getValueTCH(appFormDB.stringField2)));
    }

    @Test(priority = 210)
    public void chooseTodayDateInDateField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField("Catalog field 2*"))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField("String field 1"), "text1")
                .edit(decimalField.getTCHField("Decimal field 1"), "150")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(compare(dateField.getTCHValue("Date field 1"), getTodayFullDate()));
    }
}