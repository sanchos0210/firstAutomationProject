package com.abmcloud.cf.test.AppForm.CreationApp.Body;

import com.abmcloud.cf.test.API.BaseTest;
import org.testng.annotations.Test;

public class FilesTab extends BaseTest {

    @Test(priority = 1)
    public void addJPEGFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\jpg-файл.jpg")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # jpg-файл.jpg uploaded successfully.");
    }

    @Test(priority = 3)
    public void addGIFFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\gif-файл.gif")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # gif-файл.gif uploaded successfully.");
    }

    @Test(priority = 4)
    public void addDOCFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\doc-файл.doc")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # doc-файл.doc uploaded successfully.");
    }

    @Test(priority = 5)
    public void addDOCXFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\docx-файл.docx")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # docx-файл.docx uploaded successfully.");
    }

    @Test(priority = 6)
    public void addPDFFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\pdf-файл.pdf")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # pdf-файл.pdf uploaded successfully.");
    }

    @Test(priority = 7)
    public void addXLSFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\xls-файл.xls")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # xls-файл.xls uploaded successfully.");
    }

    @Test(priority = 8)
    public void addXLSXFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\xlsx-файл.xlsx")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # xlsx-файл.xlsx uploaded successfully.");
    }

    @Test(priority = 9)
    public void addTXTFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\txt-файл.txt")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # txt-файл.txt uploaded successfully.");
    }

    @Test(priority = 10)
    public void addPNGFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\png-файл.png")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # png-файл.png uploaded successfully.");
    }

    @Test(priority = 10)
    public void addCSVFile() {
        steps
                .open(APP_FORM_DEMO_DB)
                .loginAs(USER, EMAIL, PASSWORD, EN)
                .createAppButtonClick()
                .openFilesTab()
                .addFile("testData\\csv-файл.csv")
                .asserts().assertTextInElement(appEditPage.addFileNotification, "File # csv-файл.csv uploaded successfully.");
    }
}
