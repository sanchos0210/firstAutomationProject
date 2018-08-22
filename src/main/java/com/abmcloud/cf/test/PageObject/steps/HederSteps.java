package com.abmcloud.cf.test.PageObject.steps;

import io.qameta.allure.Step;

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
}
