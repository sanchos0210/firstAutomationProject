package com.abmcloud.cf.test.ProlineTests;

import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.architecture.BaseTest;
import com.abmcloud.cf.test.steps.EditAppSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.DataInfo.EditAppDataProline.*;
import static org.testng.Assert.assertEquals;

public class CopyAppTests extends BaseTest {

    EditAppSteps editAppSteps;
    EditAppDataProline editAppDataProline;
    String savedValue;

    @BeforeMethod
    public void objectCreation() {
        editAppSteps = new EditAppSteps();
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
        savedValue = editAppSteps.getDecimalValue(editAppDataProline.amountField);
        assertEquals(savedValue, "10000000");
        savedValue = editAppSteps.getStringValue(editAppDataProline.clientNameField);
        assertEquals(savedValue, clientName);
        savedValue = editAppSteps.getStringValue(editAppDataProline.contactPersonField);
        assertEquals(savedValue, contactPerson);
        savedValue = editAppSteps.getStringValue(editAppDataProline.contactPersonNumberField);
        assertEquals(savedValue, contactPersonNumber);
        savedValue = editAppSteps.getDecimalValue(editAppDataProline.sukuBungaField);
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
        savedValue = editAppSteps.getDecimalValue(editAppDataProline.amountField);
        assertEquals(savedValue, "7500000");
        savedValue = editAppSteps.getStringValue(editAppDataProline.clientNameField);
        assertEquals(savedValue, "Eddy");
        savedValue = editAppSteps.getStringValue(editAppDataProline.contactPersonField);
        assertEquals(savedValue, "Max");
        savedValue = editAppSteps.getStringValue(editAppDataProline.contactPersonNumberField);
        assertEquals(savedValue, "0998774565");
        savedValue = editAppSteps.getDecimalValue(editAppDataProline.sukuBungaField);
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
                .assertFalse(isElementPresent(appEditPage.changesHistory))
                .assertFalse(isElementPresent(appEditPage.viewsHistory))
                .assertFalse(isElementPresent(appEditPage.changesHistory))
                .assertFalse(isElementPresent(appEditPage.cancelAppButton));
    }
}
