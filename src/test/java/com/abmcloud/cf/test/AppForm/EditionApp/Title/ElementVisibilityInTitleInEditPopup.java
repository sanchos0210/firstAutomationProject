package com.abmcloud.cf.test.AppForm.EditionApp.Title;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementVisibilityInTitleInEditPopup extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test
    public void visibilityOfElementsInTitleOfEditPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .asserts()
                .assertTextInElement(appEditPage.editPopupTitle, "Edit document # " + numberOfCreatedApp)
                .assertTrue(helpers.isElementPresent(appEditPage.closeAppFormButton))
                .assertTrue(helpers.isElementPresent(appEditPage.copyLinkButton))
                .assertTrue(helpers.isElementPresent(appEditPage.showInformationBlock));
    }
}
