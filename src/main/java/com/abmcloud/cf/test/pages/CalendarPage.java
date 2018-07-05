package com.abmcloud.cf.test.pages;

import com.abmcloud.cf.test.API.BasePage;
import com.abmcloud.cf.test.API.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalendarPage extends BasePage {

    public CalendarPage(Driver driver) {
        super(driver);
    }

    @FindBy(css = ".pointer.register_btn")
    public WebElement reestrButton;

    @FindBy(css = ".form-control.pointer.text-ellipsis.catalog-value.catalog-editable")
    public WebElement dateInRegistry;

    @FindBy(css = "*[ng-reflect-ng-switch='payDateChange'] .d-inline-block.change_date .pay-btn.btn.btn-info.btn-sm")
    public WebElement changePaymentDateApproveButton;

    @FindBy(css = ".row.popup__header h3")
    public WebElement headerOfRegistry;

    @FindBy(css = ".btn.btn-default.no-border.pointer.popup_close")
    public WebElement closeRegistry;

    @FindBy(xpath = "//*[@class = 'action_panel']//text()[not(ancestor::i) and contains(., 'Оплатить')]//parent::*")
    public WebElement payButton;

    @FindBy(xpath = "//*[@class = 'action_panel']//text()[not(ancestor::i) and contains(., 'Утвердить')]//parent::*")
    public WebElement approveButton;

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
}
