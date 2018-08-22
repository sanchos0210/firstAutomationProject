package com.abmcloud.cf.test.PageObject.pages;

import com.abmcloud.cf.test.Driver.BasePage;
import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(css = "*[ng-reflect-ng-switch=\"accountChange\"] *.d-inline-block.change_date *.pay-btn.btn.btn-info.btn-sm")
    public WebElement changeAccountOfPaymentButton;

    @FindBy(css = ".row.popup__header h3")
    public WebElement headerOfRegistry;

    @FindBy(css = "register *.height100.register_block span")
    public WebElement organizationFilterInRegistry;

    @FindBy(css = "register-account *.height100.register_block span")
    public WebElement accountsFilterInRegistry;

    @FindBy(css = "*.export")
    public WebElement exportAppListButton;

    @FindBy(css = ".btn.btn-default.no-border.pointer.popup_close")
    public WebElement closeRegistry;

    @FindBy(xpath = "//*[@class = 'action_panel']//text()[not(ancestor::i) and contains(., 'Оплатить')]//parent::*")
    public WebElement payButton;

    @FindBy(xpath = "//*[@class = 'action_panel']//text()[not(ancestor::i) and contains(., 'Утвердить')]//parent::*")
    public WebElement approveButton;

    @FindBy(css = "ul.ui-tree-container>p-treenode>li.ui-treenode.ui-treenode-leaf")
    public List<WebElement> filterGroupsList;

    @FindBy(css = "ul.filter_column li span")
    public List<WebElement> filterByCatalogs;

    @FindBy(css = ".btn.btn-default.btn-header.btn-filter.pointer")
    public WebElement filterButton;

    @FindBy(css = ".ui-widget-content.no-top-border.budget")
    public WebElement balanceRow;
}
