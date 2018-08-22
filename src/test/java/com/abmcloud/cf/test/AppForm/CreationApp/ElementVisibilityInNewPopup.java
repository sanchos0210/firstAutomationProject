package com.abmcloud.cf.test.AppForm.CreationApp;

import com.abmcloud.cf.test.Driver.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Epic("Отображение элементов в форме заявки")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ElementVisibilityInNewPopup extends BaseTest {

    @Test(priority = 1)
    @Story("Проверяем отображение элементов в форме создания заявки")
    public void elementsVisibilityInCreationAppPopup() {
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .showInformationBlockClick()
                .asserts().isElementPresent(objectManager.getAppEditPage().approvalSteps, false)
                .isElementPresent(objectManager.getAppEditPage().changesHistory, false)
                .isElementPresent(objectManager.getAppEditPage().viewsHistory, false)
                .isElementPresent(objectManager.getAppEditPage().cancelAppButton, false)
                .isElementPresent(objectManager.getAppEditPage().approveAppButton, false)
                .isElementPresent(objectManager.getAppEditPage().copyLinkButton, false)
                .isElementPresent(objectManager.getAppEditPage().saveButton, true);
    }
}
