package com.abmcloud.cf.test.PageObject.Components;

import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatePicker {

    @FindBy(css = ".daterangepicker.dropdown-menu.ltr.single.opensright.show-calendar .calendar.left.single")
    public WebElement headerOfDatepicker;

    @FindBy(xpath = "//table[@class='table-condensed']//td[@class='today active start-date active end-date available'"+
            "or @class='today weekend active start-date active end-date available']/following::td[1]")
    public WebElement tomorrowDate;

    @FindBy(xpath = "//table[@class='table-condensed']//td[@class='today active start-date active end-date available'"+
            "or @class='today weekend active start-date active end-date available']/preceding::td[1]")
    public WebElement yesterdayDate;

    @FindBy(css = "*[title='Изменить периодичность']")
    public WebElement periodicityButton;

    @FindBy(css = ".text-warning.datepicker_in_header.pointer.ng-pristine.ng-valid.ng-touched *:nth-of-type(1)")
    public WebElement periodicityByDays;

    @FindBy(css = "date-picker .text-warning.datepicker_in_header.pointer.ng-pristine.ng-valid.ng-touched *:nth-of-type(2)")
    public WebElement periodicityByWeeks;

    @FindBy(css = "date-picker .text-warning.datepicker_in_header.pointer.ng-pristine.ng-valid.ng-touched *:nth-of-type(3)")
    public WebElement periodicityByMonth;

    @FindBy(css = "date-picker .text-warning.datepicker_in_header.pointer.ng-pristine.ng-valid.ng-touched *:nth-of-type(4)")
    public WebElement periodicityByYears;

    public DatePicker(Driver driver) {
        PageFactory.initElements(driver.getWebDriver(), this);
    }
}
