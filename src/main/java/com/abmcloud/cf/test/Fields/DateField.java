package com.abmcloud.cf.test.Fields;

import com.abmcloud.cf.test.API.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DateField extends BaseField {

    public DateField(Driver driver) {
        super(driver);
    }

    public WebElement getField(String nameOfField) {
        return driver.$(By.xpath("//*[contains(text(), '"+nameOfField+"')]//following::div"));
    }

    public String getValue(String nameOfField) {
        return getField(nameOfField).getText();
    }

    public WebElement getTCHField(String nameOfField) {
        return driver.$(By.xpath("//*[contains(text(), '"+nameOfField+"')]//following-sibling::span//span"));
    }

    public String getTCHValue(String nameOfField) {
        return getTCHField(nameOfField).getText();
    }

    public WebElement getDateInDatePicker(WebElement datePicker, String date) {
        WebElement soughtDate = null;
        List<WebElement> datesInThisMonth = datePicker.findElements(By.xpath(".//*[@class = 'calendar left single']//table[@class = 'table-condensed']//td[@class = 'available' or @class = 'weekend available']"));
        for(int i = 0; i < datesInThisMonth.size();i++) {
            if(date.equals(datesInThisMonth.get(i).getText())) {
                soughtDate = datesInThisMonth.get(i);
                break;
            }
        }
        return soughtDate;
    }
}
