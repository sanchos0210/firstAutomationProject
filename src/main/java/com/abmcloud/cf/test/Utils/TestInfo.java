package com.abmcloud.cf.test.Utils;

import org.openqa.selenium.WebElement;

public class TestInfo {

    private String testName;

    public TestInfo(String testName) {
        this.testName = testName;
    }

    public UsersData activeUser;
    public WebElement selectedApp;
    public String numberOfSelectedApp;
    public String statusOfSelectedApp;
    public String numberOfCreatedApp;
    public String textOfNotification;

    public WebElement selectedCatalogItem;

    public String getTestName() {
        return testName;
    }

}
