package com.abmcloud.cf.test.AppList.BuisnessProcessTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppListDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import org.testng.annotations.Test;

public class ChainsStepsTests extends BaseTest {

    @Test(priority = 1)
    public void approverIsAuthor() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare("Verezhevych Alexandr", getApprovers(2)))
                .getAppListStep().closeChainStepsPopup()
                .logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare("Test1 User1", getApprovers(2)));
    }

    @Test(priority = 10)
    public void twoApproversInStep() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare("Verezhevych Alexandr, Test1 User1", getApprovers(2)));
    }

    @Test(priority = 20)
    public void correctApproverVerification() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp, "Ok")
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare("Verezhevych Alexandr", getApprovers(2)));
    }

    @Test(priority = 30)
    public void checkStatusName() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertTextIn(selectedApp, appListPage.statusOfApp, "Новая")
                .getAppListStep().status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertTextIn(selectedApp, appListPage.statusOfApp, "В процессе")
                .getAppListStep().status(APPROVE, selectedApp, "approve")
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertTextIn(selectedApp, appListPage.statusOfApp, "Оплачена");
    }
}