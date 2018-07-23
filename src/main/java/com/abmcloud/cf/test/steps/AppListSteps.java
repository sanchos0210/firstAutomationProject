package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.API.Logs;
import com.abmcloud.cf.test.pages.AppEditPage;
import com.abmcloud.cf.test.pages.AppListPage;
import com.abmcloud.cf.test.pages.LoginPage;
import io.qameta.allure.Step;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class AppListSteps extends BaseSteps {

    public AppListSteps(Driver driver) {
        this.driver = driver;
        appListPage = new AppListPage(driver);
        logs = new Logs(AppListSteps.class.getName());
    }

    private LoginPage getLoginPage() {
        if(loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    private AppEditPage getAppEditPage() {
        if(appEditPage == null) {
            appEditPage = new AppEditPage(driver);
        }
        return appEditPage;
    }

    public void clickOn(By selector, WebElement row) {
        row.findElement(selector).click();
    }

    @Step("Вылогинится")
    public LoginSteps logOut() {
        appListPage.avatar.click();
        appListPage.signOutButton.click();
        driver.assertThat(textToBePresentInElement((getLoginPage().cashflowTitle), "ABM cashflow"));     //verificationThat
        return getLoginSteps();
    }

    @Step("Закрыть попап \"Цепочка согласования\"")
    public AppListSteps closeChainStepsPopup() {
        driver.$(By.cssSelector(".fa.fa-fw.fa-close")).click();
        return this;
    }

    @Step("Кликаем на кнопку \"Новая заявка\"")
    public AppFormSteps createAppButtonClick() {
        appListPage.addNewButton.click();
        getWait().waitForElementClickable(4, getAppEditPage().editPopupTitle);
        return getAppFormSteps();
    }

    public void selectAppByNumberInTable(String number, List<WebElement> table) {
        getWait().waitForClickable(appListPage.numberOfApp);
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
        selectAppByNumberInTable(number, driver.$$(By.cssSelector("table tbody tr")));
        return this;
    }

    private AppListSteps saveTextAndNumberOfNotification() {
        BaseTest.textOfNotification = appListPage.applSavedNotification.getText();
        switch(BaseTest.activeUser.getLocalizeLanguage()) {
            case BaseTest.EN: {     //for english language
                BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(11, 18);
                break;
            }
            case BaseTest.RU: {     //for russian language
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
        return getAppFormSteps();
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
        getWait().preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Согласованные\"")
    public AppListSteps openApproved() {
        appListPage.globalFilter.click();
        appListPage.approved.click();
        getWait().preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Согласованные мной\"")
    public AppListSteps openApprovedByMe() {
        appListPage.globalFilter.click();
        appListPage.approvedByMe.click();
        getWait().preloadWait();
        return this;
    }

    @Step("Фильтровать по \"У меня на согласовании\"")
    public AppListSteps openOnMyApproval() {
        appListPage.globalFilter.click();
        appListPage.onMyApprovalButton.click();
        getWait().preloadWait();
        return this;
    }

    @Step("Фильтровать по \"Отмененные\"")
    public AppListSteps openCanceled() {
        appListPage.globalFilter.click();
        appListPage.canceledButton.click();
        getWait().preloadWait();
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
    public AppListSteps createApp(JSONArray fields) {
        appListPage.addNewButton.click();
        for(int i = 0; i < fields.length(); i++) {
            JSONArray field = fields.getJSONArray(i);
            if(field.get(0).equals("DECIMAL_FIELD")) {
                getAppFormSteps().editDecimalField((String) field.get(1), (String) field.get(2));
            }

            if(field.get(0).equals("STRING_FIELD")) {
                getAppFormSteps().editStringField((String) field.get(1), (String) field.get(2));
            }

            if(field.get(0).equals("CATALOG_FIELD")) {
                getAppFormSteps().catalogFieldClick((String) field.get(1))
                            .catalogElementClick((String) field.get(2));
            }
        }
        getAppEditPage().saveButton.click();
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
        saveTextAndNumberOfNotification();
        return this;
    }

    @Step("Кликнуть редактировать заявку в меню действия")
    public AppFormSteps editButtonClick(WebElement application) {
        clickOn(appListPage.edit, application);
        return getAppFormSteps();
    }

    @Step("Согласовать заявку из меню действий")
    public AppListSteps approve(WebElement application) {
        clickOn(appListPage.approve, application);
        saveTextAndNumberOfNotification();
        return this;
    }

    @Step("Кликнуть на кнопку \"Утвердить\"")
    public AppListSteps approveButtonClick(WebElement application) {
        clickOn(appListPage.approve, application);
        return this;
    }

    @Step("Согласовать заявку из меню действий и вписать комментарий:")
    public AppListSteps approve(String comment, WebElement application) {
        clickOn(appListPage.approve, application);
        approveInApprovePopup(comment);
        return this;
    }

    @Step("Кликнуть копировать заявку в меню действий")
    public AppFormSteps copyButtonClick(WebElement application) {
        clickOn(appListPage.copy, application);
        return getAppFormSteps();
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
