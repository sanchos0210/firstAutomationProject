package com.abmcloud.cf.test.ProlineTests;

import com.abmcloud.cf.test.API.BaseTest;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    String invalidEmail = "indonesiashflow@gmail.com";
    String invalidPassword = "testprone";
    String emailFromUser1 = "indonesia.cashflow@gmail.com";
    String passwordFromUser2 = "testproline3";

    @Test(priority = 1)
    public void loginWithInvalidEmail() {
        steps
                .open(APP_FORM_DEMO_DB)
                .login(invalidEmail, PASSWORD)
                .asserts().assertTextInElement(loginPage.errorMessage, "Неправильный адрес электронной почты");
    }

    @Test(priority = 2)
    public void loginWithInvalidPassword() {
        steps
                .open(APP_FORM_DEMO_DB)
                .login(EMAIL, invalidPassword)
                .asserts().assertTextInElement(loginPage.errorMessage, "Неправильный адрес электронной почты");
    }

    @Test(priority = 3)
    public void loginWithEmailAndLoginFromDifferentUsers() {
        steps
                .open(APP_FORM_DEMO_DB)
                .login(emailFromUser1, passwordFromUser2)
                .asserts().assertTextInElement(loginPage.errorMessage, "Неправильный адрес электронной почты");
    }

    @Test(priority = 4)
    public void loginWithSuccessful() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginWithSuccessful(EMAIL, PASSWORD)
                .asserts().assertTextInElement(loginPage.profileName, USER);
    }

    @Test(priority = 5)
    public void loginWithEnter() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginWithEnter(EMAIL, PASSWORD)
                .asserts().assertTextInElement(loginPage.profileName, USER);
    }

    @Test(priority = 6)
    public void loginAndLogOut() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginWithSuccessful(EMAIL, PASSWORD)
                .logOut()
                .asserts().assertTextInElement(loginPage.cashflowTitle, "ABM cashflow");
    }

    @Test(priority = 7)
    public void checkForgotPasswordButton() {
        steps
                .open(APP_FORM_DEMO_DB)
                .forgotYourPasswordClick()
                .asserts().assertTextInElement(loginPage.changePasswordButton, "Изменить пароль");
    }
}