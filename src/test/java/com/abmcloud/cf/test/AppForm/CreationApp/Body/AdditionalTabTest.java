package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.Fields.CatalogField;
import com.abmcloud.cf.test.Fields.DecimalField;
import com.abmcloud.cf.test.Fields.StringField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdditionalTabTest extends BaseTest {

    AppFormDBInfo appFormDBInfo;
    DecimalField decimalField;
    StringField stringField;
    CatalogField catalogField;

    @BeforeMethod
    public void objectCreation() {
        appFormDBInfo = new AppFormDBInfo();
        decimalField = new DecimalField();
        stringField = new StringField();
        catalogField = new CatalogField();
    }

    @Test(priority = 1)
    public void requiredDecimalFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments4)
                .createAppButtonClick()
                .openTab("Additional")
                .edit(stringField.getField(appFormDBInfo.stringField1), "text")
                .catalogElementClick(catalogField.getField(appFormDBInfo.catalogField2))
                .catalogElementClick(appFormDBInfo.value1)
                .openTab("Main")
                .edit(decimalField.getField(appFormDBInfo.decimalField1), "150")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 10)
    public void requiredStringFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments4)
                .createAppButtonClick()
                .openTab("Additional")
                .edit(decimalField.getField(appFormDBInfo.decimalField1), "150")
                .catalogElementClick(catalogField.getField(appFormDBInfo.catalogField2))
                .catalogElementClick(appFormDBInfo.value1)
                .openTab("Main")
                .edit(decimalField.getField(appFormDBInfo.decimalField1), "150")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 20)
    public void requiredCatalogFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments4)
                .createAppButtonClick()
                .openTab("Additional")
                .edit(decimalField.getField(appFormDBInfo.decimalField1), "150")
                .edit(stringField.getField(appFormDBInfo.stringField1), "text")
                .openTab("Main")
                .edit(decimalField.getField(appFormDBInfo.decimalField1), "150")
                .saveButtonClick()
                .asserts().assertTrue(compare("Oops! Looks like you have not filled out all of the required fields!", textOfNotification));
    }

    @Test(priority = 30)
    public void autoInsertDecimalFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments4)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDBInfo.catalogField1))
                .catalogElementClick(appFormDBInfo.contractor1)
                .openTab("Additional")
                .asserts().assertTrue(compare("1000", decimalField.getValue(appFormDBInfo.decimalField1)));
    }

    @Test(priority = 40)
    public void autoInsertStringFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments4)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDBInfo.catalogField1))
                .catalogElementClick(appFormDBInfo.contractor1)
                .openTab("Additional")
                .asserts().assertTrue(compare("value 1", stringField.getValue(appFormDBInfo.stringField1)));
    }

    @Test(priority = 40)
    public void autoInsertCatalogFieldInAdditionalTab() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .openAppList(appFormDBInfo.preparePayments4)
                .createAppButtonClick()
                .catalogElementClick(catalogField.getField(appFormDBInfo.catalogField1))
                .catalogElementClick(appFormDBInfo.contractor1)
                .openTab("Additional")
                .asserts().assertTrue(compare("Value 1", catalogField.getValue(appFormDBInfo.catalogField2)));
    }
}
