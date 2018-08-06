package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldEdition extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test(priority = 1)
    public void editionDecimalField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .editDecimalField(dbInfo.getString("decimal_field_3"), "500")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare("500", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_3"))));
    }

    @Test(priority = 2)
    public void autoInsertFieldsInEditPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick("Contractor 1")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .catalogFieldClick(dbInfo.getString("catalog_field_2"))
                .catalogElementClick("Contractor 5")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTrue(helpers.compare("5000", helpers.getValueOfDecimalField(dbInfo.getString("decimal_field_2"))))
                .assertTrue(helpers.compare("value 5", helpers.getValueOfStringField(dbInfo.getString("string_field_2"))))
                .assertTrue(helpers.compare("false", helpers.getValueOfBooleanField(dbInfo.getString("boolean_field_3"))))
                .assertTrue(helpers.compare("02.10.2018", helpers.getValueOfDateField(dbInfo.getString("date_field_1"))));
    }

    @Test(priority = 10)
    public void editionStringField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .editStringField(dbInfo.getString("string_field_3"), "new text")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare("new text", helpers.getValueOfStringField(dbInfo.getString("string_field_3"))));
    }

    @Test(priority = 20)
    public void editionBooleanField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .booleanButtonClick(dbInfo.getString("boolean_field_3"))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare("true", helpers.getValueOfBooleanField(dbInfo.getString("boolean_field_3"))));
    }

    @Test(priority = 30)
    public void editionCatalogField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .catalogFieldClick(dbInfo.getString("catalog_field_3"))
                .catalogFolderClick(dbInfo.getString("folder_1"))
                .catalogElementClick(dbInfo.getString("value_2"))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare("Value 2", helpers.getValueOfCatalogField(dbInfo.getString("catalog_field_3"))));
    }

    @Test(priority = 40)
    public void editionDateField() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .clickOnDateField(dbInfo.getString("date_field_2"))
                .buttonClick(appEditPage.tomorrowDate)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts().assertTrue(helpers.compare(helpers.getTomorrowFullDate(), helpers.getValueOfDateField(dbInfo.getString("date_field_2"))));
    }
}
