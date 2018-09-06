package com.abmcloud.cf.test.PageObject.Components;

import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterOfGroups {

    @FindBy(css = ".fa.fa-filter")
    public WebElement filterButton;

    public FilterOfGroups(Driver driver) {
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void openFilters() {
        filterButton.click();
    }
}
