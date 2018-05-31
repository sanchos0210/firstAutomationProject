package proline.ProlineTests;

import com.abmcloud.cf.test.DBInfo.EditAppDataProline;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.DBInfo.EditAppDataProline.*;
import static org.testng.Assert.assertEquals;

public class CopyAppTests extends BaseTest {

    AppFormSteps appFormSteps;
    EditAppDataProline editAppDataProline;
    String savedValue;

    @BeforeMethod
    public void objectCreation() {
        appFormSteps = new AppFormSteps();
        editAppDataProline = new EditAppDataProline();
    }

    @Test(priority = 1)
    public void copyApp() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(editAppDataProline)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .copyButtonClick(selectedApp)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp);
        savedValue = appFormSteps.getDecimalValue(editAppDataProline.amountField);
        assertEquals(savedValue, "10000000");
        savedValue = appFormSteps.getStringValue(editAppDataProline.clientNameField);
        assertEquals(savedValue, clientName);
        savedValue = appFormSteps.getStringValue(editAppDataProline.contactPersonField);
        assertEquals(savedValue, contactPerson);
        savedValue = appFormSteps.getStringValue(editAppDataProline.contactPersonNumberField);
        assertEquals(savedValue, contactPersonNumber);
        savedValue = appFormSteps.getDecimalValue(editAppDataProline.sukuBungaField);
        assertEquals(savedValue, sukuBunga);
    }

    @Test(priority = 2)
    public void copyAppAndEditAllFields() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(editAppDataProline)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .copyButtonClick(selectedApp)
                .edit(editAppDataProline.amountField, "7500000")
                .edit(editAppDataProline.clientNameField, "Eddy")
                .edit(editAppDataProline.contactPersonField, "Max")
                .edit(editAppDataProline.contactPersonNumberField, "0998774565")
                .edit(editAppDataProline.sukuBungaField, "12,1")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp);
        savedValue = appFormSteps.getDecimalValue(editAppDataProline.amountField);
        assertEquals(savedValue, "7500000");
        savedValue = appFormSteps.getStringValue(editAppDataProline.clientNameField);
        assertEquals(savedValue, "Eddy");
        savedValue = appFormSteps.getStringValue(editAppDataProline.contactPersonField);
        assertEquals(savedValue, "Max");
        savedValue = appFormSteps.getStringValue(editAppDataProline.contactPersonNumberField);
        assertEquals(savedValue, "0998774565");
        savedValue = appFormSteps.getDecimalValue(editAppDataProline.sukuBungaField);
        assertEquals(savedValue, "12.1");
    }

    @Test(priority = 3)
    public void checkNonVisibilityOf() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(editAppDataProline)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .copyButtonClick(selectedApp)
                .backButtonClick()      //Check that button is working
                .actionMenuButtonClick(selectedApp)
                .copyButtonClick(selectedApp)
                .asserts()
                .assertFalse(isElementPresent(appEditPage.changesHistory))
                .assertFalse(isElementPresent(appEditPage.viewsHistory))
                .assertFalse(isElementPresent(appEditPage.changesHistory))
                .assertFalse(isElementPresent(appEditPage.cancelAppButton));
    }
}
