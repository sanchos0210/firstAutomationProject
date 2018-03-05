package com.abmcloud.cf.test.pages;

import com.abmcloud.cf.test.architecture.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppEditPage extends BasePage {

    public AppEditPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = "popup-page h3")
            public WebElement editPopupTitle;

    @FindBy(xpath = "//*[contains(text(), 'Changes history')]")
            public WebElement changesHistory;

    @FindBy(xpath = "//*[contains(text(), 'Views history')]//parent::*")
            public WebElement viewsHistory;

    @FindBy(xpath = "//*[contains(text(), 'Approval steps')]")
            public WebElement approvalSteps;

    @FindBy(xpath = "//*[contains(text(), 'Amount')]//parent::legend")
            public WebElement amountHistory;


    @FindBy(css = ".absolute.pointer.slide_info.slide_info-left")
            public WebElement showInformationBlock;

    @FindBy(css = "button.no-border.option_btn.option_btn-save")
            public WebElement saveButton;

    @FindBy(css = ".no-border.pointer.option_btn.option_btn-cancel")
            public WebElement cancelAppButton;

    @FindBy(css = ".no-border.pointer.option_btn.option_btn-approve")
            public WebElement approveAppButton;

    @FindBy(css = "date-field div.form-control.pointer.catalog-value.catalog-editable")
            public WebElement paymentDateField;

    @FindBy(xpath = "//table[@class='table-condensed']//td[@class='today active start-date active end-date available']/following::td[1]")
            public WebElement futureDate;

    @FindBy(xpath = "//table[@class='table-condensed']//td[@class='today active start-date active end-date available']/preceding::td[1]")
            public WebElement precedentDate;

    @FindBy(css = ".col-xs-2.col-md-2.select_bool.input__switch p-inputswitch")
            public WebElement inOutSwitch;

    @FindBy(css = "desktop-buttons .btn.btn-default.no-border.pointer.close_btn")
            public WebElement backToAppList;

}
