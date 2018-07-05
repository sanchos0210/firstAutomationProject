package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdditionalTabTest extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test(priority = 1)
    public void requiredDecimalFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .createAppButtonClick()
                .openTab("Additional")
                .editStringField(dbInfo.getString("string_field_1"), "text")
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .openTab("Main")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 10)
    public void requiredStringFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .createAppButtonClick()
                .openTab("Additional")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .openTab("Main")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 20)
    public void requiredCatalogFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .createAppButtonClick()
                .openTab("Additional")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .editStringField(dbInfo.getString("string_field_1"), "text")
                .openTab("Main")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .saveButtonClick()
                .asserts().assertTrue(helpers.compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 30)
    public void autoInsertDecimalFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .openTab("Additional")
                .asserts().assertTrue(helpers.compare("1000", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_1"))));
    }

    @Test(priority = 40)
    public void autoInsertStringFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .openTab("Additional")
                .asserts().assertTrue(helpers.compare("value 1", helpers.getValueOfStringField(dbInfo.getString("string_field_1"))));
    }

    @Test(priority = 40)
    public void autoInsertCatalogFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .createAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .openTab("Additional")
                .asserts().assertTrue(helpers.compare("Value 1", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_2"))));
    }
}
