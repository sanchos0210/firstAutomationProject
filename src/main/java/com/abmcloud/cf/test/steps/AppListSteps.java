package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.EditAppData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class AppListSteps extends BaseSteps {

    public void clickOn(By selector, WebElement row) {
        row.findElement(selector).click();
    }

    @Step("Вылогинится")
    public LoginSteps logOut() {
        appListPage.avatar.click();
        appListPage.signOutButton.click();
        verificationThat(textToBePresentInElement((loginPage.cashflowTitle), "ABM cashflow"));
        return new LoginSteps();
    }

    @Step("Закрыть попап \"Цепочка согласования\"")
    public AppListSteps closeChainStepsPopup() {
        $(By.cssSelector(".fa.fa-fw.fa-close")).click();
        return this;
    }

    @Step("Кликаем на кнопку \"Новая заявка\"")
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

    @Step("Выбрать заявку с номером:")
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
    @Step("Согласовать заявку и вписать комментарий:")
    public AppListSteps approveInApprovePopup(String comment) {
        appListPage.commentFieldInCancelPopUp.sendKeys(comment);
        appListPage.approveButtonInApprovePopUp.click();
        saveTextAndNumberOfNotification();
        selectAppByNumber(BaseTest.numberOfSelectedApp);
        return this;
    }

    @Step("Отменить заявку и вписать комментарий:")
    public AppListSteps cancelInCancelPopup(String comment) {
        appListPage.commentFieldInCancelPopUp.sendKeys(comment);
        appListPage.cancelButtonInCancelPopUp.click();
        saveTextAndNumberOfNotification();
        return this;
    }

    @Step("Кликнуть на номер заявки")
    public AppFormSteps clickOnNumberOf(WebElement application) {
        clickOn(appListPage.numberOfApp, application);
        return new AppFormSteps();
    }

    @Step("Открыть файлы по заявке")
    public AppListSteps openFilesOf(WebElement application) {
        clickOn(appListPage.filesButton, application);
        return this;
    }

    //------------------------------------Global filter steps-----------------------------------------------------------
    @Step("Фильтровать по \"Доступно мне\"")
    public AppListSteps openAvailableToMe() {
        appListPage.globalFilter.click();
        appListPage.availibleToMe.click();
        preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Согласованные\"")
    public AppListSteps openApproved() {
        appListPage.globalFilter.click();
        appListPage.approved.click();
        preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Согласованные мной\"")
    public AppListSteps openApprovedByMe() {
        appListPage.globalFilter.click();
        appListPage.approvedByMe.click();
        preloadWait();
        return this;
    }

    @Step("Фильтровать по \"У меня на согласовании\"")
    public AppListSteps openOnMyApproval() {
        appListPage.globalFilter.click();
        appListPage.onMyApprovalButton.click();
        preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Отмененные\"")
    public AppListSteps openCanceled() {
        appListPage.globalFilter.click();
        appListPage.canceledButton.click();
        preloadWait();
        return this;
    }
    //----------------------------------Status steps--------------------------------------------------------------------
    @Step("Кликнуть на статус заявки")
    public AppListSteps clickOnStatusOf(WebElement application) {
        clickOn(appListPage.statusOfApp, application);
        return this;
    }

    @Step("Выполнить действие в статусе заявки:")
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

    @Step("Выполнить действие в статусе заявки:")
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
    @Step("Создать заявку")
    public AppListSteps createApp(EditAppData editAppData) {
        appListPage.addNewButton.click();
        editAppData.createApp();
        saveTextAndNumberOfNotification();
        return this;
    }

    public AppListSteps createApp2(EditAppData editAppData) {
        appListPage.addNewButton.click();
        editAppData.fillTheFields(BaseTest.fieldsToFill);
        appEditPage.saveButton.click();
        saveTextAndNumberOfNotification();
        return this;
    }
    //----------------------------------------Action menu steps---------------------------------------------------------
    @Step("Открыть меню действия по заявке")
    public AppListSteps actionMenuButtonClick(WebElement application) {
        clickOn(appListPage.actionMenu, application);
        return this;
    }

    @Step("Отправить заявку на согласование из меню действий")
    public AppListSteps sendForApprovalButtonClick(WebElement application) {
        clickOn(appListPage.sendForApproval, application);
        saveTextAndNumberOfNotification();                //selectAppByNumber(BaseTest.numberOfSelectedApp);
        return this;
    }

    @Step("Кликнуть редактировать заявку в меню действия")
    public AppFormSteps editButtonClick(WebElement application) {
        clickOn(appListPage.edit, application);
        return new AppFormSteps();
    }

    @Step("Согласовать заявку из меню действий")
    public AppListSteps approveButtonClick(WebElement application) {
        clickOn(appListPage.approve, application);
        saveTextAndNumberOfNotification();
        return this;
    }

    @Step("Согласовать заявку из меню действий и вписать комментарий:")
    public AppListSteps approveButtonClick(String comment, WebElement application) {
        clickOn(appListPage.approve, application);
        approveInApprovePopup(comment);
        return this;
    }

    @Step("Кликнуть копировать заявку в меню действий")
    public AppFormSteps copyButtonClick(WebElement application) {
        clickOn(appListPage.copy, application);
        return new AppFormSteps();
    }

    @Step("Отменить заявку из меню действий")
    public AppListSteps cancelButtonClick(WebElement application) {
        clickOn(appListPage.cancel, application);
        return this;
    }

    @Step("Отменить заявку из меню действий и вписать комментарий:")
    public AppListSteps cancelButtonClick(String comment, WebElement application) {
        clickOn(appListPage.cancel, application);
        cancelInCancelPopup(comment);
        return this;
    }
}
