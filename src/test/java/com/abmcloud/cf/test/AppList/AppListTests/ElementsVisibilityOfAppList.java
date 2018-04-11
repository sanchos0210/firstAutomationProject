package com.abmcloud.cf.test.AppList.AppListTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppListDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import org.testng.annotations.Test;

public class ElementsVisibilityOfAppList extends BaseTest {

    @Test(priority = 1)
    public void filesButton() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .openFilesOf(selectedApp)
                .asserts().assertTextInElement(appListPage.FilesPopup, "Файлы к заявке № "+numberOfCreatedApp);
    }

    @Test(priority = 2)
    public void stepsPopup() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTextInElement(appListPage.stepsPopup, "Цепочка согласования по заявке # "+numberOfCreatedApp);
    }

    @Test(priority = 3)
    public void editPopup() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle, "Редактирование заявки № "+numberOfCreatedApp);
    }

    @Test(priority = 4)
    public void viewPopup() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTextInElement(appEditPage.editPopupTitle, "Просмотр заявки № "+numberOfCreatedApp);
    }


}