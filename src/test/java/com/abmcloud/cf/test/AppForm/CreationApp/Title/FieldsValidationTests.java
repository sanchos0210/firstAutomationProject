package com.abmcloud.cf.test.AppForm.CreationApp.Title;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;

@Epic("Валидация полей в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class FieldsValidationTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    public void createIncomeApp() {
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .inOutButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"), "150")
                .editStringField(dbInfo.getString("string_field_3"), "text")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare("Inflow", objectManager.getBooleanField().getInOutValue());
    }

    public void createOutFlowApp() {
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .editDecimalField(dbInfo.getString("decimal_field_3"), "150")
                .editStringField(dbInfo.getString("string_field_3"), "text")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().compare("Outflow", objectManager.getBooleanField().getInOutValue());
    }

    @Test(priority = 1)
    public void switchingInOutButton() {
        createIncomeApp();
        createOutFlowApp();
    }

    public void defaultOutflowValue() {
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .asserts().compare("Outflow", objectManager.getBooleanField().getInOutValue());
    }

    public void defaultInflowValue() {
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .createAppButtonClick()
                .asserts().compare("Inflow", objectManager.getBooleanField().getInOutValue());
    }

    @Test(priority = 2)
    public void defaultInOutValue() {
        defaultOutflowValue();
        defaultInflowValue();
    }

    @Test(priority = 3)
    public void checkDisabledBooleanField() {
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_2"))
                .createAppButtonClick()
                .asserts().assertTrue(objectManager.getBooleanField().isInOutDisable())
                .getAppFormStep().backButtonClick()
                .openAppList(dbInfo.getString("prepare_payments_3"))
                .createAppButtonClick()
                .asserts().assertTrue(objectManager.getBooleanField().isInOutDisable());
    }
}