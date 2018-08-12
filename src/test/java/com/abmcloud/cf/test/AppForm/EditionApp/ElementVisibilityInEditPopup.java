package com.abmcloud.cf.test.AppForm.EditionApp;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
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
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts()
                .assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().approvalSteps))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().changesHistory))
                .assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().viewsHistory))
                .assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().cancelAppButton))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().approveAppButton))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().saveButton));
    }

    @Test(priority = 2)
    public void historyChangesIsVisible() {
        dbInfo = new DataBaseInfo("app_form_db.json");
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .editDecimalField(dbInfo.getString("decimal_field_3"), "1900")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts()
                .assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().changesHistory));
    }

    @Test(priority = 3)
    public void cannotCancelAndCanApprove2UsersFromEditPopup() {
        dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().cancelAppButton))
                .assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().approveAppButton))
                .getAppFormStep().backButtonClick()
                .logOut()
                .loginAs(USER1, EMAIL1, PASSWORD1, RU)
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().approveAppButton));
    }

}
