package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Logs;
import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.PageObject.Components.Confirmation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserProfileSteps extends BaseSteps {

    Confirmation confirmation;

    private By redirectApplicationsLocator = By.cssSelector("subusersredirectappls .btn.btn-primary.btn-sm.pointer");
    private By usersListLocators = By.cssSelector(".ui-listbox-list li");
    private By userNameLocator = By.cssSelector(".profile_name");
    private By checkBoxLocator = By.cssSelector(".ui-chkbox.ui-widget");
    private By backButton = By.cssSelector(".fa.fa-angle-left.pointer");

    @FindBy(xpath = "//span[.='Перенаправить заявки']")
    public WebElement redirectApplicationsButton;

    @FindBy(xpath = "//span[.='Вернуть заявки мне на утверждение']")
    public WebElement returnMyAppForApprovalButton;

    @FindBy(xpath = "//span[.='Перенаправить']")
    public WebElement redirectButton;

    public UserProfileSteps(ObjectManager objectManager) {
        this.objectManager = objectManager;
        this.driver = objectManager.getDriver();
        logs = new Logs(UserProfileSteps.class.getName());
        confirmation = objectManager.getConfirmation();
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    @Step("Кликнуть на кнопку \"Перенаправить заявки\"")
    public UserProfileSteps redirectApplicationsClick() {
        redirectApplicationsButton.click();
        objectManager.getWait().waitForClickable(3, usersListLocators);
        return this;
    }

    @Step("Выбрать заместителя: ${name}")
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

    @Step("Кликнуть на кнопку \"Перенаправить\"")
    public UserProfileSteps redirectApplications() {
        redirectButton.click();
        confirmation.clickOnRedButton();
        return this;
    }

    @Step("Закрыть профиль пользователя")
    public AppListSteps closeUserProfile() {
        driver.fluentWait(backButton).click();
        return objectManager.getAppListSteps();
    }

    @Step("Кликнуть на кнопку \"Вернуть мои заявки\"")
    public UserProfileSteps returnMyApplications() {
        returnMyAppForApprovalButton.click();
        confirmation.clickOnRedButton();
        objectManager.getWait().waitForElementClickable(3, redirectApplicationsButton);
        return this;
    }
}
