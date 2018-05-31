package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.AppListDBInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.Fields.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangesHistoryTests extends BaseTest {

    DecimalField decimalField;
    StringField stringField;
    CatalogField catalogField;
    BooleanField booleanField;
    DateField dateField;

    @BeforeMethod
    public void objectCreation() {
        decimalField = new DecimalField();
        stringField = new StringField();
        catalogField = new CatalogField();
        booleanField = new BooleanField();
        dateField = new DateField();
    }

    @Test(priority = 1)
    public void changesHistoryOfDecimalField() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp2(new AppListDBInfo(ALL_FIELDS_ARE_FILLED))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .edit(decimalField.getField(AppListDBInfo.sumFieldName), "200")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(AppListDBInfo.sumFieldName, AppListDBInfo.sumFieldValue, "200");
    }

    @Test(priority = 2)
    public void changesHistoryOfStringField() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp2(new AppListDBInfo(ALL_FIELDS_ARE_FILLED))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .edit(stringField.getField(AppListDBInfo.descriptionFieldName), "Другое описание")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(AppListDBInfo.descriptionFieldName, AppListDBInfo.descriptionFieldValue, "Другое описание");
    }

    @Test(priority = 3)
    public void changesHistoryOfCatalogField() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp2(new AppListDBInfo(ALL_FIELDS_ARE_FILLED))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .catalogElementClick(catalogField.getField(AppListDBInfo.contractorFieldName))
                .catalogElementClick(catalogField.getItem(AppListDBInfo.contractorItem2))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(AppListDBInfo.contractorFieldName, AppListDBInfo.contractorItem1, AppListDBInfo.contractorItem2);
    }

    @Test(priority = 4)
    public void changesHistoryOfBooleanField() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp2(new AppListDBInfo(ALL_FIELDS_ARE_FILLED))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .booleanButtonClick(booleanField.getField(AppListDBInfo.paymentTypeField))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(AppListDBInfo.paymentTypeField, "Безналичные", "Наличные");
    }

    @Test(priority = 5)
    public void changesHistoryOfDateField() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp2(new AppListDBInfo(ALL_FIELDS_ARE_FILLED))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .clickOnDateField(dateField.getField(AppListDBInfo.paymentDateField))
                .catalogElementClick(appEditPage.tomorrowDate)
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .changesHistoryClick()
                .asserts().checkHistoryOf(AppListDBInfo.paymentDateField, getTodayFullDate(), getTomorrowFullDate());
    }
}
