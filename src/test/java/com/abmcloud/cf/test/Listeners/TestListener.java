package com.abmcloud.cf.test.Listeners;

import com.abmcloud.cf.test.BaseTest;
import com.abmcloud.cf.test.Driver.Logs;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot(ITestResult iTestResult) {
        WebDriver driver = ((BaseTest) iTestResult.getInstance()).objectManager.getDriver().getWebDriver();
        TakesScreenshot scr = ((TakesScreenshot) driver);
        return scr.getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Logs logs = ((BaseTest) iTestResult.getInstance()).objectManager.getLogs();
        logs.infoMsg("TEST " + ((BaseTest) iTestResult.getInstance()).objectManager.getTestInfo().getTestName() + " HAS STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Logs logs = ((BaseTest) iTestResult.getInstance()).objectManager.getLogs();
        logs.infoMsg("TEST SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        makeScreenshot(iTestResult);
        Logs logs = ((BaseTest) iTestResult.getInstance()).objectManager.getLogs();
        logs.infoMsg("TEST FAILURE");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
