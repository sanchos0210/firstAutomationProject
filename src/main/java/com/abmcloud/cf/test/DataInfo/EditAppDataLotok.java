package com.abmcloud.cf.test.DataInfo;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditAppDataLotok extends EditAppData {

    public static String summ = "5000";
    public static String accountsSumm;

    public EditAppDataLotok() {
        this.driver = BaseTest.driver;
        PageFactory.initElements(this.driver, this);
    }

    //---------------------------------Boolean InOut locators-----------------------------------------------------------
    @FindBy(xpath = "//*[.='Надходження']//parent::*")
    public String incomeBooleanButton;
    //------------------------------------------------------------------------------------------------------------------

    @FindBy(xpath = "//label[contains(text(), 'Сума до сплати')]/following-sibling::*/input")
    public WebElement summField;

    @FindBy(xpath = "//label[contains(text(), 'Сума рахунку')]/following-sibling::*/input")
    public WebElement accountsSummField;

    @FindBy(css = "directory directoryelement")     //first result in catalog popup
    public WebElement firstResult;

    @FindBy(css = "description-field[ng-reflect-title='Назва банку'] textarea")
    public WebElement banksNames;

    @FindBy(css = "date-field[ng-reflect-title='та дата договору']")
    public WebElement dateOfAgreement;

    //-----------------------------------------Article elements---------------------------------------------------------
    @FindBy(xpath = "//label[contains(text(), 'Стаття')]/following-sibling::*")
    public WebElement article;

    @FindBy(xpath = "//directory//*[contains(text(), 'Доход')]")
    public WebElement incomeFolder;

    @FindBy(xpath = "//span[contains(text(), 'Вiдсотки банку')]//parent::*//parent::*")
    public WebElement banksPerCent;
    //-------------------------------------------Contractor locators----------------------------------------------------

    @FindBy(xpath = "//label[contains(text(), 'Контрагент')]/following-sibling::*")
    public WebElement contractor;

    @FindBy(xpath = "//span[contains(text(), 'ПОСТАЧАЛЬНИКИ')]//parent::*//parent::*")
    public WebElement supliersFolder;
    //------------------------------------------------------------------------------------------------------------------

    @FindBy(xpath = "//label[contains(text(), 'Розрахунковий рахунок')]/following-sibling::*")
    public WebElement calcAccount;

    @FindBy(xpath = "//label[contains(text(), 'Договір')]/following-sibling::*")
    public WebElement agreement;

    @FindBy(css = "decimal-field[ng-reflect-title='МФО'] input")
    public WebElement mfoField;

    public void createApp() {
        AppFormSteps appFormSteps = new AppFormSteps();
        article.click();        //click on article catalog field
        appFormSteps.catalogElementClick(incomeFolder)   //click on folder "Income"
                    .catalogElementClick(banksPerCent);    //now we already choose article for application
        summField.sendKeys("5000");
        accountsSummField.sendKeys("5243");
        contractor.click();
        appFormSteps.catalogElementClick(supliersFolder)
                    .catalogElementClick(firstResult);        //now we already choose contractor for application
        calcAccount.click();
        appFormSteps.catalogElementClick(firstResult);        //now we already choose calculation account for application
        agreement.click();
        appFormSteps.catalogElementClick(firstResult);        //now we already choose agreement account for application
        appEditPage.saveButton.click();
    }
}
