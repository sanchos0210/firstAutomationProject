package proline.ProlineTests.NewAppPopup;

import com.abmcloud.cf.test.DBInfo.EditAppDataProline;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.DBInfo.EditAppDataProline.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class AppCreationTests extends BaseTest {

    EditAppDataProline editAppDataProline;
    String savedValue;
    AppFormSteps appFormSteps;

    @BeforeMethod
    public void objectCreation() {
        editAppDataProline = new EditAppDataProline();
        appFormSteps = new AppFormSteps();
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
        savedValue = appFormSteps.getDecimalValue(editAppDataProline.amountField);
        assertEquals(savedValue, EditAppDataProline.amount);
        savedValue = appFormSteps.getStringValue(editAppDataProline.clientNameField);
        assertEquals(savedValue, EditAppDataProline.clientName);
        savedValue = appFormSteps.getStringValue(editAppDataProline.contactPersonField);
        assertEquals(savedValue, EditAppDataProline.contactPerson);
        savedValue = appFormSteps.getStringValue(editAppDataProline.contactPersonNumberField);
        assertEquals(savedValue, EditAppDataProline.contactPersonNumber);
        savedValue = appFormSteps.getDecimalValue(editAppDataProline.sukuBungaField);
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
                .openAppList(editAppDataProline.pencairanDepositMenuButton)
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
                .openAppList(editAppDataProline.loanBusinessMenuButton)
                .createAppButtonClick();
        editAppDataProline.typeRequiredFieldsForLoanBusiness();
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appListPage.applSavedNotification, "was successfully saved"));
    }
}