package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.*;
import com.abmcloud.cf.test.PageObject.Components.Notification;

public class BaseSteps {

   public Driver driver;
   public Logs logs;
   public ObjectManager objectManager;

    public Wait getWait() {
       return objectManager.getWait();
   }

    protected LoginSteps getLoginSteps() {
        return objectManager.getLoginSteps();
    }

    protected AppListSteps getAppListSteps() {
        return objectManager.getAppListSteps();
    }

    protected AppFormSteps getAppFormSteps() {
        return objectManager.getAppFormSteps();
    }

    protected CalendarTableSteps getCalendarSteps() {
        return objectManager.getCalendarTableSteps();
    }

    protected CalendarListSteps getCalendarListStep() {
       return objectManager.getCalendarListSteps();
    }

    protected Notification getNotification() {
       return new Notification(driver, getWait());
    }

    protected ConfirmPopupSteps getConfirmPopup() {
        return new ConfirmPopupSteps(driver, logs);
    }

    public Asserts asserts() {
        return objectManager.getAsserts();
    }
}
