package com.abmcloud.cf.test.DBInfo;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.Fields.CatalogField;
import com.abmcloud.cf.test.Fields.DecimalField;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AppListDBInfo extends EditAppData {

    //fields
    public static final String articleFieldName = "Статья";
    public static final String sumFieldName = "Сумма";
    public static final String currencyFieldName = "Валюта";
    public static final String paymentDateField = "Дата оплаты";
    public static final String contractorFieldName = "Контрагент";
    public static final String agreementFieldName = "Договор";
    public static final String paymentTypeField = "Тип оплаты";
    public static final String descriptionFieldName = "Описание";

    //fields values
    public static String articleItem1 = "Статья 1";
    public static String sumFieldValue = "150";
    public static String contractorItem1 = "Контрагент 1";
    public static String contractorItem2 = "Контрагент 2";
    public static String agreementItem1 = "Договор 1";
    public static String descriptionFieldValue = "Описание";

    public AppField articleField = new AppField(CATALOG_FIELD, articleFieldName, articleItem1);
    public AppField sumField = new AppField(DECIMAL_FIELD, sumFieldName, sumFieldValue);
    public AppField contractorField = new AppField(CATALOG_FIELD, contractorFieldName, contractorItem1);
    public AppField agreementField = new AppField(CATALOG_FIELD, agreementFieldName, agreementItem1);
    public AppField descriptionField = new AppField(STRING_FIELD, descriptionFieldName, descriptionFieldValue);

    public List<AppField> fieldsToFilling = new ArrayList();

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
                sumFieldValue = val;
                break;
            }
            case 'B': {
                contractorItem1 = val;
                break;
            }
            case 'C': {
                sumFieldValue = "10000";
                contractorItem1 = "Контрагент 2";
                break;
            }
        }
    }

    public AppListDBInfo(char field) {
        this.driver = BaseTest.driver;
        PageFactory.initElements(this.driver, this);
        switch (field) {
            case 'C': {
                sumFieldValue = "10000";
                contractorItem1 = "Контрагент 2";
                break;
            }
            case 'D': {
                fieldsToFilling.add(articleField);
                fieldsToFilling.add(sumField);
                fieldsToFilling.add(contractorField);
                fieldsToFilling.add(agreementField);
                fieldsToFilling.add(descriptionField);
                BaseTest.fieldsToFill = fieldsToFilling;
            }
        }
    }

    public void createApp() {
        DecimalField decimalField = new DecimalField();
        CatalogField catalogField = new CatalogField();
        AppFormSteps appFormSteps = new AppFormSteps();
        appFormSteps.catalogElementClick(catalogField.getField(articleFieldName))
                .catalogElementClick(article1)
                .edit(decimalField.getField(sumFieldName), sumFieldValue)
                .catalogElementClick(catalogField.getField(contractorFieldName))
                .catalogElementClick(catalogField.getItem(contractorItem1))
                .catalogElementClick(catalogField.getField(agreementFieldName))
                .catalogElementClick(agreement1);
        appEditPage.saveButton.click();
    }
}