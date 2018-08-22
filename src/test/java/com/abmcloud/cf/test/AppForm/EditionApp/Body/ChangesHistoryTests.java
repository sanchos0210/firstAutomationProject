package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("Проверка истории изменений в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ChangesHistoryTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test(priority = 1)
    public void changesHistoryOfDecimalField() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .editDecimalField(dbInfo.getString("sum_field_name"), "200")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(dbInfo.getString("sum_field_name"), "150", "200");
    }

    @Test(priority = 2)
    public void changesHistoryOfStringField() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .editStringField(dbInfo.getString("description"), "Другое описание")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(dbInfo.getString("description"),dbInfo.getString("description"), "Другое описание");
    }

    @Test(priority = 3)
    public void changesHistoryOfCatalogField() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .catalogFieldClick(dbInfo.getString("contractor_field_name"))
                .catalogElementClick("Контрагент 2")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(dbInfo.getString("contractor_field_name"), "Контрагент 1", "Контрагент 2");
    }

    @Test(priority = 4)
    public void changesHistoryOfBooleanField() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .booleanButtonClick(dbInfo.getString("payment_type_field_name"))
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(dbInfo.getString("payment_type_field_name"), "Безналичные", "Наличные");
    }

    @Test(priority = 5)
    public void changesHistoryOfDateField() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .clickOnDateField(dbInfo.getString("payment_date_field_name"))
                .buttonClick(objectManager.getDatePicker().tomorrowDate)
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(dbInfo.getString("payment_date_field_name"), objectManager.getDateUtil().getTodayFullDate(), objectManager.getDateUtil().getTomorrowFullDate());
    }
}
