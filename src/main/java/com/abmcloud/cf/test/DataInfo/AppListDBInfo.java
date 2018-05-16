package com.abmcloud.cf.test.DataInfo;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.Fields.CatalogField;
import com.abmcloud.cf.test.Fields.DecimalField;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppListDBInfo extends EditAppData {

    public String article = "Статья";
    public String sumField = "Сумма";
    public String sum = "100";
    public String currency = "Валюта";
    public String contractor = "Контрагент";
    public String contractorItem = "Контрагент 1";
    public String agreement = "Договор";

    public String[][] chain1 = {{"Документ создан", null},{"Автор", null},{"Первый", "Второй"},{"Бухгалтер", null}};

    public String[][] chain2 = {{"Документ создан", null},{"Вместе", null},{"Оплачено", null}};

    public String[][] chain3 = {{"Документ создан", null},{"Казначей", null}};

    @FindBy(xpath = "//span[contains(text(), 'Статья 1')]//parent::*//parent::*")
    public WebElement article1;

    @FindBy(xpath = "//span[contains(text(), 'Договор 1')]//parent::*//parent::*")
    public WebElement agreement1;

    public AppListDBInfo() {
        this.driver = BaseTest.driver;
        PageFactory.initElements(this.driver, this);
    }

    public AppListDBInfo(char field, String val) {
        this.driver = BaseTest.driver;
        PageFactory.initElements(this.driver, this);
        switch(field) {
            case 'A': {
                sum = val;
                break;
            }
            case 'B': {
                contractorItem = val;
                break;
            }
            case 'C': {
                sum = "10000";
                contractorItem = "Контрагент 2";
            }
        }
    }

    public void createApp() {
        DecimalField decimalField = new DecimalField();
        CatalogField catalogField = new CatalogField();
        AppFormSteps appFormSteps = new AppFormSteps();
        appFormSteps.catalogElementClick(catalogField.getField(article))
                .catalogElementClick(article1)
                .edit(decimalField.getField(sumField), sum)
                .catalogElementClick(catalogField.getField(contractor))
                .catalogElementClick(catalogField.getItem(contractorItem))
                .catalogElementClick(catalogField.getField(agreement))
                .catalogElementClick(agreement1);
        appEditPage.saveButton.click();
    }
}