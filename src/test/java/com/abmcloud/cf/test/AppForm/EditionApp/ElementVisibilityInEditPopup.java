package com.abmcloud.cf.test.AppForm.EditionApp;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("Отображение элементов в попапе редактирования в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ElementVisibilityInEditPopup extends BaseTest{

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
    }

    @Test(priority = 1)
    public void visibilityOfElementsInEditPopup() {
        dbInfo = new DataBaseInfo("app_form_db.json");
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .asserts()
                .isElementPresent(objectManager.getAppEditPage().approvalSteps, true)
                .isElementPresent(objectManager.getAppEditPage().changesHistory, false)
                .isElementPresent(objectManager.getAppEditPage().viewsHistory, true)
                .isElementPresent(objectManager.getAppEditPage().cancelAppButton, true)
                .isElementPresent(objectManager.getAppEditPage().approveAppButton, false)
                .isElementPresent(objectManager.getAppEditPage().saveButton, false);
    }

    @Test(priority = 2)
    public void historyChangesIsVisible() {
        dbInfo = new DataBaseInfo("app_form_db.json");
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .editDecimalField(dbInfo.getString("decimal_field_3"), "1900")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .asserts()
                .isElementPresent(objectManager.getAppEditPage().changesHistory, true);
    }

    @Test(priority = 3)
    public void cannotCancelAndCanApprove2UsersFromEditPopup() {
        dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts()
                .isElementPresent(objectManager.getAppEditPage().cancelAppButton, false)
                .isElementPresent(objectManager.getAppEditPage().approveAppButton, true)
                .getAppFormStep().backButtonClick()
                .logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().isElementPresent(objectManager.getAppEditPage().approveAppButton, true);
    }

}
