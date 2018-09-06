package com.abmcloud.cf.test.PageObject.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class HederSteps extends BaseSteps {

    @Step("Вылогинится")
    public LoginSteps logOut() {
        logs.infoMsg("Logining out from CF system: " + objectManager.getAppListPage().avatar.toString() + " " + objectManager.getAppListPage().signOutButton.toString());
        try {
            objectManager.getAppListPage().avatar.click();
            objectManager.getAppListPage().signOutButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        driver.assertThat(textToBePresentInElement((objectManager.getLoginPage().cashflowTitle), "ABM cashflow"));     //verificationThat
        return getLoginSteps();
    }

    @Step("Открыть профиль пользователя")
    public UserProfileSteps openUserProfile() {
        objectManager.getAppListPage().avatar.click();
        objectManager.getAppListPage().userProfileButton.click();
        objectManager.getWait().waitForClickable(3, By.cssSelector("div.user_content"));
        return objectManager.getUserProfileSteps();
    }
}
