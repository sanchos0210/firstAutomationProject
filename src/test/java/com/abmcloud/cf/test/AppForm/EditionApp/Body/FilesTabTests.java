package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DBInfo.DataBaseInfo;
import com.abmcloud.cf.test.DBInfo.UsersData;
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
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(dbInfo.getJsonArray("required_fields"))
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .openFilesTab()
                .addFile("C:\\Users\\vera\\Desktop\\cashflow\\прикрепить файлы\\png-файл.png")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # png-файл.png uploaded successfully.");
    }
}