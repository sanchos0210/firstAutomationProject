package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.RU;

@Epic("Проверка истории просмотров в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ViewsHistoryTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test
    public void viewsHistoryTest() {
        steps
                .open(APP_LIST_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .asserts().assertTextInElement(objectManager.getAppEditPage().viewsHistory, "История просмотров 1")
                .getAppFormStep()
                .backButtonClick()
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .showInformationBlockClick()
                .asserts().assertTextInElement(objectManager.getAppEditPage().viewsHistory, "История просмотров 2");
    }
}
