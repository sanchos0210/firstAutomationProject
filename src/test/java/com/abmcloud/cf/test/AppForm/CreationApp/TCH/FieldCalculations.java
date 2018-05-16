package com.abmcloud.cf.test.AppForm.CreationApp.TCH;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.Fields.DecimalField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldCalculations extends BaseTest {

    AppFormDBInfo appFormDBInfo;
    DecimalField decimalField;

    @BeforeMethod
    public void objectCreation() {
        appFormDBInfo = new AppFormDBInfo();
        decimalField = new DecimalField();
    }

    @Test(priority = 1)
    public void calculateFieldByCondition() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments5)
                .createAppButtonClick()
                .edit(decimalField.getField(appFormDBInfo.decimalField1), "11")
                .asserts().assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3), "11"))
                .getAppFormStep().edit(decimalField.getField(appFormDBInfo.decimalField1), "111")
                .asserts().assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3), "10"));
    }

    @Test(priority = 10)
    public void calculationTCHFieldByChangingFieldInShapka() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments5)
                .createAppButtonClick()
                .addNewLineClick()
                .edit(decimalField.getField(appFormDBInfo.decimalField1), "11")
                .asserts()
                .assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3, 1), "11"))
                .assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3, 2), "11"))
                .getAppFormStep()
                .edit(decimalField.getField(appFormDBInfo.decimalField1), "111")
                .asserts()
                .assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3, 1), "10"))
                .assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3, 2), "10"));
    }

    @Test(priority = 20)
    public void calculationTCHFieldByChangingOtherTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments5)
                .createAppButtonClick()
                .addNewLineClick()
                .addNewLineClick()
                .edit(decimalField.getTCHField(appFormDBInfo.decimalField2, 2), "77")
                .asserts()
                .assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3, 1), "10"))
                .assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3, 2), "77"))
                .assertTrue(compare(decimalField.getTCHValue(appFormDBInfo.decimalField3, 3), "10"));
    }
}