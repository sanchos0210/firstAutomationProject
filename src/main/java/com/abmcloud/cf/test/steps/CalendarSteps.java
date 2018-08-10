package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.API.Logs;
import com.abmcloud.cf.test.Fields.DateField;
import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.CalendarPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarSteps extends BaseSteps {

    private Map<String, String> paid;

    public CalendarSteps(Driver driver) {
        this.driver = driver;
        calendarPage = new CalendarPage(driver);
        logs = new Logs(CalendarSteps.class.getName());
        paid = new HashMap<String, String>();
        refreshPaidData();
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

    private void refreshPaidData() {
        List<WebElement> visibleDates = getVisibleDatesList();
        List<WebElement> paidCells = getPaidCells();
        if(visibleDates.size() != paidCells.size()) {
            logs.errorMsg(new RuntimeException("Количество дат и ячеек оплачено не совпадают!"));
        }
        for(int i = 0; i < visibleDates.size(); i++) {
            paid.put(visibleDates.get(i).getText(), paidCells.get(i).findElement(By.cssSelector("b")).getText());
        }
    }

    @Step("Открыть фильтр период")
    public CalendarSteps clickOnPeriodFilter() {
        calendarPage.periodButton.click();
        return this;
    }

    @Step("Выбрать период: Сегодня")
    public CalendarSteps chooseToday() {
        calendarPage.datePickerToday.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать период: Этот месяц")
    public CalendarSteps chooseThisMonth() {
        calendarPage.datePickerThisMonth.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать период: Прошлый месяц")
    public CalendarSteps chooseLastMonth() {
        calendarPage.datePickerLastMonth.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать период: Этот год")
    public CalendarSteps chooseThisYear() {
        calendarPage.datePickerThisYear.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать период: Прошлый год")
    public CalendarSteps chooseLastYear() {
        calendarPage.datePickerLastYear.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Открыть фильтр \"Период\"")
    public CalendarSteps clickOnPeriodicityFilter() {
        //calendarPage.periodicityButton.click();
        return this;
    }

    @Step("Выбрать периодичность: \"По месяцам\"")
    public CalendarSteps chooseByMonth() {
        //calendarPage.periodicityByMonth.click();
        Select select = new Select(calendarPage.periodicityButton);
        select.selectByIndex(2);
        getWait().calendarPreloadWait();
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
        appListPage.applSavedNotification.click();
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

    private List<WebElement> getVisibleDatesList() {
        return driver.$$(By.cssSelector(".calendarTable thead .ui-state-default.ui-unselectable-text.width-special span.ui-column-title.calendar_head"));
    }

    private int getNumOfCellInRowForDate(String date) {
        List<WebElement> visibleDates = getVisibleDatesList();
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
        if(cellsInRow == null) throw new NoSuchElementException("Нужной строки нету в таблице");
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
    public CalendarSteps assertPaid(String date, int sum) {
        List<WebElement> paidCells = getPaidCells();
        String verSum = paidCells.get(getNumOfCellInRowForDate(date)).findElement(By.cssSelector("b")).getText();

        //Следующие 2 строки обрабатывают сумму, полученную в строке из ячейки Оплачено в календаре
        verSum = verSum.replaceAll("\\s+","");      //чтобы убрать пробелы в сумме
        verSum = verSum.replaceAll("\\((.+?)\\)", "$1");        //чтобы убрать скобки в сумме


        int verificationSum = Integer.parseInt(verSum);
        String paidBefore = paid.get(date);

        paidBefore = paidBefore.replaceAll("\\s+","");      //чтобы убрать пробелы в сумме
        paidBefore = paidBefore.replaceAll("\\((.+?)\\)", "$1");        //чтобы убрать скобки в сумме


        int oonvertedPaidBefore = Integer.parseInt(paidBefore);
        int fullSum = sum + oonvertedPaidBefore;
        asserts().assertTrue(fullSum == verificationSum);
        return this;
    }

    public CalendarSteps assertVisibleDates(List<String> expectedDates) {
        List<WebElement> visibleDates = getVisibleDatesList();
        List<String> actualDates = new ArrayList<>();
        for(WebElement date: visibleDates) {
            actualDates.add(date.getText());
        }
        Assert.assertEquals(expectedDates, actualDates);
        return this;
    }
}