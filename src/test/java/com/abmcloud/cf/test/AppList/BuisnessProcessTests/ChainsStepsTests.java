package com.abmcloud.cf.test.AppList.BuisnessProcessTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.Test;

public class ChainsStepsTests extends BaseTest {

    @Test(priority = 1)
    public void approverIsAuthor() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare("Verezhevych Alexandr", helpers.getApprovers(2)))
                .getAppListStep().closeChainStepsPopup()
                .logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare("Test1 User1", helpers.getApprovers(2)));
    }

    @Test(priority = 10)
    public void twoApproversInStep() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare("Verezhevych Alexandr, Test1 User1", helpers.getApprovers(2)));
    }

    @Test(priority = 20)
    public void correctApproverVerification() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp, "Ok")
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare("Verezhevych Alexandr", helpers.getApprovers(2)));
    }

    @Test(priority = 30)
    public void checkStatusName() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertTextIn(selectedApp, appListPage.statusOfApp, "Новая")
                .getAppListStep().status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertTextIn(selectedApp, appListPage.statusOfApp, "В процессе")
                .getAppListStep().status(APPROVE, selectedApp, "approve")
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().assertTextIn(selectedApp, appListPage.statusOfApp, "Оплачена");
    }

    @Test(priority = 40)
    public void paymentFromApplicationListTest() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_6th_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .openCalendar("Утвердить оплаты")
                .openRegistry()
                .checkAppWithNumber(numberOfCreatedApp)
                .approveButtonClick()
                .closeRegistry()
                .openAppList(dbInfo.getString("prepare_payments"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTextIn(selectedApp, appListPage.statusOfApp, "Оплачена");
    }

    @Test(priority = 50)
    public void paymentFromCalendarThrowApprove() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_5th_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .openCalendar("Утвердить оплаты")
                .openRegistry()
                .checkAppWithNumber(numberOfCreatedApp)
                .approveButtonClick()
                .closeRegistry()
                .assertPaid(helpers.getTodayFullDate(), "(10 000)");
    }

    @Test(priority = 60)
    public void requiredCommentForApproval() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .approveButtonClick(selectedApp)
                .asserts().assertTrue(helpers.isButtonDisable(appListPage.approveButtonInApprovePopUp));
    }

    @Test(priority = 60)
    public void nonRequiredCommentForApproval() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp, "");
    }
}