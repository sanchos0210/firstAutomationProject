package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.API.Driver;
import com.abmcloud.cf.test.API.Logs;
import com.abmcloud.cf.test.DBInfo.UsersData;
import com.abmcloud.cf.test.pages.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

public class LoginSteps extends BaseSteps {

    public LoginSteps(Driver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        logs = new Logs(LoginSteps.class.getName());
    }


    @Step("Заходим на страницу логина")
    public LoginSteps open(String url) {
        driver.get(url);
        return this;
    }

    @Step("Попробовать залогинится")
    public LoginSteps login(String email, String pass) {
        try {
            loginPage.emailInput.sendKeys(email);
            loginPage.passwordInput.sendKeys(pass);
            loginPage.submitButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
        }
        return this;
    }

    @Step("Залогинится")
    public AppListSteps loginAs(UsersData user) {
        logs.infoMsg("Logining to CF system: " + user.getUserEmail()  + "; " + user.getUserPassword());
            login(user.getUserEmail(), user.getUserPassword());
        try {
            getWait().loginWait();
            BaseTest.activeUser = user;
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return  getAppListSteps();
    }

    @Step("Залогинится по кнопке Enter")
    public AppListSteps loginWithEnter(String email, String pass) {
        logs.infoMsg("Logining to CF system: " + email  + "; " + pass);
        try {
            loginPage.emailInput.sendKeys(email);
            loginPage.passwordInput.sendKeys(pass + Keys.ENTER);
            getWait().loginWait();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return  getAppListSteps();
    }

    @Step("Кликнуть на \"Забыл пароль\"")
    public LoginSteps forgotYourPasswordClick() {
        try {
            logs.infoMsg("Click on button: " + loginPage.forgotYourPassword.toString());
            loginPage.forgotYourPassword.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }
}