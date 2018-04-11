package com.abmcloud.cf.test.AppList.AppListTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppListDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import org.testng.annotations.Test;

public class ActionMenuTests extends BaseTest {

    @Test(priority = 1)
    public void sendForApprovalButtonInActMenu() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 1"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .approveButtonClick(selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .asserts().checkThat(selectedApp, appListPage.statusOfApp, "Оплачена");
    }

    @Test(priority = 2)
    public void approveButtonInZeroStep() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .asserts().assertFalse(isButtonPresentInRow(selectedApp, appListPage.approve));
    }

    @Test(priority = 3)
    public void approveButtonInActMenuOnParallelStep() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE,selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approve)
                .getAppListStep().logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approve);
    }

    @Test(priority = 4)
    public void approveButtonInTwoApprovers() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approve)
                .getAppListStep().logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .asserts().assertVisibilityButtonInRow(selectedApp, appListPage.approve);
    }

    @Test(priority = 9)
    public void editButtonOpenEitPopup() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle,
                "Редактирование заявки № " + numberOfSelectedApp);
    }

    @Test(priority = 10)
    public void editButtonIsNotPresent() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .asserts()
                .assertTrue(isButtonPresentInRow(selectedApp, appListPage.edit));
    }

    @Test(priority = 20)
    public void copyPopupOpening() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .copyButtonClick(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle, "Копирование заявки");
        }

    @Test(priority = 29)
    public void cancelButtonIsPresent() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .asserts().assertTrue(isButtonPresentInRow(selectedApp, appListPage.cancel));
    }

    @Test(priority = 30)
    public void cancelButtonIsNotPresent() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .asserts().assertFalse(isButtonPresentInRow(selectedApp, appListPage.cancel));
    }
}
