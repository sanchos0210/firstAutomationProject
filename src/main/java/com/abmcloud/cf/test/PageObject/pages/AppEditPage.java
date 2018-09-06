package com.abmcloud.cf.test.PageObject.pages;

import com.abmcloud.cf.test.Driver.BasePage;
import com.abmcloud.cf.test.Driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppEditPage extends BasePage {

    public AppEditPage(Driver driver) {
        super(driver);
    }

    @FindBy(css = ".btn.btn-xs.btn-success.pointer.line.line-add")
    public WebElement addNewLine;

    @FindBy(css = "desktop-buttons .no-border.pointer.option_btn .fa.fa-paperclip")
    public WebElement filesTab;

    @FindBy(css = ".ui-fileupload-choose.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-icon-left input")
    public WebElement addFileInput;

    @FindBy(css = ".ui-message.ui-messages-success")
    public WebElement addFileNotification;

    @FindBy(css = "popup-page h3")
            public WebElement editPopupTitle;

    @FindBy(xpath = "//*[contains(text(), 'Changes history')]")
            public WebElement changesHistory;

    @FindBy(xpath = "//div[@class='history']//p-accordiontab[last()]//*[@class='ui-accordion-toggle-icon fa fa-fw fa-caret-right']//parent::a")
            public WebElement viewsHistory;

    @FindBy(xpath = "//*[contains(text(), 'Approval steps')]")
            public WebElement approvalSteps;

    @FindBy(xpath = "//*[contains(text(), 'Amount')]//parent::legend")
            public WebElement amountHistory;

    @FindBy(css = ".absolute.pointer.slide_info.slide_info-left")
            public WebElement showInformationBlock;

    @FindBy(css = ".option_btn-save.pointer.no-border.save_standard")
            public WebElement saveButton;

    @FindBy(css = ".no-border.pointer.option_btn.option_btn-cancel")
            public WebElement cancelAppButton;

    @FindBy(css = ".no-border.pointer.option_btn.option_btn-approve")
            public WebElement approveAppButton;

    @FindBy(css = "date-field div.form-control.pointer.catalog-value.catalog-editable")
            public WebElement paymentDateField;

    @FindBy(xpath = "//bool-field[@ng-reflect-key='in_out']//bool-select-button//*[@class='pointer select_btn']")
            public WebElement inOutSwitch;

    @FindBy(css = "desktop-buttons .btn.btn-default.no-border.pointer.close_btn")
            public WebElement closeAppFormButton;

    @FindBy(css = ".btn.btn-danger.btn_accept.btn-xs.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")
            public WebElement yesGoOut;

    @FindBy(css = ".ui-button-primary.select_clear")
            public WebElement clearCatalogValueButton;

    @FindBy(css = ".pull-right .fa.fa-close.pointer")
            public WebElement closeCatalogPopupLocator;

    @FindBy(css = "select-catalog .modal_full-screen.over.overlay")
            public WebElement headerOfCatalogPopup;

    @FindBy(css = "#copy-link")
    public WebElement copyLinkButton;
}
