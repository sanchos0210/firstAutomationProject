package com.abmcloud.cf.test.AppList.HeaderOfApplList;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import org.testng.annotations.Test;

public class GlobalFilterTests extends BaseTest {

    DataBaseInfo dbInfo;

    public GlobalFilterTests() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test(priority = 1)
    public void availableToMe() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openAvailableToMe()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Редактирование заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 10)
    public void approvedByMe() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp, "Ok")
                .openApprovedByMe()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 20)
    public void approved() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp, "Ok")
                .logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openApproved()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 30)
    public void cancelledByMeInCancelledFilter() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp)
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "NO")
                .openCanceled()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 40)
    public void cancelled() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp)
                .logOut()
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "NO")
                .logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openCanceled()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }
}