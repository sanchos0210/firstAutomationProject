package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Logs;
import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.PageObject.Components.Confirmation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserProfileSteps extends BaseSteps {

    Confirmation confirmation;

    private By redirectApplicationsLocator = By.cssSelector("subusersredirectappls .btn.btn-primary.btn-sm.pointer");
    private By usersListLocators = By.cssSelector(".ui-listbox-list li");
    private By userNameLocator = By.cssSelector(".profile_name");
    private By checkBoxLocator = By.cssSelector(".ui-chkbox.ui-widget");
    private By backButton = By.cssSelector(".fa.fa-angle-left.pointer");

    public UserProfileSteps(ObjectManager objectManager) {
        this.objectManager = objectManager;
        this.driver = objectManager.getDriver();
        logs = new Logs(UserProfileSteps.class.getName());
        confirmation = objectManager.getConfirmation();
    }

    public UserProfileSteps redirectApplicationsClick() {
        driver.fluentWait(redirectApplicationsLocator).click();
        objectManager.getWait().waitForClickable(3, usersListLocators);
        return this;
    }

    public UserProfileSteps choseUser(String name) {
        List<WebElement> usersList = driver.$$(usersListLocators);
        for(WebElement user: usersList) {
            String userName = user.findElement(userNameLocator).getText();
            if(name.equals(userName)) {
                user.findElement(checkBoxLocator).click();
                break;
            }
        }
        return this;
    }

    public UserProfileSteps redirectApplications() {
        String nameOfButton = null;
        switch(objectManager.getTestInfo().activeUser.getLocalizeLanguage()) {
            case RU: {
                nameOfButton = "Перенаправить";
                break;
            }
            case EN: {
                nameOfButton = "Redirect";
                break;
            }
        }
        driver.fluentWait(By.xpath("//span[.='" + nameOfButton +"']")).click();
        confirmation.clickOnRedButton();
        return this;
    }

    public AppListSteps closeUserProfile() {
        driver.fluentWait(backButton).click();
        return objectManager.getAppListSteps();
    }

    public UserProfileSteps returnMyApplications() {
        driver.fluentWait(redirectApplicationsLocator).click();
        confirmation.clickOnRedButton();
        objectManager.getWait().waitForClickable(3, By.cssSelector("div.user_content"));
        return this;
    }
}
