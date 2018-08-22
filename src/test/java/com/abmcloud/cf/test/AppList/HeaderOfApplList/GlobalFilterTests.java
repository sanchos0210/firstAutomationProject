package com.abmcloud.cf.test.AppList.HeaderOfApplList;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("Проверка глобального фильтра в списке заявок")
@Feature("Список заявок")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class GlobalFilterTests extends BaseTest {

    DataBaseInfo dbInfo;

    public GlobalFilterTests() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test(priority = 1)
    public void availableToMe() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openAvailableToMe()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Редактирование заявки № " + testInfo.numberOfCreatedApp);
    }

    @Test(priority = 10)
    public void approvedByMe() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE, testInfo.selectedApp, "Ok")
                .openApprovedByMe()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № " + testInfo.numberOfCreatedApp);
    }

    @Test(priority = 20)
    public void approved() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE, testInfo.selectedApp, "Ok")
                .logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openApproved()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № " + testInfo.numberOfCreatedApp);
    }

    @Test(priority = 30)
    public void cancelledByMeInCancelledFilter() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE, testInfo.selectedApp)
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(CANCEL, testInfo.selectedApp, "NO")
                .openCanceled()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № " + testInfo.numberOfCreatedApp);
    }

    @Test(priority = 40)
    public void cancelled() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(APPROVE, testInfo.selectedApp)
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openOnMyApproval()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(CANCEL, testInfo.selectedApp, "NO")
                .logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openCanceled()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № " + testInfo.numberOfCreatedApp);
    }
}