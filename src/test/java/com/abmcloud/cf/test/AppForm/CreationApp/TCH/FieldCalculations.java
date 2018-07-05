package com.abmcloud.cf.test.AppForm.CreationApp.TCH;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldCalculations extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test(priority = 1)
    public void calculateFieldByCondition() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_5"))
                .createAppButtonClick()
                .editDecimalField(dbInfo.getString("decimal_field_1"), "11")
                .asserts().assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3")), "11"))
                .getAppFormStep()
                .editDecimalField(dbInfo.getString("decimal_field_1"), "111")
                .asserts().assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3")), "10"));
    }

    @Test(priority = 10)
    public void calculationTCHFieldByChangingFieldInShapka() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_5"))
                .createAppButtonClick()
                .addNewLineClick()
                .editDecimalField(dbInfo.getString("decimal_field_1"), "11")
                .asserts()
                .assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3"), 1), "11"))
                .assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3"), 2), "11"))
                .getAppFormStep()
                .editDecimalField(dbInfo.getString("decimal_field_1"), "111")
                .asserts()
                .assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3"), 1), "10"))
                .assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3"), 2), "10"));
    }

    @Test(priority = 20)
    public void calculationTCHFieldByChangingOtherTCHField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_5"))
                .createAppButtonClick()
                .addNewLineClick()
                .addNewLineClick()
                .editTCHDecimalField(dbInfo.getString("decimal_field_2"), 2, "77")
                .asserts()
                .assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3"), 1), "10"))
                .assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3"), 2), "77"))
                .assertTrue(helpers.compare(helpers.getValueOfDecimalFieldTCH(dbInfo.getString("decimal_field_3"), 3), "10"));
    }
}