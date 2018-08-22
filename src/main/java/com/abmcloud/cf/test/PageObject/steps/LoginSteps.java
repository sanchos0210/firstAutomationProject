package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.Logs;
import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.Utils.UsersData;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

public class LoginSteps extends BaseSteps {

    public LoginSteps(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
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
            objectManager.getLoginPage().emailInput.sendKeys(email);
            objectManager.getLoginPage().passwordInput.sendKeys(pass);
            objectManager.getLoginPage().submitButton.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
        }
        return this;
    }

    @Step("Залогинится")
    public AppListSteps loginAs(String name, String email, String pass, char lang) {
        UsersData user = new UsersData(name, email, pass, lang);
        logs.infoMsg("Logining to CF system: " + user.getUserEmail()  + "; " + user.getUserPassword());
            login(user.getUserEmail(), user.getUserPassword());
        try {
            getWait().loginWait();
            objectManager.getTestInfo().activeUser = user;
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
            objectManager.getLoginPage().emailInput.sendKeys(email);
            objectManager.getLoginPage().passwordInput.sendKeys(pass + Keys.ENTER);
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
            logs.infoMsg("Click on button: " + objectManager.getLoginPage().forgotYourPassword.toString());
            objectManager.getLoginPage().forgotYourPassword.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
        return this;
    }
}