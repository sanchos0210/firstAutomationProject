package com.abmcloud.cf.test.AppForm.CreationApp.Title;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.Fields.BooleanField;
import com.abmcloud.cf.test.Fields.CatalogField;
import com.abmcloud.cf.test.Fields.DecimalField;
import com.abmcloud.cf.test.Fields.StringField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldsValidationTests extends BaseTest {

    BooleanField booleanField;
    CatalogField catalogField;
    DecimalField decimalField;
    StringField stringField;
    AppFormDBInfo appFormDBInfo;

    @BeforeMethod
    public void objectCreation() {
        booleanField = new BooleanField();
        catalogField = new CatalogField();
        decimalField = new DecimalField();
        stringField = new StringField();
        appFormDBInfo = new AppFormDBInfo();
    }

    public void createIncomeApp() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .booleanButtonClick(appEditPage.inOutSwitch)
                .catalogElementClick(catalogField.getField(appFormDBInfo.catalogField3))
                .catalogElementClick(appFormDBInfo.value1)
                .edit(decimalField.getField(appFormDBInfo.decimalField3), "150")
                .edit(stringField.getField(appFormDBInfo.stringField3), "text")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(compare("Inflow", booleanField.getInOutValue()));
    }

    public void createOutFlowApp() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDBInfo.catalogField3))
                .catalogElementClick(appFormDBInfo.value1)
                .edit(decimalField.getField(appFormDBInfo.decimalField3), "150")
                .edit(stringField.getField(appFormDBInfo.stringField3), "text")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(compare("Outflow", booleanField.getInOutValue()));
    }

    @Test(priority = 1)
    public void switchingInOutButton() {
        createIncomeApp();
        createOutFlowApp();
    }

    public void defaultOutflowValue() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments2)
                .createAppButtonClick()
                .asserts().assertTrue(compare("Outflow", booleanField.getInOutValue()));
    }

    public void defaultInflowValue() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments3)
                .createAppButtonClick()
                .asserts().assertTrue(compare("Inflow", booleanField.getInOutValue()));
    }

    @Test(priority = 2)
    public void defaultInOutValue() {
        defaultOutflowValue();
        defaultInflowValue();
    }

    @Test(priority = 3)
    public void checkDisabledBooleanField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments2)
                .createAppButtonClick()
                .asserts().assertTrue(booleanField.isInOutDisable())
                .getAppFormStep().backButtonClick()
                .openAppList(appFormDBInfo.preparePayments3)
                .createAppButtonClick()
                .asserts().assertTrue(booleanField.isInOutDisable());
    }
}