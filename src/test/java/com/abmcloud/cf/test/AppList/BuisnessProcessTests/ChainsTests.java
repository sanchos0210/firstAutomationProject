package com.abmcloud.cf.test.AppList.BuisnessProcessTests;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import org.testng.annotations.Test;

public class ChainsTests extends BaseTest {

    @Test(priority = 1)
    public void approveChainWithRuleAmountMoreThan() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
            steps
                    .open(APP_LIST_DEMO_DB)
                    .loginAs(USER, EMAIL, PASSWORD, RU)
                    .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                    .selectAppByNumber(numberOfCreatedApp)
                    .clickOnStatusOf(selectedApp)
                    .asserts().assertTrue(helpers.compare(dbInfo.getStringArray("chain1"), helpers.getChainSteps2()));
        }

    @Test(priority = 10)
    public void approveChainWithRuleAmountLessThan() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare(dbInfo.getStringArray("chain2"), helpers.getChainSteps2()));
    }

    @Test(priority = 20)
    public void approveChainWithTwoRules() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare(dbInfo.getStringArray("chain3"), helpers.getChainSteps2()));
    }

    @Test(priority = 30)
    public void checkParallelSteps() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare("Первый, Второй", helpers.getParallelStepsNames(3)));
    }

    @Test(priority = 40)
    public void checkCancelChain() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_cancel_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "NO")
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare(dbInfo.getStringArray("cancel_chain"), helpers.getChainSteps2()));
    }

    @Test(priority = 50)
    public void checkDefaultCancelStep() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfCreatedApp)
                .status(CANCEL, selectedApp, "NO")
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare(dbInfo.getStringArray("default_cancel_chain"), helpers.getChainSteps2()));
    }

    @Test(priority = 60)
    public void updateChainOfApp() {
        String[][] expectedSteps = {{"Документ создан", null},{"Вместе", null},{"Автор", null},{"Первый","Второй"},{"Бухгалтер", null}};
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare(dbInfo.getStringArray("chain2"), helpers.getChainSteps2()))
                .getAppListStep().closeChainStepsPopup()
                .status(SEND_FOR_APPROVAL, selectedApp)
                .selectAppByNumber(numberOfSelectedApp)
                .clickOnNumberOf(selectedApp)
                .editDecimalField(dbInfo.getString("sum_field_name"), "10000")
                .catalogFieldClick(dbInfo.getString("contractor_field_name"))
                .catalogElementClick("Контрагент 1")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnStatusOf(selectedApp)
                .asserts().assertTrue(helpers.compare(expectedSteps, helpers.getChainSteps()));
    }


}