package com.abmcloud.cf.test.AppForm.CreationApp;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.TestListener;


@Listeners({ TestListener.class })
@Epic("Отображение элементов в форме заявки")
@Feature("Форма заявки")
public class ElementVisibilityInNewPopup extends BaseTest {

    AppFormDBInfo appFormDB;

    @BeforeMethod
    public void objectCreation() {
        appFormDB = new AppFormDBInfo();
    }

    @Test(priority = 1)
    @Story("Проверяем отображение элементов в форме создания заявки")
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
