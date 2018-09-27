package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;

@Epic("Расчет полей в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class FieldCalculationTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test(priority = 1)
    public void calculationChain() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_5"))
                .createAppButtonClick()
                .openTab("Additional")
                .asserts()
                .compare(objectManager.getDecimalField().getValue("Decimal field 1"), "35")
                .compare(objectManager.getDecimalField().getValue("Decimal field 2"), "350")
                .compare(objectManager.getDecimalField().getValue("Decimal field 3"), "3500");
    }
}
