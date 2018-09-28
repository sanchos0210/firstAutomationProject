package com.abmcloud.cf.test.AppList.BuisnessProcessTests;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.Json;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.CANCEL;
import static com.abmcloud.cf.test.Driver.Constants.RU;

public class SuperAppTests extends BaseTest {

    @Test(priority = 1)
    public void cancelSuperAppTest() {
        Json dbInfo = new Json("super_app_db.json");
        steps
                .open(SUPER_APP_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .createApp(dbInfo.getJsonArray("fields_configuration_for_1st_chain"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .status(CANCEL, testInfo.selectedApp, "Cancel")
                .asserts().compare("Заявка № " + testInfo.numberOfCreatedApp + " отменена.", testInfo.textOfNotification);
    }

}
