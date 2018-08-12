package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FilesTabTests extends BaseTest {

    DataBaseInfo dbInfo;

    @BeforeMethod
    public void objectCreation() {
        dbInfo = new DataBaseInfo("app_form_db.json");
    }

    @Test
    public void addFileInEditPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .openFilesTab()
                .addFile("testData\\png-файл.png")
                .asserts().assertTextInElement(objectManager.getAppEditPage().addFileNotification, "File # png-файл.png uploaded successfully.");
    }
}