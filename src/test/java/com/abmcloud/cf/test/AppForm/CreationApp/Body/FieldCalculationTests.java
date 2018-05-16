package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.Fields.DecimalField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldCalculationTests extends BaseTest {

    AppFormDBInfo appFormDBInfo;
    DecimalField decimalField;

    @BeforeMethod
    public void objectCreation() {
        appFormDBInfo = new AppFormDBInfo();
        decimalField = new DecimalField();
    }

    @Test(priority = 1)
    public void calculationChain() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments5)
                .createAppButtonClick()
                .openTab("Additional")
                .asserts()
                .assertTrue(compare(decimalField.getValue("Decimal field 1"), "35"))
                .assertTrue(compare(decimalField.getValue("Decimal field 2"), "350"))
                .assertTrue(compare(decimalField.getValue("Decimal field 3"), "3500"));
    }
}
