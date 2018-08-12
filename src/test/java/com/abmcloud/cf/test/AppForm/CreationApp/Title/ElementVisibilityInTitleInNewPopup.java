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
                .assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Add document")
                .assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().closeAppFormButton))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().copyLinkButton))
                .assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().showInformationBlock));
    }
}
