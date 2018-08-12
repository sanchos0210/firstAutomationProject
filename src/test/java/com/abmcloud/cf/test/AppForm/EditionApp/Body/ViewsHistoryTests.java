package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ViewsHistoryTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_list_db.json");
    }

    @Test
    public void viewsHistoryTest() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_3rd_chain"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts().assertTextInElement(objectManager.getAppEditPage().viewsHistory, "История просмотров 1")
                .getAppFormStep()
                .backButtonClick()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts().assertTextInElement(objectManager.getAppEditPage().viewsHistory, "История просмотров 2");
    }
}
