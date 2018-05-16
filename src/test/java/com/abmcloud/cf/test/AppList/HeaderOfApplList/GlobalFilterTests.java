package com.abmcloud.cf.test.AppList.HeaderOfApplList;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppListDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import org.testng.annotations.Test;

public class GlobalFilterTests extends BaseTest {

    @Test(priority = 1)
    public void availibleToMe() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(new AppListDBInfo())
                .logOut()
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .openAvailableToMe()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle, "Редактирование заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 10)
    public void approvedByMe() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .logOut()
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp, "Ok")
                .openApprovedByMe()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 20)
    public void approved() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .logOut()
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp, "Ok")
                .logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .openApproved()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 30)
    public void cancelledByMeInCancelledFilter() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp)
                .logOut()
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "NO")
                .openCanceled()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 40)
    public void cancelled() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp)
                .logOut()
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "NO")
                .logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .openCanceled()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }
}