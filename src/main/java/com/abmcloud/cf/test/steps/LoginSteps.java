package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.UsersData;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginSteps extends BaseSteps {

    public LoginSteps() {}

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заходим на страницу логина")
    public LoginSteps open(String url) {
        driver.get(url);
        return this;
    }

    public LoginSteps login(String email, String pass) {
        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(pass);
        loginPage.submitButton.click();
        return this;
    }

    public AppListSteps loginWithSuccessful(String email, String pass) {
        login(email, pass);
        loginWait();
        return  new AppListSteps();
    }

    @Step("Логинимся")
    public AppListSteps loginAs(UsersData user) {
        try {
            login(user.getUserEmail(), user.getUserPassword());
            loginWait();
            BaseTest.activeUser = user;
        }catch(NoSuchElementException e) {}
        return  new AppListSteps();
    }

    public AppListSteps loginWithEnter(String email, String pass) {
        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(pass + Keys.ENTER);
        loginWait();
        return  new AppListSteps();
    }

    public LoginSteps forgotYourPasswordClick() {
        loginPage.forgotYourPassword.click();
        return this;
    }
}