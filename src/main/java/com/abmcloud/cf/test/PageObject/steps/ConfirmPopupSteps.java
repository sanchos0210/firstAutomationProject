package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.Logs;
import com.abmcloud.cf.test.PageObject.pages.ConfirmElement;

public class ConfirmPopupSteps {

    Logs logs;
    ConfirmElement confirmPopupElement;

    public ConfirmPopupSteps(Driver driver, Logs logs) {
        confirmPopupElement = new ConfirmElement(driver);
        this.logs = logs;
    }

    public void approve(String comment) {
        try {
            confirmPopupElement.commentFieldInCancelPopUp.sendKeys(comment);
            confirmPopupElement.approveButtonInApprovePopUp.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    public void cancel(String comment) {
        try {
            confirmPopupElement.commentFieldInCancelPopUp.sendKeys(comment);
            confirmPopupElement.cancelButtonInCancelPopUp.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }
}
