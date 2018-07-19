package com.abmcloud.cf.test.AppList.AppListTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.Test;

public class StatusOfAppTests extends BaseTest {

    DataBaseInfo dbInfo;

    public StatusOfAppTests() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test(priority = 1)
    public void sendForApprovalButton() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, "123", RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertTextIn(selectedApp, appListPage.statusOfApp, "Отправлена в банк");
    }

    @Test(priority = 10)
    public void approveButtonInStatus() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approveFromStatus);
    }

    @Test(priority = 20)
    public void approveButtonInStatusOnParallelStep() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE,selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approveFromStatus)
                .getAppListStep().logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approveFromStatus);
    }

    @Test(priority = 30)
    public void approveButtonInTwoApprovers() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approveFromStatus)
                .getAppListStep().logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approveFromStatus);
    }

    @Test(priority = 40)
    public void cancelButtonInStatusIsPresent() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts()
                .assertTrue(helpers.isButtonPresentInRow(selectedApp, appListPage.cancelFromStatus));
    }

    @Test(priority = 50)
    public void cancelButtonInStatusIsNotPresent() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts()
                .assertFalse(helpers.isButtonPresentInRow(selectedApp, appListPage.cancelFromStatus));
    }
}
