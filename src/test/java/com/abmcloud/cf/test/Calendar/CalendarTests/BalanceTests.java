package com.abmcloud.cf.test.Calendar.CalendarTests;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.RU;
import static com.abmcloud.cf.test.Driver.Constants.SEND_FOR_APPROVAL;

@Epic("Проверка балансов")
@Feature("Календарь")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class BalanceTests extends BaseTest {

    Json dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new Json("app_list_db.json");
    }

    @Test(priority = 1)
    public void balanceIsVisible() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar(dbInfo.getString("payment_calendar"))
                .asserts()
                .isElementPresent(objectManager.getCalendarPage().balanceRow, true);
    }

    @Test(priority = 2)
    public void balanceIsNotVisible() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER1, EMAIL1, PASSWORD, RU)
                .openCalendar(dbInfo.getString("approve_payment"))
                .asserts()
                .isElementPresent(objectManager.getCalendarPage().balanceRow, false);
    }

    @Test(priority = 3)
    public void createAppAsOutcomeAndVerifyBalance() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .openCalendar(dbInfo.getString("payment_calendar"))
                .checkBalances()
                .openAppList(dbInfo.getString("prepare_payments"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .openCalendar(dbInfo.getString("payment_calendar"))
                .openRegistry()
                .checkAppWithNumber(testInfo.numberOfCreatedApp)
                .payButtonClick()
                .closeRegistry()
                .assertBalance(objectManager.getDateUtil().getTodayFullDate(), 150);
    }
}