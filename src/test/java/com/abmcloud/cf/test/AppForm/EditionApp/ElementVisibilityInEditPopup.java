package com.abmcloud.cf.test.AppForm.EditionApp;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.AppListDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.Fields.DecimalField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementVisibilityInEditPopup extends BaseTest{

    AppFormDBInfo appFormDBInfo;
    DecimalField decimalField;

    @BeforeMethod
    public void objectCreation() {
        appFormDBInfo = new AppFormDBInfo();
        decimalField = new DecimalField();
    }

    @Test(priority = 1)
    public void visibilityOfElementsInEditPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(appFormDBInfo)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts()
                .assertTrue(isElementPresent(appEditPage.approvalSteps))
                .assertFalse(isElementPresent(appEditPage.changesHistory))
                .assertTrue(isElementPresent(appEditPage.viewsHistory))
                .assertTrue(isElementPresent(appEditPage.cancelAppButton))
                .assertFalse(isElementPresent(appEditPage.approveAppButton))
                .assertTrue(isElementPresent(appEditPage.saveButton));
    }

    @Test(priority = 2)
    public void historyChangesIsVisible() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(appFormDBInfo)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .edit(decimalField.getField(appFormDBInfo.decimalField3), "1900")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts()
                .assertTrue(isElementPresent(appEditPage.changesHistory));
    }

    @Test(priority = 3)
    public void cannotCancelAndCanApprove2UsersFromEditPopup() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertFalse(isElementPresent(appEditPage.cancelAppButton))
                .assertTrue(isElementPresent(appEditPage.approveAppButton))
                .getAppFormStep().backButtonClick()
                .logOut()
                .loginAs(new UsersData(USER1, EMAIL1, PASSWORD1, RU))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(isElementPresent(appEditPage.approveAppButton));
    }

}
