package com.abmcloud.cf.test.ProlineTests.ApplicationList;


import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.architecture.BaseTest;
import org.testng.annotations.Test;

public class CancelApp extends BaseTest {

    @Test(priority = 1)
    public void cancelAppFromActionMenu() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .cancelButtonClick("Охрана отмена", selectedApp)
                .assertTrue(compare(textOfNotification, "Document # " + numberOfSelectedApp + " was canceled."));
    }

    @Test(priority = 2)
    public void cancelAppFromStatus() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "Охрана отмена")
                .assertTrue(compare(textOfNotification, "Document # " + numberOfSelectedApp + " was canceled."));
    }

    @Test(priority = 3)
    public void cancelAppFromEditPopup() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .cancelButtonClick()
                .cancelInCancelPopup("Охрана отмена")
                .assertTrue(compare(textOfNotification,
                "Document # " + numberOfSelectedApp + " was canceled."));
    }

    @Test(priority = 4)
    public void cancelAppFromViewPopup() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .sendForApprovalButtonClick(selectedApp)
                .selectAppByNumber(BaseTest.numberOfSelectedApp)
                .clickOnNumberOf(selectedApp)
                .cancelButtonClick()
                .cancelInCancelPopup("Cancel")
        .assertTrue(compare(textOfNotification,
                "Document # " + numberOfSelectedApp + " was canceled."));
    }

    @Test(priority = 5)
    public void cancelAppAndCheckItInCancelledList() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new EditAppDataProline())
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "Охрана отмена")
                .openCanceled()
                .selectAppByNumber(numberOfCreatedApp);
        checkThat(selectedApp, appListPage.statusOfApp, "Canceled");
    }

    //ТЕСТ БУДЕТ РАБОТАТЬ, КОГДА БУДЕТ РАБОТАТЬ ГЛОБАЛЬНЫЙ ФИЛЬТР
    /*@Test(priority = 6)
    public void checkReasonOfCancellation() {
        open(TEST_PROLINE);
        loginSteps.loginWithSuccessful(EMAIL, PASSWORD);
        appListPage.createNewApp();
        numberOfCreatedApp = appListPage.saveNumberOfCreatedAppTo();
        appListPage.selectAppByNumber(numberOfCreatedApp);
        appListPage.actionMenu(SEND_FOR_APPROVAL, selectedApp);
        appListPage.clickOnNumberOf(selectedApp);
        appEditPage.cancelAppButton.click();
        appListPage.commentFieldInCancelPopUp.sendKeys("Охрана отмена");
        appListPage.cancelButtonInCancelPopUp.click();
        appListPage.openCanceled();
        appListPage.selectAppByNumber(numberOfCreatedApp);
        appListPage.clickOn(appListPage.statusOfApp, selectedApp);
        НАДО ДОПИСАТЬ (клик по кнопке "показать комментарии" и ассерт комментария)
    }*/
}