package com.abmcloud.cf.test.API;

import com.abmcloud.cf.test.Fields.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helpers {

    Driver driver;
    Wait wait;
    BaseField baseField;

    public Helpers(Driver driver) {
        this.driver = driver;
    }

    private Wait getWait() {
        if(wait == null) {
            wait = new Wait(driver);
        }
        return wait;
    }

    private DecimalField getDecimalField() {
        baseField = new DecimalField(driver);
        return (DecimalField) baseField;
    }

    private BooleanField getBooleanField() {
        baseField = new BooleanField(driver);
        return (BooleanField) baseField;
    }

    private DateField getDateField() {
        baseField = new DateField(driver);
        return (DateField) baseField;
    }

    private CatalogField getCatalogField() {
        baseField = new CatalogField(driver);
        return (CatalogField) baseField;
    }

    private StringField getStringField() {
        baseField = new StringField(driver);
        return (StringField) baseField;
    }

    public boolean isElementPresent(WebElement element) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            getWait().waitForElementClickable(2, element);
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
    }

    public boolean isButtonPresentInRow(WebElement row, By buttonLocator) {
        driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            getWait().waitForElementClickable(2, row.findElement(buttonLocator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.getWebDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
    }

    public boolean compare(String referenseValue, String verificationValue) {
        return referenseValue.equals(verificationValue);
    }

    public boolean compare(String[][] referenseArray, String[][] verificationArray) {
        for(int i = 0; i < verificationArray.length; i++) {
            for(int j = 0; j < 2; j++) {
                String referenceValue = referenseArray[i][j];
                String verificationValue = verificationArray[i][j];
                if(referenceValue == null & verificationValue == null) break;
                Assert.assertTrue(referenceValue.equals(verificationValue));
            }
        }
        return true;
    }

    public boolean compare(String[] referenseArray, String[] verificationArray) {
        for(int i = 0; i < verificationArray.length; i++) {
            String referenceValue = referenseArray[i];
            String verificationValue = verificationArray[i];
            if(referenceValue == null & verificationValue == null) break;
            Assert.assertTrue(referenceValue.equals(verificationValue));
        }
        return true;
    }

    public boolean compare(List<Object> referenceList, List<Object> verificationList) {
        return referenceList.equals(verificationList);
    }

    public String getTodayFullDate() {
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        return getDate(today, "dd.MM.yyyy");
    }

    public List<String> getDaysInMonthFullDates(char requiredMonth) {
        List<String> daysInMonth = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        switch(requiredMonth) {
            case 'A': break;        //for current month
            case 'B': {     //for last month
                month = month - 1;
                break;
            }
        }
        c.set(year, month, 1);
        int currentMonth = c.get(Calendar.MONTH);
        while (c.get(Calendar.MONTH) == currentMonth) {
            Date date = c.getTime();
            String s = df.format(date);
            daysInMonth.add(s);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysInMonth;
    }

    public List<String> getMonthsInYearFullDate(char requiredYear) {
        List<String> months = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("MMMM");
        DateFormat yy = new SimpleDateFormat("yyyy г.");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        switch (requiredYear) {
            case 'A': break;       //for current year
            case 'B': {             //for last year
                year = year - 1;
                break;
            }
        }
        c.set(year, 0, 1);
        int currentYear = c.get(Calendar.YEAR);
        while (c.get(Calendar.YEAR) == currentYear) {
            Date date = c.getTime();
            String s = df.format(date).toLowerCase() + " " + yy.format(date);
            months.add(s);
            c.add(Calendar.MONTH, 1);
        }
        return months;
    }

    public String getTodayDate() {
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        return getDate(today, "dd");
    }

    public String getTomorrowDate() {
        DateFormat df = new SimpleDateFormat("dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = c.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    public String getDate(Date date, String format) {
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat(format);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String soughtDate = df.format(date);
        return soughtDate;
    }

    public String getStepName(int stepNum) {
        WebElement step = driver.$(By.cssSelector("step-view-approved .list-group > li:nth-of-type("+stepNum+") > ul > li div"));
        return step.getText();
    }

    public String getParallelStepsNames(int stepNum) {
        List<WebElement> parallelSteps = driver.$$(By.cssSelector("step-view-approved .list-group > li:nth-of-type("+stepNum+") >ul > li"));
        List<String> stepsNames = new ArrayList<String>();
        for(int i = 0; i < parallelSteps.size(); i++) {
            WebElement step = parallelSteps.get(i);
            stepsNames.add(i, step.findElement(By.cssSelector("div")).getText());
        }
        String result = StringUtils.join(stepsNames, ", ");
        return result;
    }

    public String[][] getChainSteps() {
        List<WebElement> chainSteps = driver.$$(By.cssSelector("step-view-approved .list-group > li"));
        String[][] chainStepsList = new String[chainSteps.size()][2];
        for(int i = 0; i < chainSteps.size(); i++) {
            WebElement step = chainSteps.get(i);
            List<WebElement> parallelStepsList = step.findElements(By.cssSelector("ul.parallel_steps li .list-group-item.steps_in_appl"));
            for(int j = 0; j < parallelStepsList.size(); j++) {
                WebElement nameOfStep = parallelStepsList.get(j).findElement(By.cssSelector("div"));
                if(nameOfStep.getText() != null) chainStepsList[i][j] = nameOfStep.getText();
            }
        }
        return chainStepsList;
    }

    public List<Object> getChainSteps2() {
        List<Object> chainStepsList = new ArrayList<>();
        List<WebElement> chainSteps = driver.$$(By.cssSelector("step-view-approved .list-group > li"));
        for(int i = 0; i < chainSteps.size(); i++) {
            List<Object> approversList = new ArrayList<>();
            WebElement step = chainSteps.get(i);
            List<WebElement> parallelStepsList = step.findElements(By.cssSelector("ul.parallel_steps li .list-group-item.steps_in_appl"));
            for(int j = 0; j < parallelStepsList.size(); j++) {
                WebElement nameOfStep = parallelStepsList.get(j).findElement(By.cssSelector("div"));
                if(nameOfStep.getText() != null) {
                    approversList.add(nameOfStep.getText());
                }
            }
            chainStepsList.add(approversList);
        }
        return chainStepsList;
    }

    public String getApprovers(int stepNum) {
        List<WebElement> approvers = driver.$$(By.cssSelector("step-view-approved .list-group > li:nth-of-type("+stepNum+") ul.parallel_steps li .list-group-item.steps_in_appl ul li"));
        List<String> approversName = new ArrayList<String>();
        for(int i = 0; i < approvers.size(); i++) {
            approversName.add(i, approvers.get(i).findElement(By.cssSelector("img + span")).getText());
        }
        String result = StringUtils.join(approversName, ", ");
        return result;
    }

    public String getTomorrowFullDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = c.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    public String getYesterdayFullDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date tomorrow = c.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    public String getValueOfDecimalField(String name) {
        return getDecimalField().getValue(name);
    }

    public String getValueOfDecimalFieldTCH(String name) {
        return getDecimalField().getTCHValue(name);
    }

    public String getValueOfDecimalFieldTCH(String name, int rowNum) {
        return getDecimalField().getTCHValue(name, rowNum);
    }

    public boolean isDecimalFieldDisabled(String name) {
        return getDecimalField().isDisabled(name);
    }

    public boolean isDecimalFieldDisabledTCH(String name) {
        return getDecimalField().isDisabledTCH(name);
    }

    public String getValueOfBooleanField(String name) {
        return getBooleanField().getValue(name);
    }

    public String getValueOfBooleanFieldTCH(String name) {
        return getBooleanField().getValueTCH(name);
    }

    public String getInOutValue() {
        return getBooleanField().getInOutValue();
    }

    public boolean isBooleanFieldDisable(String name) {
        return getBooleanField().isDisable(name);
    }

    public boolean isInOutDisable() {
        return getBooleanField().isInOutDisable();
    }

    public String getValueOfDateField(String name) {
        return getDateField().getValue(name);
    }

    public String getValueOfDateFieldTCH(String name) {
        return getDateField().getTCHValue(name);
    }

    public WebElement getDateInDatePicker(WebElement datePicker, String date) {
        return getDateField().getDateInDatePicker(datePicker, date);
    }

    public String getValueOfCatalogField(String name) {
        return getCatalogField().getValue(name);
    }

    public WebElement getCatalogItem(String name) {
        return getCatalogField().getItem(name);
    }

    public String getValueOfStringField(String name) {
        return getStringField().getValue(name);
    }

    public String getValueOfStringFieldTCH(String name) {
        return getStringField().getValueTCH(name);
    }

    public boolean isStringFieldDisabled(String name) {
        return getStringField().isDisabled(name);
    }

    public boolean isStringFieldDisabledTCH(String name) {
        return getStringField().isDisabledTCH(name);
    }

    public boolean isButtonDisable(WebElement button) {
        try {
            driver.assertThat(ExpectedConditions.attributeContains(button, "disabled", "true"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
