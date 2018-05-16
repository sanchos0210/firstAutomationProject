package com.abmcloud.cf.test.AppForm.EditionApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import com.abmcloud.cf.test.DataInfo.AppFormDBInfo;
import com.abmcloud.cf.test.DataInfo.UsersData;
import org.testng.annotations.Test;

public class FilesTabTests extends BaseTest {

    @Test
    public void addFileInEditPopup() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(new AppFormDBInfo())
                .selectAppByNumber(numberOfCreatedApp)
                .clickOnNumberOf(selectedApp)
                .openFilesTab()
                .addFile("C:\\Users\\vera\\Desktop\\cashflow\\прикрепить файлы\\png-файл.png")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # png-файл.png uploaded successfully.");
    }
}
