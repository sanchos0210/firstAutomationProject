package proline.ProlineTests;

import com.abmcloud.cf.test.DataInfo.EditAppDataProline;
import com.abmcloud.cf.test.DataInfo.UsersData;
import com.abmcloud.cf.test.API.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ViewAppPopup extends BaseTest {

    EditAppDataProline editAppDataProline;

    @BeforeMethod
    public void objectCreation() {
        editAppDataProline = new EditAppDataProline();
    }

    @Test(priority = 1)
    public void checkVisibilityInViewPopup() {
        steps
                .open(TEST_PROLINE)
                .loginAs(new UsersData(USER, EMAIL, PASSWORD, EN))
                .createApp(editAppDataProline)
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .editButtonClick(selectedApp)
                .edit(editAppDataProline.amountField, "500")
                .saveApplication()
                .selectAppByNumber(numberOfCreatedApp)
                .actionMenuButtonClick(selectedApp)
                .sendForApprovalButtonClick(selectedApp)
                .selectAppByNumber(BaseTest.numberOfSelectedApp)
                .clickOnNumberOf(selectedApp)
                .backButtonClick()      //Check that button is working
                .clickOnNumberOf(selectedApp)
                .showInformationBlockClick()
                .asserts().assertTrue(isElementPresent(appEditPage.changesHistory))
                .assertTrue(isElementPresent(appEditPage.viewsHistory))
                .assertTrue(isElementPresent(appEditPage.approveAppButton))
                .assertFalse(isElementPresent(appEditPage.saveButton))
                .assertTrue(isElementPresent(appEditPage.cancelAppButton));
    }
}
