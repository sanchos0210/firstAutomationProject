package com.abmcloud.cf.test.DataInfo;

import com.abmcloud.cf.test.Fields.CatalogField;
import com.abmcloud.cf.test.Fields.DecimalField;
import com.abmcloud.cf.test.Fields.StringField;
import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppFormDBInfo extends EditAppData {

    public AppFormDBInfo() {
        this.driver = BaseTest.driver;
        PageFactory.initElements(this.driver, this);
    }
    //--------------------------------------------Menu buttons----------------------------------------------------------
    @FindBy(xpath = "//*[contains(text(), 'Prepare Payments 2')]")
    public WebElement preparePayments2;

    @FindBy(xpath = "//*[contains(text(), 'Prepare Payments 3')]")
    public WebElement preparePayments3;
    //-------------------------------------------Catalog field 3--------------------------------------------------------
    public String catalogField3 = "Catalog field 3";

    @FindBy(xpath = "//span[contains(text(), 'Value 1')]//parent::*//parent::*")
    public WebElement value1;

    @FindBy(xpath = "//span[contains(text(), 'Folder 1')]//parent::*//parent::*")
    public WebElement folder1;

    @FindBy(xpath = "//span[contains(text(), 'Value 2')]//parent::*//parent::*")
    public WebElement value2;

    @FindBy(xpath = "//span[contains(text(), 'Project1')]//parent::*//parent::*")
    public WebElement project1;

    @FindBy(xpath = "//span[contains(text(), 'Project2')]//parent::*//parent::*")
    public WebElement project2;
    //-------------------------------------------Catalog field 2--------------------------------------------------------
    public String catalogField2 = "Catalog field 2";

    @FindBy(xpath = "//span[contains(text(), 'Contractor 1')]//parent::*//parent::*")
    public WebElement contractor1;

    @FindBy(xpath = "//span[contains(text(), 'Contractor 2')]//parent::*//parent::*")
    public WebElement contractor2;
    //---------------------------------------Names of decimal fields----------------------------------------------------
    public String decimalField1 = "Decimal field 1";

    public String decimalField2 = "Decimal field 2";

    public String decimalField3 = "Decimal field 3";

    public String decimalField5 = "Decimal field 5";
    //--------------------------------------Names of catalog fields-----------------------------------------------------
    public String catalogField1 = "Catalog field 1";

    public String catalogField5 = "Catalog field 5";
    //--------------------------------------Names of string fields------------------------------------------------------
    public String stringField1 = "String field 1";

    public String stringField2 = "String field 2";

    public String stringField3 = "String field 3";

    public String stringField5 = "String field 5";

    public String booleanField = "Boolean field";

    public String booleanField1 = "Boolean field 1";

    public String booleanField2 = "Boolean field 2";

    public String booleanField3 = "Boolean field 3";

    public String booleanField4 = "Boolean field 4";

    public String dateField1 = "Date field 1";

    public String dateField2 = "Date field 2";

    public String dateField4 = "Date field 4";

    /*@FindBy(xpath = "//*[contains(text(), 'Date field 4')]//following::span")
    public WebElement dateField4;*/

    public void createApp() {
        DecimalField decimalField = new DecimalField();
        StringField str = new StringField();
        CatalogField catalog = new CatalogField();
        AppFormSteps appFormSteps = new AppFormSteps();
        appFormSteps.catalogElementClick(catalog.getField(catalogField3))
                .catalogElementClick(value1)
                .edit(decimalField.getField("Decimal field 3"),"150")
                .edit(str.getField(stringField3),"text2");
        appEditPage.saveButton.click();
    }

    //---------------------------------------------Prepare payments 2---------------------------------------------------
}
