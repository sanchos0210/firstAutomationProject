package com.abmcloud.cf.test.AppForm.CreationApp.Title;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldsValidationTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    public void createIncomeApp() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .inOutButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"), "150")
                .editStringField(dbInfo.getString("string_field_3"), "text")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare("Inflow", helpers.getInOutValue()));
    }

    public void createOutFlowApp() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"), "150")
                .editStringField(dbInfo.getString("string_field_3"), "text")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare("Outflow", helpers.getInOutValue()));
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
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.compare("Outflow", helpers.getInOutValue()));
    }

    public void defaultInflowValue() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.compare("Inflow", helpers.getInOutValue()));
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
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.isInOutDisable())
                .getAppFormStep().backButtonClick()
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .createAppButtonClick()
                .asserts().assertTrue(helpers.isInOutDisable());
    }
}