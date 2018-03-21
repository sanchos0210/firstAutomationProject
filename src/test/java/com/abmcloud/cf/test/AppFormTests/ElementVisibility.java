package com.abmcloud.cf.test.AppFormTests;

import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.API.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementVisibility extends BaseTest {

    AppFormDBInfo appFormDB;

    @BeforeMethod
    public void objectCreation() {
        appFormDB = new AppFormDBInfo();
    }

    @Test(priority = 1)
    public void elementsVisibilityInCreationAppPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .showInformationBlockClick()
                .asserts().assertFalse(isElementPresent(appEditPage.approvalSteps))
                .assertFalse(isElementPresent(appEditPage.changesHistory))
                .assertFalse(isElementPresent(appEditPage.viewsHistory))
                .assertFalse(isElementPresent(appEditPage.cancelAppButton))
                .assertFalse(isElementPresent(appEditPage.approveAppButton))
                .assertFalse(isElementPresent(appEditPage.copyLinkButton))
                .assertTrue(isElementPresent(appEditPage.saveButton));
    }
}
