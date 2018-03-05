package com.abmcloud.cf.test.ProlineTests.NewAppPopup;

import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.architecture.BaseTest;
import com.abmcloud.cf.test.steps.EditAppSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.DataInfo.EditAppDataProline.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class AppCreationTests extends BaseTest {

    EditAppDataProline editAppDataProline;
    String savedValue;
    EditAppSteps editAppSteps;

    @BeforeMethod
    public void objectCreation() {
        editAppDataProline = new EditAppDataProline();
        editAppSteps = new EditAppSteps();
    }

    /*@Test(priority = 1)
    public void createAppWithSuccessful() {
        open(TEST_PROLINE);
        steps
                .loginWithSuccessful(EMAIL, PASSWORD)
                .createNewApp()
                .saveNumberOfCreatedAppTo()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp);
        savedValue = editAppSteps.getDecimalValue(editAppDataProline.amountField);
        assertEquals(savedValue, EditAppDataProline.amount);
        savedValue = editAppSteps.getStringValue(editAppDataProline.clientNameField);
        assertEquals(savedValue, EditAppDataProline.clientName);
        savedValue = editAppSteps.getStringValue(editAppDataProline.contactPersonField);
        assertEquals(savedValue, EditAppDataProline.contactPerson);
        savedValue = editAppSteps.getStringValue(editAppDataProline.contactPersonNumberField);
        assertEquals(savedValue, EditAppDataProline.contactPersonNumber);
        savedValue = editAppSteps.getDecimalValue(editAppDataProline.sukuBungaField);
        assertEquals(savedValue, EditAppDataProline.sukuBunga);
    }*/

    @Test(priority = 2)
    public void createAppWithoutAmount() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN));
        appListPage.addNewButton.click();
        editAppDataProline.typeRequiredFieldsForDeposits("", clientName, contactPerson, contactPersonNumber, sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appEditPage.applSavedNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 3)
    public void createAppWithoutClientName() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN));
        appListPage.addNewButton.click();
        editAppDataProline.typeRequiredFieldsForDeposits(amount, "", contactPerson, contactPersonNumber, sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appEditPage.applSavedNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 4)
    public void createAppWithoutContactPerson() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN));
        appListPage.addNewButton.click();
        editAppDataProline.typeRequiredFieldsForDeposits(amount, clientName, "", contactPersonNumber, sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appEditPage.applSavedNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 5)
    public void createAppWithoutContactPersonNumber() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN));
        appListPage.addNewButton.click();
        editAppDataProline.typeRequiredFieldsForDeposits(amount, clientName, contactPerson, "", sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appEditPage.applSavedNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 6)
    public void createAppWithoutSukuBunga() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN));
        appListPage.addNewButton.click();
        editAppDataProline.typeRequiredFieldsForDeposits(amount, clientName, contactPerson, contactPersonNumber, "");
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appEditPage.applSavedNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 7)
    public void createPencarianDepositOutflowApp() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openApplList(editAppDataProline.pencairanDepositMenuButton)
                .createAppButtonClick();
        editAppDataProline.typeRequiredFieldsForDeposits(amount, clientName, contactPerson, contactPersonNumber, sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appListPage.applSavedNotification, "was successfully saved"));
    }

    @Test(priority = 8)
    public void createLoanBusinessOutflowApp() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openApplList(editAppDataProline.loanBusinessMenuButton)
                .createAppButtonClick();
        editAppDataProline.typeRequiredFieldsForLoanBusiness();
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appListPage.applSavedNotification, "was successfully saved"));
    }
}