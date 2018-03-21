package com.abmcloud.cf.test.ProlineTests.ApplicationList;

import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.API.BaseTest;
import org.testng.annotations.Test;

public class ApprovalOfApp extends BaseTest {

    @Test(priority = 1)
    public void sendForApprovalFromStatus() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .asserts()
                .assertTrue(compare(textOfNotification, "Document # "+ numberOfSelectedApp +" was successfully sent for approval"));
    }

    @Test(priority = 2)
    public void sendForApprovalFromActionMenu() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .sendForApprovalButtonClick(selectedApp)
                .asserts()
                .assertTrue(compare(textOfNotification, "Document # "+ numberOfSelectedApp +" was successfully sent for approval"));
    }

    @Test(priority = 3)
    public void approveFromActionMenu() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .approveButtonClick("Ok", selectedApp)
                .asserts()
        .assertTrue(compare(textOfNotification, "Document # "+ numberOfSelectedApp +" was successfully approved."));
    }

    @Test(priority = 4)
    public void approveFromStatus() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .status(APPROVE, selectedApp, "Ok")
                .asserts()
        .assertTrue(compare(textOfNotification,
                "Document # "+ numberOfSelectedApp +" was successfully approved."));
    }

    @Test(priority = 4)
    public void approveAppFromViewPopup() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .sendForApprovalButtonClick(selectedApp)
                .selectAppByNumber(BaseTest.numberOfSelectedApp)
                .clickOnNumberOf(selectedApp)
                .approveButtonClick()
                .approveInApprovePopup("Ok")
                .asserts()
        .assertTrue(compare(textOfNotification,
                "Document # " + numberOfSelectedApp + " was successfully approved."));

    }
        @Test(priority = 5)
    public void approveAllSteps() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .sendForApprovalButtonClick(selectedApp)
                .selectAppByNumber(BaseTest.numberOfSelectedApp)
                .actionMenuButtonClick(selectedApp)
                .approveButtonClick("Ok", selectedApp)
                .logOut()
                .loginAs(new UsersData(USER2, EMAIL2, PASSWORD2, EN))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .approveButtonClick("Ok", selectedApp)
                .logOut()
                .loginAs(new UsersData(USER3, EMAIL3, PASSWORD3, EN))
                .openOnMyApproval()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .approveButtonClick("Ok", selectedApp);
        checkThat(selectedApp, appListPage.statusOfApp, APPROVED);
    }
}