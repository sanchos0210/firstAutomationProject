package com.abmcloud.cf.test.PageObject.pages;

import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmElement {

    public ConfirmElement(Driver driver) {
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    @FindBy(css = "#comment")
    public WebElement commentFieldInCancelPopUp;

    @FindBy(css = "button.btn.btn-danger.btn-xs.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-icon-left")
    public WebElement cancelButtonInCancelPopUp;

    @FindBy(xpath = "//*[@class = 'ui-button-text ui-clickable'][.='Утвердить']//parent::button")
    public WebElement approveButtonInApprovePopUp;
}