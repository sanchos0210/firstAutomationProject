package com.abmcloud.cf.test.AppForm.CreationApp.TCH;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;

@Epic("Расчет полей в табличной части форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class FieldCalculations extends BaseTest {

    Json dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new Json("app_form_db.json");
    }

    @Test(priority = 1)
    public void calculateFieldByCondition() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_5"))
                .newAppButtonClick()
                .editDecimalField(dbInfo.getString("decimal_field_1"), "11")
                .asserts().compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3")), "11")
                .getAppFormStep()
                .editDecimalField(dbInfo.getString("decimal_field_1"), "111")
                .asserts().compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3")), "10");
    }

    @Test(priority = 10)
    public void calculationTCHFieldByChangingFieldInShapka() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_5"))
                .newAppButtonClick()
                .addNewLineClick()
                .editDecimalField(dbInfo.getString("decimal_field_1"), "11")
                .asserts()
                .compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3"), 1), "11")
                .compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3"), 2), "11")
                .getAppFormStep()
                .editDecimalField(dbInfo.getString("decimal_field_1"), "111")
                .asserts()
                .compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3"), 1), "10")
                .compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3"), 2), "10");
    }

    @Test(priority = 20)
    public void calculationTCHFieldByChangingOtherTCHField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_5"))
                .newAppButtonClick()
                .addNewLineClick()
                .addNewLineClick()
                .editTCHDecimalField(dbInfo.getString("decimal_field_2"), 2, "77")
                .asserts()
                .compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3"), 1), "10")
                .compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3"), 2), "77")
                .compare(objectManager.getDecimalField().getTCHValue(dbInfo.getString("decimal_field_3"), 3), "10");
    }
}