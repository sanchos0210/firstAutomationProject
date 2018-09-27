package com.abmcloud.cf.test.AppForm.CreationApp.Title;

import com.abmcloud.cf.test.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;

@Epic("Отображение элементов в попапе создания")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class ElementVisibilityInTitleInNewPopup extends BaseTest {

    @Test
    public void elementVisibilityInTitleInNewPopup() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .asserts()
                .assertTextInElement(objectManager.getAppEditPage().editPopupTitle, "Add document")
                .isElementPresent(objectManager.getAppEditPage().closeAppFormButton, true)
                .isElementPresent(objectManager.getAppEditPage().copyLinkButton, false)
                .isElementPresent(objectManager.getAppEditPage().showInformationBlock, true);
    }
}
