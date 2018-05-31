package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.AppListDBInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.Test;

public class ViewsHistoryTests extends BaseTest {

    @Test
    public void viewsHistoryTest() {
        steps
                .open(APP_LIST_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, RU))
                .createApp2(new AppListDBInfo(ALL_FIELDS_ARE_FILLED))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts().assertTextInElement(appEditPage.viewsHistory, "История просмотров 1")
                .getAppFormStep()
                .backButtonClick()
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts().assertTextInElement(appEditPage.viewsHistory, "История просмотров 2");
    }
}
