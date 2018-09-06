package com.abmcloud.cf.test.PageObject.Components;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.Logs;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmation {

    Logs logs;

    @FindBy(css = "#comment")
    public WebElement commentFieldInCancelPopUp;

    @FindBy(css = ".btn.btn-danger.btn-xs")   //button.btn.btn-danger.btn-xs.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-icon-left
    public WebElement cancelButtonInCancelPopUp;

    @FindBy(xpath = "//*[@class = 'ui-button-text ui-clickable'][.='Утвердить']//parent::button")
    public WebElement approveButtonInApprovePopUp;

    public Confirmation(Driver driver) {
        PageFactory.initElements(driver.getWebDriver(), this);
        this.logs = new Logs(Confirmation.class.getName());
    }

    public void approve(String comment) {
        try {
            commentFieldInCancelPopUp.sendKeys(comment);
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        approve();
    }

    public void approve() {
        try {
            approveButtonInApprovePopUp.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    public void cancel(String comment) {
        try {
            commentFieldInCancelPopUp.sendKeys(comment);
            cancelButtonInCancelPopUp.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    public void clickOnRedButton() {
        cancelButtonInCancelPopUp.click();
    }
}