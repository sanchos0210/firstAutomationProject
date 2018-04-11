package com.abmcloud.cf.test.AppList.BuisnessProcessTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppListDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
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
}