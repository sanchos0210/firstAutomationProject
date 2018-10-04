package com.abmcloud.cf.test.Calendar.DetailsAndRegistryTests;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Ignore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.*;

@Epic("Проверка реестра")
@Feature("Календарь")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class RegistryTests extends BaseTest {

    Json dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new Json("app_list_db.json");
    }

    @Test(priority = 1)
    public void elementVisibilityInRegistry() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .openCalendar(dbInfo.getString("payment_calendar"))
                .openRegistry()
                .checkAppWithNumber(testInfo.numberOfCreatedApp)
                .asserts()
                .assertTextInElement(objectManager.getCalendarPage().organizationFilterInRegistry, "ОРГАНИЗАЦИЯ")
                .assertTextInElement(objectManager.getCalendarPage().accountsFilterInRegistry, "СЧЕТА")
                .isElementPresent(objectManager.getCalendarPage().exportAppListButton, true)
                .isElementPresent(objectManager.getCalendarPage().payButton, true)
                .isElementPresent(objectManager.getCalendarPage().changeAccountOfPaymentButton, true)
                .isElementPresent(objectManager.getCalendarPage().changePaymentDateApproveButton, true);
    }

    @Test(priority = 2)
    public void elementVisibilityInDetailsPopup() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER1, EMAIL1, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_5th_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .openCalendar(dbInfo.getString("approve_payment"))
                .clickOnSum(objectManager.getDateUtil().getDate(TODAY, "dd.MM.yyyy"), "Организация 1", 'B')
                .checkAppWithNumber(testInfo.numberOfCreatedApp)
                .asserts()
                .isElementPresent(objectManager.getCalendarPage().organizationFilterInRegistry, false)
                .isElementPresent(objectManager.getCalendarPage().accountsFilterInRegistry, false)
                .isElementPresent(objectManager.getCalendarPage().exportAppListButton, false)
                .isElementPresent(objectManager.getCalendarPage().approveButton, true)
                .isElementPresent(objectManager.getCalendarPage().changeAccountOfPaymentButton, false)
                .isElementPresent(objectManager.getCalendarPage().changePaymentDateApproveButton, true);
    }

    @Test(priority = 10)
    public void changePaymentDateForPaymentInRegistry() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .openCalendar(dbInfo.getString("payment_calendar"))
                .openRegistry()
                .checkAppWithNumber(testInfo.numberOfCreatedApp)
                .changePaymentDate(objectManager.getDateUtil().getDate(TOMORROW, "d"))
                .closeRegistry()
                .clickOnSum(objectManager.getDateUtil().getDate(TOMORROW, "dd.MM.yyyy"), "Организация 1", 'B')    //B = OUTCOME
                .checkAppWithNumber(testInfo.numberOfCreatedApp);
    }

    @Ignore     //пока не работает функционал
    @Test(priority = 20)
    public void changePaymentDateForApplicationInRegistry() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER1, EMAIL1, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_5th_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .openCalendar(dbInfo.getString("approve_payment"))
                .openRegistry()
                .checkAppWithNumber(testInfo.numberOfCreatedApp)
                .changePaymentDate(objectManager.getDateUtil().getDate(TOMORROW, "d"))
                .closeRegistry()
                .clickOnSum(objectManager.getDateUtil().getDate(TOMORROW, "dd.MM.yyyy"), "Организация 1", 'B')    //B = OUTCOME
                .checkAppWithNumber(testInfo.numberOfCreatedApp);
    }

    @Test(priority = 30)
    public void payAppTest() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .openCalendar(dbInfo.getString("payment_calendar"))
                .openRegistry()
                .checkAppWithNumber(testInfo.numberOfCreatedApp)
                .payButtonClick()
                .closeRegistry()
                .assertPaid(objectManager.getDateUtil().getDate(TODAY, "dd.MM.yyyy"), 150);
    }
}
