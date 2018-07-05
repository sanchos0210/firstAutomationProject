package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.pages.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class LoginSteps extends BaseSteps {

    public LoginSteps(Driver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    @Step("Заходим на страницу логина")
    public LoginSteps open(String url) {
        driver.get(url);
        return this;
    }

    @Step("Попробовать залогинится")
    public LoginSteps login(String email, String pass) {
        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(pass);
        loginPage.submitButton.click();
        return this;
    }

    @Step("Залогинится")
    public AppListSteps loginWithSuccessful(String email, String pass) {
        login(email, pass);
        getWait().loginWait();
        return  getAppListSteps();
    }

    @Step("Залогинится")
    public AppListSteps loginAs(UsersData user) {
        try {
            login(user.getUserEmail(), user.getUserPassword());
            getWait().loginWait();
            BaseTest.activeUser = user;
        }catch(NoSuchElementException e) {}
        return  getAppListSteps();
    }

    @Step("Залогинится по кнопке Enter")
    public AppListSteps loginWithEnter(String email, String pass) {
        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(pass + Keys.ENTER);
        getWait().loginWait();
        return  getAppListSteps();
    }

    @Step("Кликнуть на \"Забыл пароль\"")
    public LoginSteps forgotYourPasswordClick() {
        loginPage.forgotYourPassword.click();
        return this;
    }
}