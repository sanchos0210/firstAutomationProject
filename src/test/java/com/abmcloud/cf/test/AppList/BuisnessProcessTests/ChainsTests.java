package com.abmcloud.cf.test.AppList.BuisnessProcessTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.AppListDBInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.Fields.CatalogField;
import org.testng.annotations.Test;

public class ChainsTests extends BaseTest {

    @Test(priority = 1)
    public void approveChainWithRuleAmountMoreThan() {
            AppListDBInfo appListDBInfo = new AppListDBInfo();
            steps
                    .open(APP_LIST_DEMO_DB)
                    .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                    .createApp(new AppListDBInfo(SUM, "10000"))
                    .selectAppByNumber(numberOfCreatedApp)
                    .clickOnStatusOf(selectedApp)
                    .asserts().assertTrue(compare(appListDBInfo.chain1, getChainSteps()));
        }

    @Test(priority = 10)
    public void approveChainWithRuleAmountLessThan() {
        AppListDBInfo appListDBInfo = new AppListDBInfo();
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(CONTRACTOR, "Контрагент 2"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare(appListDBInfo.chain2, getChainSteps()));
    }

    @Test(priority = 20)
    public void approveChainWithTwoRules() {
        AppListDBInfo appListDBInfo = new AppListDBInfo();
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare(appListDBInfo.chain3, getChainSteps()));
    }

    @Test(priority = 30)
    public void checkParallelSteps() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare("Первый, Второй", getParallelStepsNames(3)));
    }

    @Test(priority = 40)
    public void checkCancelChain() {
        String[][] expectedSteps = {{"Документ создан", null}, {"Документ отменен", null}, {"Отменятор", null}};
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(CONTRACTOR_AND_SUM, ""))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "NO")
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare(expectedSteps, getChainSteps()));
    }

    @Test(priority = 50)
    public void checkDefaultCancelStep() {
        String[][] expectedSteps = {{"Документ создан", null}, {"Документ отменен", null}};
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo(SUM, "10000"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "NO")
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare(expectedSteps, getChainSteps()));
    }

    @Test(priority = 60)
    public void editChainOfApp() {
        AppListDBInfo appListDBInfo = new AppListDBInfo();
        CatalogField catalogField = new CatalogField();
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp(new AppListDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare(appListDBInfo.chain3, getChainSteps()))
                .getAppListStep().closeChainStepsPopup()
                .clickOnNumberOf(selectedApp)
                .catalogElementClick(catalogField.getField(appListDBInfo.contractorFieldName))
                .catalogElementClick(catalogField.getItem("Контрагент 2"))
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(compare(appListDBInfo.chain2, getChainSteps()));
    }
}