package com.abmcloud.cf.test.PageObject.pages;

import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogListPage extends BasePage {

    public By checkBoxLocator = By.cssSelector(("input[type = checkbox]"));
    public By editButtonLocator = By.cssSelector("span.action-bar a i.fa.fa-pencil-square-o");
    public By copyButtonLocator = By.cssSelector("span.action-bar a i.fa.fa-copy");


    public By catalogItems = By.cssSelector(".directory.pointer");

    @FindBy(css = ".fa.fa-trash")
    public WebElement deleteButton;

    public CatalogListPage(Driver driver) {
        super(driver);
    }
}
