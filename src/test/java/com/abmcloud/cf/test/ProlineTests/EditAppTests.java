package com.abmcloud.cf.test.ProlineTests;

import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.architecture.BaseTest;
import com.abmcloud.cf.test.steps.EditAppSteps;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.DataInfo.EditAppDataProline.*;
import static org.testng.Assert.assertEquals;

public class EditAppTests extends BaseTest {

    String savedValue;
    EditAppSteps editAppSteps;
    EditAppDataProline editAppDataProline;

    @BeforeMethod
    public void objectCreation() {
        editAppSteps = new EditAppSteps();
        editAppDataProline = new EditAppDataProline();
    }

    @Test(priority = 1)
    public void editAllFieldsOfApp() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(editAppDataProline)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .edit(editAppDataProline.amountField, "20000")
                .edit(editAppDataProline.clientNameField, "Eddy")
                .edit(editAppDataProline.contactPersonField, "Max")
                .edit(editAppDataProline.contactPersonNumberField, "0998774565")
                .edit(editAppDataProline.sukuBungaField, "12,1")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp);
        assertEquals(editAppSteps.getDecimalValue(editAppDataProline.amountField), "20000");
        assertEquals(editAppSteps.getStringValue(editAppDataProline.clientNameField), "Eddy");
        assertEquals(editAppSteps.getStringValue(editAppDataProline.contactPersonField), "Max");
        assertEquals(editAppSteps.getStringValue(editAppDataProline.contactPersonNumberField), "0998774565");
        assertEquals(editAppSteps.getDecimalValue(editAppDataProline.sukuBungaField), "12.1");
    }

    @Test(priority = 2)
    public void checkChangesHistory() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(editAppDataProline)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .edit(editAppDataProline.amountField, "20.000")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 1"))
                .changesHistoryClick()
                .checkHistoryOf("Amount", amount, "20.000")
                                        //Need to make same verify for currency
                .catalogElementClick(appEditPage.paymentDateField)
                .catalogElementClick(appEditPage.futureDate)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 2"))
                .changesHistoryClick()
                .checkHistoryOf("Payment date", getTodaysDate(), getTommorowDate())
                .booleanButtonClick(editAppDataProline.applicationChannelButton)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 3"))
                .changesHistoryClick()
                .checkHistoryOf("Application Channel", "Offline", "Online")
                .edit(editAppDataProline.clientNameField, "Batman")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 4"))
                .changesHistoryClick()
                .checkHistoryOf("Client name", clientName, "Batman")
                .edit(editAppDataProline.contactPersonField, "Superman")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 5"))
                .changesHistoryClick()
                .checkHistoryOf("Contact Person", contactPerson, "Superman")
                .edit(editAppDataProline.contactPersonNumberField, "0998774565")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 6"))
                .changesHistoryClick()
                .checkHistoryOf("Contact Person Number", contactPersonNumber, "0998774565")
                .edit(editAppDataProline.sukuBungaField, "12,1")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 7"))
                .changesHistoryClick()
                .checkHistoryOf("Suku Bunga", "44.00", "12.1");
    }

    @Test(priority = 3)
    public void checkViewHistory() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(editAppDataProline)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .backButtonClick()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.viewsHistory, "Views history 2"));
    }

    @Test(priority = 4)
    public void checkApprovalStepsInEditPopup() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(editAppDataProline)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.approvalSteps, "Approval steps"));
    }
}
