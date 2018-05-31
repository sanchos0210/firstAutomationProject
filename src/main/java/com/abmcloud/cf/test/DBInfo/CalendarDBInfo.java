package com.abmcloud.cf.test.DBInfo;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.Fields.CatalogField;
import com.abmcloud.cf.test.Fields.DecimalField;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarDBInfo extends EditAppData {

    public String article = "Статья";
    public String sumField = "Сумма";
    public String sum = "1000";
    public String currency = "Валюта";
    public String contractor = "Контрагент";
    public String contractorItem = "Контрагент 1";
    public String agreement = "Договор";

    @FindBy(xpath = "//span[contains(text(), 'Статья 1')]//parent::*//parent::*")
    public WebElement article1;

    @FindBy(xpath = "//span[contains(text(), 'Договор 1')]//parent::*//parent::*")
    public WebElement agreement1;

    public CalendarDBInfo() {
        this.driver = BaseTest.driver;
        PageFactory.initElements(this.driver, this);
    }

    //--------------------------------------------Menu buttons----------------------------------------------------------
    @FindBy(xpath = "//*[contains(text(), 'Оплата')]")
    public WebElement calendarMenuButton;

    //------------------------------------------------------------------------------------------------------------------
    public void createApp() {
        DecimalField decimalField = new DecimalField();
        CatalogField catalog = new CatalogField();
        AppFormSteps appFormSteps = new AppFormSteps();
        appFormSteps.catalogElementClick(catalog.getField(article))
                .catalogElementClick(article1)
                .edit(decimalField.getField(sumField), sum)
                .catalogElementClick(catalog.getField(contractor))
                .catalogElementClick(catalog.getItem(contractorItem))
                .catalogElementClick(catalog.getField(agreement))
                .catalogElementClick(agreement1);
        appEditPage.saveButton.click();
    }
}
