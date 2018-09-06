package com.abmcloud.cf.test.AppList.BuisnessProcessTests;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.RU;

@Epic("Проверка определения цепочек для заявки")
@Feature("Список заявок")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ChainsTests extends BaseTest {

    @Test(priority = 1)
    public void approveChainWithRuleAmountMoreThan() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
            steps
                    .open(APP_LIST_TEST_DB)
                    .loginAs(USER, EMAIL, PASSWORD, RU)
                    .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                    .selectAppByNumber(testInfo.numberOfCreatedApp)
                    .clickOnStatusOf(testInfo.selectedApp)
                    .asserts().compare(dbInfo.getList("chain1"), objectManager.getStepsPopup().getChainSteps2());
        }

    @Test(priority = 10)
    public void approveChainWithRuleAmountLessThan() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare(dbInfo.getList("chain2"), objectManager.getStepsPopup().getChainSteps2());
    }

    @Test(priority = 20)
    public void approveChainWithTwoRules() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare(dbInfo.getList("chain3"), objectManager.getStepsPopup().getChainSteps2());
    }

    @Test(priority = 30)
    public void checkParallelSteps() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare("Первый, Второй", objectManager.getStepsPopup().getParallelStepsNames(3));
    }

    @Test(priority = 40)
    public void checkCancelChain() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_cancel_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(CANCEL, testInfo.selectedApp, "NO")
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare(dbInfo.getList("cancel_chain"), objectManager.getStepsPopup().getChainSteps2());
    }

    @Test(priority = 50)
    public void checkDefaultCancelStep() {
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(CANCEL, testInfo.selectedApp, "NO")
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare(dbInfo.getList("default_cancel_chain"), objectManager.getStepsPopup().getChainSteps2());
    }

    @Test(priority = 60)
    public void updateChainOfApp() {
        String[][] expectedSteps = {{"Документ создан", null},{"Вместе", null},{"Автор", null},{"Первый","Второй"},{"Бухгалтер", null}};
        DataBaseInfo dbInfo = new DataBaseInfo("app_list_db.json");
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_2nd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare(dbInfo.getList("chain2"), objectManager.getStepsPopup().getChainSteps2())
                .getAppListStep().closeChainStepsPopup()
                .status(SEND_FOR_APPROVAL, testInfo.selectedApp)
                .selectAppByNumber(testInfo.numberOfSelectedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .editDecimalField(dbInfo.getString("sum_field_name"), "10000")
                .catalogFieldClick(dbInfo.getString("contractor_field_name"))
                .catalogElementClick("Контрагент 1")
                .saveApplication()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnStatusOf(testInfo.selectedApp)
                .asserts().compare(expectedSteps, objectManager.getStepsPopup().getChainSteps());
    }
}