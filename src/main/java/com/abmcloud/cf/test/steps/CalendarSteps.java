package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.Fields.DateField;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CalendarSteps extends BaseSteps {

    public CalendarSteps openRegistry() {
        calendarPage.reestrButton.click();
        waitForElementClickable(3, calendarPage.headerOfRegistry);
        return this;
    }

    public CalendarSteps changeDateInRegistryOn(String date) {
        calendarPage.dateInRegistry.click();
        DateField dateField = new DateField();
        dateField.getDateInDatePicker($(By.xpath("//*[@class='daterangepicker dropdown-menu ltr single opensright show-calendar'][last()-1]")), date).click();
        return this;
    }

    public CalendarSteps checkAppWithNumber(String numberOfApp) {
        AppListSteps appListSteps = new AppListSteps();
        appListSteps.selectAppByNumberInTable(numberOfApp, $$(By.cssSelector("table.appl_table.bg_on_hover tbody tr")));
        appListSteps.clickOn(appListPage.checkBox, BaseTest.selectedApp);
        return this;
    }

    public CalendarSteps payButtonClick() {
        calendarPage.pauButton.click();
        waitForElementClickable(3, calendarPage.headerOfRegistry);
        return this;
    }

    public CalendarSteps closeRegistry() {
        calendarPage.closeRegistry.click();
        calendarPreloadWait();
        return this;
    }

    public CalendarSteps changePaymentDate(String date) {
        DateField dateField = new DateField();
        dateField.getField("Сменить дату оплаты").click();
        dateField.getDateInDatePicker($(By.xpath("//*[@class='daterangepicker dropdown-menu ltr single opensright show-calendar'][last()]")), date).click();
        calendarPage.changePaymentDateApproveButton.click();
        waitForElementClickable(3, calendarPage.headerOfRegistry);
        return this;
    }

    private List<WebElement> getRowsBy(char in_out) {
        List<WebElement> rows = null;
        switch(in_out) {
            case 'A': {     //for income
                rows = $$(By.xpath("//td[@class = 'total_in_title']//parent::tr//preceding::tr[@class = 'ui-widget-content no-top-border tree-row__0']"));
                break;
            }
            case 'B': {     //for outcome
                rows = $$(By.xpath("//td[@class = 'total_in_title']//parent::tr//following::tr[@class = 'ui-widget-content no-top-border tree-row__0']"));
                break;
            }
        }
        if(rows == null) throw new NoSuchElementException("Не найдены данные в календаре");
        return rows;
    }

    private void getTotalIn() {
        $$(By.xpath("//td[@class = 'total_in_title']//parent::tr"));
    }

    private void getTotalOutCells() {
        $$(By.xpath("//td[@class = 'total_out_title']//parent::tr"));
    }

    private List<WebElement> getPaidCells() {
        return $$(By.cssSelector(".ui-widget-content.no-top-border.is_paid_in_calendar td.dropdown.calendar_cell"));
    }

    private int getNumOfCellInRowForDate(String date) {
        List<WebElement> visibleDates = $$(By.cssSelector(".calendarTable thead .ui-state-default.ui-unselectable-text.width-special span.ui-column-title.calendar_head"));
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

    public CalendarSteps clickOnSum(String date, String rowGroup, char in_out) {
        List<WebElement> cells = getCellsInRowWithGroup(getRowsBy(in_out), rowGroup);
        try {
            cells.get(getNumOfCellInRowForDate(date)).findElement(By.cssSelector(".btn.no-border.nopadding.calendar-out")).click();
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException("Не найдены данные в ячейке");
        }
        waitForElementClickable(3, calendarPage.headerOfRegistry);
        return this;
    }

    public CalendarSteps assertPaid(String date, String sum) {
        List<WebElement> paidCells = getPaidCells();
        String verSum = paidCells.get(getNumOfCellInRowForDate(date)).findElement(By.cssSelector("b")).getText();
        asserts()
                .assertTrue(sum.equals(verSum));
        return this;
    }
}