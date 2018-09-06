package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.Driver;
import com.abmcloud.cf.test.Driver.ObjectManager;
import com.abmcloud.cf.test.PageObject.Components.Confirmation;
import com.abmcloud.cf.test.PageObject.Components.Notification;
import com.abmcloud.cf.test.PageObject.pages.CatalogListPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CatalogListSteps extends MenuSteps {

    /**
     * Components */
    private CatalogListPage catalogListPage;
    private Notification notification;
    private Confirmation confirmation;

    protected WebElement selectedElement;

    public CatalogListSteps(Driver driver, ObjectManager objectManager) {
        this.driver = driver;
        this.objectManager = objectManager;
        catalogListPage = objectManager.getCatalogListPage();
        notification = objectManager.getNotification();
        confirmation = objectManager.getConfirmation();
    }

    @Step("Выбрать элемент каталога в списке: {name}")
    public CatalogListSteps selectByName(String name) {
        By textOfElementLocator = By.cssSelector("directoryelement span");
        List<WebElement> listOfElements = driver.$$(".directory.pointer");
        for(WebElement element: listOfElements) {
            String nameOfElement = element.findElement(textOfElementLocator).getText();
            if (nameOfElement.equals(name)) {
                selectedElement = element;
                objectManager.getTestInfo().selectedCatalogItem = element;
                break;
            }
        }
        return this;
    }

    protected void hoverElement(WebElement element) {
        Actions action = new Actions(objectManager.getDriver().getWebDriver());
        action.moveToElement(element).build().perform();
    }

    @Step("Открыть форму редактирования: {nameOfElement} ")
    public CatalogListSteps openEditFormOf(String nameOfElement) {
        selectByName(nameOfElement);
        hoverElement(selectedElement);
        selectedElement.findElement(objectManager.getCatalogListPage().editButtonLocator).click();
        objectManager.getWait().waitForClickable(3, By.cssSelector("catalogcrudpopup h3"));
        return this;
    }

    @Step("Открыть форму копирования элемента: {nameOfElement} ")
    public CatalogFormSteps openCopyFormOf(String nameOfElement) {
        selectByName(nameOfElement);
        hoverElement(selectedElement);
        selectedElement.findElement(objectManager.getCatalogListPage().copyButtonLocator).click();
        objectManager.getWait().waitForClickable(3, By.cssSelector("catalogcrudpopup h3"));
        return objectManager.getCatalogFormSteps();
    }

    @Step("Отметить чек-бокс элемента: {nameOfFolder} ")
    public CatalogListSteps check(String nameOfFolder) {
        selectByName(nameOfFolder);
        selectedElement.findElement(catalogListPage.checkBoxLocator).click();
        return this;
    }

    @Step("Кликнуть на кнопку \"Создать новую\"")
    public CatalogFormSteps addNewClick() {
        objectManager.getAppListPage().addNewButton.click();
        objectManager.getWait().waitForElementClickable(3, objectManager.getCatalogFormPage().popupTitle);
        return objectManager.getCatalogFormSteps();
    }

    @Step("Кликнуть на кнопку \"Удалить\"")
    public CatalogListSteps deleteButtonClick() {
        objectManager.getCatalogListPage().deleteButton.click();
        confirmation.clickOnRedButton();
        notification.saveTextOfNotification();
        notification.notificationClick();
        return this;
    }

    @Step("Открыть папку {nameOfFolder}")
    public CatalogListSteps openFolder(String nameOfFolder) {
        selectByName(nameOfFolder);
        selectedElement.click();
        objectManager.getWait().catalogPreloadWait();
        return this;
    }

    public CatalogListSteps openCatalogList(String nameOfCatalog) {
        driver.fluentWait(By.xpath("//span[contains(text(), '"+ nameOfCatalog +"')]")).click();
        objectManager.getWait().catalogPreloadWait();
        return this;
    }
}
