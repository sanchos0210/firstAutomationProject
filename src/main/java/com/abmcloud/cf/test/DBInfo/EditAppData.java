package com.abmcloud.cf.test.DBInfo;

import com.abmcloud.cf.test.API.API;
import com.abmcloud.cf.test.Fields.CatalogField;
import com.abmcloud.cf.test.Fields.DecimalField;
import com.abmcloud.cf.test.Fields.StringField;
import com.abmcloud.cf.test.steps.AppFormSteps;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class EditAppData extends API {

    public static final char DECIMAL_FIELD = 'A';
    public static final char STRING_FIELD = 'B';
    public static final char BOOLEAN_FIELD = 'C';
    public static final char CATALOG_FIELD = 'D';
    public static final char DATA_FIELD = 'F';

    protected WebDriver driver;

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    public abstract void createApp();

    public void fillTheFields(List<AppField> fieldsToFilling) {
        for (AppField i: fieldsToFilling) {
            switch(i.type) {
                case 'A': {
                    DecimalField decimalField = new DecimalField();
                    AppFormSteps appFormSteps = new AppFormSteps();
                    appFormSteps.edit(decimalField.getField(i.name), i.value);
                    break;
                }
                case 'B': {
                    StringField stringField = new StringField();
                    AppFormSteps appFormSteps = new AppFormSteps();
                    appFormSteps.edit(stringField.getField(i.name), i.value);
                    break;
                }
                case 'C': {     //for boolean fields
                    break;
                }
                case 'D': {
                    CatalogField catalogField = new CatalogField();
                    AppFormSteps appFormSteps = new AppFormSteps();
                    appFormSteps.catalogElementClick(catalogField.getField(i.name))
                                .catalogElementClick(catalogField.getItem(i.value));
                    break;
                }
            }
        }
    }

    /*public void catalogElementClick(WebElement element) {
        waitForElementClickable(10, element);
        element.click();
    }*/
}
