package com.abmcloud.cf.test.AppForm.EditionApp.Title;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementVisibilityInTitleInEditPopup extends BaseTest {

    AppFormDBInfo appFormDBInfo;

    @BeforeMethod
    public void objectCreation() {
        appFormDBInfo = new AppFormDBInfo();
    }

    @Test
    public void visibilityOfElementsInTitleOfEditPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(appFormDBInfo)
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTextInElement(appEditPage.editPopupTitle, "Edit document # " + numberOfCreatedApp)
                .assertTrue(isElementPresent(appEditPage.closeAppFormButton))
                .assertTrue(isElementPresent(appEditPage.copyLinkButton))
                .assertTrue(isElementPresent(appEditPage.showInformationBlock));
    }
}
