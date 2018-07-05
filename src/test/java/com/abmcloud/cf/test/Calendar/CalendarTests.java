package com.abmcloud.cf.test.Calendar;

import com.abmcloud.cf.test.API.BaseTest;

public class CalendarTests extends BaseTest {

    /*DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("calendar_db.json");
    }

    @Test(priority = 1)
    public void payAppTest() {
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .openCalendar("Оплата")
                .openRegistry()
                .checkAppWithNumber(numberOfCreatedApp)
                .payButtonClick()
                .closeRegistry()
                .assertPaid(getTodayFullDate(), "(1 000)");
    }

    @Test(priority = 10)
    public void changePaymentDateInRegistry() {
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .openCalendar("Оплата")
                .openRegistry()
                .checkAppWithNumber(numberOfCreatedApp)
                .changePaymentDate(getTomorrowDate())
                .closeRegistry()
                .clickOnSum(getTomorrowFullDate(), "Организация 1", OUTCOME)
                .checkAppWithNumber(numberOfCreatedApp);
    }

    @Test(priority = 20)
    public void checkPeriodFilterVisibility() {
       steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
               .asserts()
               .assertTextInElement(calendarPage.datePickerToday, "Сегодня")
               .assertTextInElement(calendarPage.datePickerThisMonth, "Этот месяц")
               .assertTextInElement(calendarPage.datePickerThisYear, "Этот год")
               .assertTextInElement(calendarPage.datePickerLastMonth, "Прошлый месяц")
               .assertTextInElement(calendarPage.datePickerLastYear, "Прошлый год");
    }
/*
    @Test(priority = 30)
    public void checkPeriodicityFilterVisibility() {
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .openCalendar(calendarDBInfo.calendarMenuButton)
                //.clickOnPeriodicityFilter()
                .asserts()
                .assertTextInElement(calendarPage.periodicityByDays, "по дням")
                .assertTextInElement(calendarPage.periodicityByWeeks, "по неделям")
                .assertTextInElement(calendarPage.periodicityByMonth, "по месяцам")
                .assertTextInElement(calendarPage.periodicityByYears, "по годам");
    }*/
}