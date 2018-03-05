package com.abmcloud.cf.test.pages;

import com.abmcloud.cf.test.architecture.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "inputEmail4")
            public WebElement emailInput;

    @FindBy(id = "inputPassword3")
            public WebElement passwordInput;

    @FindBy(id = "login_btn")
            public WebElement submitButton;

    @FindBy(css = ".msg.error")
            public WebElement errorMessage;

    @FindBy(css = "span.profile_name")
            public WebElement profileName;

    @FindBy(xpath = "//*[.='ABM cashflow']")
            public WebElement cashflowTitle;

    @FindBy(css = ".pointer.reset_pass_in")
            public WebElement forgotYourPassword;

    @FindBy(css = ".btn.btn-warning.btn-auth.pointer.reset_pass_submit")
            public WebElement changePasswordButton;

    public void loginWithSuccessful(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitButton.click();
        loginWait();
    }
}
