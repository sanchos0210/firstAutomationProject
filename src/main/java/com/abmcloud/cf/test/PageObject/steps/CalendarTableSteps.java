package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
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

public class CalendarTableSteps extends MenuSteps {

    private Map<String, String> paid;
    private Map<String, String> balances;

    public CalendarTableSteps(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
        logs = objectManager.getLogs();
        paid = new HashMap<>();
        balances = new HashMap<>();
        refreshPaidData();
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
    public CalendarTableSteps clickOnPeriodFilter() {
        objectManager.getCalendarPage().periodButton.click();
        return this;
    }

    @Step("Выбрать период: Сегодня")
    public CalendarTableSteps chooseToday() {
        objectManager.getCalendarPage().datePickerToday.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать период: Этот месяц")
    public CalendarTableSteps chooseThisMonth() {
        objectManager.getCalendarPage().datePickerThisMonth.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать период: Прошлый месяц")
    public CalendarTableSteps chooseLastMonth() {
        objectManager.getCalendarPage().datePickerLastMonth.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать период: Этот год")
    public CalendarTableSteps chooseThisYear() {
        objectManager.getCalendarPage().datePickerThisYear.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать период: Прошлый год")
    public CalendarTableSteps chooseLastYear() {
        objectManager.getCalendarPage().datePickerLastYear.click();
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Открыть фильтр \"Период\"")
    public CalendarTableSteps clickOnPeriodicityFilter() {
        objectManager.getDatePicker().periodicityButton.click();
        return this;
    }

    @Step("Выбрать периодичность: \"По месяцам\"")
    public CalendarTableSteps chooseByYears() {
        Select select = new Select(objectManager.getDatePicker().periodicityButton);
        select.selectByIndex(3);
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Выбрать периодичность: \"По месяцам\"")
    public CalendarTableSteps chooseByMonth() {
        Select select = new Select(objectManager.getDatePicker().periodicityButton);
        select.selectByIndex(2);
        getWait().calendarPreloadWait();
        return this;
    }

    @Step("Открыть Реестр")
    public CalendarListSteps openRegistry() {
        objectManager.getCalendarPage().reestrButton.click();
        getWait().waitForElementClickable(3, objectManager.getCalendarPage().headerOfRegistry);
        return getCalendarListStep();
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
    public CalendarListSteps clickOnSum(String date, String rowGroup, char in_out) {
        List<WebElement> cells = getCellsInRowWithGroup(getRowsBy(in_out), rowGroup);
        try {
            cells.get(getNumOfCellInRowForDate(date)).findElement(By.cssSelector(".btn.no-border.nopadding.calendar-out")).click();
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException("Не найдены данные в ячейке");
        }
        getWait().waitForElementClickable(3, objectManager.getCalendarPage().headerOfRegistry);
        return getCalendarListStep();
    }

    @Step("Проверить оплату на дату с суммой:")
    public CalendarTableSteps assertPaid(String date, int sum) {
        List<WebElement> paidCells = getPaidCells();
        String stringVerificationSum = paidCells.get(getNumOfCellInRowForDate(date)).findElement(By.cssSelector("b")).getText();

        int verificationSum = convertStringToInt(stringVerificationSum);
        String stringPaidBefore = paid.get(date);

        int paidBefore = convertStringToInt(stringPaidBefore);
        int fullSum = paidBefore + sum;
        Assert.assertEquals(fullSum, verificationSum);
        return this;
    }

    private int convertStringToInt(String stringNum) {
        stringNum = stringNum.replaceAll("\\s+","");      //чтобы убрать пробелы в сумме
        stringNum = stringNum.replaceAll("\\((.+?)\\)", "$1");        //чтобы убрать скобки в сумме
        return Integer.parseInt(stringNum);
    }

    @Step("Проверить баланс на дату с суммой:")
    public CalendarTableSteps assertBalance(String date, int sum) {
        List<WebElement> balanceCells = objectManager.getCalendarPage().balanceRow.findElements(By.cssSelector(".totalBalances"));
        String verSum = balanceCells.get(getNumOfCellInRowForDate(date)).findElement(By.cssSelector("b")).getText();

        int verificationSum = convertStringToInt(verSum);
        String stringBalanceBefore = balances.get(date);

        int balanceBefore = convertStringToInt(stringBalanceBefore);
        int fullSum = sum + balanceBefore;
        Assert.assertEquals(fullSum, verificationSum);
        return this;
    }

    public CalendarTableSteps assertVisibleDates(List<String> expectedDates) {
        List<WebElement> visibleDates = getVisibleDatesList();
        List<String> actualDates = new ArrayList<>();
        for(WebElement date: visibleDates) {
            actualDates.add(date.getText());
        }
        Assert.assertEquals(expectedDates, actualDates);
        return this;
    }

    @Step("Открыть фильтр по каталогам")
    public CalendarTableSteps openCatalogFilter() {
        objectManager.getCalendarPage().filterButton.click();
        return this;
    }

    @Step("Записать баланс")
    public CalendarTableSteps checkBalances() {
        List<WebElement> visibleDates = getVisibleDatesList();
        List<WebElement> balanceCells = objectManager.getCalendarPage().balanceRow.findElements(By.cssSelector(".totalBalances"));
        if(visibleDates.size() != balanceCells.size()) {
            logs.errorMsg(new RuntimeException("Количество дат и ячеек баланса не совпадают!"));
        }
        for(int i = 0; i < visibleDates.size(); i++) {
            balances.put(visibleDates.get(i).getText(), balanceCells.get(i).findElement(By.cssSelector("b")).getText());
        }
        return this;
    }
}