package com.abmcloud.cf.test.AppFormTests.TCH;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.Fields.BooleanField;
import com.abmcloud.cf.test.Fields.CatalogField;
import com.abmcloud.cf.test.Fields.DecimalField;
import com.abmcloud.cf.test.Fields.StringField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TCHFieldsValidation extends BaseTest {

    AppFormDBInfo appFormDB;
    CatalogField catalogField;
    DecimalField decimalField;
    StringField stringField;
    BooleanField booleanField;

    @BeforeMethod
    public void objectCreation() {
        appFormDB = new AppFormDBInfo();
        catalogField = new CatalogField();
        decimalField = new DecimalField();
        stringField = new StringField();
        booleanField = new BooleanField();
    }

    @Test(priority = 10)
    public void requiredDecimalTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField("String field1"), "text1")
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
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .edit(stringField.getTCHField("String field1"), "text1")
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
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .edit(stringField.getTCHField("String field1"), "text1")
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
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .edit(stringField.getTCHField("String field1"), "text1")
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
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .asserts()
                .assertTrue(compare("1", decimalField.getValueTCH(appFormDB.decimalField2)))
                .assertTrue(compare("text1", stringField.getValueTCH(appFormDB.stringField2)));
    }

    @Test(priority = 70)
    public void autoClearDecAndStrTCHFieldAfterAutoInsert() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField3))
                .catalogElementClick(appFormDB.project1)
                .clearCatalogValue(catalogField.getField(appFormDB.catalogField3))
                .asserts()
                .assertTrue(compare("", decimalField.getValueTCH(appFormDB.decimalField2)))
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
                .assertTrue(compare("1000", decimalField.getValueTCH(appFormDB.decimalField2)))
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
                .assertTrue(compare("", decimalField.getValueTCH(appFormDB.decimalField2)))
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
                .assertTrue(compare("35", decimalField.getValueTCH(appFormDB.decimalField1)));
    }

    @Test(priority = 110)
    public void requiredStringTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField(appFormDB.catalogField2))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField("String field1"), "text1")
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
                .catalogElementClick(catalogField.getField(appFormDB.catalogField1))
                .catalogElementClick(appFormDB.value1)
                .catalogElementClick(catalogField.getField("Catalog field 2*"))
                .catalogElementClick(appFormDB.contractor1)
                .edit(stringField.getTCHField(appFormDB.stringField1), "text3")
                .edit(decimalField.getTCHField(appFormDB.decimalField1),"150")
                .booleanButtonClick(booleanField.getField(appFormDB.booleanField))
                /*.saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)*/
                .asserts()
                .assertTrue(compare("true" ,booleanField.getValue(appFormDB.booleanField)));
                /*.getAppFormStep().booleanButtonClick(booleanField.getField(appFormDB.booleanField))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(compare("false", booleanField.getValue(appFormDB.booleanField)));*/
    }

    @Test(priority = 1)
    public void requiredCatalogTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDB.preparePayments2)
                .createAppButtonClick()
                .edit(stringField.getTCHField("String field1"), "text1")
                .edit(decimalField.getTCHField(appFormDB.decimalField1), "150")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }
}
