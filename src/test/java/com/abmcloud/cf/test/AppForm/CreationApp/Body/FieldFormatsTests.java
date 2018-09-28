package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;

@Epic("Валидация форматов полей в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class FieldFormatsTests extends BaseTest {

    Json dbInfo;

    @BeforeMethod
    public void prepareToTest() {
        dbInfo = new Json("app_form_db.json");
    }

    @Test(priority = 1)
    public void placeholderOfDecimalField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts()
                .compare("0,1 - 1000000000", objectManager.getDecimalField().getPlaceHolder(dbInfo.getString("decimal_field_2")));
    }

    @Test(priority = 10)
    public void prefixAndSuffixOfDecimalField() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .createAppButtonClick()
                .editDecimalField(dbInfo.getString("decimal_field_2"), "555")
                .asserts()
                .compare("555", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_2")));
        //todo need add screenshot
    }
}
