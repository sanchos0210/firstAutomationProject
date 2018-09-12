package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Constants;
import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.PageObject.Components.Confirmation;
import com.abmcloud.cf.test.PageObject.Components.Notification;
import io.qameta.allure.Step;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AppListSteps extends MenuSteps {

    Notification notification;
    Confirmation confirmation;

    public AppListSteps(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
        logs = objectManager.getLogs();
        notification = objectManager.getNotification();
        confirmation = objectManager.getConfirmation();
    }

    public void clickOn(By selector, WebElement row) {
        logs.infoMsg("Click on element in " + row.toString() + selector.toString());
        try {
            row.findElement(selector).click();
        } catch (RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    @Step("Закрыть попап \"Цепочка согласования\"")
    public AppListSteps closeChainStepsPopup() {
        logs.infoMsg("Click on :" + objectManager.getAppListPage().closeChainStepsPopup.toString());
        try {
            objectManager.getAppListPage().closeChainStepsPopup.click();
        } catch (RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }

    @Step("Кликаем на кнопку \"Новая заявка\"")
    public AppFormSteps createAppButtonClick() {
        logs.infoMsg("Click on: " + objectManager.getAppListPage().addNewButton.toString());
        try {
            objectManager.getAppListPage().addNewButton.click();
        } catch (RuntimeException e) {
            logs.errorMsg(e);
        }
        getWait().waitForElementClickable(4, objectManager.getAppEditPage().appFormBody);
        return getAppFormSteps();
    }

    public void selectAppByNumberInTable(String number, List<WebElement> table) {
        getWait().waitForClickable(objectManager.getAppListPage().numberOfApp);
        try {
            for (int j = 0; j < table.size(); j++) {
                WebElement row = table.get(j);
                String n = row.findElement(objectManager.getAppListPage().numberOfApp).getText();
                if (n.equals(number)) {
                    objectManager.getTestInfo().selectedApp = row;
                    objectManager.getTestInfo().numberOfSelectedApp = row.findElement(objectManager.getAppListPage().numberOfApp).getText();
                    objectManager.getTestInfo().statusOfSelectedApp = row.findElement(objectManager.getAppListPage().statusOfApp).getText();
                    break;
                }
                if(j == table.size()) {
                    logs.warning("Application has not found in application list!");
                }
            }
        } catch(RuntimeException e) {
                logs.errorMsg(e);
                throw e;
        }
    }

    @Step("Выбрать заявку с номером:")
    public AppListSteps selectAppByNumber(String number) {
        logs.infoMsg("Searching application in appList with num: " + number);
        selectAppByNumberInTable(number, objectManager.getAppListPage().rowsInAppList);
        return this;
    }

    //---------------------------------Steps in approve/cancel confirm popup--------------------------------------------
    @Step("Согласовать заявку и вписать комментарий:")
    public AppListSteps approveInApprovePopup(String comment) {
        logs.infoMsg("Approving application throw confirming popup with comment: " + comment);
        confirmation.approve(comment);
        notification.saveTextAndAppNumAndClickOnNotification();
        selectAppByNumber(objectManager.getTestInfo().numberOfSelectedApp);
        return this;
    }

    @Step("Отменить заявку и вписать комментарий:")
    public AppListSteps cancelInCancelPopup(String comment) {
        logs.infoMsg("Cancelling application throw confirming popup with comment: " + comment);
        confirmation.cancel(comment);
        notification.saveTextAndAppNumAndClickOnNotification();
        return this;
    }

    @Step("Кликнуть на номер заявки")
    public AppFormSteps clickOnNumberOf(WebElement application) {
        logs.infoMsg("Click on number of some application");
        clickOn(objectManager.getAppListPage().numberOfApp, application);
        return getAppFormSteps();
    }

    @Step("Открыть файлы по заявке")
    public AppListSteps openFilesOf(WebElement application) {
        logs.infoMsg("Click on files button of some application");
        clickOn(objectManager.getAppListPage().filesButton, application);
        return this;
    }

    //------------------------------------Global filter steps-----------------------------------------------------------
    @Step("Фильтровать по \"Доступно мне\"")
    public AppListSteps openAvailableToMe() {
        try {
            objectManager.getAppListPage().globalFilter.click();
            objectManager.getAppListPage().availibleToMe.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        getWait().preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Согласованные\"")
    public AppListSteps openApproved() {
        try {
            objectManager.getAppListPage().globalFilter.click();
            objectManager.getAppListPage().approved.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        getWait().preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Согласованные мной\"")
    public AppListSteps openApprovedByMe() {
        try {
            objectManager.getAppListPage().globalFilter.click();
            objectManager.getAppListPage().approvedByMe.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        getWait().preloadWait();
        return this;
    }

    @Step("Фильтровать по \"У меня на согласовании\"")
    public AppListSteps openOnMyApproval() {
        try {
            objectManager.getAppListPage().globalFilter.click();
            objectManager.getAppListPage().onMyApprovalButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        getWait().preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Отмененные\"")
    public AppListSteps openCanceled() {
        try {
            objectManager.getAppListPage().globalFilter.click();
            objectManager.getAppListPage().canceledButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        getWait().preloadWait();
        return this;
    }
    //----------------------------------Status steps--------------------------------------------------------------------
    @Step("Кликнуть на статус заявки")
    public AppListSteps clickOnStatusOf(WebElement application) {
        logs.infoMsg("Click on status of some application");
        clickOn(objectManager.getAppListPage().statusOfApp, application);
        return this;
    }

    @Step("Выполнить действие в статусе заявки:")
    public AppListSteps status(Constants approvingConstant, WebElement application) {
        //logs.infoMsg("Executing some action in status of application");
        switch(approvingConstant) {
            case SEND_FOR_APPROVAL: {     //send for approval from status
                clickOn(objectManager.getAppListPage().sendForApprovalFromStatus, application);
                break;
            }
            case APPROVE: {     //approve application from status
                clickOn(objectManager.getAppListPage().approveFromStatus, application);
                break;
            }
            case CANCEL: {     //cancel application from status
                clickOn(objectManager.getAppListPage().cancelFromStatus, application);
                break;
            }
        }
        notification.saveTextAndAppNumAndClickOnNotification();
        return this;
    }

    @Step("Выполнить действие в статусе заявки:")
    public AppListSteps status(Constants approvingConstant, WebElement application, String comment) {
        logs.infoMsg("Executing some action in status of application");
        switch(approvingConstant) {
            case APPROVE: {     //approve application from status
                clickOn(objectManager.getAppListPage().approveFromStatus, application);
                approveInApprovePopup(comment);
                break;
            }
            case CANCEL: {     //cancel application from status
                clickOn(objectManager.getAppListPage().cancelFromStatus, application);
                cancelInCancelPopup(comment);
                break;
            }
        }
        return this;
    }
    //------------------------------------------------------------------------------------------------------------------
    public AppListSteps createApp(JSONArray fields) {
        logs.infoMsg("Creation application has started");
        try {
            objectManager.getAppListPage().addNewButton.click();
            for (int i = 0; i < fields.length(); i++) {
                JSONArray field = fields.getJSONArray(i);
                if (field.get(0).equals("DECIMAL_FIELD")) {
                    getAppFormSteps().editDecimalField((String) field.get(1), (String) field.get(2));
                }

                if (field.get(0).equals("STRING_FIELD")) {
                    getAppFormSteps().editStringField((String) field.get(1), (String) field.get(2));
                }

                if (field.get(0).equals("CATALOG_FIELD")) {
                    getAppFormSteps().catalogFieldClick((String) field.get(1))
                            .catalogElementClick((String) field.get(2));
                }
                if(field.get(0).equals("BOOLEAN_FIELD")) {
                    if(field.get(1).equals("in_out")) {
                        getAppFormSteps().inOutButtonClick();
                    }
                    else {
                        getAppFormSteps().booleanButtonClick((String) field.get(1));
                    }
                }
            }
            getAppFormSteps().saveApplication();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }
    //----------------------------------------Action menu steps---------------------------------------------------------
    @Step("Открыть меню действия по заявке")
    public AppListSteps actionMenuButtonClick(WebElement application) {
        logs.infoMsg("Click on action menu button of some application");
        clickOn(objectManager.getAppListPage().actionMenu, application);
        return this;
    }

    @Step("Отправить заявку на согласование из меню действий")
    public AppListSteps sendForApprovalButtonClick(WebElement application) {
        logs.infoMsg("Send for approval some application");
        clickOn(objectManager.getAppListPage().sendForApproval, application);
        notification.saveTextAndAppNumAndClickOnNotification();
        return this;
    }

    @Step("Кликнуть редактировать заявку в меню действия")
    public AppFormSteps editButtonClick(WebElement application) {
        logs.infoMsg("Click on edit button of some application");
        clickOn(objectManager.getAppListPage().edit, application);
        return getAppFormSteps();
    }

    @Step("Согласовать заявку из меню действий")
    public AppListSteps approve(WebElement application) {
        logs.infoMsg("Click on approve button of some application");
        clickOn(objectManager.getAppListPage().approve, application);
        notification.saveTextAndAppNumAndClickOnNotification();
        return this;
    }

    @Step("Кликнуть на кнопку \"Утвердить\"")
    public AppListSteps approveButtonClick(WebElement application) {
        logs.infoMsg("Click on approve button of some application");
        clickOn(objectManager.getAppListPage().approve, application);
        return this;
    }

    @Step("Согласовать заявку из меню действий и вписать комментарий:")
    public AppListSteps approve(String comment, WebElement application) {
        logs.infoMsg("Click on approve button of some application");
        clickOn(objectManager.getAppListPage().approve, application);
        approveInApprovePopup(comment);
        return this;
    }

    @Step("Кликнуть копировать заявку в меню действий")
    public AppFormSteps copyButtonClick(WebElement application) {
        logs.infoMsg("Click on copy button of some application");
        clickOn(objectManager.getAppListPage().copy, application);
        return getAppFormSteps();
    }

    @Step("Отменить заявку из меню действий")
    public AppListSteps cancelButtonClick(WebElement application) {
        logs.infoMsg("Click on cancel button of some application");
        clickOn(objectManager.getAppListPage().cancel, application);
        return this;
    }

    @Step("Отменить заявку из меню действий и вписать комментарий:")
    public AppListSteps cancelButtonClick(String comment, WebElement application) {
        logs.infoMsg("Click on approve button of some application");
        clickOn(objectManager.getAppListPage().cancel, application);
        cancelInCancelPopup(comment);
        return this;
    }

    @Step("Обновить страницу со списком заявок")
    public AppListSteps refreshPage() {
        driver.getWebDriver().get(driver.getWebDriver().getCurrentUrl());
        getWait().loginWait();
        return this;
    }
}
