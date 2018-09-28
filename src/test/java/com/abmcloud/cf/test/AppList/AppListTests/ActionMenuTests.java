package com.abmcloud.cf.test.AppList.AppListTests;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.APPROVE;
import static com.abmcloud.cf.test.Driver.Constants.RU;
import static com.abmcloud.cf.test.Driver.Constants.SEND_FOR_APPROVAL;

@Epic("Проверка меню действий")
@Feature("Список заявок")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ActionMenuTests extends BaseTest {

    Json dbInfo;

    @BeforeMethod
    public void prepareToTest() {
        dbInfo = new Json("app_list_db.json");
    }

    @Test(priority = 1)
    public void sendForApprovalButtonInActMenu() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .approve(testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertTextIn(testInfo.selectedApp, objectManager.getAppListPage().statusOfApp, "Оплачена");
    }

    @Test(priority = 2)
    public void approveButtonInZeroStep() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .asserts().isButtonPresentInRow(testInfo.selectedApp, objectManager.getAppListPage().approve, false);
    }

    @Test(priority = 3)
    public void approveButtonInActMenuOnParallelStep() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE,testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approve)
                .getAppListStep().logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approve);
    }

    @Test(priority = 4)
    public void approveButtonInTwoApprovers() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approve)
                .getAppListStep().logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approve);
    }

    @Test(priority = 9)
    public void editButtonOpenEitPopup() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .editButtonClick(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle,
                "Редактирование заявки № " + testInfo.numberOfSelectedApp);
    }

    @Test(priority = 10)
    public void editButtonIsNotPresent() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .asserts()
                .isButtonPresentInRow(testInfo.selectedApp, objectManager.getAppListPage().edit, true);
    }

    @Test(priority = 20)
    public void copyPopupOpening() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .copyButtonClick(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Копирование заявки");
        }

    @Test(priority = 29)
    public void cancelButtonIsPresent() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .asserts().isButtonPresentInRow(testInfo.selectedApp, objectManager.getAppListPage().cancel, true);
    }

    @Test(priority = 30)
    public void cancelButtonIsNotPresent() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .asserts().isButtonPresentInRow(testInfo.selectedApp, objectManager.getAppListPage().cancel, false);
    }
}
