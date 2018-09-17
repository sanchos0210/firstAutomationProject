package com.abmcloud.cf.test.PageObject.Components;

import com.abmcloud.cf.test.Driver.*;
import com.abmcloud.cf.test.Utils.TestInfo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Notification {

    Logs logs;
    Wait wait;
    TestInfo testInfo;

    @FindBy(css = "div.sn-content")
    private WebElement applSavedNotification;

    public Notification(ObjectManager objectManager) {
        Driver driver = objectManager.getDriver();
        PageFactory.initElements(driver.getWebDriver(), this);
        logs = objectManager.getLogs();
        wait = objectManager.getWait();
        testInfo = objectManager.getTestInfo();
    }

    public void saveTextAndAppNumAndClickOnNotification() {
        saveTextOfNotificationAndClick();
        try {
            switch (testInfo.activeUser.getLocalizeLanguage()) {
                case EN: {     //for english language
                    testInfo.numberOfCreatedApp = testInfo.textOfNotification.substring(11, 18);
                    break;
                }
                case RU: {     //for russian language
                    testInfo.numberOfCreatedApp = testInfo.textOfNotification.substring(9, 16);
                    break;
                }
            }
        } catch(RuntimeException e) {
            logs.warning(e.getMessage());
        }
    }

    public void saveTextOfNotificationAndClick() {
        try {
            wait.waitForElementVisibility(8, applSavedNotification);
            testInfo.textOfNotification = applSavedNotification.getText();
            notificationClick();
            if(testInfo.textOfNotification == null) {
                logs.warning("Text of notification is NULL !");
            }
        } catch(RuntimeException e) {
            logs.warning(e.getMessage());
        }

    }

    public void notificationClick() {
        applSavedNotification.click();
    }
}
