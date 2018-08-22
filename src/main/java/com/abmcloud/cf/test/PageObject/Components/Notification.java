package com.abmcloud.cf.test.PageObject.Components;

import com.abmcloud.cf.test.Driver.BaseTest;
import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.Logs;
import com.abmcloud.cf.test.Driver.Wait;
import com.abmcloud.cf.test.Utils.TestInfo;
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

    public void saveTextAndNumberInNotificationOfSavedApplication(TestInfo testInfo) {
        try {
            wait.waitForElementVisibillity(applSavedNotification);
            testInfo.textOfNotification = applSavedNotification.getText();
            if(testInfo.textOfNotification == null) {
                logs.warning("Text of notification is NULL !");
            }
            switch (testInfo.activeUser.getLocalizeLanguage()) {
                case BaseTest.EN: {     //for english language
                    testInfo.numberOfCreatedApp = testInfo.textOfNotification.substring(11, 18);
                    break;
                }
                case BaseTest.RU: {     //for russian language
                    testInfo.numberOfCreatedApp = testInfo.textOfNotification.substring(9, 16);
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
