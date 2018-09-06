package com.abmcloud.cf.test.AppList.AppListTests;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.RU;

@Epic("Отображение элементов в списке заявок")
@Feature("Список заявок")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ElementsVisibilityOfAppList extends BaseTest {

    DataBaseInfo dbInfo;

    public ElementsVisibilityOfAppList() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test(priority = 1)
    public void filesButton() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .openFilesOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppListPage().FilesPopup, "Файлы к заявке № "+ testInfo.numberOfCreatedApp);
    }

    @Test(priority = 2)
    public void stepsPopup() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppListPage().stepsPopup, "Цепочка согласования по заявке #" + testInfo.numberOfCreatedApp);
    }

    @Test(priority = 3)
    public void editPopup() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Редактирование заявки № " + testInfo.numberOfCreatedApp);
    }

    @Test(priority = 4)
    public void viewPopup() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts().assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Просмотр заявки № " + testInfo.numberOfCreatedApp);
    }


}