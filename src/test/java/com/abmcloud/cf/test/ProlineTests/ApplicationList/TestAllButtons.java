package com.abmcloud.cf.test.ProlineTests.ApplicationList;

import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.architecture.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestAllButtons extends BaseTest {

    @Test(priority = 1)
    public void addFilesButton() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .openFilesOf(selectedApp);
        verificationThat(ExpectedConditions.textToBePresentInElement(appListPage.FilesPopup, "Files for document # " + numberOfSelectedApp));
    }

    @Test(priority = 2)
    public void openingPopupEditByClickOnNumberOfApp() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp);
        verificationThat(ExpectedConditions.textToBePresentInElement(appEditPage.editPopupTitle, "Edit document # " + numberOfSelectedApp));
    }

    @Test(priority = 3)
    public void openingPopupEditByClickOnEditButton() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp);
        verificationThat(ExpectedConditions.textToBePresentInElement(appEditPage.editPopupTitle, "Edit document # " + numberOfSelectedApp));
    }

    @Test(priority = 4)
    public void copiesPopupOpening() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .copyButtonClick(selectedApp);
        verificationThat(ExpectedConditions.textToBePresentInElement(appEditPage.editPopupTitle, "Copy document"));
    }

    @Test(priority = 5)
    public void aprovalStepsOpeningByClickOnStatus() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp);
        verificationThat(ExpectedConditions.textToBePresentInElement(appListPage.stepsPopup, "Approval steps, document # " + numberOfSelectedApp));
    }

    @Test(priority = 6)
    public void openingDatePickerByClickOnDate() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN));
        appListPage.selectedDate.click();
        verificationThat(ExpectedConditions.textToBePresentInElement(appListPage.datePickerThisMonth, "This month"));
    }
}
