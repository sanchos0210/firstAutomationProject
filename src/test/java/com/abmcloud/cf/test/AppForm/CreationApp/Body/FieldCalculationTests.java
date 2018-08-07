package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldCalculationTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test(priority = 1)
    public void calculationChain() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_5"))
                .createAppButtonClick()
                .openTab("Additional")
                .asserts()
                .assertTrue(helpers.compare(helpers.getValueOfDecimalField("Decimal field 1"), "35"))
                .assertTrue(helpers.compare(helpers.getValueOfDecimalField("Decimal field 2"), "350"))
                .assertTrue(helpers.compare(helpers.getValueOfDecimalField("Decimal field 3"), "3500"));
    }
}
