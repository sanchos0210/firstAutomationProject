package com.abmcloud.cf.test.PageObject.steps;

import com.abmcloud.cf.test.Driver.*;

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

    public Asserts asserts() {
        return objectManager.getAsserts();
    }
}
