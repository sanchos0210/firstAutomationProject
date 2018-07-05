package com.abmcloud.cf.test.API;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public Driver driver;

   public BasePage(Driver driver) {
        this.driver = driver;
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    @FindBy(css = "div.sn-content")
    public WebElement applSavedNotification;

    @FindBy(css = ".profile-toggle-link.dropdown-toggle")
    public WebElement avatar;

    @FindBy(css = "ul.dropdown-menu.top-dropdown-menu.profile-dropdown li.dropdown-item span.signout")
    public WebElement signOutButton;

    //---------------------------------------------------Date picker----------------------------------------------------
    @FindBy(css = "[name='daterangeInput'] *")
    public WebElement periodButton;

    @FindBy(xpath = "//*[@class='daterangepicker dropdown-menu ltr show-calendar opensleft'][last()]//li[contains(text(), 'Сегодня')]")
    public WebElement datePickerToday;

    @FindBy(xpath = "//*[@class='daterangepicker dropdown-menu ltr show-calendar opensleft'][last()]//li[contains(text(), 'Этот месяц')]")
    public WebElement datePickerThisMonth;

    @FindBy(xpath = "//*[@class='daterangepicker dropdown-menu ltr show-calendar opensleft'][last()]//li[contains(text(), 'Этот год')]")
    public WebElement datePickerThisYear;

    @FindBy(xpath = "//*[@class='daterangepicker dropdown-menu ltr show-calendar opensleft'][last()]//li[contains(text(), 'Прошлый месяц')]")
    public WebElement datePickerLastMonth;

    @FindBy(xpath = "//*[@class='daterangepicker dropdown-menu ltr show-calendar opensleft'][last()]//li[contains(text(), 'Прошлый год')]")
    public WebElement datePickerLastYear;
}