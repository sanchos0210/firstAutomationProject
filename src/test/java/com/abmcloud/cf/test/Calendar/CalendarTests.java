package com.abmcloud.cf.test.Calendar;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import org.junit.Ignore;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CalendarTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("calendar_db.json");
    }

    @Test(priority = 1)
    public void payAppTest() {
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .openCalendar("Оплата")
                .openRegistry()
                .checkAppWithNumber(numberOfCreatedApp)
                .payButtonClick()
                .closeRegistry()
                .assertPaid(helpers.getTodayFullDate(), 1000);
    }

    @Test(priority = 10)
    public void changePaymentDateInRegistry() {
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .openCalendar("Оплата")
                .openRegistry()
                .checkAppWithNumber(numberOfCreatedApp)
                .changePaymentDate(helpers.getTomorrowDate())
                .closeRegistry()
                .clickOnSum(helpers.getTomorrowFullDate(), "Организация 1", 'B')    //B = OUTCOME
                .checkAppWithNumber(numberOfCreatedApp);
    }

    @Test(priority = 20)
    public void checkPeriodFilterVisibility() {
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .asserts()
                .assertTextInElement(calendarPage.datePickerToday, "Сегодня")
                .assertTextInElement(calendarPage.datePickerThisMonth, "Этот месяц")
                .assertTextInElement(calendarPage.datePickerThisYear, "Этот год")
                .assertTextInElement(calendarPage.datePickerLastMonth, "Прошлый месяц")
                .assertTextInElement(calendarPage.datePickerLastYear, "Прошлый год");
    }

    @Ignore
    @Test(priority = 30)
    public void checkPeriodicityFilterVisibility() {
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                //.clickOnPeriodicityFilter()
                .asserts()
                .assertTextInElement(calendarPage.periodicityByDays, "по дням")
                .assertTextInElement(calendarPage.periodicityByWeeks, "по неделям")
                .assertTextInElement(calendarPage.periodicityByMonth, "по месяцам")
                .assertTextInElement(calendarPage.periodicityByYears, "по годам");
    }

    @Test(priority = 40)
    public void checkTodayByDay() {
        List<String> expectedDays = new ArrayList<>();
        expectedDays.add(helpers.getTodayFullDate());
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .chooseToday()
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 50)
    public void checkThisMonthByDays() {
        List<String> expectedDays = helpers.getDaysInMonthFullDates(THIS_MONTH);
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .chooseThisMonth()
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 60)
    public void checkThisYearByMonth() {
        List<String> expectedDays = helpers.getMonthsInYearFullDate(THIS_YEAR);
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodicityFilter()
                .chooseByMonth()
                .clickOnPeriodFilter()
                .chooseThisYear()
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 70)
    public void checkLastMonthByDays() {
        List<String> expectedDays = helpers.getDaysInMonthFullDates(LAST_MONTH);
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .chooseLastMonth()
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 80)
    public void checkLastYearByMonth() {
        List<String> expectedDays = helpers.getMonthsInYearFullDate(LAST_YEAR);
        steps
                .open(CALENDAR_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodicityFilter()
                .chooseByMonth()
                .clickOnPeriodFilter()
                .chooseLastYear()
                .assertVisibleDates(expectedDays);
    }

}