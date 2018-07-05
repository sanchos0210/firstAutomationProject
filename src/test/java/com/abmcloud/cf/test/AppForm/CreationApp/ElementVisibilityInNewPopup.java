package com.abmcloud.cf.test.AppForm.CreationApp;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.UsersData;
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
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createAppButtonClick()
                .showInformationBlockClick()
                .asserts().assertFalse(helpers.isElementPresent(appEditPage.approvalSteps))
                .assertFalse(helpers.isElementPresent(appEditPage.changesHistory))
                .assertFalse(helpers.isElementPresent(appEditPage.viewsHistory))
                .assertFalse(helpers.isElementPresent(appEditPage.cancelAppButton))
                .assertFalse(helpers.isElementPresent(appEditPage.approveAppButton))
                .assertFalse(helpers.isElementPresent(appEditPage.copyLinkButton))
                .assertTrue(helpers.isElementPresent(appEditPage.saveButton));
    }
}
