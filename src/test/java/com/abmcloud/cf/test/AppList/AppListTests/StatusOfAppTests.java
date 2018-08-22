package com.abmcloud.cf.test.AppList.AppListTests;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("Проверка действий в статусе заявки")
@Feature("Список заявок")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class StatusOfAppTests extends BaseTest {

    DataBaseInfo dbInfo;

    public StatusOfAppTests() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test(priority = 1)
    public void sendForApprovalButton() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertTextIn(testInfo.selectedApp, objectManager.getAppListPage().statusOfApp, "Отправлена в банк");
    }

    @Test(priority = 10)
    public void approveButtonInStatus() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approveFromStatus);
    }

    @Test(priority = 20)
    public void approveButtonInStatusOnParallelStep() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE,testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approveFromStatus)
                .getAppListStep().logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approveFromStatus);
    }

    @Test(priority = 30)
    public void approveButtonInTwoApprovers() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approveFromStatus)
                .getAppListStep().logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(testInfo.selectedApp, objectManager.getAppListPage().approveFromStatus);
    }

    @Test(priority = 40)
    public void cancelButtonInStatusIsPresent() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts()
                .isButtonPresentInRow(testInfo.selectedApp, objectManager.getAppListPage().cancelFromStatus, true);
    }

    @Test(priority = 50)
    public void cancelButtonInStatusIsNotPresent() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts()
                .isButtonPresentInRow(testInfo.selectedApp, objectManager.getAppListPage().cancelFromStatus, false);
    }
}
