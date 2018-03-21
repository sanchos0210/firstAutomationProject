package com.abmcloud.cf.test.DataInfo;

import com.abmcloud.cf.test.API.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditAppDataProline extends EditAppData {

    public static String amount = "10.000.000";
    public static String paymentDate = "";
    public static String tomorrowDate = "";
    public static String clientName = "James Statham";
    public static String contactPerson = "Jack Nicholson";
    public static String contactPersonNumber = "0659852354";
    public static String sukuBunga = "44";
    public static String jangkaWaktu = "2";
    public static String massaFix = "220";
    public static String angsuranBulanan = "1000000000";
    public static String provisi = "44";
    public static String biayaAdministrasi = "20000";

    public EditAppDataProline() {
        this.driver = BaseTest.driver;
        PageFactory.initElements(this.driver, this);
    }

    //-------------------------------------boolean button in header of popup--------------------------------------------

    @FindBy(css = "popup-header bool-field")
    public WebElement headersBooleanButton;

    @FindBy(css = "popup-header bool-field input")
    public WebElement disabledHeadersBooleanButton;

    //-----------------------------------------fields in popup----------------------------------------------------------
    @FindBy(xpath = "//label[contains(text(), 'Amount')]/following-sibling::*/input")
    public WebElement amountField;

    @FindBy(xpath = "//label[contains(text(), 'Currency')]/following-sibling::*")
    public WebElement currency;

    @FindBy(xpath = "//*[contains(text(), 'Application Channel')]//following-sibling::*/*/*/*[@class='d-inline-block pointer select_btn']")
    public WebElement applicationChannelButton;


    @FindBy(xpath = "//label[contains(text(), 'Client name')]/following-sibling::*/textarea")
    public WebElement clientNameField;

    @FindBy(xpath = "//label[contains(text(), 'Contact Person')]/following-sibling::*/textarea")
    public WebElement contactPersonField;

    @FindBy(xpath = "//label[contains(text(), 'Contact Person Number')]/following-sibling::*/textarea")
    public WebElement contactPersonNumberField;

    @FindBy(xpath = "//label[contains(text(), 'Suku Bunga, %')]/following-sibling::*/input")
    public WebElement sukuBungaField;

    @FindBy(xpath = "//label[contains(text(), 'Jangka Waktu, bulan')]/following-sibling::*/input")
    public WebElement jangkaWaktuField;

    @FindBy(xpath = "//label[contains(text(), 'Masa Fix, bulan')]/following-sibling::*/input")
    public WebElement massaFixField;

    @FindBy(xpath = "//label[contains(text(), 'Angsuran Bulanan, IDR')]/following-sibling::*/input")
    public WebElement angsuranBulananField;

    @FindBy(xpath = "//label[contains(text(), 'Provisi, %')]/following-sibling::*/input")
    public WebElement provisiField;

    @FindBy(xpath = "//label[contains(text(), 'Model Cicilan')]/following-sibling::*")
    public WebElement modelCicilan;

    @FindBy(xpath = "//directory//*[contains(text(), 'pokok+bunga')]")
    public WebElement pokokBunga;

    @FindBy(xpath = "//label[contains(text(), 'Tipe Jaminan Utama')]/following-sibling::*")
    public WebElement tipeJaminanUtama;

    @FindBy(xpath = "//directory//*[contains(text(), 'Gudang')]")
    public WebElement gudang;

    @FindBy(xpath = "//label[contains(text(), 'Tipe Sertifikat Jaminan Utama')]/following-sibling::*")
    public WebElement tipeSertifikatJaminanUtama;

    @FindBy(xpath = "//directory//*[contains(text(), 'HPL')]")
    public WebElement hpl;

    @FindBy(xpath = "//label[contains(text(), 'Biaya Administrasi, IDR')]/following-sibling::*/input")
    public WebElement biayaAdministrasiField;

    @FindBy(xpath = "//label[contains(text(), 'Harga Jaminan Utama, IDR')]/following-sibling::*/*")
    public WebElement hargaJaminanUtama;

    //---------------------------------------Proline menu buttons-------------------------------------------------------
    @FindBy(xpath = "//*[contains(text(), 'Deposits Business')]")
    public WebElement depositsBusinessMenuButton;

    @FindBy(xpath = "//*[contains(text(), 'Pencairan Deposit')]")
    public WebElement pencairanDepositMenuButton;

    @FindBy(xpath = "//*[contains(text(), 'Loan Business')]")
    public WebElement loanBusinessMenuButton;

    @FindBy(xpath = "//*[contains(text(), 'Expense')]")
    public WebElement expenseMenuButton;
    //------------------------------------------------------------------------------------------------------------------
    public void createDepositBusinessApp() {
//        typeRequiredFieldsForLoanBusiness();
        typeRequiredFieldsForDeposits(amount, clientName, contactPerson, contactPersonNumber, sukuBunga);
        appEditPage.saveButton.click();
    }

    public void typeRequiredFieldsForDeposits(String sumStr, String clientNameStr, String contactPersonStr, String contactPersonNumberStr, String sukuBungaStr) {
        amountField.sendKeys(sumStr);
        clientNameField.sendKeys(clientNameStr);
        contactPersonField.sendKeys(contactPersonStr);
        contactPersonNumberField.sendKeys(contactPersonNumberStr);
        sukuBungaField.sendKeys(sukuBungaStr);

    }

    public void typeRequiredFieldsForLoanBusiness() {
        typeRequiredFieldsForDeposits(amount, clientName, contactPerson, contactPersonNumber, sukuBunga);
        jangkaWaktuField.sendKeys(jangkaWaktu);
        massaFixField.sendKeys(massaFix);
        angsuranBulananField.sendKeys(angsuranBulanan);
        provisiField.sendKeys(provisi);
        modelCicilan.click();
        pokokBunga.click();
        biayaAdministrasiField.sendKeys(biayaAdministrasi);
        hargaJaminanUtama.sendKeys("110000");
        tipeJaminanUtama.click();
        gudang.click();
        tipeSertifikatJaminanUtama.click();
        hpl.click();
    }

    public void createApp() {
        createDepositBusinessApp();
    }
}
