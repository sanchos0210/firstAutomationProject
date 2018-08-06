package com.abmcloud.cf.test.AppForm.EditionApp;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementVisibilityInEditPopup extends BaseTest{

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
    }

    @Test(priority = 1)
    public void visibilityOfElementsInEditPopup() {
        dbInfo = new DataBaseInfo("app_form_db.json");
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts()
                .assertTrue(helpers.isElementPresent(appEditPage.approvalSteps))
                .assertFalse(helpers.isElementPresent(appEditPage.changesHistory))
                .assertTrue(helpers.isElementPresent(appEditPage.viewsHistory))
                .assertTrue(helpers.isElementPresent(appEditPage.cancelAppButton))
                .assertFalse(helpers.isElementPresent(appEditPage.approveAppButton))
                .assertFalse(helpers.isElementPresent(appEditPage.saveButton));
    }

    @Test(priority = 2)
    public void historyChangesIsVisible() {
        dbInfo = new DataBaseInfo("app_form_db.json");
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .editDecimalField(dbInfo.getString("decimal_field_3"), "1900")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts()
                .assertTrue(helpers.isElementPresent(appEditPage.changesHistory));
    }

    @Test(priority = 3)
    public void cannotCancelAndCanApprove2UsersFromEditPopup() {
        dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertFalse(helpers.isElementPresent(appEditPage.cancelAppButton))
                .assertTrue(helpers.isElementPresent(appEditPage.approveAppButton))
                .getAppFormStep().backButtonClick()
                .logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.isElementPresent(appEditPage.approveAppButton));
    }

}
