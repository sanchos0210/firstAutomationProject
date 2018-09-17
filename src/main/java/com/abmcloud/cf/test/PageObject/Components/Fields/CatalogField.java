package com.abmcloud.cf.test.PageObject.Components.Fields;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public class CatalogField extends BaseField {

    private String nameOfActiveCatalogField;
    private By choseFolderLocator = By.cssSelector(".btn-xs.btn-select");
    private By elementsInModalList = By.cssSelector("modalcataloglist .directory.pointer");
    private By textOfElementInList = By.cssSelector("span.catalog_folder");

    public CatalogField(Driver driver, ObjectManager objectManager) {
        super(driver, objectManager);
    }

    public WebElement getField(String nameOfField) {
        WebElement catalogField = driver.fluentWait(By.xpath("//catalog-field[contains(@ng-reflect-title, '" + nameOfField +"')]"));
        return catalogField;
    }

    public String getValue(String nameOfField) {
        int i = 0;
        WebElement selectedValue;
        do {
            selectedValue = getField(nameOfField).findElement(By.cssSelector("a"));
            i++;
        } while(selectedValue.getText() == null || i == 500);
        logs.warning("VALUE OF CATALOG FIELD: " + selectedValue.getText());
        if(selectedValue.getText() == null) {
            logs.warning("Value of catalog " + nameOfField + " was not found!");
        }
        return selectedValue.getText();
    }

    public WebElement getItem(String nameOfItem) {
        WebElement item = driver.$(By.xpath("//directoryelement//span[contains(text(), '" + nameOfItem + "')]//parent::*//parent::*"));
        return item;
    }

    public void catalogFieldClick(String nameOfField) {
        nameOfActiveCatalogField = nameOfField;
        WebElement catalogField = getField(nameOfField);
        try {
            catalogField.click();
        } catch (RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    public void catalogElementClick(String nameOfItem) {
        WebElement catalogElement = getItem(nameOfItem);
        try {
            catalogElement.click();
            WebElement activeCatalogField = getField(nameOfActiveCatalogField);
            objectManager.getWait().waitForAttributeContains(activeCatalogField, "ng-reflect-selected", nameOfItem);
        } catch (RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    public void catalogFolderClick(String nameOfFolder) {
        WebElement element = getItem(nameOfFolder);
        try {
            element.click();
        } catch (RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    public void clearCatalogValue(String nameOfField) {
        WebElement catalogField = getField(nameOfField);
        try {
            catalogField.click();
            objectManager.getWait().waitForElementClickable(4, objectManager.getAppEditPage().clearCatalogValueButton);
            objectManager.getAppEditPage().clearCatalogValueButton.click();
            objectManager.getAppEditPage().closeCatalogPopupLocator.click();
        } catch(RuntimeException e) {
            logs.errorMsg(e);
            throw e;
        }
    }

    public void chooseFolder(String nameOfFolder) {
        List<WebElement> folders = driver.$$(elementsInModalList);
        for (WebElement folder : folders) {
            String nameOfCurrentFolder = folder.findElement(textOfElementInList).getText();
            if (nameOfFolder.equals(nameOfCurrentFolder)) {
                Actions action = new Actions(objectManager.getDriver().getWebDriver());
                action.moveToElement(folder).build().perform();
                folder.findElement(choseFolderLocator).click();
                break;
            }
        }
        WebElement activeCatalogField = getField(nameOfActiveCatalogField);
        objectManager.getWait().waitForAttributeContains(activeCatalogField, "ng-reflect-selected", nameOfFolder);
    }

    public boolean isItemPresent(String nameOfItem) {
        try {
            WebElement item = getItem(nameOfItem);
            item.click();
            return true;
        } catch (TimeoutException e) {
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}