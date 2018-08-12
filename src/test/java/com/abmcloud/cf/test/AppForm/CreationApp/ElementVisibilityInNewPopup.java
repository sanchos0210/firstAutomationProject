package com.abmcloud.cf.test.AppForm.CreationApp;

import com.abmcloud.cf.test.API.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;


@Epic("Отображение элементов в форме заявки")
@Feature("Форма заявки")
public class ElementVisibilityInNewPopup extends BaseTest {

    @Test(priority = 1)
    @Story("Проверяем отображение элементов в форме создания заявки")
    public void elementsVisibilityInCreationAppPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .showInformationBlockClick()
                .asserts().assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().approvalSteps))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().changesHistory))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().viewsHistory))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().cancelAppButton))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().approveAppButton))
                .assertFalse(helpers.isElementPresent(objectManager.getAppEditPage().copyLinkButton))
                .assertTrue(helpers.isElementPresent(objectManager.getAppEditPage().saveButton));
    }
}
