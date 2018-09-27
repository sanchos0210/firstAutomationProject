package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Utils.DataBaseInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.abmcloud.cf.test.Driver.Constants.EN;

@Epic("Прикрепление файлов к заявке в списке заявок")
@Feature("Форма заявки")
@Listeners(com.abmcloud.cf.test.Listeners.TestListener.class)
public class FilesTabTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test
    public void addFileInEditPopup() {
        steps
                .open(APP_FORM_COMPANY_URL)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(testInfo.numberOfCreatedApp)
                .clickOnNumberOf(testInfo.selectedApp)
                .openFilesTab()
                .addFile("testData\\png-файл.png")
                .asserts().assertTextInElement(objectManager.getAppEditPage().addFileNotification, "File # png-файл.png uploaded successfully.");
    }
}