package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.API.Logs;
import com.abmcloud.cf.test.pages.ConfirmElement;

public class ConfirmPopup {

    Logs logs;
    ConfirmElement confirmPopupElement;

    public ConfirmPopup(Driver driver, Logs logs) {
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
