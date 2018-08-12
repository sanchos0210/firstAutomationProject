package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.API.Logs;
import com.abmcloud.cf.test.API.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Notification {

    Logs logs;
    Wait wait;

    @FindBy(css = "div.sn-content")
    private WebElement applSavedNotification;

    public Notification(Driver driver, Wait wait) {
        PageFactory.initElements(driver.getWebDriver(), this);
        logs = new Logs(Notification.class.getName());
        this.wait = wait;
    }

    public void saveTextAndNumberInNotificationOfSavedApplication() {
        try {
            wait.waitForElementVisibillity(applSavedNotification);
            BaseTest.textOfNotification = applSavedNotification.getText();
            if(BaseTest.textOfNotification == null) {
                logs.warning("Text of notification is NULL !");
            }
            switch (BaseTest.activeUser.getLocalizeLanguage()) {
                case BaseTest.EN: {     //for english language
                    BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(11, 18);
                    break;
                }
                case BaseTest.RU: {     //for russian language
                    BaseTest.numberOfCreatedApp = BaseTest.textOfNotification.substring(9, 16);
                    break;
                }
            }
            applSavedNotification.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    public void notificationClick() {
        applSavedNotification.click();
    }
}
