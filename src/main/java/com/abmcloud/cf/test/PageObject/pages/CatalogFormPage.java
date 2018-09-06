package com.abmcloud.cf.test.PageObject.pages;

import com.abmcloud.cf.test.Driver.BasePage;
import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogFormPage extends BasePage {

    public CatalogFormPage(Driver driver) {
        super(driver);
    }

    @FindBy(css = ".modal-header.fixed_header h3")
    public WebElement popupTitle;

    @FindBy(css = ".no-border.pointer.option_btn.option_btn-save")
    public WebElement saveButton;
}
