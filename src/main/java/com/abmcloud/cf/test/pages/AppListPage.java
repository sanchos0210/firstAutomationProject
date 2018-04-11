package com.abmcloud.cf.test.pages;

import com.abmcloud.cf.test.API.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppListPage extends BasePage {

    public AppListPage(WebDriver driver) {
        super(driver);
    }

    //row locators
    public By checkBox = By.cssSelector("p-dtcheckbox .ui-chkbox-box.ui-widget.ui-corner-all.ui-state-default");
    public By filesButton = By.cssSelector("actions-bar-cell .fa.fa-paperclip");
    public By actionMenu = By.cssSelector("td:nth-of-type(2) actions-bar-cell #msg-dd1 .dropdown-toggle");
    public By numberOfApp = By.cssSelector(".ui-cell-data int-cell *");
    public By category = By.cssSelector("td:nth-of-type(4) .ui-cell-data filter-item .element *");
    //action menu locators
    public By approve = By.cssSelector(".dropdown-item .fa.fa-thumbs-o-up");
    public By sendForApproval = By.cssSelector("li.dropdown-item.text-success .fa.fa-share");
    public By edit = By.cssSelector(".fa.fa-pencil");
    public By copy = By.cssSelector(".fa.fa-copy");
    public By cancel = By.cssSelector("li .fa.fa-thumbs-o-down");
    //locators in status-cell
    public By statusOfApp = By.cssSelector("td status-cell span.label.appl-status");
    public By sendForApprovalFromStatus = By.cssSelector(".btn.btn-default.btn-sm.no-border.pointer .fa.fa-share");
    public By approveFromStatus = By.cssSelector("td:nth-of-type(12) .btn.btn-default.btn-xs.no-border.pointer.status_btn .fa.fa-thumbs-o-up");
    public By cancelFromStatus = By.cssSelector("td:nth-of-type(12) .btn.btn-default.btn-xs.no-border.pointer.status_btn.status_cancel .fa.fa-thumbs-o-down");

    @FindBy(css = ".btn.btn-default.btn-header.btn-filter.pointer")
    public WebElement filterButton;     //кнопка фильтр - открывает поиск

    //Locators of Global filter----------------------------------------------------------------------
    @FindBy(css = "button.btn-group.btn.btn-header.btn-default.no-border.btn-type")
    public WebElement globalFilter;

    @FindBy(css = ".col-xs-12.col-sm-3.col-md-3.col-lg-3.dropdown__filter .dropdown-item *")
    public WebElement availibleToMe;

    @FindBy(css = ".col-xs-12.col-sm-4.col-md-4.col-lg-4.border-left .dropdown-item:nth-of-type(2) *")
    public WebElement createdByMeButton;

    @FindBy(css = ".col-xs-12.col-sm-5.col-md-5.col-lg-5.border-left .dropdown-item:nth-of-type(2) *")
    public WebElement onMyApprovalButton;

    @FindBy(css = ".col-xs-12.col-sm-4.col-md-4.col-lg-4.border-left .dropdown-item:nth-of-type(1) *")
    public WebElement canceledButton;
    //-----------------------------------------------------------------------------------------------

    @FindBy(css = ".btn.btn-primary.btn-sm.pointer")
            public WebElement addNewButton;

    /*@FindBy(css = "table tbody tr")
            public List<WebElement> table;
            */
    @FindBy(css = "#comment")
            public WebElement commentFieldInCancelPopUp;

    @FindBy(css = "button.btn.btn-danger.btn-xs.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-icon-left")
            public WebElement cancelButtonInCancelPopUp;

    @FindBy(xpath = "//*[@class = 'ui-button-text ui-clickable'][.='Утвердить']")
            public WebElement approveButtonInApprovePopUp;

    @FindBy(css = "upload-popup p-dialog .ui-dialog-titlebar.ui-widget-header.ui-helper-clearfix.ui-corner-top .ui-dialog-title")
            public WebElement FilesPopup;
    //-------------------------------------Steps popup locators---------------------------------------------------------
    @FindBy(css = "steps-popup p-dialog .ui-dialog-titlebar.ui-widget-header.ui-helper-clearfix.ui-corner-top .ui-dialog-title")
            public WebElement stepsPopup;

    @FindBy(css = "")
            public WebElement stepName;
    //------------------------------------------------------------------------------------------------------------------

    @FindBy(xpath = "//li[@class='active'][contains(text(), 'This month')]")
            public WebElement datePickerThisMonth;

    @FindBy(css = "[name='daterangeInput'] *")
            public WebElement selectedDate;
}