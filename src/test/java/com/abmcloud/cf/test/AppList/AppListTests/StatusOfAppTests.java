package com.abmcloud.cf.test.AppList.AppListTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.AppListDBInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.Test;

public class StatusOfAppTests extends BaseTest {

    @Test(priority = 1)
    public void sendForApprovalButton() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .checkThat(selectedApp, appListPage.statusOfApp, "Отправлена в банк");
    }

    @Test(priority = 10)
    public void approveButtonInStatus() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
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
                .createApp(new AppListDBInfo(SUM, "10000"))
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
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
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
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts()
                .assertTrue(isButtonPresentInRow(selectedApp, appListPage.cancelFromStatus));
    }

    @Test(priority = 50)
    public void cancelButtonInStatusIsNotPresent() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts()
                .assertFalse(isButtonPresentInRow(selectedApp, appListPage.cancelFromStatus));
    }
}
