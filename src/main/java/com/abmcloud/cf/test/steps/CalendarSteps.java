package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.Fields.DateField;
import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.CalendarPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CalendarSteps extends BaseSteps {

    public CalendarSteps(Driver driver) {
        this.driver = driver;
        calendarPage = new CalendarPage(driver);
    }

    private AppListPage getAppListPage() {
        if(appListPage == null) {
            appListPage = new AppListPage(driver);
        }
        return appListPage;
    }

    private AppEditPage getAppEditPage() {
        if(appEditPage == null) {
            appEditPage = new AppEditPage(driver);
        }
        return appEditPage;
    }

    @Step("Проверить период")
    public CalendarSteps clickOnPeriodFilter() {
        calendarPage.periodButton.click();
        return this;
    }

    @Step("Открыть фильтр \"Период\"")
    public CalendarSteps clickOnPeriodicityFilter() {
        calendarPage.periodicityButton.click();
        return this;
    }

    @Step("Открыть Реестр")
    public CalendarSteps openRegistry() {
        calendarPage.reestrButton.click();
        getWait().waitForElementClickable(3, calendarPage.headerOfRegistry);
        return this;
    }

    @Step("Открыть реестр на дату:")
    public CalendarSteps changeDateInRegistryOn(String date) {
        calendarPage.dateInRegistry.click();
        DateField dateField = new DateField(driver);
        dateField.getDateInDatePicker(driver.$(By.xpath("//*[@class='daterangepicker dropdown-menu ltr single opensright show-calendar'][last()-1]")), date).click();
        return this;
    }

    @Step("Выбрать платежку с номером:")
    public CalendarSteps checkAppWithNumber(String numberOfApp) {
        AppListSteps appListSteps = getAppListSteps();
        appListSteps.selectAppByNumberInTable(numberOfApp, driver.$$(By.cssSelector("table.appl_table.bg_on_hover tbody tr")));
        appListSteps.clickOn(getAppListPage().checkBox, BaseTest.selectedApp);
        return this;
    }

    @Step("Оплатить выбранные платежки")
    public CalendarSteps payButtonClick() {
        calendarPage.payButton.click();
        getWait().waitForElementClickable(3, calendarPage.headerOfRegistry);
        return this;
    }

    @Step("Утвердить выбранные платежки")
    public CalendarSteps approveButtonClick() {
        calendarPage.approveButton.click();
        getAppEditPage().applSavedNotification.click();
        getWait().waitForElementClickable(3, calendarPage.headerOfRegistry);
        return this;
    }

    @Step("Закрыть реестр")
    public CalendarSteps closeRegistry() {
        calendarPage.closeRegistry.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Изменить дату оплаты в реестре на:")
    public CalendarSteps changePaymentDate(String date) {
        DateField dateField = new DateField(driver);
        dateField.getField("Сменить дату оплаты").click();
        dateField.getDateInDatePicker(driver.$(By.xpath("//*[@class='daterangepicker dropdown-menu ltr single opensright show-calendar'][last()]")), date).click();
        calendarPage.changePaymentDateApproveButton.click();
        getWait().waitForElementClickable(3, calendarPage.headerOfRegistry);
        getAppListPage().applSavedNotification.click();
        return this;
    }

    private List<WebElement> getRowsBy(char in_out) {
        List<WebElement> rows = null;
        switch(in_out) {
            case 'A': {     //for income
                rows = driver.$$(By.xpath("//td[@class = 'total_in_title']//parent::tr//preceding::tr[@class = 'ui-widget-content no-top-border tree-row__0']"));
                break;
            }
            case 'B': {     //for outcome
                rows = driver.$$(By.xpath("//td[@class = 'total_in_title']//parent::tr//following::tr[@class = 'ui-widget-content no-top-border tree-row__0']"));
                break;
            }
        }
        if(rows == null) throw new NoSuchElementException("Не найдены данные в календаре");
        return rows;
    }

    private void getTotalIn() {
        driver.$$(By.xpath("//td[@class = 'total_in_title']//parent::tr"));
    }

    private void getTotalOutCells() {
        driver.$$(By.xpath("//td[@class = 'total_out_title']//parent::tr"));
    }

    private List<WebElement> getPaidCells() {
        return driver.$$(By.cssSelector(".ui-widget-content.no-top-border.is_paid_in_calendar td.dropdown.calendar_cell"));
    }

    private int getNumOfCellInRowForDate(String date) {
        List<WebElement> visibleDates = driver.$$(By.cssSelector(".calendarTable thead .ui-state-default.ui-unselectable-text.width-special span.ui-column-title.calendar_head"));
        for (int i = 0; i < visibleDates.size(); i++) {
            if (date.equals(visibleDates.get(i).getText())) return i;
        }
        throw new NoSuchElementException("Выбранная дата не отображена в календаре");
    }

    private List<WebElement> getCellsInRowWithGroup(List<WebElement> rows, String groupValue) {
        List<WebElement> cellsInRow = null;
        for(int i = 0; i < rows.size(); i++) {
            WebElement chosenRow = rows.get(i);
            if(groupValue.equals(chosenRow.findElement(By.cssSelector(".calendar_group_name")).getText())) {
                cellsInRow = chosenRow.findElements(By.cssSelector(".cell-gr1"));
            }
        }
        if(cellsInRow == null) throw new NoSuchElementException("Нужной строки нету в списке");
        return cellsInRow;
    }

    @Step("Расшифровать сумму с нужными датой, группировкой и инаутом")
    public CalendarSteps clickOnSum(String date, String rowGroup, char in_out) {
        List<WebElement> cells = getCellsInRowWithGroup(getRowsBy(in_out), rowGroup);
        try {
            cells.get(getNumOfCellInRowForDate(date)).findElement(By.cssSelector(".btn.no-border.nopadding.calendar-out")).click();
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException("Не найдены данные в ячейке");
        }
        getWait().waitForElementClickable(3, calendarPage.headerOfRegistry);
        return this;
    }

    @Step("Проверить оплату на дату с суммой:")
    public CalendarSteps assertPaid(String date, String sum) {
        List<WebElement> paidCells = getPaidCells();
        String verSum = paidCells.get(getNumOfCellInRowForDate(date)).findElement(By.cssSelector("b")).getText();
        asserts().assertTrue(sum.equals(verSum));
        return this;
    }
}