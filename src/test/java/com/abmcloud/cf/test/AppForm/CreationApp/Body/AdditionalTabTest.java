package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;

@Epic("Валидация полей в дополнительной вкладке в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class AdditionalTabTest extends BaseTest {

    Json dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new Json("app_form_db.json");
    }

    @Test(priority = 1)
    public void requiredDecimalFieldInAdditionalTab() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .newAppButtonClick()
                .openTab("Additional")
                .editStringField(dbInfo.getString("string_field_1"), "text")
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .openTab("Main")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 10)
    public void requiredStringFieldInAdditionalTab() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .newAppButtonClick()
                .openTab("Additional")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick(dbInfo.getString("value_1"))
                .openTab("Main")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 20)
    public void requiredCatalogFieldInAdditionalTab() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .newAppButtonClick()
                .openTab("Additional")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .editStringField(dbInfo.getString("string_field_1"), "text")
                .openTab("Main")
                .editDecimalField(dbInfo.getString("decimal_field_1"), "150")
                .saveButtonClick()
                .asserts().compare("Oops! Looks like you have not filled out all of the required fields!", testInfo.textOfNotification);
    }

    @Test(priority = 30)
    public void autoInsertDecimalFieldInAdditionalTab() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .openTab("Additional")
                .asserts().compare("1000", objectManager.getDecimalField().getValue(dbInfo.getString("decimal_field_1")));
    }

    @Test(priority = 40)
    public void autoInsertStringFieldInAdditionalTab() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .openTab("Additional")
                .asserts().compare("value 1", objectManager.getStringField().getValue(dbInfo.getString("string_field_1")));
    }

    @Test(priority = 50)
    public void autoInsertCatalogFieldInAdditionalTab() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .openAppList(dbInfo.getString("prepare_payments_4"))
                .newAppButtonClick()
                .catalogFieldClick(dbInfo.getString("catalog_field_1"))
                .catalogElementClick(dbInfo.getString("contractor_1"))
                .openTab("Additional")
                .asserts().compare("Value 1", objectManager.getCatalogField().getValue(dbInfo.getString("catalog_field_2")));
    }
}
