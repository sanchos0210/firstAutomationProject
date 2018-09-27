package com.abmcloud.cf.test.Calendar.CalendarTests;

import com.abmcloud.cf.test.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.RU;

@Epic("Отображение элементов в календаре")
@Feature("Календарь")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ElementVisibility extends BaseTest {

    @Test(priority = 1)
    public void checkFilterByGroups() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .asserts()
                .assertTextInElementsList(objectManager.getCalendarPage().filterGroupsList, "Организация")
                .assertTextInElementsList(objectManager.getCalendarPage().filterGroupsList, "Статья")
                .assertTextInElementsList(objectManager.getCalendarPage().filterGroupsList, "Валюта")
                .assertTextInElementsList(objectManager.getCalendarPage().filterGroupsList, "Контрагент")
                .assertTextInElementsList(objectManager.getCalendarPage().filterGroupsList, "Договор");
    }

    @Test(priority = 2)
    public void checkFilterByCatalogs() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .openCatalogFilter()
                .asserts()
                .assertTextInElementsList(objectManager.getCalendarPage().filterByCatalogs, "Организация")
                .assertTextInElementsList(objectManager.getCalendarPage().filterByCatalogs, "Статья")
                .assertTextInElementsList(objectManager.getCalendarPage().filterByCatalogs, "Валюта")
                .assertTextInElementsList(objectManager.getCalendarPage().filterByCatalogs, "Контрагент")
                .assertTextInElementsList(objectManager.getCalendarPage().filterByCatalogs, "Договор");
    }

    @Test(priority = 3)
    public void checkRegistry() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .openRegistry()
                .asserts().assertTextInElement(objectManager.getCalendarPage().headerOfRegistry, "Реестр платежей на");
    }

    @Test(priority = 10)
    public void checkPeriodFilterVisibility() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodFilter()
                .asserts()
                .assertTextInElement(objectManager.getCalendarPage().datePickerToday, "Сегодня")
                .assertTextInElement(objectManager.getCalendarPage().datePickerThisMonth, "Этот месяц")
                .assertTextInElement(objectManager.getCalendarPage().datePickerThisYear, "Этот год")
                .assertTextInElement(objectManager.getCalendarPage().datePickerLastMonth, "Прошлый месяц")
                .assertTextInElement(objectManager.getCalendarPage().datePickerLastYear, "Прошлый год");
    }

    @Ignore
    @Test(priority = 20)
    public void checkPeriodicityFilterVisibility() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCalendar("Оплата")
                .clickOnPeriodicityFilter()
                .asserts()
                .assertTextInElement(objectManager.getDatePicker().periodicityByDays, "по дням")
                .assertTextInElement(objectManager.getDatePicker().periodicityByWeeks, "по неделям")
                .assertTextInElement(objectManager.getDatePicker().periodicityByMonth, "по месяцам")
                .assertTextInElement(objectManager.getDatePicker().periodicityByYears, "по годам");
    }
}
