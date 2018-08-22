package com.abmcloud.cf.test.AppForm.EditionApp.Title;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("Отображение элементов в тайтле в попапе редактирования")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ElementVisibilityInTitleInEditPopup extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test
    public void visibilityOfElementsInTitleOfEditPopup() {
        steps
                .open(APP_FORM_TEST_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .asserts()
                .assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Edit document # " + testInfo.numberOfCreatedApp)
                .isElementPresent(objectManager.getAppEditPage().closeAppFormButton, true)
                //.assertTrue(helpers.isElementPresent(appEditPage.copyLinkButton))     this button in not present
                .isElementPresent(objectManager.getAppEditPage().showInformationBlock, true);
    }
}
