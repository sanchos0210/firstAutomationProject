package com.abmcloud.cf.test.AppForm.CreationApp.Title;

import com.abmcloud.cf.test.API.BaseTest;
import org.testng.annotations.Test;

public class ElementVisibilityInTitleInNewPopup extends BaseTest {

    @Test
    public void elementVisibilityInTitleInNewPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts()
                .assertTextInElement(appEditPage.editPopupTitle, "Add document")
                .assertTrue(helpers.isElementPresent(appEditPage.closeAppFormButton))
                .assertFalse(helpers.isElementPresent(appEditPage.copyLinkButton))
                .assertTrue(helpers.isElementPresent(appEditPage.showInformationBlock));
    }
}
