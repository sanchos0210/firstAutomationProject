package com.abmcloud.cf.test.ProlineTests.NewAppPopup;

import com.abmcloud.cf.test.DataInfo.EditAppDataLotok;
import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.architecture.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.DataInfo.EditAppDataProline.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class fieldsValidationTests extends BaseTest {

    EditAppDataProline editAppDataProline;
    EditAppDataLotok editAppDataLotok;

    @BeforeMethod
    public void objectCreation() {
        editAppDataProline = new EditAppDataProline();
        editAppDataLotok = new EditAppDataLotok();

    }

    @Test(priority = 1)
    public void createAppWithNegativeAmount() {
        steps
                .open(TEST_PROLINE)
                .loginWithSuccessful(EMAIL, PASSWORD);
        appListPage.addNewButton.click();
        editAppDataProline.amountField.sendKeys("-1000000");
        editAppDataProline.clientNameField.sendKeys(clientName);
        editAppDataProline.contactPersonField.sendKeys(contactPerson);
        editAppDataProline.contactPersonNumberField.sendKeys(contactPersonNumber);
        editAppDataProline.sukuBungaField.sendKeys(sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appEditPage.applSavedNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 2)
    public void createAppWithNullAmount() {
        steps
                .open(TEST_PROLINE)
                .loginWithSuccessful(EMAIL, PASSWORD);
        appListPage.addNewButton.click();
        editAppDataProline.amountField.sendKeys("0");
        editAppDataProline.clientNameField.sendKeys(clientName);
        editAppDataProline.contactPersonField.sendKeys(contactPerson);
        editAppDataProline.contactPersonNumberField.sendKeys(contactPersonNumber);
        editAppDataProline.sukuBungaField.sendKeys(sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appEditPage.applSavedNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    @Test(priority = 3)
    public void typeNumberWithLettersToAmountField() {
        steps
                .open(TEST_PROLINE)
                .loginWithSuccessful(EMAIL, PASSWORD);
        appListPage.addNewButton.click();
        editAppDataProline.amountField.sendKeys("1q2w3e");
        editAppDataProline.clientNameField.sendKeys(clientName);
        editAppDataProline.contactPersonField.sendKeys(contactPerson);
        editAppDataProline.contactPersonNumberField.sendKeys(contactPersonNumber);
        editAppDataProline.sukuBungaField.sendKeys(sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appEditPage.applSavedNotification, "Oops! Looks like you have not filled out all of the required fields!"));
    }

    //тест не работает в пн, сб и вс
    @Test(priority = 4)
    public void createAppWithPrecedentDate() {
        steps
                .open(TEST_PROLINE)
                .loginWithSuccessful(EMAIL, PASSWORD);
        appListPage.addNewButton.click();
        editAppDataProline.amountField.sendKeys(amount);
        appEditPage.paymentDateField.click();
        appEditPage.precedentDate.click();
        editAppDataProline.clientNameField.sendKeys(clientName);
        editAppDataProline.contactPersonField.sendKeys(contactPerson);
        editAppDataProline.contactPersonNumberField.sendKeys(contactPersonNumber);
        editAppDataProline.sukuBungaField.sendKeys(sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appListPage.applSavedNotification, "was successfully saved"));
    }

    //тест не работает в сб и вс
    @Test(priority = 5)
    public void createAppWithFutureDate() {
        steps
                .open(TEST_PROLINE)
                .loginWithSuccessful(EMAIL, PASSWORD);
        appListPage.addNewButton.click();
        editAppDataProline.amountField.sendKeys(amount);
        appEditPage.paymentDateField.click();
        appEditPage.futureDate.click();
        editAppDataProline.clientNameField.sendKeys(clientName);
        editAppDataProline.contactPersonField.sendKeys(contactPerson);
        editAppDataProline.contactPersonNumberField.sendKeys(contactPersonNumber);
        editAppDataProline.sukuBungaField.sendKeys(sukuBunga);
        appEditPage.saveButton.click();
        verificationThat(textToBePresentInElement(appListPage.applSavedNotification, "was successfully saved"));
    }

    @Test(priority = 6)
    public void createAppAsOutflow() {
        steps
                .open(TEST_LOTOK)
                .loginAs(new UsersData("тест Заявитель", "buysome@ukr.net", "123456", RU))
                .createAppButtonClick()
                .catalogElementClick(editAppDataLotok.article)
                .catalogElementClick(editAppDataLotok.incomeFolder)
                .catalogElementClick(editAppDataLotok.banksPerCent)
                .type("5000", editAppDataLotok.summField)
                .catalogElementClick(editAppDataLotok.contractor)
                .catalogElementClick(editAppDataLotok.supliersFolder)
                .catalogElementClick(editAppDataLotok.firstResult)
                .catalogElementClick(editAppDataLotok.calcAccount)
                .catalogElementClick(editAppDataLotok.firstResult)
                .catalogElementClick(editAppDataLotok.agreement)
                .catalogElementClick(editAppDataLotok.firstResult)
                .type("56987", editAppDataLotok.accountsSummField)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .assertValueOfBooleanField(editAppDataLotok.incomeBooleanButton, "Виплати");
    }

    @Test(priority = 7)
    public void createAppAsIncome() {
        steps
                .open(TEST_LOTOK)
                .loginAs(new UsersData("тест Заявитель", "buysome@ukr.net", "123456", RU))
                .createAppButtonClick()
                .booleanButtonClick(editAppDataLotok.incomeBooleanButton)
                .catalogElementClick(editAppDataLotok.article)
                .catalogElementClick(editAppDataLotok.incomeFolder)
                .catalogElementClick(editAppDataLotok.banksPerCent)
                .type("5000", editAppDataLotok.summField)
                .catalogElementClick(editAppDataLotok.contractor)
                .catalogElementClick(editAppDataLotok.supliersFolder)
                .catalogElementClick(editAppDataLotok.firstResult)
                .catalogElementClick(editAppDataLotok.calcAccount)
                .catalogElementClick(editAppDataLotok.firstResult)
                .catalogElementClick(editAppDataLotok.agreement)
                .catalogElementClick(editAppDataLotok.firstResult)
                .type("56987", editAppDataLotok.accountsSummField)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .assertValueOfBooleanField(editAppDataLotok.incomeBooleanButton, "Надходження");
    }

    @Test(priority = 8)
    public void disabledBooleanFieldWithDefaultValue() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .assertThatFieldIsDisabled(editAppDataProline.disabledHeadersBooleanButton)
                .assertThat(ExpectedConditions.attributeContains(editAppDataProline.headersBooleanButton, CURRENT_VALUE, "true"))
                .backButtonClick()
                .openApplList(editAppDataProline.pencairanDepositMenuButton)
                .createAppButtonClick()
                .assertThatFieldIsDisabled(editAppDataProline.disabledHeadersBooleanButton)
                .assertThat(ExpectedConditions.attributeContains(editAppDataProline.headersBooleanButton, CURRENT_VALUE, "false"))
                .backButtonClick()
                .openApplList(editAppDataProline.loanBusinessMenuButton)
                .createAppButtonClick()
                .assertThatFieldIsDisabled(editAppDataProline.disabledHeadersBooleanButton)
                .assertThat(ExpectedConditions.attributeContains(editAppDataProline.headersBooleanButton, CURRENT_VALUE, "false"))
                .backButtonClick()
                .openApplList(editAppDataProline.expenseMenuButton)
                .createAppButtonClick()
                .assertThatFieldIsDisabled(editAppDataProline.disabledHeadersBooleanButton)
                .assertThat(ExpectedConditions.attributeContains(editAppDataProline.headersBooleanButton, CURRENT_VALUE, "false"));
    }

    @Test(priority = 9)
    public void requiredCatalogFieldValidation() {
        steps
                .open(TEST_LOTOK)
                .loginAs(new UsersData("тест Заявитель", "buysome@ukr.net", "123456", RU))
                .createAppButtonClick()
                .catalogElementClick(editAppDataLotok.article)
                .catalogElementClick(editAppDataLotok.incomeFolder)
                .catalogElementClick(editAppDataLotok.banksPerCent)
                .type("5000", editAppDataLotok.summField)
                .catalogElementClick(editAppDataLotok.contractor)
                .catalogElementClick(editAppDataLotok.supliersFolder)
                .catalogElementClick(editAppDataLotok.firstResult)
                .catalogElementClick(editAppDataLotok.calcAccount)
                .catalogElementClick(editAppDataLotok.firstResult)
                .type("56987", editAppDataLotok.accountsSummField)
                .saveButtonClick()
                .assertTrue(compare(textOfNotification, "Упс! Похоже, вы не заполнили все необходимые поля!"));
    }

    /*@Test(priority = 10)
    public void fieldsAutoInsertWithValueFromCatalogTable() {
        steps
                .open(TEST_LOTOK)
                .loginAs(new UsersData("тест Заявитель", "buysome@ukr.net", "123456", RU))
                .createApp(editAppDataLotok)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .assertTextInElement(editAppDataLotok.)
    }*/

    @Test(priority = 11)
    public void verifyOfDisabledDecimalField() {
        steps
                .open(TEST_LOTOK)
                .loginAs(new UsersData("тест Заявитель", "buysome@ukr.net", "123456", RU))
                .createAppButtonClick()
                .assertThatFieldIsDisabled(editAppDataLotok.mfoField);
    }

    @Test(priority = 12)
    public void verifyOfDisabledStringField() {
        steps
                .open(TEST_LOTOK)
                .loginAs(new UsersData("тест Заявитель", "buysome@ukr.net", "123456", RU))
                .createAppButtonClick()
                .assertThatFieldIsDisabled(editAppDataLotok.banksNames);
    }

    @Test(priority = 11)
    public void verifyOfDisabledDateField() {
        steps
                .open(TEST_LOTOK)
                .loginAs(new UsersData("тест Заявитель", "buysome@ukr.net", "123456", RU))
                .createAppButtonClick()
                .assertThatDateFieldIsDisabled(editAppDataLotok.dateOfAgreement);
    }
}