package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.EditAppData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class AppListSteps extends BaseSteps {

    public void clickOn(By selector, WebElement row) {
        row.findElement(selector).click();
    }

    public LoginSteps logOut() {
        appListPage.avatar.click();
        appListPage.signOutButton.click();
        verificationThat(textToBePresentInElement((loginPage.cashflowTitle), "ABM cashflow"));
        return new LoginSteps();
    }

    public AppListSteps closeChainStepsPopup() {
        $(By.cssSelector(".fa.fa-fw.fa-close")).click();
        return this;
    }

    public AppFormSteps createAppButtonClick() {
        appListPage.addNewButton.click();
        waitForElementClickable(4, appEditPage.editPopupTitle);
        return new AppFormSteps();
    }

    public void selectAppByNumberInTable(String number, List<WebElement> table) {
        waitForClickable(appListPage.numberOfApp);
        //List<WebElement> table = $$(By.cssSelector("table tbody tr"));
        for(int j = 0; j < table.size(); j++) {
            WebElement row = table.get(j);
            String n = row.findElement(appListPage.numberOfApp).getText();
            if(n.equals(number)) {
                BaseTest.selectedApp = row;
                BaseTest.numberOfSelectedApp = row.findElement(appListPage.numberOfApp).getText();
                try {
                    BaseTest.statusOfSelectedApp = row.findElement(appListPage.statusOfApp).getText();
                } catch(NoSuchElementException e) {
                    BaseTest.statusOfSelectedApp = null;
                }
                break;
            }
        }
    }

    public AppListSteps selectAppByNumber(String number) {
        selectAppByNumberInTable(number, $$(By.cssSelector("table tbody tr")));
        return this;
    }

    private AppListSteps saveTextAndNumberOfNotification() {
        BaseTest.textOfNotification = appListPage.applSavedNotification.getText();
        switch(BaseTest.activeUser.getLocalizeLanguage()) {
            case EN: {     //for english language
                BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(11, 18);
                break;
            }
            case RU: {     //for russian language
                BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(9, 16);
                break;
            }
        }
        appListPage.applSavedNotification.click();
        return this;
    }

    //---------------------------------Steps in approve/cancel confirm popup--------------------------------------------
    public AppListSteps approveInApprovePopup(String comment) {
        appListPage.commentFieldInCancelPopUp.sendKeys(comment);
        appListPage.approveButtonInApprovePopUp.click();
        saveTextAndNumberOfNotification();
        selectAppByNumber(BaseTest.numberOfSelectedApp);
        return this;
    }

    public AppListSteps cancelInCancelPopup(String comment) {
        appListPage.commentFieldInCancelPopUp.sendKeys(comment);
        appListPage.cancelButtonInCancelPopUp.click();
        saveTextAndNumberOfNotification();
        return this;
    }

    public AppFormSteps clickOnNumberOf(WebElement application) {
        clickOn(appListPage.numberOfApp, application);
        return new AppFormSteps();
    }

    public AppListSteps openFilesOf(WebElement application) {
        clickOn(appListPage.filesButton, application);
        return this;
    }

    //------------------------------------Global filter steps-----------------------------------------------------------

    public AppListSteps openOnMyApproval() {
        appListPage.globalFilter.click();
        appListPage.onMyApprovalButton.click();
        preloadWait();
        return this;
    }

    public AppListSteps openCanceled() {
        appListPage.globalFilter.click();
        appListPage.canceledButton.click();
        preloadWait();
        return this;
    }
    //----------------------------------Status steps--------------------------------------------------------------------

    public AppListSteps clickOnStatusOf(WebElement application) {
        clickOn(appListPage.statusOfApp, application);
        return this;
    }

    public AppListSteps status(char point, WebElement application) {
        switch(point) {
            case 'A': {     //send for approval from status
                clickOn(appListPage.sendForApprovalFromStatus, application);
                saveTextAndNumberOfNotification();
                break;
            }
            case 'C': {     //approve application from status
                clickOn(appListPage.approveFromStatus, application);
                saveTextAndNumberOfNotification();
                break;
            }
            case 'E': {     //cancel application from status
                clickOn(appListPage.cancelFromStatus, application);
                saveTextAndNumberOfNotification();
                break;
            }
        }
        return this;
    }

    public AppListSteps status(char point, WebElement application, String comment) {
        switch(point) {
            case 'C': {     //approve application from status
                clickOn(appListPage.approveFromStatus, application);
                approveInApprovePopup(comment);
                break;
            }
            case 'E': {     //cancel application from status
                clickOn(appListPage.cancelFromStatus, application);
                cancelInCancelPopup(comment);
                break;
            }
        }
        return this;
    }
    //------------------------------------------------------------------------------------------------------------------

    public AppListSteps createApp(EditAppData editAppData) {
        appListPage.addNewButton.click();
        editAppData.createApp();
        saveTextAndNumberOfNotification();
        return this;
    }
    //----------------------------------------Action menu steps---------------------------------------------------------
    public AppListSteps actionMenuButtonClick(WebElement application) {
        clickOn(appListPage.actionMenu, application);
        return this;
    }

    public AppListSteps sendForApprovalButtonClick(WebElement application) {
        clickOn(appListPage.sendForApproval, application);
        saveTextAndNumberOfNotification();                //selectAppByNumber(BaseTest.numberOfSelectedApp);
        return this;
    }

    public AppFormSteps editButtonClick(WebElement application) {
        clickOn(appListPage.edit, application);
        return new AppFormSteps();
    }

    public AppListSteps approveButtonClick(WebElement application) {
        clickOn(appListPage.approve, application);
        saveTextAndNumberOfNotification();
        return this;
    }

    public AppListSteps approveButtonClick(String comment, WebElement application) {
        clickOn(appListPage.approve, application);
        approveInApprovePopup(comment);
        return this;
    }

    public AppFormSteps copyButtonClick(WebElement application) {
        clickOn(appListPage.copy, application);
        return new AppFormSteps();
    }

    public AppListSteps cancelButtonClick(WebElement application) {
        clickOn(appListPage.cancel, application);
        return this;
    }

    public AppListSteps cancelButtonClick(String comment, WebElement application) {
        clickOn(appListPage.cancel, application);
        cancelInCancelPopup(comment);
        return this;
    }
}
