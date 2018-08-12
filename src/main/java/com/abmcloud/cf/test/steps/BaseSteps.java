package com.abmcloud.cf.test.steps;

import com.abmcloud.cf.test.API.*;

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

    protected CalendarSteps getCalendarSteps() {
        return objectManager.getCalendarSteps();
    }

    protected CalendarListSteps getCalendarListStep() {
       return objectManager.getCalendarListSteps();
    }

    protected Notification getNotification() {
       return new Notification(driver, getWait());
    }

    protected ConfirmPopup getConfirmPopup() {
        return new ConfirmPopup(driver, logs);
    }

    public Asserts asserts() {
        return objectManager.getAsserts();
    }
}
