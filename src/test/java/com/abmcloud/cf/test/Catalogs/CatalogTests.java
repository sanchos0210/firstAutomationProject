package com.abmcloud.cf.test.Catalogs;

import com.abmcloud.cf.test.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.RU;

@Epic("Создание и удаление каталогов")
@Feature("Каталоги")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class CatalogTests extends BaseTest {

//    DataBaseInfo dbInfo;
//
//    @BeforeMethod
//    public void getDataForTest() {
//        dbInfo = new DataBaseInfo("app_list_db.json");
//    }

    @Test(priority = 1)
    public void elementVisibility() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .asserts()
                .isElementPresent(objectManager.getAppListPage().addNewButton, true)
                .isElementPresent(objectManager.getCatalogListPage().deleteButton, true)
                .isElementPresent(objectManager.getCatalogListPage().checkBoxLocator, true);
    }

    @Test(priority = 2)
    public void elementVisibilityInNewPopup() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .addNewClick()
                .asserts()
                .assertTextInElement(objectManager.getCatalogFormPage().popupTitle, "Добавление нового элемента справочника \"Организация\"");
    }

    @Test(priority = 10)
    public void createAndDeleteAFolder() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .addNewClick()
                .editStringField("Наименование", "Новая папочка")
                .booleanButtonClick("Тип элемента")
                .saveCatalogElement()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Элемент справочника успешно сохранен.")
                .getCatalogListSteps()
                .check("Новая папочка")
                .deleteButtonClick()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Выбранные элементы каталога \"Организация\" успешно удалены.");
    }

    public void createFolderInFolder() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openFolder("Другие организации")
                .addNewClick()
                .editStringField("Наименование", "Совсем другие организации")
                .booleanButtonClick("Тип элемента")
                .catalogFieldClick("Папка")
                .choseFolder("Другие организации")
                .saveCatalogElement()
                .openEditFormOf("Совсем другие организации");
    }

    @Test(priority = 15)
    public void createFolderInFolderTest() {
        createFolderInFolder();
        //Need to delete created folder after test
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openFolder("Другие организации")
                .check("Совсем другие организации")
                .deleteButtonClick()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Выбранные элементы каталога \"Организация\" успешно удалены.");
    }

    public void createItem() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .addNewClick()
                .editStringField("Наименование", "Новая организация")
                .saveCatalogElement()
                .openEditFormOf("Новая организация");
    }

    @Test(priority = 20)
    public void createItemTest() {
        createItem();
        //Need to delete created item after test
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .check("Новая организация")
                .deleteButtonClick()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Выбранные элементы каталога \"Организация\" успешно удалены.");
    }

    public void createItemInFolder() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openFolder("Другие организации")
                .addNewClick()
                .editStringField("Наименование", "Еще одна новая организация")
                .catalogFieldClick("Папка")
                .choseFolder("Другие организации")
                .saveCatalogElement()
                .openEditFormOf("Еще одна новая организация");
    }

    @Test(priority = 25)
    public void createItemInFolderTest() {
        createItemInFolder();
        //Need to delete created item after test
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openFolder("Другие организации")
                .check("Еще одна новая организация")
                .deleteButtonClick()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Выбранные элементы каталога \"Организация\" успешно удалены.");
    }

    public void copyFolder() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openCopyFormOf("Другие организации")
                .editStringField("Наименование", "Любимые организации")
                .saveCatalogElement()
                .selectByName("Любимые организации")
                .asserts()
                .compare(objectManager.getTestInfo().textOfNotification, "Элемент справочника успешно сохранен.")
                .isFolderEmpty(testInfo.selectedCatalogItem, true);
    }

    @Test(priority = 30)
    public void copyFolderTest() {
        copyFolder();
        //Need to delete created folder after test
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .check("Любимые организации")
                .deleteButtonClick()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Выбранные элементы каталога \"Организация\" успешно удалены.");
    }

    public void choseOrganizationForDepartment() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openCatalogList("Подразделение")
                .addNewClick()
                .editStringField("Наименование", "Подразделение 2")
                .catalogFieldClick("Организация")
                .catalogElementClick("Организация 2")
                .saveCatalogElement()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Элемент справочника успешно сохранен.");
    }

    @Test(priority = 35)
    public void choseOrganizationForDepartmentTest() {
        choseOrganizationForDepartment();
        //Need to delete created item after test
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openCatalogList("Подразделение")
                .check("Подразделение 2")
                .deleteButtonClick()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Выбранные элементы каталога \"Подразделение\" успешно удалены.");
    }

    public void copyFolderInFolder() {
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openFolder("Коммерческие")
                .openCopyFormOf("Государственные")
                .editStringField("Наименование", "Частные")
                .saveCatalogElement()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Элемент справочника успешно сохранен.");
    }

    @Test(priority = 40)
    public void copyFolderInFolderTest() {
        copyFolderInFolder();
        //Need to delete created folder
        steps
                .open(APP_LIST_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, RU)
                .openCatalogs()
                .openFolder("Коммерческие")
                .check("Частные")
                .deleteButtonClick()
                .asserts().compare(objectManager.getTestInfo().textOfNotification, "Выбранные элементы каталога \"Организация\" успешно удалены.");
    }
}