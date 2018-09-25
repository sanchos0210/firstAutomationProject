package com.abmcloud.cf.test.AppList.BuisnessProcessTests;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.APPROVE;
import static com.abmcloud.cf.test.Driver.Constants.RU;
import static com.abmcloud.cf.test.Driver.Constants.SEND_FOR_APPROVAL;

@Epic("Проверка определения шагов и цепочек для заявке")
@Feature("Список заявок")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ChainsStepsTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void initializeJsonFile() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test(priority = 1)
    public void approverIsAuthor() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Verezhevych Alexandr", objectManager.getStepsPopup().getApprovers(2))
                .getAppListStep().closeChainStepsPopup()
                .logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Test1 User1", objectManager.getStepsPopup().getApprovers(2));
    }

    @Test(priority = 2)
    public void approverIsOrganizationLead() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_7th_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Verezhevych Alexandr", objectManager.getStepsPopup().getApprovers(2));
    }

    @Test(priority = 10)
    public void twoApproversInStep() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Verezhevych Alexandr, Test1 User1", objectManager.getStepsPopup().getApprovers(2));
    }

    @Test(priority = 11)
    public void checkDeputy() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER3, EMAIL3, PASSWORD3, RU)
                .openUserProfile()
                .redirectApplicationsClick()
                .choseUser("User2 Deputy")
                .redirectApplications()
                .closeUserProfile()
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_8th_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Deputy User2", objectManager.getStepsPopup().getApprovers(2))
                //Need to return application from deputy
                .getAppListStep()
                .logOut()
                .loginAs(USER3, EMAIL3, PASSWORD3, RU)
                .openUserProfile()
                .returnMyApplications()
                .closeUserProfile();
    }

    @Test(priority = 12)
    public void choseDeputyAndCheckCreatedApp() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER3, EMAIL3, PASSWORD3, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_8th_chain"))
                .openUserProfile()
                .redirectApplicationsClick()
                .choseUser("User2 Deputy")
                .redirectApplications()
                .closeUserProfile()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Deputy User2", objectManager.getStepsPopup().getApprovers(2))
                .getAppListStep()
                .openUserProfile()
                .returnMyApplications()
                .closeUserProfile()
                .refreshPage()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Test3 User3", objectManager.getStepsPopup().getApprovers(2));
    }

    @Test(priority = 20)
    public void correctApproverVerification() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE, testInfo.selectedApp, "Ok")
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Verezhevych Alexandr", objectManager.getStepsPopup().getApprovers(2));
    }

    @Test(priority = 30)
    public void checkStatusName() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertTextIn(testInfo.selectedApp, objectManager.getAppListPage().statusOfApp, "Новая")
                .getAppListStep().status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertTextIn(testInfo.selectedApp, objectManager.getAppListPage().statusOfApp, "В процессе")
                .getAppListStep().status(APPROVE, testInfo.selectedApp, "approve")
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .asserts().assertTextIn(testInfo.selectedApp, objectManager.getAppListPage().statusOfApp, "Оплачена");
    }

    @Test(priority = 40)
    public void paymentFromApplicationListTest() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_6th_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .openCalendar("Утвердить оплаты")
                .openRegistry()
                .checkAppWithNumber(testInfo.numberOfCreatedApp)
                .approveButtonClick()
                .closeRegistry()
                .openAppList(dbInfo.getString("prepare_payments"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().assertTextIn(testInfo.selectedApp, objectManager.getAppListPage().statusOfApp, "Оплачена");
    }

    @Test(priority = 50)
    public void paymentFromCalendarThrowApprove() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_5th_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .openCalendar("Утвердить оплаты")
                .openRegistry()
                .checkAppWithNumber(testInfo.numberOfCreatedApp)
                .approveButtonClick()
                .closeRegistry()
                .assertPaid(objectManager.getDateUtil().getTodayFullDate(), 10000);
    }

    @Test(priority = 60)
    public void requiredCommentForApproval() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .actionMenuButtonClick(testInfo.selectedApp)
                .approveButtonClick(testInfo.selectedApp)
                .asserts().isButtonDisable(objectManager.getConfirmation().approveButtonInApprovePopUp, true);
    }

    @Test(priority = 70)
    public void nonRequiredCommentForApproval() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE, testInfo.selectedApp, "");
    }
}