package com.abmcloud.cf.test.ProlineTests;

import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.DataInfo.EditAppDataProline.*;
import static org.testng.Assert.assertEquals;

public class EditAppTests extends BaseTest {

    String savedValue;
    AppFormSteps appFormSteps;
    EditAppDataProline editAppDataProline;

    @BeforeMethod
    public void objectCreation() {
        appFormSteps = new AppFormSteps();
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
        assertEquals(appFormSteps.getDecimalValue(editAppDataProline.amountField), "20000");
        assertEquals(appFormSteps.getStringValue(editAppDataProline.clientNameField), "Eddy");
        assertEquals(appFormSteps.getStringValue(editAppDataProline.contactPersonField), "Max");
        assertEquals(appFormSteps.getStringValue(editAppDataProline.contactPersonNumberField), "0998774565");
        assertEquals(appFormSteps.getDecimalValue(editAppDataProline.sukuBungaField), "12.1");
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
                .asserts()
                .checkHistoryOf("Amount", amount, "20.000")
                                        //Need to make same verify for currency
                .getAppFormStep()
                .catalogElementClick(appEditPage.paymentDateField)
                .catalogElementClick(appEditPage.tomorrowDate)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 2"))
                .changesHistoryClick()
                .asserts().checkHistoryOf("Payment date", getTodaysDate(), getTomorrowDate())
                .getAppFormStep()
                .booleanButtonClick(editAppDataProline.applicationChannelButton)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 3"))
                .changesHistoryClick()
                .asserts().checkHistoryOf("Application Channel", "Offline", "Online")
                .getAppFormStep()
                .edit(editAppDataProline.clientNameField, "Batman")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 4"))
                .changesHistoryClick()
                .asserts().checkHistoryOf("Client name", clientName, "Batman")
                .getAppFormStep()
                .edit(editAppDataProline.contactPersonField, "Superman")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 5"))
                .changesHistoryClick()
                .asserts().checkHistoryOf("Contact Person", contactPerson, "Superman")
                .getAppFormStep()
                .edit(editAppDataProline.contactPersonNumberField, "0998774565")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 6"))
                .changesHistoryClick()
                .asserts().checkHistoryOf("Contact Person Number", contactPersonNumber, "0998774565")
                .getAppFormStep()
                .edit(editAppDataProline.sukuBungaField, "12,1")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .showInformationBlockClick()
                .assertThat(ExpectedConditions.textToBePresentInElement(appEditPage.changesHistory, "Changes history 7"))
                .changesHistoryClick()
                .asserts().checkHistoryOf("Suku Bunga", "44.00", "12.1");
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
