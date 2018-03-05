package com.abmcloud.cf.test.ProlineTests;

import com.abmcloud.cf.test.architecture.BaseTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class LoginTests extends BaseTest {

    String invalidEmail = "indonesiashflow@gmail.com";
    String invalidPassword = "testprone";
    String emailFromUser1 = "indonesia.cashflow@gmail.com";
    String passwordFromUser2 = "testproline3";

    @Test(priority = 1)
    public void loginWithInvalidEmail() {
        steps
                .open(TEST_PROLINE)
                .login(invalidEmail, PASSWORD);
        verificationThat(textToBePresentInElement((loginPage.errorMessage), "Неправильный адрес электронной почты"));
    }

    @Test(priority = 2)
    public void loginWithInvalidPassword() {
        steps
                .open(TEST_PROLINE)
                .login(EMAIL, invalidPassword);
        verificationThat(textToBePresentInElement((loginPage.errorMessage), "Неправильный адрес электронной почты"));
    }

    @Test(priority = 3)
    public void loginWithEmailAndLoginFromDifferentUsers() {
        steps
                .open(TEST_PROLINE)
                .login(emailFromUser1, passwordFromUser2);
        verificationThat(textToBePresentInElement((loginPage.errorMessage), "Неправильный адрес электронной почты"));
    }

    @Test(priority = 4)
    public void loginWithSuccessful() {
        steps
                .open(TEST_PROLINE)
                .loginWithSuccessful(EMAIL, PASSWORD);
        verificationThat(textToBePresentInElement((loginPage.profileName), USER));
    }

    @Test(priority = 5)
    public void loginWithEnter() {
        steps
                .open(TEST_PROLINE)
                .loginWithEnter(EMAIL, PASSWORD);
        verificationThat(textToBePresentInElement((loginPage.profileName), USER));
    }

    @Test(priority = 6)
    public void loginAndLogOut() {
        steps
                .open(TEST_PROLINE)
                .loginWithSuccessful(EMAIL, PASSWORD);
        appListPage.avatar.click();
        appListPage.signOutButton.click();
        verificationThat(textToBePresentInElement((loginPage.cashflowTitle), "ABM cashflow"));
    }

    @Test(priority = 7)
    public void checkForgotPasswordButton() {
        steps
                .open(TEST_PROLINE)
                .forgotYourPasswordClick();
        verificationThat(textToBePresentInElement((loginPage.changePasswordButton), "Изменить пароль"));
    }
}