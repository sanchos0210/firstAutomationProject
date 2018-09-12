package com.abmcloud.cf.test.Calendar.CalendarTests;

import com.abmcloud.cf.test.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;

@Epic("Проверка фильтра по периоду и периодичности")
@Feature("Календарь")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class PeriodAndPeriodicityTests extends BaseTest {
/*
    @Test(priority = 40)
    public void checkTodayByDay() {
        List<String> expectedDays = new ArrayList<>();
        expectedDays.add(objectManager.getDateUtil().getTodayFullDate());
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .chooseToday()
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 50)
    public void checkThisMonthByDays() {
        List<String> expectedDays = objectManager.getDateUtil().getDaysInMonthFullDates(THIS_MONTH);
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .chooseThisMonth()
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 60)
    public void checkThisYearByMonth() {
        List<String> expectedDays = objectManager.getDateUtil().getMonthsInYearFullDate(THIS_YEAR);
        steps
                .open(APP_LIST_TEST_DB)
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
        List<String> expectedDays = objectManager.getDateUtil().getDaysInMonthFullDates(LAST_MONTH);
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .chooseLastMonth()
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 80)
    public void checkLastYearByMonth() {
        List<String> expectedDays = objectManager.getDateUtil().getMonthsInYearFullDate(LAST_YEAR);
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodicityFilter()
                .chooseByMonth()
                .clickOnPeriodFilter()
                .chooseLastYear()
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 90)
    public void defaultPeriodTest() {
        List<String> expectedDays = objectManager.getDateUtil().getDefaultPeriodForCalendar();
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .chooseThisMonth()
                .openAppList("Подготовка платежей")
                .openCalendar("Оплата")
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 90)
    public void setDefaultPeriodTest() {
        List<String> expectedDays = objectManager.getDateUtil().getSetDefaultPeriodForCalendar();
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Утвердить оплаты")
                .assertVisibleDates(expectedDays);
    }

    @Test(priority = 100)
    public void periodicityByYears() {
        List<String> expectedDays = objectManager.getDateUtil().getThisYear();
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Утвердить оплаты")
                .clickOnPeriodicityFilter()
                .chooseByYears()
                .assertVisibleDates(expectedDays);
    }*/
}
